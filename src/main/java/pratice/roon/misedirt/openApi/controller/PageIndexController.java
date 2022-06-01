package pratice.roon.misedirt.openApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pratice.roon.misedirt.openApi.service.ApiCallService;

@RequestMapping("/mise")
@Controller
public class PageIndexController {
    @Autowired
    private ApiCallService apiCallService;

    @GetMapping("/main")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("/mise/main");
        modelAndView.addObject("initialCityMeasure",apiCallService.measureByCity("서울","1"));

        return modelAndView;
    }
}
