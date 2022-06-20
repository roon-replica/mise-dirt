package pratice.roon.misedirt.openApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import pratice.roon.misedirt.openApi.service.PmColor;

import java.util.List;

//TODO : DTO를 이렇게 복잡하게 만들기도 하나
//TODO : JsonIgnoreProperties 남발하는 거 좀 안 좋은듯
@Data
public class ApiResponse {
    private Response response;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Response {
        private Body body;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @Data
        public static class Body {
            private String resultCode;
            private String totalCount;
            private String pageNo;
            private List<Items.Item> items;

            @Data
            public static class Items {

                @JsonIgnoreProperties(ignoreUnknown = true)
                @Data
                public static class Item {
                    private String stationName;
                    private String dataTime;
                    private String coValue;
                    private String pm10Value;
                    private String pm25Value;
                    private String pm10Color;
                    private String pm25Color;

                    public void setPm10Color(int pm10) {
                        if (pm10 == -1) { //
                            this.pm10Color = PmColor.UNKNOWN.getValue();
                            ;
                        } else if (pm10 < 15) {
                            this.pm10Color = PmColor.BLUE.getValue();
                        } else if (pm10 < 35) {
                            this.pm10Color = PmColor.GREEN.getValue();
                        } else if (pm10 < 75) {
                            this.pm10Color = PmColor.YELLOW.getValue();
                        } else {
                            this.pm10Color = PmColor.RED.getValue();
                        }
                    }

                    public void setPm25Color(int pm25) {
                        if (pm25 == -1) {
                            this.pm25Color = PmColor.UNKNOWN.getValue();
                        } else if (pm25 < 15) {
                            this.pm25Color = PmColor.BLUE.getValue();
                        } else if (pm25 < 35) {
                            this.pm25Color = PmColor.GREEN.getValue();
                        } else if (pm25 < 75) {
                            this.pm25Color = PmColor.YELLOW.getValue();
                        } else {
                            this.pm25Color = PmColor.RED.getValue();
                        }
                    }
                }
            }
        }
    }

}
