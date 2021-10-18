package ua.goit.task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static final String PATH_TO_FILE_TXT = "src/main/resources/Task2/file.txt";
    private static final String PATH_TO_FILE_JSON = "src/main/resources/Task2/user.json";
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        File file = new File(PATH_TO_FILE_TXT);
        List<User> users = new ArrayList<>();

        createListFromFile(file, users);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonUser = gson.toJson(users);
        File jsonFile = new File(PATH_TO_FILE_JSON);

        checkAndCreateNewFile(file, jsonFile);
        writeToJsonFile(jsonUser, jsonFile);

    }

    private static void createListFromFile(File file, List<User> users) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                String[] str = bufferedReader.readLine().split(" ");
                if (str[1].matches("[0-9]+")) {
                    User user = new User();
                    user.setName(str[0]);
                    user.setAge(Integer.parseInt(str[1]));
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void checkAndCreateNewFile(File file, File jsonFile) {
        if (!jsonFile.exists()) {
            jsonFile.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void writeToJsonFile(String jsonUser, File jsonFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(jsonFile))) {
            bufferedWriter.write(jsonUser);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
