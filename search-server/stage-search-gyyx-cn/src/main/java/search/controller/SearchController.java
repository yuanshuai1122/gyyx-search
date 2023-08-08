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


    private final SearchService service;


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
                                         @RequestParam("keywords") String keywords,
                                         @RequestParam(value = "extension", required = false) String extension,
                                         @RequestParam(value = "projectName", required = false) String projectName,
                                         @RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize) {
        log.info("开始搜索信息列表，channel:{}, keywords:{}, extension:{}, projectName:{}, pageNum:{}, pageSize:{}", channel, keywords, extension, projectName, pageNum, pageSize);
        return service.searchList(channel, keywords, extension, projectName, pageNum, pageSize);
    }


}
