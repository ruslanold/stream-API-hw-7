import java.util.Objects;

public class Book {

    private String name;
    private int countPage;

    public Book(){}

    public Book(String name, int countPage) {
        this.name = name;
        this.countPage = countPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return countPage == book.countPage && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countPage);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", countPage=" + countPage +
                '}';
    }
}
