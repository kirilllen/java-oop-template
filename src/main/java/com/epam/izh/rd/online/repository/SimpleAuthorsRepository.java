package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorsRepository implements AuthorRepository{
    public static int authorNumber=0; //счётчик количества авторов
    Author[] authors=new Author[100_000]; //по ТЗ нельзя использовать ArrayList (коллекции), примем максимальное количество авторов 100_000, т.к. размер массива в ходе выполнения программы менять нельзя

    @Override
    public boolean save(Author author) {
        Author foundAuthor; //поле для найденного автора
        /*
          Метод должен сохранять автора в массив authors.
          Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
          То есть он ровно того размера, сколько сущностей мы в него сохранили.
          <p>
          Если на вход для сохранения приходит автор, который уже есть в массиве (сохранен), то автор не сохраняется и
          метод возвращает false.
          <p>
          Можно сравнивать только по полному имени (имя и фамилия), считаем, что двух авторов
          с одинаковыми именем и фамилией быть не может.
          Подсказка - можно использовать для проверки метод findByFullName.
          <p>
          Если сохранение прошло успешно, метод должен вернуть true.
         */
        foundAuthor=findByFullName(author.getName(), author.getLastName());
        if (foundAuthor!=null) {
            return false;
        }
        else {
            authors[authorNumber]=author;
            authorNumber++;
            return true;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        int authorId=0; //переменная для возвращения автора
        boolean found=false; //переменная для обозначения того, что мы нашли автора
        Author author;
        /*
          Метод должен находить в массиве authors автора по имени и фамилии (считаем, что двух авторов
          с одинаковыми именем и фамилией быть не может.)
          <p>
          Если автор с таким именем и фамилией найден - возвращаем его, если же не найден, метод должен вернуть null.
         */
        for (int i=0; i<authorNumber; i++){
            if (lastname.equals(authors[i].getLastName())) {
                if (name.equals(authors[i].getName())){
                    authorId=i;
                    found=true;
                }
            }
        }
        author= (found) ? authors[authorId] : null;
        return author;
    }

    @Override
    public boolean remove(Author author) {
        Author foundAuthor; //переменная найденного автора
        boolean found=false;
        int authorId;
        /*
          Метод должен удалять автора из массива authors, если он там имеется.
          Автора опять же, можно определять только по совпадению имении и фамилии.
          <p>
          Важно: при удалении автора из массива размер массива должен уменьшиться!
          То есть, если мы сохранили 2 авторов и вызвали count() (метод ниже), то он должен вернуть 2.
          Если после этого мы удалили 1 автора, метод count() должен вернуть 1.
          <p>
          Если автор был найден и удален, метод должен вернуть true, в противном случае, если автор не был найден, метод
          должен вернуть false.
         */
        foundAuthor=findByFullName(author.getName(), author.getLastName());
        if (foundAuthor==null) {
            return false;
        }
        else {
            //определим порядковый номер автора
            authorId=getAuthorId(foundAuthor);
            authors[authorId]=null;
            authorNumber--; //уменьшаем количество авторов на одного
            for (int i=authorId; i<authorNumber; i++){  //смещение массива (удаление лишнего элемента)
                authors[i]=authors[i+1];
                authors[i+1]=null;
            }
            return true;
        }
    }

    // метод для определения id автора
    private int getAuthorId(Author author){
        boolean found=false;
        int foundId=-1;
        for (int i=0; i<authorNumber; i++){
            if (author.getLastName().equals(authors[i].getLastName())) {
                if (author.getName().equals(authors[i].getName())) {
                    foundId=i;
                    found=true;
                }
            }

        }
        if (found) {
            return foundId;
        }
        else {
            return -1; //если не находим, возвращаем -1
        }
    }

    @Override
    public int count() {
        /*
          Метод возвращает количество сохраненных сущностей в массиве authors.
         */
        return authorNumber;
    }
}
