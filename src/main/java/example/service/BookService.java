package example.service;

import example.dao.BookDAO;
import example.pojo.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BookService {
    @Resource
    BookDAO bookDAO;

    public List<Book> queryList() {
        return bookDAO.queryList();
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getBookByID(int id) {
        return bookDAO.getBookById(id);
    }

    public int add(Book book) throws Exception {
        if (book.getTitle() == null || book.getTitle().equals("")) {
            throw new Exception("书名不能为空");
        }
        return bookDAO.add(book);
    }

    public int add(Book book1, Book book2) {
        int rows = 0;
        rows = bookDAO.add(book1);
        rows = bookDAO.add(book2);
        return rows;
    }

    public int delete(int id) {
        return bookDAO.delete(id);
    }

    /**
     * 多删
     */
    public int delete(String[] sid) {
        int rows = 0;
        for (String idstr : sid) {
            int id = Integer.parseInt(idstr);
            rows += delete(id);
        }
        return rows;
    }

    public int update(Book book) {
        return bookDAO.update(book);
    }
}
