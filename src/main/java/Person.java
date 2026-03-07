import java.util.Objects;

public class Person {

    private String name;
    private String city;
    private int age;

    public Person(String city) {
        this.city = city;
    }

    public Person(String name, String city, int age) {
        this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        this.city = city.substring(0,1).toUpperCase() + city.substring(1).toLowerCase();
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person peron = (Person) o;
        return age == peron.age && Objects.equals(name, peron.name) && Objects.equals(city, peron.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

}
