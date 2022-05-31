package pratice.roon.misedirt.openApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pratice.roon.misedirt.openApi.dto.ApiResponse;
import pratice.roon.misedirt.openApi.service.ApiCallService;

@RestController
public class ApiCallerController {
    @Autowired
    private ApiCallService apiCallService;

    @GetMapping("/openapi/measure/{sidoName}/{pageNo}")
    public ApiResponse.Response.Body measure(@PathVariable String sidoName, @PathVariable String pageNo) {
        return apiCallService.measureByCity(sidoName,pageNo);
    }
}
