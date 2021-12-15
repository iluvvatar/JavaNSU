package app;

import app.entities.Group;
import app.entities.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.*;
import java.util.ArrayList;

public class CsvReader {
    static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static ArrayList<Student> readStudents(File file, Group group){
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Student student = new Student();
                student.setGroup(group);
                student.setFirstName(values[1]);
                student.setSecondName(values[2]);
                student.setLastName(values[0]);
                student.setBirthdayDate(dateFormat.parse(values[3]));
                students.add(student);
                System.out.println("readStudents " + student.getGroup());
            }
        }
        catch (IOException | ParseException e){
            e.printStackTrace();
            System.exit(1);
        }
        return students;
    }
}
