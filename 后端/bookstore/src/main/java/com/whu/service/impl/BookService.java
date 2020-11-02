package com.whu.service.impl;

// import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import com.whu.mapper.IBookMapper;
import com.whu.mapper.ICategoryMapper;
import com.whu.pojo.Book;
import com.whu.pojo.Category;
import com.whu.service.IBookService;

import java.util.List;

public class BookService implements IBookService {
    ICategoryMapper categoryMapper;
    IBookMapper bookMapper;

    public void setCategoryMapper(ICategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public void setBookMapper(IBookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public List<Book> getAllBook() {
        List<Book> books =bookMapper.getAllBook();
        return books;
    }

    @Override
    public List<Book> getByCategory(int category_id) {
        Category c=categoryMapper.getByCategory(category_id);
        List<Book> books =c.getBooks();
        return books;
    }

    @Override
    public Book getById(int book_id) {
        return bookMapper.getById(book_id);
    }

    @Override
    public int add(Book book) {
        return bookMapper.add(book);
    }

    @Override
    public int update(Book book) {
        return bookMapper.update(book);
    }

    @Override
    public int delete(int book_id) {
        return bookMapper.delete(book_id);
    }

}
