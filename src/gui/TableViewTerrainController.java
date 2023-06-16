/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entite.Club;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.TerrainService;
import entite.Terrain;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import service.ClubService;

/**
 * FXML Controller class
 *
 * @author oussama.hadjahmed
 */
public class TableViewTerrainController implements Initializable {

    @FXML
    private TableColumn<Terrain, String> name;
    @FXML
    private TableView<Terrain> terrainsTable;
    @FXML
    private TableColumn<Terrain, Integer> idTerrain;
    @FXML
    private TableColumn<Terrain, Integer> status;
    @FXML
    private TableColumn<Terrain, String> clubName;
    @FXML
    private TableColumn<Terrain, String> editTerrain;
    Terrain terrain = null;
    ObservableList<Terrain> terrainList = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> nomClubInterface;
    private List<Club> clubsInterface = new ArrayList<Club>(0);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
        chargeList();
        List<String> noms = new ArrayList<>();
        noms = clubsInterface.stream().map(Club::getClubName).collect(Collectors.toList());
        nomClubInterface.getItems().add(0, "All");
        if (noms.size() != 0) {
            nomClubInterface.getItems().addAll(noms);
        } else {
            nomClubInterface.getItems().add("Acun Club !");
        }

    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getAddViewTerrain(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addTerrain.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refreshTerrainTable() {
        TerrainService ts = new TerrainService();
        String clubNamess = nomClubInterface.getValue();

        try {

            if (clubNamess != null && clubNamess.length() != 0 && !clubNamess.equals("All")) {
                terrainList.clear();
                System.out.println("clubNamess : " + clubNamess);
                terrainList.addAll(ts.readByClubName(clubNamess));
                terrainsTable.setItems(terrainList);

            } else {
                terrainList.clear();
                terrainList.addAll(ts.readAllWitClubName());
                terrainsTable.setItems(terrainList);

            }

        } catch (Exception e) {
            Logger.getLogger(TableViewTerrainController.class.getName()).log(Level.SEVERE, null, e);

        }

    }

    @FXML
    private void print(MouseEvent event) {
    }

    private void loadDate() {

        refreshTerrainTable();
        TerrainService ts = new TerrainService();

        idTerrain.setCellValueFactory(new PropertyValueFactory<>("idTerrain"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        clubName.setCellValueFactory(new PropertyValueFactory<>("club"));
        System.out.print("test list :"+ terrainList);

        //user.setCellValueFactory(new PropertyValueFactory<>("user"));
        //emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        //add cell of button edit 
        Callback<TableColumn<Terrain, String>, TableCell<Terrain, String>> cellFoctory = (TableColumn<Terrain, String> param) -> {
            // make cell containing buttons
            final TableCell<Terrain, String> cell = new TableCell<Terrain, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {

                                terrain = terrainsTable.getSelectionModel().getSelectedItem();
                                ts.delete(terrain.getIdTerrain());

                                refreshTerrainTable();

                            } catch (Exception ex) {
                                Logger.getLogger(TableViewClubController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            terrain = terrainsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("addTerrain.fxml"));
                            try {
                                loader.load();

                            } catch (IOException ex) {
                                Logger.getLogger(TableViewClubController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AddTerrainController addTerrainController = loader.getController();
                            addTerrainController.setUpdate(true);
                            addTerrainController.setTextField(terrain.getIdTerrain(), terrain.getName(), terrain.getStatus(), terrain.getClub().getClubName());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            refreshTerrainTable();
                            stage.show();

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        editTerrain.setCellFactory(cellFoctory);
        terrainsTable.setItems(terrainList);

    }

    void chargeList() {
        ClubService cd = new ClubService();
        // Club cs = new Club();
        // cs.setName("All");
        clubsInterface = cd.readAll();
        //clubsInterface.add(cs);

    }

    @FXML
    private void getByClubName(MouseEvent event) {
        refreshTerrainTable();

    }

}
