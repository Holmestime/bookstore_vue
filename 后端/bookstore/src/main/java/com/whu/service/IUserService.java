package com.whu.service;

import com.whu.pojo.Cart;
import com.whu.pojo.User;

import java.util.List;

public interface IUserService {
    /**
     * 插入用户
     * @param user 新User对象
     * @return
     */
    public int add(User user);

    /**
     * 根据name字段得到User对象
     * @param name 用户名
     * @return
     */
    public User get(String name);

    /**
     * 修改密码 与controller层函数名不同
     * 匹配事务控制 的 通配
     * @param user
     * @return
     */
    public int updatePw(User user);

    /**
     * 添加到购物车
     * @param cart Cart实例
     * @return
     */
    public int addCart(Cart cart);

    /**
     * 列举指定用户的购物车
     * @param cart
     * @return
     */
    public List<Cart> listCart(int user_id);

    /**
     * 清空购物车
     * @param id
     * @return
     */
    public int deleteCart(int user_id);

    /**
     * 删除购物车的某条记录
     * @param cart Cart实例
     */
    public int delete_one(Cart cart);

    /**
     * 更新某个物品数量
     * @param cart Cart实例
     */
    public int update_num(Cart cart);

    /**
     * 删增加某个物品数量
     * @param cart Cart实例
     */
    public int add_num(Cart cart);

    /**
     * 从数据库中查询相应的记录
     * @param cart Cart实例
     */
    public Cart select_one(Cart cart);
}
