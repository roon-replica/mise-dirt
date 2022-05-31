package pratice.roon.misedirt.openApi.dto;

import lombok.Data;

import java.util.List;

public class Response {

    @Data
    public static class City{
        private String resultCode;
        private String totalCount;
        private String pageNo;

        private List<Item> items;

        @Data
        public static class Item{
            private String stationName;
            private String mangName;
            private String dataTime;
            private String coValue;
            private String pm10Value;
            private String pm25Value;
        }
    }



}
