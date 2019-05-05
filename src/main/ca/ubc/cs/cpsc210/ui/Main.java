package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.model.exceptions.InvalidName;
import ca.ubc.cs.cpsc210.model.exceptions.InvalidMeasurement;
import ca.ubc.cs.cpsc210.model.reminders.Project;
import ca.ubc.cs.cpsc210.model.reminders.Task;
import ca.ubc.cs.cpsc210.model.userdata.userprofile.UserProfile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import ca.ubc.cs.cpsc210.parsers.*;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ca.ubc.cs.cpsc210.parsers.UserProfileParser.parse;
import static ca.ubc.cs.cpsc210.persistence.JsonForHealthCare.usertojson;
import static ca.ubc.cs.cpsc210.persistence.JsonForReminders.tasklisttojson;

public class Main extends Application {
    private static UserProfile user;
    private static Project project;


    public static void main(String[] args) {
        launch(args);
        JSONArray users = new JSONArray();
        users.put(usertojson(user));
        readUserDoc(users);

        JSONArray tasks = new JSONArray();
        tasks.put(tasklisttojson((ArrayList<Task>) project.getTasks()));
        readProjDoc(tasks);

    }


    //EFFECTS: reads json file of for user profile
    public static void readUserDoc(JSONArray users) {
        try {
            System.out.println(new FileReader("medapp.json").toString());
            user = parse(readFile("medapp.json",Charset.defaultCharset()));
            System.out.println("read file");
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (InvalidMeasurement invalidMeasurement) {
            invalidMeasurement.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: reads json file of for project
    public static void readProjDoc(JSONArray projs) {
        try {
            System.out.println(new FileReader("proj.json").toString());
            List<Task> tasks = RemindersParser.parse((readFile("proj.json", Charset.defaultCharset())));
            for (Task t: tasks) {
                project.add(t);
            }
            System.out.println("read file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //EFFECTS: starts scene
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("newmeddapp.fxml"));
        primaryStage.setTitle("my eHealth Record");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();
    }

    //EFFECTS: reads specific file
    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    //EFFECTS: gets main user
    public static UserProfile getUser() {
        return user;
    }

    //EFFECTS: sets main user
    public static void setUser(UserProfile user) {
        Main.user = user;
    }

    //EFFECTS: gets main projects
    public static Project getProject() {
        return project;
    }

    //EFFECTS: sets main project
    public static void setProject(Project project) {
        Main.project = project;
    }

}
