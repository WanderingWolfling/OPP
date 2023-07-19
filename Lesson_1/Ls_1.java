import java.util.ArrayList;
import java.util.List;

class Person {
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

        // Проведение исследования - получение всех детей выбранного человека
        List<Person> children = familyTree.getAllChildren(john);
        System.out.println("Children of John:");
        for (Person child : children) {
            System.out.println(child.getName());
        }
    }
}
