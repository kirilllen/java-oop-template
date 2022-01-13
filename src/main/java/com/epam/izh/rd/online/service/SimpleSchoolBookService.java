package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook>{
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    /**
     * Метод должен сохранять книгу.
     *
     * Перед сохранением книги нужно проверить, сохранен ли такой автор в базе авторов.
     * То есть вы должен взять имя и фамилию автора из книги и обратиться к сервису авторов и узнать о наличии такого автора.
     * Напомню, что мы считаем, что двух авторов с одинаковыми именем и фамилией быть не может.
     *
     * Если такой автор сущесвует (сохранен) - значит можно сохранять и книгу.
     * Если же такого автора в базе нет, значит книгу сохранять нельзя.
     *
     * Соответственно, если книга была успешно сохранена - метод возвращает true, если же книга не была сохранена - метод возвращает false.
     */
    @Override
    public boolean save(SchoolBook book) {
        Author author;
        author=authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName());
        if (author==null){
            return false;
        }
        else {
            save(book);
            return true;
        }
    }

    /**
     * Метод должен находить книгу по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public SchoolBook[] findByName(String name) {
        return new SchoolBook[0];
    }

    /**
     * Метод должен находить количество сохраненных книг по конкретному имени книги.
     */
    @Override
    public int getNumberOfBooksByName(String name) {
        return 0;
    }

    /**
     * Метод должен удалять все книги по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public boolean removeByName(String name) {
        return false;
    }

    /**
     * Метод должен возвращать количество всех книг.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public int count() {
        return 0;
    }

    /**
     * Метод должен возвращать автора книги по названию книги.
     *
     * То есть приждется сходить и в репозиторий с книгами и в сервис авторов.
     *
     * Если такой книги не найдено, метод должен вернуть null.
     */
    @Override
    public Author findAuthorByBookName(String name) {
        return null;
    }

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }
}
