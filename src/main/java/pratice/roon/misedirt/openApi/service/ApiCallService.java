package pratice.roon.misedirt.openApi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pratice.roon.misedirt.openApi.config.LocalCacheConfig;
import pratice.roon.misedirt.openApi.dto.ApiResponse;
import pratice.roon.misedirt.openApi.dto.ApiRequest;
import pratice.roon.misedirt.openApi.entity.Dirt;
import pratice.roon.misedirt.openApi.entity.Region;
import pratice.roon.misedirt.openApi.entity.repository.DirtRepository;
import pratice.roon.misedirt.openApi.entity.repository.RegionRepository;

import javax.transaction.Transactional;
import java.util.List;


@Slf4j
@Service
public class ApiCallService {
    @Value("${openapi.mise.auth-key.decoded}")
    private String DECODED_AUTH_KEY;

    @Value("${openapi.mise.endpoint}")
    private String END_POINT;

    @Value("${openapi.mise.uri.measureByCity}")
    private String CITY_MEASURE_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DirtRepository dirtRepository;

    @Autowired
    private RegionRepository regionRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String pageSize = "50";
    private String version = "1.0";

    @Autowired
    private CacheManager cacheManager;

    //TODO : 왜 @LogExecutionTime 붙이면 API가 동작을 안하지.. -> @LogExecution AOP가 proceed하면 @Cacheable의 AOP는 무시되나 봄..
    //@LogExecutionTime
    @Cacheable(cacheNames = LocalCacheConfig.openApiCacheManagerName)
    public ApiResponse.Response.Body measureByCity(String sidoName, String pageNo) {
        String BASE_URL = END_POINT + CITY_MEASURE_URL;

        String params = ApiRequest.CityMeasure.measureByCityRequestParam(DECODED_AUTH_KEY, "json", pageNo, pageSize, sidoName, version);
        HttpEntity<String> httpRequestEntity = ApiRequest.CityMeasure.jsonRequestHttpEntity(MediaType.APPLICATION_JSON);

        // httpRequestEntity.getHeaders().set("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTc1NDIyNTUsImV4cCI6MTY1NzU0NTg1NSwic3ViIjoidXNlcjEifQ.lWafMhXTDYt9_0Wg_5RmYD4b7GC6bpvfUpaJdMjbqMY");
//        httpRequestEntity.getHeaders().setBearerAuth(SecurityContextHolder.getContext().getAuthentication().);
        ResponseEntity<String> response = restTemplate.exchange(BASE_URL + params, HttpMethod.GET, httpRequestEntity, String.class);

        try {
            ApiResponse cityMeasure = objectMapper.readValue(response.getBody(), ApiResponse.class);
            setMiseDirtColors(cityMeasure.getResponse().getBody().getItems());

            saveDirtByRegion(cityMeasure.getResponse().getBody().getItems());

            return cityMeasure.getResponse().getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Transactional
    protected void saveDirtByRegion(List<ApiResponse.Response.Body.Items.Item> items) {
        for (ApiResponse.Response.Body.Items.Item item : items) {
            Region region = Region.builder()
                    .name(item.getStationName())
                    .build();

            regionRepository.save(region);

            String pm10 = item.getPm10Value();
            String pm25 = item.getPm25Value();

            // TODO : -1로 설정하는건 별로인듯.. pm 값 저장할 때 문자열로 하는게 나은가?
            if(pm10.equals("-")) pm10 = "-1";
            if(pm25.equals("-")) pm25 = "-1";

            Dirt dirt = Dirt.builder()
                    .pm10(Integer.parseInt(pm10))
                    .pm25(Integer.parseInt(pm25))
                    .region(region)
                    .build();

            dirtRepository.save(dirt);
        }
    }


    private void setMiseDirtColors(List<ApiResponse.Response.Body.Items.Item> items) {
        for (ApiResponse.Response.Body.Items.Item item : items) {
            if (item.getPm10Value().equals("-")) {
                item.setPm10Color(-1);
            } else {
                item.setPm10Color(Integer.parseInt(item.getPm10Value()));
            }

            if (item.getPm25Value().equals("-")) {
                item.setPm25Color(-1);
            } else {
                item.setPm25Color(Integer.parseInt(item.getPm25Value()));
            }

        }
    }


    private static final int TEN_MINUTE = 10 * 60 * 1000;

    @Scheduled(fixedRate = TEN_MINUTE)
    @CacheEvict(cacheNames = LocalCacheConfig.openApiCacheManagerName)
    public void evictCache() {
        log.info("[Local Cache] evicted cacheEntry");
    }

}

