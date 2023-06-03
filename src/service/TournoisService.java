package service;


import entite.Team;
import entite.Tournois;
import entite.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TournoisService implements Iservice<Tournois>{
    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public TournoisService() {
        connexion = DataSource.getInstance().getCnx();

    }

    @Override
    public void insert(Tournois tournois) {
        String query = "INSERT INTO tournois (type, name, dateDebut , dateFin) VALUES (?, ?,?,?)";

        try (PreparedStatement pst = connexion.prepareStatement(query)) {

            pst.setString(1, tournois.getType());
            pst.setString(2, tournois.getName());
            pst.setDate(3, tournois.getDateDebut());
            pst.setDate(4, tournois.getDateFin());

            pst.executeUpdate();
            System.out.println("tournois inserted successfully.");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Tournois> readAll() {
        String requete="select * from tournois";
        List<Tournois> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
                Tournois tournois=new Tournois(
                        rs.getInt("idTournois"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getDate("dateDebut"),
                        rs.getDate("dateFin")
                );
                list.add(tournois);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Tournois readById(int id) {
        String query = "SELECT * FROM tournois WHERE idTournois = ?";

        try {
            pst = connexion.prepareStatement(query);
            pst.setInt(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {
                int idTournois = rs.getInt("idTournois");
                String type = rs.getString("type");
                String name = rs.getString("name");
                Date dateDebut = rs.getDate("dateDebut");
                Date dateFin = rs.getDate("dateFin");

                return new Tournois(idTournois,type,name,dateDebut,dateFin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM tournois WHERE idTournois = ?";

        try {
            pst = connexion.prepareStatement(query);
            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("Tournois deleted successfully.");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Tournois tournois) {
        String query = "UPDATE tournois SET name = ? WHERE id = ?";

        try {
            pst = connexion.prepareStatement(query);
            pst.setString(1, tournois.getName());


            pst.executeUpdate();
            System.out.println("Material updated successfully.");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void registreTournois(Team team,int user1,int user2){
        String query = "INSERT INTO Team (named) VALUES (?)";
        UserService userService = new UserService();


        try (PreparedStatement pst = connexion.prepareStatement(query)) {
            /*List<User> teamList = new ArrayList<>();
            User u1 = userService.readById(user1);
            User u2 = userService.readById(user2)
            teamList.add(u1);
            teamList.add(u2);
            userService.ajouteUserToTeam(u1,);
            team.setTeams(teamList);*/
            pst.setString(1, team.getName());


            pst.executeUpdate();
            System.out.println("team inserted successfully.");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
