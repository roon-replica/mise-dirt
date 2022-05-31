package pratice.roon.misedirt.openApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pratice.roon.misedirt.openApi.dto.Response;

import java.io.IOException;
import java.util.Arrays;

@RestController
public class ApiCaller {
    @Value("${openapi.mise.auth-key.encoding}")
    private String AUTH_ENCODE_KEY;

    @Value("${openapi.mise.auth-key.decoding}")
    private String AUTH_DECODE_KEY;

    @Value("${openapi.mise.endpoint}")
    private String END_POINT;

    @Value("${openapi.mise.uri.forcast}")
    private String FORCAST_URL;

    @Value("${openapi.mise.uri.measureByCity}")
    private String CITY_MEASURE_URL;

    @Autowired
    private RestTemplate restTemplate;

    private static final ObjectMapper objMapper = new ObjectMapper();

    @GetMapping("/openapi/measure")
    public String measure() {
        String BASE_URL = END_POINT + CITY_MEASURE_URL;

        StringBuilder params = new StringBuilder();
        params.append("?");
        params.append("serviceKey=" + AUTH_DECODE_KEY);
        params.append("&");

        params.append("returnType=json");
        params.append("&");

        params.append("numOfRows=20");
        params.append("&");

        params.append("pageNo=1");
        params.append("&");

        params.append("sidoName=서울");
        params.append("&");

        params.append("ver=1.0");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Response.City> response = restTemplate.exchange(BASE_URL + params, HttpMethod.GET, httpEntity, Response.City.class);
        System.out.println(response.getBody());

        return response.getBody().toString();
    }
}
