package gui;

import entite.Club;
import entite.Tournois;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Date;

public class CreationTournoisController {

    @FXML
    private ComboBox<Club> addT_club;

    @FXML
    private DatePicker addT_dateD;

    @FXML
    private DatePicker addT_dateFin;

    @FXML
    private TextField addT_id;

    @FXML
    private TextField addT_name;

    @FXML
    private TextField addT_type;

    @FXML
    private Button adduser_addbtn;

    @FXML
    private TableView<Tournois> adduser_col_tableView;

    @FXML
    private TextField adduser_search;

    @FXML
    private Button adduser_updatebtn;

    @FXML
    private AnchorPane arche;

    @FXML
    private TableColumn<Tournois, String> club;

    @FXML
    private TableColumn<Tournois, Date> dateDebut;

    @FXML
    private TableColumn<Tournois, Date> dateFin;

    @FXML
    private TableColumn<Tournois, String> name_tournois;

    @FXML
    private TableColumn<Tournois, Integer> participants;

    @FXML
    private TableColumn<Tournois, Integer> tournois_id;

    @FXML
    private TableColumn<Tournois, String> type;



}
