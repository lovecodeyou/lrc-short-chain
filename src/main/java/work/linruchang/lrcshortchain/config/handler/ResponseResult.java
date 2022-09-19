package work.linruchang.lrcshortchain.config.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应体数据
 *
 * @author LinRuChang
 * @version 1.0
 * @date 2020/09/23
 * @since 1.8
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -8763051012733533025L;


    /**
     * 用户需要的结果
     */
    T result;

    /**
     * 响应码
     */
    String code;

    /**
     * 提示信息
     */
    String message;

    /**
     * 其他信息 - 异常信息等
     */
    String otherMessage;

    /**
     * 是否是用户需要的响应结果
     */
    Boolean success;

    public static <T> ResponseResult<T> getInstance(boolean successFlag) {
        return successFlag?success():fail();
    }
    public static <T> ResponseResult<T> getInstance(boolean successFlag,T result) {
        return successFlag?success(result):fail(result);
    }
    public static <T> ResponseResult<T> getInstance(boolean successFlag,T result, String messge) {
        return successFlag?success(result,messge):fail(result,messge);
    }


    //请求处理成功 - 即结果为用户需要的数据 - 200
    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static <T> ResponseResult<T> success(T result) {
        RequestEnum.ResponseCodeEnum success = RequestEnum.ResponseCodeEnum.SUCCESS;
        return (ResponseResult<T>) ResponseResult.builder().result(result).message(success.getDescription()).code(success.getCode()).success(true).build();
    }

    public static <T> ResponseResult<T> success(T result, String messge) {
        return (ResponseResult<T>) ResponseResult.builder().result(result).message(messge).code(RequestEnum.ResponseCodeEnum.SUCCESS.getCode()).success(true).build();
    }


    //请求处理失败 - 后台处理出错 - 500
    public static <T> ResponseResult<T> fail() {
        return fail(null);
    }

    public static <T> ResponseResult<T> fail(T result) {
        RequestEnum.ResponseCodeEnum fail = RequestEnum.ResponseCodeEnum.FAIL;
        return (ResponseResult<T>) ResponseResult.builder().result(result).message(fail.getDescription()).code(fail.getCode()).success(false).build();
    }

    public static <T> ResponseResult<T> fail(T result, String messge) {
        return (ResponseResult<T>) ResponseResult.builder().result(result).message(messge).code(RequestEnum.ResponseCodeEnum.FAIL.getCode()).success(false).build();
    }

    public static <T> ResponseResult<T> fail(T result, String messge, String otherMessage) {
        return (ResponseResult<T>) ResponseResult.builder().result(result).message(messge).otherMessage(otherMessage).code(RequestEnum.ResponseCodeEnum.FAIL.getCode()).success(false).build();
    }


    //【没有权限、登陆失败这些】- -1000
    public static <T> ResponseResult<T> unauthorized() {
        return unauthorized(null);
    }

    public static <T> ResponseResult<T> unauthorized(T result) {
        RequestEnum.ResponseCodeEnum unauthorized = RequestEnum.ResponseCodeEnum.UN_AUTHORIZED;
        return (ResponseResult<T>) ResponseResult.builder().result(result).message(unauthorized.getDescription()).code(unauthorized.getCode()).success(false).build();
    }

    public static <T> ResponseResult<T> unauthorized(T result, String messge) {
        return (ResponseResult<T>) ResponseResult.builder().result(result).message(messge).code(RequestEnum.ResponseCodeEnum.UN_AUTHORIZED.getCode()).success(false).build();
    }
}
