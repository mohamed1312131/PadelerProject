package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import entite.Reclamation;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import org.controlsfx.control.Notifications;
import service.ReclamationService;

public class AddReclamationController implements Initializable {

   
    @FXML
    private TextArea Description;

    @FXML
    private Button BTN_create;
    @FXML
    private ChoiceBox<String> selectedCategorie;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Obtenir la liste des catégories depuis votre service
        List<String> categories = getCategoriesFromService();

        // Ajouter les catégories à la ChoiceBox
        selectedCategorie.getItems().addAll(categories);
        
        BTN_create.setOnAction(event -> {
            createReclamation();
            
            
        });
    }
     // Méthode pour obtenir la liste des catégories depuis votre service
    private List<String> getCategoriesFromService() {
        // Logique pour obtenir les catégories depuis votre service
        // Remplacez cette partie avec votre propre logique
        List<String> categories = new ArrayList<>();
        categories.add("Club");
        categories.add("Terrain");
        categories.add("Matériel");
        return categories;
    }
    public void createReclamation() {
    String descriptionText = Description.getText();
    String categorie = selectedCategorie.getValue(); // Récupérer la valeur sélectionnée dans la ChoiceBox

    if (categorie == null || categorie.isEmpty() || descriptionText.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champs manquants");
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }

  try {
    Reclamation reclamation = new Reclamation(descriptionText);
    reclamation.setCategorie(categorie); // Définir la catégorie de la réclamation

    ReclamationService reclamationService = new ReclamationService();
    reclamationService.insert(reclamation);

    // Clear input fields

    // Show success notification
    Notifications.create()
        .title("Succès")
        .text("La réclamation a été créée avec succès.")
        .position(Pos.CENTER)
        .showInformation();
} catch (NumberFormatException e) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Valeur invalide");

    alert.showAndWait();
}
}
}