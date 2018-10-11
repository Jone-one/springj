package example.dao;

import example.pojo.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookDAO {

    public  List<Book> queryList();
    /**
     * 获取所有图书
     * @return
     */
    public List<Book> getAllBooks();

    /**
     * 根据图书编号获取图书对象
     */
    public Book getBookById(@Param("id") int id);

    /**
     * 添加图书
     */
    public int add(Book book);
    /**
     *根据id 删除图书
     */
    public int delete(int id);
    /**
     * 更新图书
     */
    public int update(Book book);
}
