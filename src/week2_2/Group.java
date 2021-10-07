package week2_2;

import java.util.*;
import java.util.stream.Collectors;

public class Group {
    private final ArrayList<Student> students;
    private final String groupNumber;

    public Group(String groupNumber, Student... students){
        this.groupNumber = groupNumber;
        this.students = new ArrayList<>();
        Collections.addAll(this.students, students);
    }

    public Group(String groupNumber, ArrayList<Student> students){
        this.groupNumber = groupNumber;
        this.students = students;
    }

    public String getGroupNumber() {
        return groupNumber;
    }
    public List<Student> getAll(){
        return this.students;
    }
    public void addStudent(Student newStudent){
        this.students.add(newStudent);
    }
    public Student getByNumber(int serialNumber){
        // Stupidly loop
        for (Student student : this.students){
            if (student.getNumber() == serialNumber){
                return student;
            }
        }
        return null;
    }
    public void deleteStudent(Student student){
        int idx = this.students.indexOf(student);
        if (idx >= 0){
            this.students.remove(idx);
        }
    }
    public Map<Integer, Integer> getCountByAge(){
        Map<Integer, Integer> countByAge = new HashMap<>();
        for (Student student : this.students){
            int count = countByAge.containsKey(student.getAge()) ? countByAge.get(student.getAge()) + 1 : 1;
            countByAge.put(student.getAge(), count);
        }
        return countByAge;
    }
    public List<Student> getByMinScore(double score){
        return this.students.stream().filter(x -> x.getAverageScore() >= score).collect(Collectors.toList());
    }
    public void printInfo(){
        System.out.println("Group #" + this.groupNumber);
        System.out.println("Number of students: " + this.students.size());
        System.out.println("Average score: " + this.getAverageScore());
        System.out.println();
    }
    public double getAverageScore(){
        return this.students.stream().mapToDouble(Student::getAverageScore).sum() / this.students.size();
    }
}
