package com.whu.controller;

import com.whu.pojo.Book;
import com.whu.utils.Result;

import java.util.List;

public interface IBookController {
    /**
     * 获得所有书籍
     * @param
     * @return
     */
    public List<Book> getAllBook();


    /**
     * 通过书籍ID获得信息
     * @param id 书的索引
     * @return
     */
    public Book getById(int book_id);

    /**
     * 获得一类书信息
     * @param id
     * @return
     */
    public List<Book> getByCategory(int category_id);


    /**
     * 添加书籍信息
     * @param book
     * @return
     */
    public Result add(Book book);


    /**
     * 更新书本内容（除书名外的信息）
     * @param book 实例对象
     * @return
     */
    public Result update(Book book);


    /**
     * 删除指定ID书本信息
     * @param id 书的索引
     * @return
     */
    public Result delete(int book_id);
}
