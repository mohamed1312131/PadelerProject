/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;

import entite.Publication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import service.PublicationService;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class ResultatController implements Initializable {

    @FXML
    private TextField comment;

    @FXML
    private Button commenter;

    @FXML
    private TextArea description;

    @FXML
    private ImageView image;

    @FXML
    private Button like;

    @FXML
    private AnchorPane pub;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PublicationService publicationService = new PublicationService();

        // Assuming you have the publication ID available
        int publicationId = 1; // Replace with the actual ID

        // Call the readById method to fetch the publication details
        Publication publication = publicationService.readById(publicationId);
        description.setText(publication.getDescription());
        image.setImage(new Image(publication.getImage()));

    }    


    
}
