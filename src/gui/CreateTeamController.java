package gui;

import entite.Team;
import entite.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.TeamService;
import service.UserService;

public class CreateTeamController {

    @FXML
    private TextField col_email_user1;

    @FXML
    private TextField col_email_user2;

    @FXML
    private TextField col_team_name;
    @FXML
    private Label col_tor_name;
    private boolean update;

    void setUpdate(boolean b) {
        this.update = b;

    }
    void setTextField(String idTournois) {
        col_tor_name.setText(idTournois);
    }
    @FXML
    void save(){
        try {
            TeamService teamService = new TeamService();
            teamService.createTeam(new Team(col_team_name.getText()), col_email_user1.getText(), col_email_user2.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Your team has been successfully created!");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while creating the team. Please try again.");
            alert.showAndWait();
        }
    }
    @FXML
    void save2(){
        TeamService teamService = new TeamService();
        teamService.insert(new Team(col_team_name.getText()));

        Team team = teamService.readByName(col_team_name.getText());

        UserService userService = new UserService();
        User email1 = userService.readByEmail(col_email_user1.getText());

        User email2 = userService.readByEmail(col_email_user2.getText());

        teamService.createTeam2(email1,team);
        teamService.createTeam2(email2,team);
    }

}