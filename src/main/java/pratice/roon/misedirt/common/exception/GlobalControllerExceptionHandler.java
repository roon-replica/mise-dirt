package pratice.roon.misedirt.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    // TODO : api 호출은 뭐 예외 발생해도.. 데이터 안 주면 그만이니 예외 처리 안해도 큰 문제는 없을듯. view를 보여주는 컨트롤러의 경우에는 예외처리 해야하므로 나중에 view 만들면 작성 필요
    // TODO: 다시 생각해보니 API도 예외 처리해줘야 할듯. 원인 파악을 위해, 처리안해주면 에러 시 응답도 html 내려가게 됨

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView mav =  new ModelAndView("error");
        mav.addObject("errorMessage",e.getMessage());

        return mav;
    }
}
