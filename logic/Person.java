package logic;

public class Person {
    private String name;
    private String lastname;
    private String id;
    private String phone;
    private int age;

    public Person(String name, String lastname, String id, String phone, int age) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
        this.phone = phone;
        this.age = age;
    }

    public Person() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String toString() {
        return name + " " + lastname + " (ID: " + id + ", Phone: " + phone + ", Age: " + age + ")";
    }
}
