package search.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import search.beans.User;
import search.dao.UserRepository;

/**
 * @program: gyyx-search
 * @description: 测试服务
 * @author: yuanshuai
 * @create: 2023-08-07 09:55
 **/

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

}
