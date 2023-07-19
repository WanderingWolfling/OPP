//  Мы создадим отдельный класс FileHandler и реализуем в нем интерфейс DataHandler, 
//  который будет использоваться в основной программе. Используя интерфейс Serializable, 
//  мы сможем сериализовать и десериализовать данные для записи в файл и чтения из файла.

import java.io.*;
import java.util.ArrayList;
import java.util.List;

interface DataHandler {
    void saveData(List<Person> data, String fileName) throws IOException;

    List<Person> loadData(String fileName) throws IOException, ClassNotFoundException;
}

class FileHandler implements DataHandler {
    @Override
    public void saveData(List<Person> data, String fileName) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
        }
    }

    @Override
    public List<Person> loadData(String fileName) throws IOException, ClassNotFoundException {
        List<Person> data;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            data = (List<Person>) inputStream.readObject();
        }
        return data;
    }
}

class Person implements Serializable {
    private String name;
    private int age;
    private List<Person> children;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public List<Person> getChildren() {
        return children;
    }
}

class FamilyTree {
    private Person root;

    public FamilyTree(Person root) {
        this.root = root;
    }

    public void addChild(Person parent, Person child) {
        parent.addChild(child);
    }

    public List<Person> getAllChildren(Person person) {
        return person.getChildren();
    }
}

public class GenealogyResearchApp {
    public static void main(String[] args) {
        DataHandler dataHandler = new FileHandler();
        String fileName = "genealogy.dat";

        // Создание людей
        Person john = new Person("John", 35);
        Person alice = new Person("Alice", 30);
        Person bob = new Person("Bob", 10);
        Person claire = new Person("Claire", 8);

        // Создание генеалогического древа
        FamilyTree familyTree = new FamilyTree(john);

        // Добавление детей
        familyTree.addChild(john, alice);
        familyTree.addChild(john, bob);
        familyTree.addChild(alice, claire);

        List<Person> familyData = new ArrayList<>();
        familyData.add(john);
        familyData.add(alice);
        familyData.add(bob);
        familyData.add(claire);

        try {
            // Сохранение данных в файл
            dataHandler.saveData(familyData, fileName);

            // Загрузка данных из файла
            List<Person> loadedData = dataHandler.loadData(fileName);

            // Вывод загруженных данных
            System.out.println("Loaded data:");
            for (Person person : loadedData) {
                System.out.println(person.getName() + ", Age: " + person.getAge());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

// В этом обновленном решении мы добавили интерфейс DataHandler, который
// содержит
// методы saveData и loadData для записи и чтения данных из файла. Затем мы
// создали
// класс FileHandler, который реализует интерфейс DataHandler и использует
// ObjectOutputStream для сохранения данных в файл и ObjectInputStream для
// загрузки данных из файла. Класс Person все еще представляет модель человека,
// а класс FamilyTree отвечает за генеалогическое древо.

// В методе main мы создаем некоторые данные для генеалогического древа,
// сохраняем их в файл с помощью FileHandler, а затем загружаем и выводим эти
// данные.