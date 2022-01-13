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
        schoolBooks[schoolBookNumber]=book;
        schoolBookNumber++;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] books; //массив для хранения найденных книг
        int counter=getBooksNumber(name);
        int[] booksId;
        booksId=getBooksId(name, counter);
        books=new SchoolBook[counter];
        for (int i=0;i<counter;i++){
            books[i]=schoolBooks[booksId[i]];
        }
        return books;
    }

    private int getBooksNumber (String name){ //метод для определения количества найденных книг
        int counter=0;
        for (int i=0; i<schoolBookNumber; i++){
            if (name.equals(schoolBooks[i].getName())) {
                counter++;
            }
        }
        return counter;
    }

    private int[] getBooksId (String name, int number) { //метод выписывает порядковые номера книг в массиве
        int[] booksId=new int[number];
        int counter=0; //счётчик для книг
        for (int i=0; i<schoolBookNumber; i++){
            if (name.equals(schoolBooks[i].getName())) {
                booksId[counter]=i;
                counter++;
            }
        }
        return booksId;
    }
    /*
     * Метод должен удалять книги из массива schoolBooks по названию.
     * Если книг с одинаковым названием в массиве несколько, метод должен удалить их все.
     * <p>
     * Важно: при удалении книги из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 разные книги и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 книгу, метод count() должен вернуть 1.
     * <p>
     * Если хотя бы одна книга была найдена и удалена, метод должен вернуть true, в противном случае,
     * если книга не была найдена, метод должен вернуть false.
     */
    @Override
    public boolean removeByName(String name) {
        SchoolBook[] foundBooks; //массив для хранения найденных книг
        int counter=getBooksNumber(name);
        int[] booksId;
        booksId=getBooksId(name, counter);
        if (counter==0) {
            return false;
        }
        else {
            for (int i : booksId) {
                schoolBooks[booksId[i]]=null;
            }
            for (int i=0; i<schoolBookNumber; i++) {
                if (schoolBooks[i]==null) { //смещаем массив
                    for (int j=i; j<schoolBookNumber; j++){
                        schoolBooks[j]=schoolBooks[j+1];
                        schoolBooks[j+1]=null;
                    }
                }

                }
            for (int i=0; i<booksId.length; i++){
                schoolBookNumber--;
            }
            return true;
        }
    }

    @Override
    public int count() {
        return schoolBookNumber;
    }
}
