/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Club;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author oussama.hadjahmed
 */
public class ClubService implements Iservice<Club> {

    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ClubService() {
        connexion = DataSource.getInstance().getCnx();

    }

    @Override
    public void insert(Club c) {
        String requete = "insert into Club (name,adresse) values('" + c.getName() + "','" + c.getAdresse() + "')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Club> readAll() {
        String requete = "select * from club";
        List<Club> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                Club c = new Club(rs.getInt("idClub"), rs.getString("name"), rs.getString("adresse"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Club readById(int id) {
        Club c = new Club();

        try {

            String requete = "SELECT * FROM club WHERE idClub=" + Integer.toString(id);
            pst = connexion.prepareStatement(requete);
            rs = pst.executeQuery(requete);

            while (rs.next()) {
                c.setIdClub(rs.getInt("idClub"));
                c.setName(rs.getString("name"));
                c.setAdresse(rs.getString("adresse"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public void delete(int id) {
        try {
            String requete = "DELETE FROM club WHERE idClub = ?";
            pst = connexion.prepareStatement(requete);
            pst.setInt(1, id);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Le club avec l'ID " + id + " a été supprimé avec succès.");
            } else {
                System.out.println("Aucun club trouvé avec l'ID " + id + ". La suppression a échoué.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Club t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
