import java.util.List;

public class Runner {

    public static void main(String[] args) {

        ObjectContainer<Person> peopleFromWarsaw = new ObjectContainer<>(p -> p.getCity().equals("Warsaw"));

        peopleFromWarsaw.add(new Person("Jan", "Warsaw", 30));
        peopleFromWarsaw.add(new Person("WeronikA","Warsaw", 20));
//        peopleFromWarsaw.add(new Person("Waldek", "Monaco", 34));
        peopleFromWarsaw.add(new Person("MonikA","Warsaw", 60));
//        peopleFromWarsaw.add(null);

        for (Person p : peopleFromWarsaw) {
            System.out.println(p);
        }

        List<Person> females = peopleFromWarsaw.getWithFilter(p -> p.getName().endsWith("a"));

        System.out.println(females);

        females.forEach(System.out::println);

        peopleFromWarsaw.removeIf(p -> p.getAge() > 50);

        for (Person p : peopleFromWarsaw) {
            System.out.println(p);
        }


    }
}
