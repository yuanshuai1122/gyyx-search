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
     * @param channel  渠道
     * @param keywords 关键词
     * @return {@link ResultBean}<{@link Object}>
     */
    @RequestMapping("/list")
    public ResultBean<Object> searchList(@RequestParam("channel") String channel,
                                         @RequestParam("keywords") String keywords) {
        log.info("开始搜索信息列表，channel:{}, keywords:{}", channel, keywords);
        return service.searchList(channel, keywords);
    }


}
