package search.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import search.beans.User;
import search.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: gyyx-search
 * @description: 测试控制器
 * @author: yuanshuai
 * @create: 2023-08-07 09:54
 **/
@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("/insert")
    public String insert() {
        User user = new User();
        user.setId("1");
        user.setUsername("张三");
        user.setPassword("zhangsan");
        userService.save(user);
        return getAll();
    }

    @RequestMapping("/delete")
    public String delete() {
        User user = new User();
        user.setId("1");
        userService.delete(user);
        return getAll();
    }

    @RequestMapping("/getAll")
    public String getAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> iterable = userService.getAll();
        iterable.forEach(e->list.add((User) e));
        return list.toString();
    }

}
