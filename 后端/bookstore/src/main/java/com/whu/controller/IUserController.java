package com.whu.controller;

import com.whu.pojo.Cart;
import com.whu.pojo.User;
import com.whu.utils.Result;

import java.util.List;

public interface IUserController {

    /**
     * 登陆
     * @param requestUser
     * @return
     */
    public Result login(User requestUser);

    /**
     * 注册
     * @param requestUser
     * @return
     */
    public Result register(User requestUser);

    /**
     * 修改密码
     * @param requestUser
     * @return
     */
    public Result pwChange(User requestUser);

    /**
     * 得到用户ID
     * @param requestUser
     * @return
     */
    public int getUserId(User requestUser);

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    public Result addCart(Cart cart);

    /**
     * 列举指定用户的购物车
     * @param cart
     * @return
     */
    public List<Cart> listCart(int user_id);

    /**
     * 清空购物车
     * @param user_id
     * @return
     */
    public Result check(int user_id);

    /**
     * 删除书本信息
     * @param cart
     * @return
     */
    public Result remove_one(Cart cart);

    /**
     * 更新用户购物车数量
     * @param cart
     * @return
     */
    public Result update_num(Cart cart);

}
