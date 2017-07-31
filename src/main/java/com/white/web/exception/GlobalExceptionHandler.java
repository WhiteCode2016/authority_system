package com.white.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常管理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // 默认的错误视图
    private static final String DEFAULT_ERROR_VIEW = "error";

    // 拦截所有的异常
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
       /* ErrorMessage<Exception> error = new ErrorMessage<Exception>();
        error.setCode(400);
        error.setMessage("系统异常");
        error.setUrl(request.getRequestURL().toString());
        error.setData(e);
        modelAndView.addObject(error);*/
        modelAndView.addObject("errorMsg", e.getMessage());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        logger.info("GlobalExceptionHandler catch an Exception", e);
        return modelAndView;
    }

}
