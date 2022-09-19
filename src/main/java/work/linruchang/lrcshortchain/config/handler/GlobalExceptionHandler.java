package work.linruchang.lrcshortchain.config.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author LinRuChang
 * @version 1.0
 * @date 2020/09/23
 * @since 1.8
 **/

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler()
    @ResponseBody
    public ResponseResult HandlerException(Exception exception, HandlerMethod handlerMethod, HttpServletRequest currentRequest) {

        //把出错详情信息写入日志文件中
        log.error("全局异常信息处理【{}】：【{}】", currentRequest.getRequestURL(), ExceptionUtil.stacktraceToOneLineString(exception));

        //本系统自定义异常
        if (exception instanceof SysetmBaseCustomException) {
            SysetmBaseCustomException sbce = (SysetmBaseCustomException) exception;
            return ResponseResult.builder()
                    .code(sbce.getCode())
                    .message(StrUtil.blankToDefault(sbce.getResponseCodeEnum().getDescription(), sbce.getMessage()))
                    .success(false)
                    .result("失败")
                    .build();
        } else { //非本系统定义异常
            return ResponseResult.fail(null, "后台处理发生错误，请联系后台管理员");
        }

    }

}
