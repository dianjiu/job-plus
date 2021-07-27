package cn.org.dianjiu.job.config.cros;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author DianJiu
 * @website https://dianjiu.co
 * @email dianjiu@dianjiu.cc
 * @date 2021/7/27 16:20
 * @desc TODO
 */
@SpringBootConfiguration
public class WebGlobalConfig {

    @Bean
    public CorsFilter corsFilter() {

        //创建CorsConfiguration对象后添加配置
        CorsConfiguration config = new CorsConfiguration();
        //设置放行哪些原始域
        config.addAllowedOrigin("*");
        //放行哪些原始请求头部信息
        config.addAllowedHeader("*");
        //暴露哪些头部信息
        config.addExposedHeader(HttpHeaders.ACCEPT);
        //放行哪些请求方式
        //config.addAllowedMethod("GET");     //get
        //config.addAllowedMethod("PUT");     //put
        //config.addAllowedMethod("POST");    //post
        //config.addAllowedMethod("DELETE");  //delete
        config.addAllowedMethod("*");     //放行全部请求

        //是否发送Cookie
        config.setAllowCredentials(true);

        //2. 添加映射路径
        UrlBasedCorsConfigurationSource corsConfigurationSource =
                new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        //返回CorsFilter
        return new CorsFilter(corsConfigurationSource);
    }
}
