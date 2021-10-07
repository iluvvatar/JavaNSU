package week4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileService {
    static final String fileName = "accounts.csv";
    static FileService instance = null;

    private FileService() {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileService getInstance(){
        if (FileService.instance == null){
            FileService.instance = new FileService();
        }
        return FileService.instance;
    }

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            stream.forEach(line -> accounts.add(Account.fromCsv(line.trim())));
            return accounts;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addLine(String line) {
        try {
            try (BufferedWriter output = new BufferedWriter(new FileWriter(fileName, true))) {
                output.append(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeLine(String line) {
        File file = new File(fileName);
        File tmpFile = new File("tmp.csv");
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            try {
                tmpFile.createNewFile();
                reader = new BufferedReader(new FileReader(file));
                writer = new BufferedWriter(new FileWriter(tmpFile));

                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    currentLine = currentLine.trim();
                    if (currentLine.equals(line)) continue;
                    writer.write(currentLine + "\n");
                }
            }
            finally {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tmpFile.renameTo(file);
    }
}
