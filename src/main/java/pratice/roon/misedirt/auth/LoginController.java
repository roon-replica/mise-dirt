package pratice.roon.misedirt.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pratice.roon.misedirt.auth.dto.AuthDTO;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // 스프링 시큐리티가 폼 로그인 과정을 처리해서 로그인 처리 로직을 직접 작성하면 안됨
//    @PostMapping("/login")
//    public String processLogin(@RequestBody AuthDTO authDTO){
//        System.out.println(authDTO);
//        return "/mise/main";
//    }

}
