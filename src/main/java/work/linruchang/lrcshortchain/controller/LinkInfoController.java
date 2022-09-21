package work.linruchang.lrcshortchain.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.linruchang.lrcshortchain.bean.LinkInfo;
import work.linruchang.lrcshortchain.config.handler.ResponseResult;
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
@RestController
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
    public ResponseResult<LinkInfo> generateShortLink(@RequestBody LinkInfo generateLinkInfo) {


        String linkUuid = UUID.fastUUID().toString(true);
        generateLinkInfo = generateLinkInfo
                .setShortLink(StrUtil.format("{}/{}", EnhanceSpringUtil.getCurrentContextUrl(), linkUuid))
                .setUuid(linkUuid)
                .setSourceUserIp(ServletUtil.getClientIP(EnhanceSpringUtil.getCurrrentRequest()));


        boolean saveFlag = linkInfoService.save(generateLinkInfo);
        if(saveFlag) {
            EhcacheUtil.set(linkUuid, generateLinkInfo, DateUnit.DAY.getMillis());
        }
        return saveFlag ? ResponseResult.success(generateLinkInfo) : ResponseResult.fail(generateLinkInfo, "生成失败");
    }

    @GetMapping("{uuid}")
    public ResponseResult<LinkInfo> redirectSourceLink(@PathVariable String uuid) {
        LinkInfo resultLinkInfo = (LinkInfo) EhcacheUtil.get(uuid);
        if(resultLinkInfo == null) {
            resultLinkInfo = linkInfoService.getOne(Wrappers.<LinkInfo>lambdaQuery().eq(LinkInfo::getUuid,uuid), false);
        }
        return resultLinkInfo != null ? ResponseResult.success(resultLinkInfo) : ResponseResult.fail(null,"短链不存在，请检查");
    }

}
