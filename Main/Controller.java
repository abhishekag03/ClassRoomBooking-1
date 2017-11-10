package Main;

//import Main.MainPage.clgobj;
import static Main.MainPage.clgobj;
import static Main.initializer.serialize;
import java.io.*;
import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class Controller implements Initializable {
//    @FXML 
//    ToggleGroup Users;
//  

    @FXML
    private TableView<Course> mytable;

    @FXML
    private TextField username;

    @FXML
    private AnchorPane studentPane;

    @FXML
    private AnchorPane facultyPane;

    @FXML
    private AnchorPane adminPane;

    @FXML
    private TextField pass;

    @FXML
    private Button requestbtn;

    @FXML
    private Button LogIn;

    @FXML
    private TableColumn<Course, String> tc1;
    @FXML

    private TableColumn<Course, String> tc2;
    @FXML

    private TableColumn<Course, String> tc3;

    @FXML
    private TableColumn<Course, String> tc4;

//    @FXML javafx.scene.control.TableColumn tc1;
//    @FXML javafx.scene.control.TableColumn tc2;
//    @FXML javafx.scene.control.TableColumn tc3;
//    @FXML javafx.scene.control.TableColumn tc4;
//    @FXML
//    private TableColumn<?, ?> tc1;
    @FXML
    private TextField name;

    @FXML
    private TextField emailId;

    @FXML
    private PasswordField password;

    @FXML
    private ToggleGroup Users;

    @FXML
    private Button SignUp;

    @FXML
    private Label loginlabel;

    @FXML
    private Label signuplabel;

    @FXML
    private TableView tableView;
//
//    @FXML
//    private TextField date;
//
//    @FXML
//    private TextField year;
//
//    @FXML
//    private TextField month;
//
//    @FXML
//    private TextField timeFrom;
//
//    @FXML
//    private TextField timeTo;
//
//    @FXML
//    private TextArea Reason;

    @FXML
    void SignUp(ActionEvent event) throws IOException, ClassNotFoundException {
//        College clgobj = deserialize("data");
        clgobj = College.deserialize("data");
        String user_name = name.getText();
        String email = emailId.getText();
        String passwd = password.getText();
        RadioButton selectedRadioButton = (RadioButton) Users.getSelectedToggle();
        String type = selectedRadioButton.getText();
//        String type = Users.getSelectedToggle().toString();
//        System.out.println("tt= "+ toogleGroupValue);
        name.setText(null);
        emailId.setText(null);
        password.setText(null);
//        System.out.println(u);
        int val = clgobj.SignUp(user_name, email, passwd, type);
        if (val == 0) {
            signuplabel.setText("User already exists");
        } else {
            signuplabel.setText("Sign up succesful");

        }
        System.out.println(type);
        College.serialize(clgobj);
    }

    public static void serializeArray(ArrayList<Requests> al) throws IOException {
        ObjectOutputStream out = null;
        try {
//			System.out.println(p.getName());x` 
            out = new ObjectOutputStream(new FileOutputStream("./src/" + "list" + ".txt"));
            System.out.println("created");
            out.writeObject(al);
        } finally {
            out.close();
        }
    }

    public static ArrayList<Requests> deserializeArray() throws IOException, ClassNotFoundException {
        System.out.println("deserializing");
        ObjectInputStream in = null;
        try {

            in = new ObjectInputStream(new FileInputStream("./src/" + "list" + ".txt"));
            ArrayList<Requests> obj = (ArrayList<Requests>) in.readObject();
            //			in.readObject();
            return obj;
        } finally {
            in.close();
        }
    }

    @FXML
    void userlogin(ActionEvent event) throws IOException, ClassNotFoundException {
        clgobj = College.deserialize("data");

        String email = username.getText();
        String passwd = pass.getText();
        username.setText(null);
        pass.setText(null);
//        College clgobj = new College();
        System.out.println("hello");

        Parent root2;

        int num = clgobj.Login(email, passwd);
        if (num == 1) {
            loginlabel.setText("successful");
//            loginlabel.setText("hello1");
            User obj = clgobj.getAllUsersMap().get(email);

            if (clgobj.getAllUsersMap().get(email).getType().equals("Student")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StdntPage.fxml"));
                root2 = (Parent) fxmlLoader.load();
//                root2 = FXMLLoader.load(getClass().getResource("StdntPage.fxml"));
                StudentController newcontroller = fxmlLoader.getController();
                newcontroller.Test("Super");
                newcontroller.Start((Student) obj);

            } else if (clgobj.getAllUsersMap().get(email).getType().equals("Admin")) {
//                root2 = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
                root2 = (Parent) fxmlLoader.load();
//                root2 = FXMLLoader.load(getClass().getResource("StdntPage.fxml"));
                AdminController newcontroller = fxmlLoader.<AdminController>getController();
//                newcontroller.Test("Super");
                newcontroller.Start((Admin) obj);

            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FacultyPage.fxml"));
                root2 = (Parent) fxmlLoader.load();
//                root2 = FXMLLoader.load(getClass().getResource("StdntPage.fxml"));
                FacultyController newcontroller = fxmlLoader.<FacultyController>getController();
//                newcontroller.Test("Super");
                newcontroller.Start((Faculty) obj);

//                root2 = FXMLLoader.load(getClass().getResource("FacultyPage.fxml"));
            }

            Scene scene = new Scene(root2);
            Stage stage = (Stage) LogIn.getScene().getWindow();
            stage.setScene(scene);


            stage.show();
//            loginlabel.setText("hello");
        } else {
            loginlabel.setText("unsuccessful");
        }
    }

//    @FXML
//    void displayCourses(ActionEvent event) throws IOException{
//        Pane newstudentPane = FXMLLoader.load(getClass().getResource("Table.fxml"));
//        studentPane.getChildren().add(newstudentPane);
//        tableView.getColumns().add(0,"monday");
//    }
    @FXML
    void viewTimeTable(ActionEvent event) throws IOException {
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("TimeTable.fxml"));
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);
    }





//     @FXML
//    void roomBooking(ActionEvent event) throws IOException{
//        Pane newstudentPane = FXMLLoader.load(getClass().getResource("RoomBooking.fxml"));
//        studentPane.getChildren().clear();
//        studentPane.getChildren().add(newstudentPane);
//    }

//    @FXML
//    void makeRequest(ActionEvent event) throws IOException, ClassNotFoundException {
//        System.out.println("make request pressed");
//        String date1 = date.getText();
//        String mnth = month.getText();
//        String year1 = year.getText();
//        String from_time = timeFrom.getText();
//        String to_time = timeTo.getText();
//        String reason = Reason.getText();
//        month.setText(null);
//        date.setText(null);
//        year.setText(null);
//        timeFrom.setText(null);
//        timeTo.setText(null);
//        Reason.setText(null);
////        Admin.
//
//        ArrayList<Requests> arr = new ArrayList<Requests>();
//        arr = deserializeArray();
//        Admin.setList(arr);
//        Requests myreq = new Requests(date1, from_time, to_time, reason, "Student");
//        Admin.addRequest(myreq);
////        System.out.println("new created abhishek");
//
//        ArrayList<Requests> allreq = new ArrayList<Requests>();
//        allreq.add(myreq);
////        College.serialize();
//        System.out.println(myreq);
//
//        serializeArray(arr);
////        clgobj.
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}