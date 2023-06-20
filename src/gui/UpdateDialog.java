/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nouha
 */
package gui;


import entite.Reclamation;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ReclamationService;

public class UpdateDialog extends Stage {

    private final TextField ReponseField;
    private final Reclamation reclamation;
    private boolean success;

    public UpdateDialog(Reclamation reclamation) {
        this.reclamation = reclamation;

        setTitle("Update Reclamation");
        initModality(Modality.APPLICATION_MODAL);

        ReponseField = new TextField(reclamation.getReponse());
        Label ReponseLabel = new Label("Reponse:");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            updateReclamation();
            success = true;
            close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
            success = false;
            close();
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(ReponseLabel, 0, 0);
        gridPane.add(ReponseField, 1, 0);
        gridPane.add(saveButton, 0, 9);
        gridPane.add(cancelButton, 1, 9);

        Scene scene = new Scene(gridPane);
        setScene(scene);
    }

    private void updateReclamation() {
        // Update the Reclamation object with the new values
        reclamation.setReponse(ReponseField.getText());
        
        // Perform the database update using the ReclamationService
        ReclamationService reclamationService = new ReclamationService();
        reclamationService.updateR(reclamation);
        reclamationService.updateE(reclamation);
    }

    public boolean isSuccess() {
        return success;
    }
}
