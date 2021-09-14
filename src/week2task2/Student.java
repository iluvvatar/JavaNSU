package week2task2;

public class Student {
    public int number;
    public String name;
    public String lastName;
    public int age;
    public double averageScore;

    public void printInfo(Student student){
        System.out.println("Student " + name + " " + lastName);
        System.out.println("Number: #" + number);
        System.out.println("Age: " + age);
        System.out.println("Average score: " + averageScore);
    }
}
