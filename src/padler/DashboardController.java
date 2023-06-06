/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padler;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 *
 * @author Esprit
 */
public class DashboardController implements Initializable{
    
     @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button home_btn;

    @FXML
    private Button adduser_btn;

    @FXML
    private Button padler_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totaluser;

    @FXML
    private Label home_totalpresent;

    @FXML
    private Label home_totalinactive;

    @FXML
    private BarChart<?, ?> home_chart;

    @FXML
    private AnchorPane adduser_form;

    @FXML
    private TableView<userData> adduser_col_tableView;

    @FXML
    private TableColumn<userData, Integer> adduser_col_userID;

    @FXML
    private TableColumn<userData, String> adduser_col_firstName;

    @FXML
    private TableColumn<userData, String> adduser_col_lastName;

    @FXML
    private TableColumn<userData, String> adduser_col_gender;

    
    @FXML
    private TableColumn<userData, String> adduser_col_phoneNum;

    @FXML
    private TableColumn<userData, String> adduser_col_date;

    @FXML
    private TextField adduser_search;

    @FXML
    private TextField adduser_userID;

    @FXML
    private TextField adduser_firstName;

    @FXML
    private TextField adduser_lastName;

    @FXML
    private ComboBox<?> adduser_gender;

    @FXML
    private TextField adduser_phoneNum;

    @FXML
    private ComboBox<?> adduser_position;

    @FXML
    private Button adduser_importbtn;

    @FXML
    private ImageView adduser_col_image;

    @FXML
    private Button adduser_addbtn;

    @FXML
    private Button adduser_updatebtn;

    @FXML
    private Button adduser_clearbtn;

    @FXML
    private Button adduser_deletebtn;

    @FXML
    private AnchorPane padler_form;

    @FXML
    private TextField padler_id;

    @FXML
    private Label padler_fitstname;

    @FXML
    private Label padler_lastname;

    @FXML
    private Label padler_position;

    @FXML
    private Button padler_updateBtn;

    @FXML
    private Button padler_clearBtn;

    @FXML
    private TableView<userData> padler_table;

    @FXML
    private TableColumn<?, ?> padler_col_userID;

    @FXML
    private TableColumn<?, ?> padler_col_firstname;

    @FXML
    private TableColumn<?, ?> padler_col_lastname;

    @FXML
    private TableColumn<?, ?> padler_col_position;
    
    
    
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    private Blob imageBlob;
    private Image image;
    
    
    public void addUserAdd() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO user "
                + "(idUser,firstName,lastName,gender,numTel,image,date) "
                + "VALUES(?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;
            if (adduser_userID.getText().isEmpty()
                    || adduser_firstName.getText().isEmpty()
                    || adduser_lastName.getText().isEmpty()
                    || adduser_gender.getSelectionModel().getSelectedItem() == null
                    || adduser_phoneNum.getText().isEmpty()
                   // || getData.path == null || getData.path == ""
                    ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT idUser FROM user WHERE idUser = '"
                        + adduser_userID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User ID: " + adduser_userID.getText() + " was already exist!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, adduser_userID.getText());
                    prepare.setString(2, adduser_firstName.getText());
                    prepare.setString(3, adduser_lastName.getText());
                    prepare.setString(4, (String) adduser_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, adduser_phoneNum.getText());
                    //prepare.setString(6, (String) adduser_position.getSelectionModel().getSelectedItem());

                   /* String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
*/
                   
                    System.out.println("string blob:"+imageBlob.toString());
                    prepare.setBlob(6, imageBlob);
                    prepare.setString(7, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    addUserShowListData();
                    addUserReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private String[] positionList = {"Admin", "Simple user"};

    public void adduserPositionList() {
        List<String> listP = new ArrayList<>();

        for (String data : positionList) {
            listP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listP);
        adduser_position.setItems(listData);
    }
    
    private String[] listGender = {"Male", "Female", "Others"};

    public void addUserGendernList() {
        List<String> listG = new ArrayList<>();

        for (String data : listGender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        adduser_gender.setItems(listData);
    }
    
    public void addUserReset() {
        adduser_userID.setText("");
        adduser_firstName.setText("");
        adduser_lastName.setText("");
        adduser_gender.getSelectionModel().clearSelection();
        //addEmployee_position.getSelectionModel().clearSelection();
        adduser_phoneNum.setText("");
        adduser_col_image.setImage(null);
        getData.path = "";
    }
    
    
    
    
    public static Blob convertToBlob(File file) throws IOException, SQLException {
        Image image = new Image(file.toURI().toString());
        java.awt.Image awtImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            return new javax.sql.rowset.serial.SerialBlob(imageBytes);
        } finally {
            outputStream.close();
        }
    

    }
    public Image convertBlobToImage(Blob blob) throws SQLException, IOException {
    byte[] imageData = blob.getBytes(1, (int) blob.length());

    ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
    BufferedImage bufferedImage = ImageIO.read(inputStream);

    return SwingFXUtils.toFXImage(bufferedImage, null);
}

    public void addUserInsertImage() throws IOException, SQLException {

        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());
    
        if (file != null) {
         //   getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 101, 127, false, true);
            adduser_col_image.setImage(image);
                    Blob blob = convertToBlob(file);
        
           imageBlob=blob;
        }
    
    }
    public ObservableList<userData> addUserListData() {

        ObservableList<userData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            userData userD;

            while (result.next()) {
                userD = new userData(result.getInt("idUser"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getString("numTel"),
                        result.getString("image"),
                        result.getDate("date"));
                listData.add(userD);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    private ObservableList<userData> addUserList;

    public void addUserShowListData() {
        addUserList = addUserListData();

        adduser_col_userID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        adduser_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        adduser_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        adduser_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        adduser_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        adduser_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        adduser_col_tableView.setItems(addUserList);

    }
    
    public void addUserSelect() throws SQLException, IOException {
        userData userD = adduser_col_tableView.getSelectionModel().getSelectedItem();
        int num = adduser_col_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        adduser_userID.setText(String.valueOf(userD.getUser_id()));
        adduser_firstName.setText(userD.getFirstName());
        adduser_lastName.setText(userD.getLastName());
        adduser_phoneNum.setText(userD.getNumTel());

        getData.path = userD.getImage();

        String uri = "file:" + userD.getImage();

        
        Image imageB = convertBlobToImage(imageBlob);

        
//        image = new Image(uri, 101, 127, false, true);
        adduser_col_image.setImage(imageB);
    }
    
    
    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            adduser_form.setVisible(false);
            padler_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            adduser_btn.setStyle("-fx-background-color:transparent");
            padler_btn.setStyle("-fx-background-color:transparent");

            //homeTotaluser();
            //homeEmployeeTotalPresent();
            //homeTotalInactive();
            //homeChart();

        } else if (event.getSource() == adduser_btn) {
            home_form.setVisible(false);
            adduser_form.setVisible(true);
            padler_form.setVisible(false);

            adduser_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            home_btn.setStyle("-fx-background-color:transparent");
            padler_btn.setStyle("-fx-background-color:transparent");

            addUserGendernList();
            //addEmployeePositionList();
            //addEmployeeSearch();

        } else if (event.getSource() == padler_btn) {
            home_form.setVisible(false);
            adduser_form.setVisible(false);
            padler_form.setVisible(true);

            padler_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            adduser_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

            //salaryShowListData();

        }

    }
    
    private double x = 0;
    private double y = 0;

    public void logout() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void displayUsername() {
        username.setText(getData.username);
    }
    
    public void close() {
        System.exit(0);
    }
    
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addUserShowListData();
        addUserGendernList();
    }

  
    
}
