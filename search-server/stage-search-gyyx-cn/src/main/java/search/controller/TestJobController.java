package search.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import search.beans.testjob.TestJob;
import search.service.TestJobService;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: gyyx-search
 * @description: 测试作业控制器
 * @author: yuanshuai
 * @create: 2023-08-07 11:06
 **/
@RequestMapping("testjob")
@RestController
@Slf4j
@RequiredArgsConstructor
public class TestJobController {

    private final TestJobService testJobService;



    @RequestMapping("/getAll")
    public List<TestJob> getAll() {
        List<TestJob> list = new ArrayList<>();
        Iterable<TestJob> iterable = testJobService.getAll();
        iterable.forEach(e->list.add(e));
        return list;
    }


}
