package week2task2;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student(1, "Ilya", "Malakhov", 0, 4.9);
        Student student2 = new Student(2, "Napoleon", "Bonaparte", 40, 4.8);
        Student student3 = new Student(3, "Jean", "Lannes", 40, 4.2);
        Student student4 = new Student(4, "Serge", "Dur-Dachnik", 1000000000, 3.5);
        Student student5 = new Student(5, "Alexander III of Macedon", "The Great", 33, 4.5);
        Student student6 = new Student(6, "Gaius", "Julius Caesar", 55, 4.5);
        Student student7 = new Student(7, "Isaac", "Newton", 84, 4.5);
        Student student8 = new Student(8, "Albert", "Einstein", 76, 4.85);
        Student student9 = new Student(9, "Aristotle", "", 61, 4.2);
        Student student10 = new Student(10, "Mikhail", "Gorshenev", 0, 2.5);

        Group group1 = new Group("228", student1, student2, student3, student8);
        group1.addStudent(student10);
        Group group2 = new Group("148", student6, student4, student5, student7);
        group2.addStudent(student9);

        student1.printInfo();
        student8.printInfo();

        group1.printInfo();
        group2.printInfo();

        System.out.println("group1.getCountByAge():");
        System.out.println(group1.getCountByAge());
        System.out.println("group2.getCountByAge():");
        System.out.println(group2.getCountByAge());
    }
}
