package pratice.roon.misedirt.openApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pratice.roon.misedirt.openApi.dto.ApiResponse;
import pratice.roon.misedirt.openApi.service.ApiCallService;

import java.util.concurrent.TimeUnit;

@RestController
public class ApiCallController {
    @Autowired
    private ApiCallService apiCallService;

    @GetMapping("/openapi/measure/{sidoName}/{pageNo}")
    public ResponseEntity<ApiResponse.Response.Body> measure(@PathVariable String sidoName, @PathVariable String pageNo) {
        return ResponseEntity.ok()
                .cacheControl(
                        CacheControl.maxAge(60, TimeUnit.MINUTES)
                )
                .body(apiCallService.measureByCity(sidoName, pageNo));
    }
}
