package cn.org.dianjiu.job.common.exception;

import cn.org.dianjiu.job.common.vo.RespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public RespVO BusinessExceptionHandler(HttpServletRequest request, BusinessException e) {
        log.error("Exception happened: ", e);
        RespVO<Object> respVO = new RespVO<>();
        respVO.setCode(e.getCode());
        respVO.setMsg(e.getMessage());
        return respVO;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public RespVO ExceptionHandler(HttpServletRequest request,Exception e) {
        log.error("Exception happened: ", e);
        RespVO<Object> respVO = new RespVO<>();
        respVO.setCode("500");
        respVO.setMsg("系统异常！");
        return respVO;
    }
}

