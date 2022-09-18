package work.linruchang.lrcshortchain.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.func.LambdaUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 增强的Spring处理工具
 *
 * @author LinRuChang
 * @version 1.0
 * @date 2022/04/02
 * @since 1.8
 **/
public class EnhanceSpringUtil extends SpringUtil {

    /**
     * 获取当前请求
     *
     * @return
     */
    public static HttpServletRequest getCurrrentRequest() {
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes::getRequest)
                .orElseThrow(() -> new RuntimeException("非Web应用，请检查"));
    }

    /**
     * 获取当前响应
     *
     * @return
     */
    public static HttpServletResponse getCurrentResponse() {
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes::getResponse)
                .orElseThrow(() -> new RuntimeException("非Web应用，请检查"));
    }

    /**
     * 获取当前请求表单、url参数
     * @return
     */
    public static Dict getCurrentRequestParams() {
        HttpServletRequest currrentRequest = getCurrrentRequest();
        Dict params = MapUtil.emptyIfNull(currrentRequest.getParameterMap())
                .entrySet().stream()
                .collect(Collectors.toMap(paramKeyValues -> paramKeyValues.getKey(), paramKeyValues -> paramKeyValues.getValue()[0], (t1, t2) -> t2, () -> Dict.create()));
        return params;
    }

    /**
     * 获取当前请求表单、url参数
     * @return
     */
    public static <T> T getCurrentRequestParams(Class<T> resultBeanClazz) {
        return getCurrentRequestParams().toBeanIgnoreCase(resultBeanClazz);
    }

    /**
     * 获取当前请求某个表单、url参数
     * @return
     */
    public static <T> T getCurrentRequestParam(String paramName, Class<T> resultTypeClazz) {
        return getCurrentRequestParams().getByPath(paramName, resultTypeClazz);
    }

    /**
     * 获取当前请求某个表单、url参数
     * @return
     */
    public static String getCurrentRequestParam(String paramName) {
        return getCurrentRequestParams().getByPath(paramName, String.class);
    }

    /**
     * 获取当前请求某个表单、url参数
     * @return
     */
    public static <T, M> M getCurrentRequestParam(Func1<T, M> paramName) {
        String fieldName = LambdaUtil.getFieldName(paramName);
        Class<M> methodResultType = EnhanceLambdaUtil.getMethodResultType(paramName);
        return getCurrentRequestParam(fieldName,methodResultType);
    }


    /**
     * 获取获取当前请求头
     * @return
     */
    public static Dict getCurrentRequestHeaders() {
        HttpServletRequest currrentRequest = getCurrrentRequest();
        Dict headerKeyValues = Dict.create();
        Enumeration<String> headerNames = currrentRequest.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerKeyValues.put(headerName, currrentRequest.getHeader(headerName));
        }
        return headerKeyValues;
    }

    /**
     * 获取获取当前某个请求头
     * @param headerName
     * @param headerValueType
     * @return
     * @param <T>
     */
    public static <T> T getCurrentRequestHeader(String headerName, Class<T> headerValueType) {
        return getCurrentRequestHeaders().getByPath(headerName,headerValueType);
    }

    /**
     * 获取获取当前某个请求头
     * @param headerName
     * @return
     */
    public static String getCurrentRequestHeader(String headerName) {
        return getCurrentRequestHeader(headerName,String.class);
    }

    /**
     * 获取获取当前某个请求头
     * @param headerName
     * @return
     * @param <T>
     * @param <M>
     */
    public static <T, M> M  getCurrentRequestHeader(Func1<T, M> headerName) {
        String fieldName = LambdaUtil.getFieldName(headerName);
        Class<M> methodResultType = EnhanceLambdaUtil.getMethodResultType(headerName);
        return getCurrentRequestHeader(fieldName,methodResultType);
    }


    /**
     * 获取当前请求属性
     * @return
     */
    public static Dict getCurrentRequestAttributes() {
        HttpServletRequest currrentRequest = getCurrrentRequest();
        Dict attributeKeyValues = Dict.create();
        Enumeration<String> attributeNames = currrentRequest.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            attributeKeyValues.put(attributeName,currrentRequest.getAttribute(attributeName));
        }
        return attributeKeyValues;
    }

    /**
     * 获取当前请求某个属性
     * @param attributeName
     * @param attributeValueType
     * @return
     * @param <T>
     */
    public static <T> T getCurrentRequestAttribute(String attributeName,Class<T> attributeValueType) {
        return getCurrentRequestAttributes().getByPath(attributeName,attributeValueType);
    }

    /**
     * 获取当前请求某个属性
     * @param attributeName
     * @return
     */
    public static String getCurrentRequestAttribute(String attributeName) {
        return getCurrentRequestAttribute(attributeName,String.class);
    }

    /**
     * 获取当前请求某个属性
     * @param attributeName
     * @return
     * @param <T>
     * @param <M>
     */
    public static <T, M> M  getCurrentRequestAttribute(Func1<T, M> attributeName) {
        String fieldName = LambdaUtil.getFieldName(attributeName);
        Class<M> methodResultType = EnhanceLambdaUtil.getMethodResultType(attributeName);
        return getCurrentRequestAttribute(fieldName,methodResultType);
    }



    /**
     * 获取当前请求会话session内的属性
     *
     * @return
     */
    public static Dict getCurrentSessionAttributes() {
        HttpServletRequest currrentRequest = getCurrrentRequest();
        Dict attributeKeyValues = Dict.create();
        Enumeration<String> attributeNames = currrentRequest.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            attributeKeyValues.put(attributeName,currrentRequest.getAttribute(attributeName));
        }
        return attributeKeyValues;
    }

    /**
     * 获取当前请求会话session内某个属性
     * @param attributeName
     * @param attributeValueType
     * @return
     * @param <T>
     */
    public static <T> T getCurrentSessionAttribute(String attributeName,Class<T> attributeValueType) {
        return getCurrentSessionAttributes().getByPath(attributeName,attributeValueType);
    }

    /**
     * 获取当前请求会话session内某个属性
     * @param attributeName
     * @return
     */
    public static String getCurrentSessionAttribute(String attributeName) {
        return getCurrentSessionAttribute(attributeName,String.class);
    }

    /**
     * 获取当前请求会话session内某个属性
     * @param attributeName
     * @return
     * @param <T>
     * @param <M>
     */
    public static <T, M> M  getCurrentSessionAttribute(Func1<T, M> attributeName) {
        String fieldName = LambdaUtil.getFieldName(attributeName);
        Class<M> methodResultType = EnhanceLambdaUtil.getMethodResultType(attributeName);
        return getCurrentRequestAttribute(fieldName,methodResultType);
    }



    /**
     * 命令浏览器删除某个cookie
     *
     * @param cookieName
     * @return
     */
    public static Cookie delCookie(String cookieName) {
        Assert.notBlank(cookieName, "cookie名不能缺失，请检查");

        Cookie cookie = new Cookie(cookieName, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        Optional.ofNullable(getCurrentResponse())
                .ifPresent(response -> response.addCookie(cookie));
        return cookie;
    }


    public static Cookie addCookie(String cookieName, String cookieValue) {
        Cookie cookie = null;
        if (StrUtil.isAllNotBlank(cookieName, cookieValue)) {
            HttpServletResponse currentResponse = getCurrentResponse();
            cookie = new Cookie(cookieName, cookieValue);
            currentResponse.addCookie(cookie);
        }
        return cookie;
    }


    /**
     * 对象属性都添加进入本地cookie
     *
     * @param bean
     * @return
     */
    public static List<Cookie> addCookie(Object bean) {
        final List cookieList = CollUtil.newArrayList();
        if (ObjectUtil.isNotEmpty(bean)) {
            boolean primitiveFlag = bean.getClass().isPrimitive();
            if (BooleanUtil.isFalse(primitiveFlag)) {
                Map<String, Object> keyValueMap = BeanUtil.beanToMap(bean, false, true);
                keyValueMap.forEach((key, value) -> {
                    if (ClassUtil.isBasicType(value.getClass()) || value.getClass() == String.class) {
                        cookieList.add(addCookie(key, Convert.toStr(value)));
                    }
                });
            }
        }
        return cookieList;
    }

}
