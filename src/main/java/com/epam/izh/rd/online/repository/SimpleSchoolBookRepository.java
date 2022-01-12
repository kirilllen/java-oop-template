package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    public final int MAX_NUMBER=1_000_000;
    public static int schoolBookNumber=0; //счётчик количества книг
    SchoolBook[] schoolBooks=new SchoolBook[MAX_NUMBER]; //по ТЗ нельзя использовать ArrayList (коллекции), примем максимальное количество книг 1_000_000, т.к. размер массива в ходе выполнения программы менять нельзя

    @Override
    public boolean save(SchoolBook book) {
        return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int[] booksId=new int[MAX_NUMBER]; //переменная для возвращения автора
        SchoolBook[] books; //массив для хранения найденных книг
        int counter=0; //переменная-счётчик найденных книг
        for (int i=0; i<schoolBookNumber; i++){
            if (name.equals(schoolBooks[i].getName())) {
                booksId[counter]=i;
                counter++;

            }
        }
        books=new SchoolBook[counter];
        for (int i=0;i<counter;i++){
            books[i]=schoolBooks[booksId[i]];
        }
        return books;
    }

    @Override
    public boolean removeByName(String name) {
        return false;
    }

    @Override
    public int count() {
        return schoolBookNumber;
    }
}
