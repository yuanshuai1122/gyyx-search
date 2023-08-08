package search.service.impl;

import cn.gydev.lib.bean.ResultBean;
import cn.gydev.lib.bean.RetCodeEnum;
import org.springframework.stereotype.Service;
import search.factory.SearchStrategyFactory;
import search.service.SearchService;
import search.strategy.SearchStrategy;

/**
 * @program: gyyx-search
 * @description: 搜索服务实现
 * @author: yuanshuai
 * @create: 2023-08-08 09:37
 **/
@Service("searchService")
public class SearchServiceImpl implements SearchService {

    @Override
    public ResultBean<Object> searchList(String channel, String keywords, String extension, String projectName, Integer pageNum, Integer pageSize){
        // 通过支付策略工厂拿到支付类型实现
        SearchStrategy payStrategy = SearchStrategyFactory.getPayStrategy(channel);
        if(payStrategy == null){
            return new ResultBean<>(RetCodeEnum.PARAM_ERROR, "搜索渠道有误", null);
        }
        return payStrategy.searchList(channel, keywords, extension, projectName, pageNum, pageSize);
    }
}
