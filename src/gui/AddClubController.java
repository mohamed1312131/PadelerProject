/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Club;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.ClubService;

/**
 * FXML Controller class
 *
 * @author oussama.hadjahmed
 */
public class AddClubController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField adresse;

    private boolean update;
    int idClub;
    String query;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addClub(MouseEvent event) {

        try {
            ClubService cs = new ClubService();
            Club club = new Club();
            if (update == false) {
                if (name.getText() != null && adresse.getText() != null) {
                    String nom = name.getText();
                    club.setName(nom);
                    String adr = adresse.getText();
                    club.setAdresse(adr);
                    cs.insert(club);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Champs Vides !");
                    alert.showAndWait();
                }

            } else {
                String nom = name.getText();
                club.setName(nom);
                String adr = adresse.getText();
                club.setAdresse(adr);
                club.setIdClub(idClub);
                cs.update(club);
            }

        } catch (Exception x) {
            Logger.getLogger(AddClubController.class.getName()).log(Level.SEVERE, null, x);
        }
    }

    @FXML
    private void clean(MouseEvent event) {
        name.setText(null);
        adresse.setText(null);
    }

    void setTextField(int id, String nom, String adress) {

        idClub = id;
        name.setText(nom);
        adresse.setText(adress);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}
