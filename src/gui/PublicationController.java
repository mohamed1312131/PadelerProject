/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import utils.DataSource;


import entite.Publication;
import entite.Team;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;
import service.PublicationService;

import java.net.URL;
import javax.swing.Icon;


/**
 * FXML Controller class
 *
 * @author safdh
 */
public class PublicationController implements Initializable {
    @FXML
    private Button addImage_importBtn;

    @FXML
    private Button btnpub;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nom;

    @FXML
    private ImageView pdp;

    @FXML
    private Label prenom;

    @FXML
    private TextArea txtpub;
    @FXML
    private ImageView imported_img;
    private Image image;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void addPublicationImage() {

        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 101, 127, false, true);
            imported_img.setImage(image);
        }
    }

    public void publishPublication() {
        PublicationService publicationService = new PublicationService();
        Publication publication = new Publication(txtpub.getText());
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");
        String imageUrl = new File(uri).toURI().toString();
        publication.setImage(imageUrl);

        // Call the insert method in the PublicationService
        publicationService.insert(publication);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Your Publication has been successfully created!");
        alert.showAndWait();
    }



    /**
     * Initializes the controller class.
     */
}
