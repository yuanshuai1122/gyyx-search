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
     * 搜索信息列表
     *
     * @param channel     通道
     * @param keywords    关键词
     * @param extension   文件后缀
     * @param projectName 项目名称
     * @param pageNum     页数
     * @param pageSize    页面大小
     * @return {@link ResultBean}<{@link Object}>
     */
    ResultBean<Object> searchList(String channel, String keywords, String extension, String projectName, Integer pageNum, Integer pageSize);


    /**
     * 根据id搜索文件详情
     *
     * @param channel 渠道
     * @param id      ID
     * @return {@link ResultBean}<{@link Object}>
     */
    ResultBean<Object> searchInfo(String channel, String id);
}
