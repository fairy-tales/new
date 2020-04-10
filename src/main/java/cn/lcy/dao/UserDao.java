package cn.lcy.dao;


import cn.lcy.domain.User;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {



    /**
     * 用户登录
      * @param user
     * @return
     */
    public User login(User user);

    /**
     * 查询所有用户
     */
    public List<User> getAll();

    /**
     * 根据id查询
     * @return
     * @param id
     */
    public User getUserById(int id);

    public User getUser(User user);
    /**
     * 删除用户
     * @return
     * @param id
     */
    public int delete(Integer[] id);
    /**
     * 更新用户
     * @param user
     * @return
     */
    public int update(User user);
    /**
     * 添加用户
     * @param user
     * @return
     */
    public int add(User user);
    /**
     * 用户查询
     * @param map
     * @return
     */
    public List<User> find(Map<String,Object> map);
    /**
     * 获取总记录数
     * @param map
     * @return
     */
    public Long getTotal(Map<String,Object> map);







}
