package pratice.roon.misedirt.openApi.dto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;

public class ApiRequest {

    public static class CityMeasure {
        private String serviceKey;
        private String returnType;
        private String numOfRows;
        private String pageNo;
        private String sidoName;
        private String ver;

        public static String sampleMeasureByCityRequestParam(String serviceKey) {
            String[] params = new String[]{
                    "serviceKey=" + serviceKey,
                    "returnType=json",
                    "numOfRows=50",
                    "pageNo=1",
                    "sidoName=서울",
                    "ver=1.0"
            };

            return "?" + String.join("&", params);
        }

        public static String measureByCityRequestParam(String serviceKey, String returnType, String pageNo, String pageSize, String sidoName, String ver) {
            String[] params = new String[]{
                    "serviceKey=" + serviceKey,
                    "returnType=" + returnType,
                    "numOfRows=" + pageSize,
                    "pageNo=" + pageNo,
                    "sidoName=" + sidoName,
                    "ver=" + ver
            };

            return "?" + String.join("&", params);
        }

        public static HttpEntity<String> jsonRequestHttpEntity(MediaType mediaType) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(mediaType);
            httpHeaders.setAccept(Arrays.asList(mediaType));

            return new HttpEntity<>(httpHeaders);
        }

    }


}
