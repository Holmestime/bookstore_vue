package com.whu.service.impl;

import com.whu.mapper.ICartMapper;
import com.whu.mapper.IUserMapper;
import com.whu.pojo.Cart;
import com.whu.pojo.User;
import com.whu.service.IUserService;

import java.util.List;


public class UserService implements IUserService {
    IUserMapper userMapper;
    ICartMapper cartMapper;

    public void setCartMapper(ICartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    public void setUserMapper(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public User get(String name) {

        return userMapper.get(name);
    }

    @Override
    public int updatePw(User user) {
        return userMapper.pwChange(user);
    }

    @Override
    public int addCart(Cart cart) { return cartMapper.add(cart); }

    @Override
    public List<Cart> listCart(int user_id){ return cartMapper.list(user_id); }

    @Override
    public int deleteCart(int user_id){ return cartMapper.delete(user_id); }

    @Override
    public int delete_one(Cart cart){ return cartMapper.remove_one(cart); }

    @Override
    public int update_num(Cart cart){ return cartMapper.update_num(cart); }

    @Override
    public Cart select_one(Cart cart){ return cartMapper.select_one(cart); }

    @Override
    public int add_num(Cart cart){ return cartMapper.add_num(cart); }
}
