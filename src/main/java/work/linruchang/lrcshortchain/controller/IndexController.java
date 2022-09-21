package work.linruchang.lrcshortchain.controller;

import cn.hutool.core.lang.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import work.linruchang.lrcshortchain.util.EnhanceSpringUtil;

/**
 * @author LinRuChang
 * @version 1.0
 * @date 2022/09/19
 * @since 1.8
 **/
@Controller
public class IndexController {

    @Autowired
    LinkInfoController linkInfoController;

    /**
     * 首页
     * @return
     */
    @GetMapping(value = {"","index","index.html","index.htm"})
    public String indexPage() {
        String currentContextUrl = EnhanceSpringUtil.getCurrentContextUrl();
        Console.log(currentContextUrl);
        return "index";
    }

    /**
     * 短链跳转到原链
     * @param uuid
     * @return
     */
    @GetMapping("{uuid}")
    public String redirectSourceLink(@PathVariable String uuid) {
        return linkInfoController.redirectSourceLink(uuid);
    }
}
