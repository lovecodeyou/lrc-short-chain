package work.linruchang.lrcshortchain;

import cn.hutool.core.lang.Console;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.Between;
import com.ejlchina.searcher.operator.StartWith;
import com.ejlchina.searcher.util.MapUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.linruchang.lrcshortchain.bean.LinkInfo;

import java.util.List;
import java.util.Map;

@SpringBootTest
class LrcShortChainApplicationTests {

	@Autowired
	private BeanSearcher searcher;

	@Test
	void contextLoads() {

		// Map<String, Object> searchConditions = MapUtils.builder().orderBy(LinkInfo::getId).build();
		// Map<String, Object> searchConditions = MapUtils.builder().build();
		Map<String, Object> searchConditions = MapUtils.builder()
				.orderBy(LinkInfo::getId).asc()
				.page(0, 15)
				.build();

		SearchResult<LinkInfo> searchResult = searcher.search(LinkInfo.class, searchConditions);

		List<LinkInfo> dataList = searchResult.getDataList();

		Console.log(dataList);

	}

}
