package logic;

public class Client extends Person {
    private String address;

    public Client(String name, String lastname, String id, String phone, int age, String address) {
        super(name, lastname, id, phone, age);
        this.address = address;
    }

    public Client() {
        super();
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    
    public String toString() {
        return super.toString() + " Address: " + address;
    }
}
