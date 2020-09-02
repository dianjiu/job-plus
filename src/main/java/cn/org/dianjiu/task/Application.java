package cn.org.dianjiu.task;

import cn.org.dianjiu.task.common.exception.BusinessException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("cfg.env","test");
        SpringApplication.run(Application.class, args);
    }

}
