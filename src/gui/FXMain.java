/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.stage.StageStyle;


public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        //String filepath ="C:\\Users\\oussama.hadjahmed\\Desktop\\ESPRIT\\PIDEV\\ConnexionBD1cinfo2\\src\\gui"+;
        // Parent root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("tableViewClub.fxml"));

            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            
            //  primaryStage.setTitle("Hello World!");
            // primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, e);

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
