package work.linruchang.lrcshortchain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author LinRuChang
 * @version 1.0
 * @date 2022/09/19
 * @since 1.8
 **/
@Controller
public class IndexController {

    /**
     * 首页
     * @return
     */
    @GetMapping(value = {"","index","index.html","index.htm"})
    public String indexPage() {
        return "index";
    }

}
