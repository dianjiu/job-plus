package cn.org.dianjiu.task.config.security;

import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: task-manage
 * @Package: cn.org.dianjiu.task.config.security
 * @ClassName: RestfulAccessDeniedHandler
 * @Author: MengWei
 * @Description: 当访问接口没有权限时，自定义的返回结果
 * @Date: 2020/8/3 21:23
 * @Version: 1.0
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //TODO 改造成抛出自定义异常
        //response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}
