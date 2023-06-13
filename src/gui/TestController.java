package gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {
    @FXML
    private VBox mainContainer;

    @FXML
    private AnchorPane sub1Container;

    @FXML
    private AnchorPane sub2Container;

    private void loadSubFXML(String fxmlPath, AnchorPane container) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane subFXML = loader.load();
            container.getChildren().setAll(subFXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSubFXML("Sub1.fxml", sub1Container);
        loadSubFXML("Sub2.fxml", sub2Container);
    }
}
