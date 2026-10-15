package kr.ac.mjc.spring;

public class Student {
    private String name;    //학생이름
    private String number;  //학번
    private String department;  //학과

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public void setNumber(int number){
        this.number=Integer.toString(number);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
