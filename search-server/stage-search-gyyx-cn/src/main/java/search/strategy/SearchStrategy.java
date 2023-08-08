package search.strategy;


import cn.gydev.lib.bean.ResultBean;

/**
 * 搜索策略接口类
 *
 * @author yuanshuai
 * @date 2023/08/08
 */
public interface SearchStrategy {

    /**
     * 搜索列表
     *
     * @param channel  通道
     * @param keywords 关键词
     * @return {@link ResultBean}<{@link Object}>
     */
    ResultBean<Object> searchList(String channel, String keywords);

}
