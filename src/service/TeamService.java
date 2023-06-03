package service;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entite.Team;
import entite.User;
import entite.UserTeams;
import utils.DataSource;

public class TeamService implements Iservice<Team> {
    public TeamService() {
        connexion = DataSource.getInstance().getCnx();

    }
    private UserService userService = new UserService();
    private Connection connexion;
    private PreparedStatement pst;
    private Statement ste;
    private ResultSet rs;
    @Override
    public void insert(Team team) {
        String requete = "INSERT INTO team (name) VALUES (?)";
        String query = "SELECT LAST_INSERT_ID() AS idTeam";

        try {
            PreparedStatement statement = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, team.getName());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idTeam = generatedKeys.getInt(1);
                team.setIdTeam(idTeam);
            } else {
                System.out.println("Failed to retrieve team ID after insertion.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Team> readAll() {
        String query = "SELECT * FROM team";
        List<Team> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(query);
            while (rs.next()) {
                int idTeam = rs.getInt("idTeam");
                String name = rs.getString("name");


                Team team = new Team(idTeam, name);
                list.add(team);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    @Override
    public Team readById(int id) {
        String query = "SELECT * FROM team WHERE idTeam = ?";

        try {
            pst = connexion.prepareStatement(query);
            pst.setInt(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                int teamId = rs.getInt("id");
                String name = rs.getString("name");

                return new Team(name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Team readByName(String n) {
        String query = "SELECT * FROM team WHERE name = ?";

        try {
            pst = connexion.prepareStatement(query);
            pst.setString(1, n);

            rs = pst.executeQuery();

            if (rs.next()) {
                int idTeam = rs.getInt("idTeam");
                String name = rs.getString("name");

                return new Team(idTeam,name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM team WHERE idTeam = ?";

        try {
            pst = connexion.prepareStatement(query);
            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("Team deleted successfully.");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Team team) {
        String query = "UPDATE team SET name = ? WHERE idTeam = ?";

        try {
            pst = connexion.prepareStatement(query);
            pst.setString(1, team.getName());


            pst.executeUpdate();
            System.out.println("Team updated successfully.");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void createTeam2(User user,Team team){
        String insetUserTeam = "INSERT INTO userTeams (idUser,idTeam,status,dateCreation) VALUES(?,?,?,?)";
        try {
            PreparedStatement insertStatement = connexion.prepareStatement(insetUserTeam, Statement.RETURN_GENERATED_KEYS);
            System.out.println("user = "+user.getIdUser());
            System.out.println("team = "+team.getIdTeam());
            insertStatement.setInt(1, user.getIdUser());
            insertStatement.setInt(2, team.getIdTeam());
            insertStatement.setBoolean(3, false);
            Date date = Date.valueOf(LocalDate.now());
            insertStatement.setDate(4,date);



            int rowsInserted = insertStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Publication publiée avec succès !");
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'insertion de la publication : " + ex.getMessage());
        }
    }
    public void createTeam(Team team, String user1, String user2) {
        String insertQuery = "INSERT INTO team (name) VALUES (?)";
        String insetUserTeam = "INSERT INTO userTeams (idUser,idTeam,status,dateCreation) VALUES(?,?,?,?)";

        try {
            PreparedStatement insertStatement = connexion.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            insertStatement.setString(1, team.getName());
            insertStatement.executeUpdate();

            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idTeam = generatedKeys.getInt(1);
                team.setIdTeam(idTeam);
                List<User> teamList = new ArrayList<>();
                User u1 = userService.readByEmail(user1);
                User u2 = userService.readByEmail(user2);
                teamList.add(u1);
                teamList.add(u2);
                userService.ajouteUserToTeam(u1, idTeam);
                userService.ajouteUserToTeam(u2, idTeam);

                // Set the teamList in the team object
                team.setUsers(teamList);

                System.out.println("Team created successfully with ID: " + idTeam);
            } else {
                System.out.println("Failed to retrieve team ID after insertion.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
