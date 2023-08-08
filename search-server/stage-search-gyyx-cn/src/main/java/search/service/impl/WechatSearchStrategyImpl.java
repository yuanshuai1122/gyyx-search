package search.service.impl;

import cn.gydev.lib.bean.ResultBean;
import cn.gydev.lib.bean.RetCodeEnum;
import org.springframework.stereotype.Component;
import search.strategy.SearchStrategy;

/**
 * @program: gyyx-search
 * @description: 微信搜索策略实现
 * @author: yuanshuai
 * @create: 2023-08-08 10:01
 **/
@Component("wechatSearchStrategy")
public class WechatSearchStrategyImpl implements SearchStrategy {
    @Override
    public ResultBean<Object> searchList(String channel, String keywords) {
        return new ResultBean<>(RetCodeEnum.SUCCESS, "获取成功", null);
    }
}
