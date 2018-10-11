package test;

import com.google.gson.Gson;
import example.pojo.Book;
import example.service.BookService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.List;

public class mytest {
    static BookService bookService;
    static RedisTemplate redisTemplate;

    @BeforeClass
    public static void before() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        bookService = ac.getBean(BookService.class);
        redisTemplate = (RedisTemplate) ac.getBean("redisTemplate");
    }

    @Test
    public void queryListtest() {
        List<Book> books = bookService.queryList();
        Gson gson = new Gson();
        if (books != null)
            for (Book book : books) {

                System.out.println(gson.toJson(book));
            }
    }

    @Test
    public void testGetallBook() {
        List<Book> books = bookService.getAllBooks();
        if (books != null) {
            for (Book book : books) {
                System.out.println(book.toString());
            }
        }
    }

    @Test
    public void testGetByID() {
        Book bok = bookService.getBookByID(2);
        System.out.println(bok.toString());
    }

    @Test
    public void testadd() {
        Date date = new Date();
        Book book = new Book(10, "qwer", 98, date);
        try {
            bookService.add(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {
      /*  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(new Date().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        Book book = new Book("极品飞机", 99.9, new Date());
        for (int i = 0; i < 5; i++) {
            try {
                bookService.add(book);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testDelete() {
        int i = bookService.delete(4);
        if (i == 1)
            System.out.println(i + "删除成功");
        else
            System.out.println("删除失败");
    }

    @Test
    public void testDeleteMany() {
        String str[] = {"3", "4", "5", "6"};
        int i = bookService.delete(str);
        System.out.println(i + "条数据被删除");
    }

    @Test
    public void testUpdate() {
        Date date = new Date();
        Book book = new Book(8, "popopopo", 25, date);
        int i = bookService.update(book);
        System.out.println(i + "条数据更新成功");
    }

    @Test
    public void testTransAdd() {
        Book book1 = new Book("trans", 99, new Date());
        Book book2 = new Book("trans", 99, new Date());
        bookService.add(book1, book2);
    }

    @Test
    public void redis() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("hello", "redis");
        valueOperations.set("ly", "liubi");
        System.out.println(valueOperations.get("name"));
    }

    @Test
    public void demo() {
        int a = 5;
        System.out.println((++a) + (++a) + (++a));//678
        int b = 5;
        System.out.println((b++) + (b++) + (b++));//567
    }


}
