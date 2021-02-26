package bbayramov2020a5.bbayramov2020a5.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {



    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleBadRequest(Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/errors/error_page");
        mv.addObject("exception", ex);
        return mv;
    }

}
