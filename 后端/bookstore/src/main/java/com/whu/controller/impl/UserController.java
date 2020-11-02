package com.whu.controller.impl;

import com.whu.pojo.Cart;
import com.whu.pojo.User;
import com.whu.service.IUserService;
import com.whu.utils.Result;
import com.whu.controller.IUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController implements IUserController {

    IUserService userService;
    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/user/getId")
    @ResponseBody
    @Override
    public int getUserId(@RequestBody User requestUser){
        String name=requestUser.getUsername();
        User user = userService.get(name);
        return user.getId();
    }


    @CrossOrigin
    @PostMapping("/user/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String name=requestUser.getUsername();
        User user=userService.get(name);
        if(user!=null&&requestUser.getPassword().equals(user.getPassword()))
            return new Result(200);
        else
            return new Result(400);
    }

    @CrossOrigin
    @PostMapping("/user/register")
    @ResponseBody
    @Override
    public Result register(@RequestBody User requestUser) {
        int result=userService.add(requestUser);
        if(result!=0)
            return new Result(200);
        else
            return new Result(400);
    }


    @CrossOrigin
    @PostMapping("/user/pwChange")
    @ResponseBody
    @Override
    public Result pwChange(@RequestBody User requestUser) {
        int result=userService.updatePw(requestUser);
        if(result!=0)
            return new Result(200);
        else
            return new Result(400);
    }


    @CrossOrigin
    @PostMapping("/user/add")
    @ResponseBody
    @Override
    public Result addCart(@RequestBody Cart cart) {
        int result;
        Cart isEmpty = userService.select_one(cart);
        if(isEmpty!=null)
        {
            result = userService.add_num(cart);
        }
        else{
            result=userService.addCart(cart);
        }
        if(result!=0)
            return new Result(200);
        else
            return new Result(400);
    }


    @CrossOrigin
    @PostMapping("/cart/list")
    @ResponseBody
    @Override
    public List<Cart> listCart(@RequestBody int user_id) {
        return userService.listCart(user_id);
    }


    @CrossOrigin
    @PostMapping("/cart/check")
    @ResponseBody
    @Override
    public Result check(@RequestBody int user_id) {
        int result=userService.deleteCart(user_id);
        if(result!=0)
            return new Result(200);
        else
            return new Result(400);
    }


    @CrossOrigin
    @PostMapping("/cart/remove_one")
    @ResponseBody
    @Override
    public Result remove_one(@RequestBody Cart cart){
        int result=userService.delete_one(cart);
        if(result!=0)
            return new Result(200);
        else
            return new Result(400);
    }


    @CrossOrigin
    @PostMapping("/cart/update_num")
    @ResponseBody
    @Override
    public Result update_num(@RequestBody Cart cart){
        int result=userService.update_num(cart);
        if(result!=0)
            return new Result(200);
        else
            return new Result(400);
    }
}
