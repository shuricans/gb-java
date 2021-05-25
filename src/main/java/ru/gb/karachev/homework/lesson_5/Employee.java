package ru.gb.karachev.homework.lesson_5;

public class Employee {

    private String fullName;
    private String position;
    private String email;
    private String phone;
    private Integer salary;
    private Integer age;

    private Employee() {
    }

    public Employee(String fullName, String position, String email, String phone, Integer salary, Integer age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + fullName + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", salary='" + salary + '\'' +
                ", age=" + age +
                '}';
    }
}
