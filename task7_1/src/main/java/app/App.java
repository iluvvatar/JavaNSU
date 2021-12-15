package app;

import app.entities.Group;
import app.entities.Student;
import app.repositories.GroupRepository;
import app.repositories.StudentRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;

public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Required two arguments");
            System.exit(2);
        }
        String command = args[0];
        String folderPath = args[1];

        if (!command.equals("updateDB")) {
            System.out.println("Unknown command");
            System.exit(2);
        }

        configureStdOut();

        File folder = new File(folderPath);
        for (File file : folder.listFiles()) {

            Group group = new Group();
            group.setName(file.getName().split("\\.")[0]);
            GroupRepository.save(group);

            ArrayList<Student> students = CsvReader.readStudents(file, group);
            StudentRepository.saveAll(students);

            System.out.println(group);
            System.out.println(group.getStudents());
            System.out.println(students.get(0));
        }
    }

    private static void configureStdOut(){
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
    }

}
