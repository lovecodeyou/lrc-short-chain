package work.linruchang.lrcshortchain.config.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.MediaType;

/**
 * 请求体内容、请求方式、响应码枚举
 *
 * @author LinRuChang
 * @version 1.0
 * @date 2020/09/21
 * @since 1.8
 **/
public class RequestEnum {

    /**
     * 请求、响应体内容类型
     * 可直接使用Spring内置的{@link MediaType}
     */
    @AllArgsConstructor
    @Getter
    public enum RequestContentTypeEnum {
        /***/
        MULTIPART("multipart/form-data", "上传文件"),
        FORM_URLENCODED("application/x-www-form-urlencoded;charset=utf-8", "post表单默认上传内容类型"),
        JSON("application/json", "json字符串"),
        XML("text/xml", "xml字符串"),
        RAW("raw", "任意格式的字符串");

        String code;
        String description;
    }

    /**
     * Http请求方式
     */
    @AllArgsConstructor
    @Getter
    public enum RequestMethodEnum {

        POST("添加数据"),
        DELETE("删除数据"),
        PUT("修改数据"),
        GET("获取数据"),
        OPTION("检测服务器所支持的请求方法");
        String description;
    }

    /**
     * 响应码枚举
     */
    @AllArgsConstructor
    @Getter
    public enum ResponseCodeEnum {

        SUCCESS("200", "处理成功"),
        FAIL("500", "服务器处理请求出错"),
        UN_AUTHORIZED("-1000", "未登录或者授权码过期,请重新登录"),


        MISS_CONFIG("-2000", "系统缺失配置"),
        REQUEST_MISS_CONFIG("-2001", "请求缺失配置"),
        INVALID_CLIENT("-2002", "非法的第三方客户端"),
        INCONSISTENT_PARAMETERS("-2003", "用户定义的回调地址与传过来的不一致"),
        INVALID_PARAMETERS("-2004", "非法参数"),
        INVALID_RESPONSE_TYPE("-2005", "不支持的授权类型"),
        MISSING_AUTHORIZATION_RIGHTS("-2006", "缺失授权权限"),
        INVALID_SCOPE("-2007", "含有不支持的授权权限"),

        INVALID_AUTH_CODE("-2008", "非法授权码"),

        INVALID_GRANT_TYPE("-2009", "非法授予类型"),
        UNSUPPORT_GRANT_TYPE("-2010", "不支持的授予类型"),

        INVALID_REFRESH_TOKEN("-2011", "非法的refresh_token"),
        EXPIRED_REFRESH_TOKEN("-2012", "refresh_token已过期"),

        USERNAME_OR_PASSWORD_ERROR("-2013", "账户或密码错误"),



        TASK_EXECUTE_ERROR("-3000", "任务执行异常");

        /**响应码**/
        String code;
        String description;
    }

}
