package work.linruchang.lrcshortchain.config.handler;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义系统运行时异常
 *
 * @author LinRuChang
 * @version 1.0
 * @date 2021/12/16
 * @since 1.8
 **/
public class SysetmBaseCustomException extends RuntimeException{
    private static final long serialVersionUID = -5877017873914862433L;

    /**
     * 响应码
     */
    @Getter
    @Setter
    String code;

    /**
     * 响应码
     */
    @Getter
    @Setter
    RequestEnum.ResponseCodeEnum responseCodeEnum;

    public SysetmBaseCustomException() {
    }
    public SysetmBaseCustomException(RequestEnum.ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum.getDescription());
        this.responseCodeEnum = responseCodeEnum;
        this.code = responseCodeEnum.getCode();
    }

    public SysetmBaseCustomException(String message) {
        super(message);
    }

    public SysetmBaseCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysetmBaseCustomException(Throwable cause) {
        super(cause);
    }

    public SysetmBaseCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
