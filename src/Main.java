import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Book book1 = new Book("book1", 344);
        Book book2 = new Book("book2", 562);
        Book book3 = new Book("book3", 151);
        Book book4 = new Book("book4", 214);
        Book book5 = new Book("book5", 351);
        Book book6 = new Book("book6", 211);
        Book book7 = new Book("book7", 461);
        Book book8 = new Book("book8", 555);

        List<Book> books1 = new ArrayList<>();
        books1.add(book1);
        books1.add(book2);
        books1.add(book3);

        List<Book> books2 = new ArrayList<>();
        books2.add(book4);
        books2.add(book5);
        books2.add(book6);

        List<Book> books3 = new ArrayList<>();
        books3.add(book7);
        books3.add(book4);
        books3.add(book8);

        List<Book> books4 = new ArrayList<>();
        books4.add(book3);
        books4.add(book5);
        books4.add(book7);


        Person vavas = new Person("Vavas", 22, books1);
        Person vavasBrother = new Person("Vavas", 24, books2);
        Person dan = new Person("Dan", 31, books3);
        Person ren = new Person("Ren", 26, books4);

        List<Person> personList = new ArrayList<>();
        personList.add(vavas);
        personList.add(vavasBrother);
        personList.add(dan);
        personList.add(ren);


        //згенерувати мапу <Person, Integer>, де Integer - кількість сторінок усіх книжок, які має людина
        Map<Person, Integer> map = personList.stream().collect(Collectors.toMap(
                Function.identity(),
                (person) -> {
                    Integer integer = person.getBooks().stream().mapToInt(Book::getCountPage).reduce((i1,i2) -> i1 + i2).getAsInt();
                    return integer;
                }
                )
        );
        System.out.println("Map<Person, Integer>" + " ---> " + map);


        //- згенерувати мапу <String, Book>, де String - ім'я людини, Book - книжка з найбільшою кількістю сторінок
        Map<String, Book> map1 = personList.stream().collect(Collectors.toMap(
                (person) -> person.getName(),
                (person) -> {
                    Book book = person.getBooks().stream().max((b1, b2) -> b1.getCountPage() - b2.getCountPage()).get();
                    return book;
                },
                (olVal, newVal) -> olVal
                )
        );

        System.out.println("Map<String, Book>" + " ---> " + map1);


        //згенерувати List<Book> відфільтрувавши тільких тих людей,
        // хто старше 25 років
        // і
        // книжки які мають більше 500 сторінок
        List<Book> filterBooks= personList.stream()
                .filter(person -> person.getAge() > 25)
                .flatMap((person) -> person.getBooks().stream())
                .filter(book -> book.getCountPage() > 500)
                .collect(Collectors.toList());

        System.out.println("List<Book>" + " ---> " + filterBooks);


        //- згенерувати мапу <Person, Book>, сортировать book по названию
        Map<Person, List<Book>> sortedBooks = personList.stream().collect(Collectors.toMap(
                person -> person,
                person -> {
                    return person.getBooks().stream()
                            .sorted((b1,b2) -> b1.getName().compareTo(b2.getName()))
                            .collect(Collectors.toList());
                }
        ));
        System.out.println("Map<Person, List<Book>>" + " ---> " + sortedBooks);




    }

}
