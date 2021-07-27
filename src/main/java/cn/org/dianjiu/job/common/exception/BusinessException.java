package cn.org.dianjiu.job.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private String code;
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}

