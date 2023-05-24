/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author oussama.hadjahmed
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException  {
        
          //String filepath ="C:\\Users\\oussama.hadjahmed\\Desktop\\ESPRIT\\PIDEV\\ConnexionBD1cinfo2\\src\\gui"+;
 
        Parent root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));
        Scene scene = new Scene(root);      
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
