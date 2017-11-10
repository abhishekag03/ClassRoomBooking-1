package Main;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

public class StudentController implements Initializable {
    private Student myStudent;
    public void Start(Student student) {
        myStudent = student;
    }
    @FXML
    private AnchorPane studentPane;

    @FXML
    void displayCourses(ActionEvent event) throws IOException {
        ArrayList<Course> myCourses = myStudent.viewCourses();
        myCourses.add(new Course("ama","ajja","jaja","mamama",4,null,null,null,null));
        System.out.println("sizeeee ::::::   " + myCourses.size());


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CourseTable.fxml"));
        Pane newstudentPane = fxmlLoader.load();
        Control newcontroller = fxmlLoader.<Control>getController();
        //newcontroller.Test("Super");
        newcontroller.Start(myCourses);


        studentPane.getChildren().add(newstudentPane);
//        tableView.getColumns().add(0,"monday");
    }

    @FXML
    void roomBooking(ActionEvent event) throws IOException {
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("RoomBooking.fxml"));
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);
    }

    @FXML
    void viewTimeTable(ActionEvent event) throws IOException {
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("TimeTable.fxml"));
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);
    }

    @FXML
    void searchCoursesStudent(ActionEvent event) throws IOException{


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchCourses.fxml"));
        Pane newstudentPane = fxmlLoader.load();
        SearchCoursesController newcontroller = fxmlLoader.<SearchCoursesController>getController();
        newcontroller.Start(myStudent);
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);

    }


    void Test(String s) {
        System.out.println(s + " chalaaa");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}