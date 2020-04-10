package cn.lcy.service;

import cn.lcy.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> getAll();
    public User getUser(User user);
    public int delete(Integer[] id);
    public int update(User user);
    public int add(User user);
    public List<User> find(Map<String,Object> map);
    public Long getTotal(Map<String,Object> map);
    public User getUserById(int id);
    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user);


}
