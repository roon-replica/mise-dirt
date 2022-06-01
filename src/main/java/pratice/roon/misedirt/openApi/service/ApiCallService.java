package pratice.roon.misedirt.openApi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pratice.roon.misedirt.openApi.config.LocalCacheConfig;
import pratice.roon.misedirt.openApi.dto.ApiResponse;
import pratice.roon.misedirt.openApi.dto.ApiRequest;


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

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String pageSize = "50";
    private String version = "1.0";

    @Cacheable(cacheNames = LocalCacheConfig.openApiCacheManagerName)
    public ApiResponse.Response.Body measureByCity(String sidoName, String pageNo) {
        String BASE_URL = END_POINT + CITY_MEASURE_URL;

        String params = ApiRequest.CityMeasure.measureByCityRequestParam(DECODED_AUTH_KEY, "json", pageNo, pageSize, sidoName, version);
        HttpEntity<String> httpRequestEntity = ApiRequest.CityMeasure.jsonRequestHttpEntity(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.exchange(BASE_URL + params, HttpMethod.GET, httpRequestEntity, String.class);

        try {
            ApiResponse cityMeasure = objectMapper.readValue(response.getBody(), ApiResponse.class);
            return cityMeasure.getResponse().getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static final int ONE_MINUTE = 60 * 1000;

    @Scheduled(fixedRate = ONE_MINUTE)
    @CacheEvict(cacheNames = LocalCacheConfig.openApiCacheManagerName)
    public void evictCache() {
        log.info("[Local Cache] evicted cacheEntry");
    }


}

