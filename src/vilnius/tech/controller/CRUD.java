package vilnius.tech.controller;

import java.util.List;
import java.util.Scanner;

public interface CRUD<T> {

    T create(Scanner scanner);
    T read(Scanner scanner, boolean create);
    T read(Scanner scanner);
    List<T> readAll();
    T update(Scanner scanner);
    void delete(Scanner scanner);
}
