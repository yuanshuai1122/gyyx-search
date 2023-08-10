package search.controller;

import cn.gydev.lib.bean.ResultBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import search.service.SearchService;

/**
 * @program: gyyx-search
 * @description: 搜索控制器
 * @author: yuanshuai
 * @create: 2023-08-08 09:30
 **/
@RestController
@RequestMapping("/search")
@Slf4j
@RequiredArgsConstructor
public class SearchController {


    private final SearchService searchService;


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
    @RequestMapping("/list")
    public ResultBean<Object> searchList(@RequestParam("channel") String channel,
                                         @RequestParam(value = "keywords", required = false) String keywords,
                                         @RequestParam(value = "extension", required = false) String extension,
                                         @RequestParam(value = "projectName", required = false) String projectName,
                                         @RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize) {
        log.info("开始搜索信息列表，channel:{}, keywords:{}, extension:{}, projectName:{}, pageNum:{}, pageSize:{}", channel, keywords, extension, projectName, pageNum, pageSize);
        return searchService.searchList(channel, keywords, extension, projectName, pageNum, pageSize);
    }


    /**
     * 根据id搜索文件详情
     *
     * @param channel 渠道
     * @param id      ID
     * @return {@link ResultBean}<{@link Object}>
     */
    @RequestMapping("/info")
    public ResultBean<Object> searchInfo(@RequestParam("channel") String channel,
                                         @RequestParam("id") String id) {
        log.info("根据id查询文件详情开始，id:{}", id);
        return searchService.searchInfo(channel, id);
    }


}
