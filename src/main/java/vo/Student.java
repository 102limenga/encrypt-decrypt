package vo;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;

    private String sex = "female";

    private String height = "210";

    private int age = 11;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, String sex, String height, int age) {
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", height='" + height + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
