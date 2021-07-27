package cn.org.dianjiu.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("cfg.env","local");
        SpringApplication.run(Application.class, args);
    }

}
