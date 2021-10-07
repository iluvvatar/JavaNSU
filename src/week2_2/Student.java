package week2_2;

public class Student {
    private int number;
    private String name;
    private String lastName;
    private int age;
    private double averageScore;

    public Student(int number, String name, String lastName, int age, double averageScore){
        this.setNumber(number);
        this.setName(name);
        this.setLastName(lastName);
        this.setAge(age);
        this.setAverageScore(averageScore);
    }

    public void printInfo(){
        System.out.println("Student " + this.name + " " + this.lastName);
        System.out.println("Number: #" + this.number);
        System.out.println("Age: " + this.age);
        System.out.println("Average score: " + this.averageScore);
        System.out.println();
    }
    public int getNumber(){
        return this.number;
    }
    public String getName(){
        return this.name;
    }
    public String getLastName(){
        return this.lastName;
    }
    public int getAge(){
        return this.age;
    }
    public double getAverageScore(){
        return this.averageScore;
    }

    public void setNumber(int number){
        assert number > 0;
        this.number = number;
    }
    public void setAge(int age) {
        assert age >= 0;
        this.age = age;
    }
    public void setAverageScore(double averageScore) {
        assert averageScore >= 0;
        this.averageScore = averageScore;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setName(String name) {
        this.name = name;
    }
}
