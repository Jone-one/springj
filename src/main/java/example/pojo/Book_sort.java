package example.pojo;

public class Book_sort {
    private int sid;
    private String sortName;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    @Override
    public String toString() {
        return "Book_sort{" +
                "sid=" + sid +
                ", sortName='" + sortName + '\'' +
                '}';
    }
}
