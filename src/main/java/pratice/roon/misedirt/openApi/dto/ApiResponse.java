package pratice.roon.misedirt.openApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

//TODO : DTO를 이렇게 복잡하게 만들기도 하나
//TODO : JsonIgnoreProperties 남발하는 거 좀 안 좋은듯
@Data
public class ApiResponse {
    private Response response;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Response{
        private Body body;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @Data
        public static class Body{
            private String resultCode;
            private String totalCount;
            private String pageNo;
            private List<Items.Item> items;

            @Data
            public static class Items {

                @JsonIgnoreProperties(ignoreUnknown = true)
                @Data
                public static class Item{
                    private String stationName;
                    private String dataTime;
                    private String coValue;
                    private String pm10Value;
                    private String pm25Value;
                }
            }
        }
    }

}
