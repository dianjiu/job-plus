package cn.org.dianjiu.task.config.security;

import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: task-manage
 * @Package: cn.org.dianjiu.task.config.security
 * @ClassName: RestAuthenticationEntryPoint
 * @Author: MengWei
 * @Description: 当未登录或者token失效访问接口时，自定义的返回结果
 * @Date: 2020/8/3 21:23
 * @Version: 1.0
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //TODO 改造成抛出自定义异常
        //response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}
