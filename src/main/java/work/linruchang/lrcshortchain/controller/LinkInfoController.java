package work.linruchang.lrcshortchain.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
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
    @PostMapping
    public ResponseResult<LinkInfo> generateShortLink(LinkInfo generateLinkInfo) {
        String linkUuid = UUID.fastUUID().toString(true);
        generateLinkInfo = generateLinkInfo.setShortLink(StrUtil.format("{}/{}", EnhanceSpringUtil.getCurrentContextUrl(), linkUuid));
        boolean saveFlag = linkInfoService.save(generateLinkInfo);
        if(saveFlag) {
            EhcacheUtil.set(linkUuid, generateLinkInfo, DateUnit.DAY.getMillis());
        }
        return saveFlag ? ResponseResult.success(generateLinkInfo) : ResponseResult.fail(generateLinkInfo, "生成失败");
    }

    @GetMapping("{uuid}")
    public ResponseResult<LinkInfo> redirectSourceLink(@PathVariable String uuid) {

        return null;
    }

}
