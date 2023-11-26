package cemsystemjava;

public class User {
    
    private static int ID = 1000;
    
    private int id;
    private String name;
    private int age;
    private String password;


    public User(String name, int age, String password) {
        this.id = ID++;
        this.name = name;
        this.age = age;
        this.password = password;
    }
        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
