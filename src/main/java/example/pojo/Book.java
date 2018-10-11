package example.pojo;

import java.util.Date;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private double price;
    private Date publishDate;
    private List<Book_sort> sort;

    public Book(int id, String title, double price, Date publishDate) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.publishDate = publishDate;
    }

    public Book(String title, double price, Date publishDate) {
        this.title = title;
        this.price = price;
        this.publishDate = publishDate;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public List<Book_sort> getSort() {
        return sort;
    }

    public void setSort(List<Book_sort> sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", publishDate=" + publishDate +
                ", sort=" + sort +
                '}';
    }
}
