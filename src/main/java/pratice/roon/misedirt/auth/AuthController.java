package pratice.roon.misedirt.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SpringWebConstraintValidatorFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import pratice.roon.misedirt.auth.dto.AuthDTO;
import pratice.roon.misedirt.auth.dto.SignUpDTO;
import pratice.roon.misedirt.auth.service.MemberService;

@Controller
public class AuthController {
    @Autowired
    private MemberService memberService;

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

    @GetMapping("/enroll")
    public String enroll(){ return "enroll";}

    @PostMapping("/enroll")
    public View enroll(SignUpDTO signUpDTO){
        if(signUpDTO.isPasswordMatched() == false){
            throw new IllegalArgumentException("password != matching password");
        }

        memberService.enrollUser(signUpDTO.getUsername(), signUpDTO.getPassword());

        return new RedirectView("/");
    }

    @GetMapping("/username")
    public String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
