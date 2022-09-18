package work.linruchang.lrcshortchain.util;

import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 增强的Lambda工具类
 *
 * @author LinRuChang
 * @version 1.0
 * @date 2022/09/18
 * @since 1.8
 **/
public class EnhanceLambdaUtil extends cn.hutool.core.lang.func.LambdaUtil {

    /**
     * 获取lambda实现的方法
     * @param paramName
     * @return
     * @param <T>
     * @param <M>
     */
    public static <T,M> Method getMethod(Func1<T, M> paramName) {
        Class<T> realClass = getRealClass(paramName);
        String methodName = getMethodName(paramName);
        return ReflectUtil.getMethodByName(realClass, methodName);
    }

    /**
     * 获取实现方法的结果类型
     * @param paramName
     * @return
     * @param <T>
     * @param <M>
     */
    public static <T,M> Class<M> getMethodResultType(Func1<T, M> paramName) {
        return (Class<M>) Optional.ofNullable(getMethod(paramName))
                .map(Method::getReturnType)
                .orElse(null);
    }

}
