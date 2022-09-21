package work.linruchang.lrcshortchain.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.linruchang.lrcshortchain.bean.LinkInfo;
import work.linruchang.lrcshortchain.config.handler.RequestEnum;
import work.linruchang.lrcshortchain.config.handler.ResponseResult;
import work.linruchang.lrcshortchain.config.handler.SysetmBaseCustomException;
import work.linruchang.lrcshortchain.service.LinkInfoService;
import work.linruchang.lrcshortchain.util.EhcacheUtil;
import work.linruchang.lrcshortchain.util.EnhanceSpringUtil;

import java.io.Serializable;

/**
 * @author LinRuChang
 * @version 1.0
 * @date 2022/09/20
 * @since 1.8
 **/
@RequestMapping("link-info")
public class LinkInfoController {

    @Autowired
    LinkInfoService linkInfoService;

    /**
     * 生成短链
     * @param generateLinkInfo
     * @return
     */
    @PostMapping("generate")
    @ResponseBody
    public ResponseResult<LinkInfo> generateShortLink(@RequestBody LinkInfo generateLinkInfo) {
        Assert.isTrue(HttpUtil.isHttp(generateLinkInfo.getSourceLink()) || HttpUtil.isHttps(generateLinkInfo.getSourceLink()), () -> new SysetmBaseCustomException(RequestEnum.ResponseCodeEnum.SOURCE_LINK_ERROR));

        String linkUuid = UUID.fastUUID().toString(true);
        generateLinkInfo = generateLinkInfo
                .setShortLink(StrUtil.format("{}/link-info/{}", EnhanceSpringUtil.getCurrentContextUrl(), linkUuid))
                .setUuid(linkUuid)
                .setSourceUserIp(ServletUtil.getClientIP(EnhanceSpringUtil.getCurrrentRequest()));


        boolean saveFlag = linkInfoService.save(generateLinkInfo);
        if(saveFlag) {
            EhcacheUtil.set(linkUuid, generateLinkInfo, DateUnit.DAY.getMillis());
        }
        return saveFlag ? ResponseResult.success(generateLinkInfo) : ResponseResult.fail(generateLinkInfo, "生成失败");
    }


    /**
     * 短链跳转到原链
     * @param uuid
     * @return
     */
    @GetMapping("{uuid}")
    public String redirectSourceLink(@PathVariable String uuid) {
        LinkInfo resultLinkInfo = (LinkInfo) EhcacheUtil.get(uuid);
        if(resultLinkInfo == null) {
            resultLinkInfo = linkInfoService.getOne(Wrappers.<LinkInfo>lambdaQuery().eq(LinkInfo::getUuid,uuid), false);

        }

        if(resultLinkInfo != null) {
            EhcacheUtil.set(uuid,resultLinkInfo,DateUnit.DAY.getMillis());
            return StrUtil.format("redirect:{}", resultLinkInfo.getSourceLink());
        }else {
            return StrUtil.format("redirect:{}", "/#/error");
        }
    }

}
