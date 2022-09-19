package work.linruchang.lrcshortchain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.linruchang.lrcshortchain.bean.LinkInfo;
import work.linruchang.lrcshortchain.mapper.LinkInfoMapper;
import work.linruchang.lrcshortchain.service.LinkInfoService;

/**
 * @author LinRuChang
 * @version 1.0
 * @date 2022/09/20
 * @since 1.8
 **/
@Service
public class LinkInfoServiceImpl extends ServiceImpl<LinkInfoMapper, LinkInfo> implements LinkInfoService {
}
