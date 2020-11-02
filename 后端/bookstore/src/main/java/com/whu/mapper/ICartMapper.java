package com.whu.mapper;

import com.whu.pojo.Cart;
import com.whu.utils.Result;

import java.util.List;

public interface ICartMapper  {
    /**
     * 用户选择加入购物车相关操作
     * @param cart Cart实例对象
     */
    public int add(Cart cart);

    /**
     * 显示user_id购物车内所有的信息
     * @param user_id 用户id
     */
    public List<Cart> list(int user_id);

    /**
     * 清空用户购物车
     * @param user_id 用户id
     */
    public int delete(int user_id);

    /**
     * 删除购物车的某条记录
     * @param user_id 用户id
     * @param user_id 用户id
     */
    public int remove_one(Cart cart);

    /**
     * 更新某件物品的数量
     * @param cart Cart实例
     */
    public int update_num(Cart cart);

    /**
     * 增加某件物品的数量
     * @param cart Cart实例
     */
    public int add_num(Cart cart);

    /**
     * 从数据库中查询相应的记录
     * @param cart Cart实例
     */
    public Cart select_one(Cart cart);


}
