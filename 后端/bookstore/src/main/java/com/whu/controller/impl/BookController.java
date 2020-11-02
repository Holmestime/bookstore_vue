package com.whu.controller.impl;

import com.whu.controller.IBookController;
import com.whu.pojo.Book;
import com.whu.service.IBookService;
import com.whu.service.IUserService;
import com.whu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class BookController implements IBookController {
    IBookService bookService;
    @Autowired
    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    @CrossOrigin
    @PostMapping("/book/getAllBook")
    @ResponseBody
    @Override
    public List<Book> getAllBook() { return bookService.getAllBook(); }


    @CrossOrigin
    @PostMapping("/book/getById")
    @ResponseBody
    @Override
    public Book getById(@RequestBody int book_id) {
        return bookService.getById(book_id);
    }


    @CrossOrigin
    @PostMapping("/book/getByCategory")
    @ResponseBody
    @Override
    public List<Book> getByCategory(@RequestBody int category_id) { return bookService.getByCategory(category_id); }


    @CrossOrigin
    @PostMapping("/book/add")
    @ResponseBody
    @Override
    public Result add(@RequestBody Book book) {
        int result=bookService.add(book);
        if(result!=0)
            return new Result(200);
        else
            return new Result(400);
    }


    @CrossOrigin
    @PostMapping("/book/update")
    @ResponseBody
    @Override
    public Result update(@RequestBody Book book) {
        int result=bookService.update(book);
        if(result!=0)
            return new Result(200);
        else
            return new Result(400);
    }


    @CrossOrigin
    @PostMapping("/book/delete")
    @ResponseBody
    @Override
    public Result delete(@RequestBody int book_id) {
        int result=bookService.delete(book_id);
        if(result!=0)
            return new Result(200);
        else
            return new Result(400);
    }
}
