package cn.lcy.service.impl;

import cn.lcy.dao.UserDao;
import cn.lcy.domain.User;
import cn.lcy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userdao;

    public List<User> getAll() {
        return userdao.getAll();
    }

    public User getUser(User user) {
        return userdao.getUser(user);
    }

    public int delete(Integer[] id) {
        return userdao.delete(id);
    }

    public int update(User user) {
        return userdao.update(user);
    }

    public int add(User user) {
        return userdao.add(user);
    }

    public List<User> find(Map<String, Object> map) {
        return userdao.find(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return userdao.getTotal(map);
    }

    public User getUserById(int id) {
        return userdao.getUserById(id);
    }

    public User login(User user) {
        return userdao.login(user);
    }
}
