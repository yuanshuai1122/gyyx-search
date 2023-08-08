package search.factory;

import search.enums.SearchEnums;
import search.strategy.SearchStrategy;
import search.utils.SpringContextUtil;

/**
 * @author:yuanshuai
 * @create: 2022-10-08 14:05
 * @Description: 通过渠道码获取对应的策略实现类的工厂
 */
public class SearchStrategyFactory {

    /**
     * 通过渠道码获取支付策略具体实现类
     * */
    public static SearchStrategy getPayStrategy(String channel){
        SearchEnums payEnum = SearchEnums.search(channel);
        if(payEnum == null){
            return null;
        }
        return SpringContextUtil.getBean(payEnum.getBeanName(),SearchStrategy.class);
    }
}
