package search.service;

import cn.gydev.lib.bean.ResultBean;

/**
 * @program: gyyx-search
 * @description: 搜索服务接口
 * @author: yuanshuai
 * @create: 2023-08-08 09:31
 **/
public interface SearchService {

    /**
     * 搜索列表
     *
     * @param channel  通道
     * @param keywords 关键词
     * @return {@link ResultBean}<{@link Object}>
     */
    ResultBean<Object> searchList(String channel, String keywords);
}
