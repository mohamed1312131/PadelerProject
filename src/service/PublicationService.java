/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Publication;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author safdh
 */
public class PublicationService implements Iservice<Publication> {

    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public PublicationService() {
        connexion = DataSource.getInstance().getCnx();

    }

    @Override
    public List<Publication> readAll() {
        String requete = "select * from publication";
        List<Publication> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                Publication p = new Publication(rs.getInt("idPublication"), rs.getString("description"), rs.getDate("datePub"), rs.getInt("reaction"));
                //Publication p = new Publication(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void insert(Publication publication) {

        String query = "INSERT INTO publication (description, image, datePub) VALUES (?, ?, ?)";

        try (Connection connection = DataSource.getInstance().getCnx();
             PreparedStatement statement = connection.prepareStatement(query)) {

            Date date = Date.valueOf(LocalDate.now());
            statement.setString(1, publication.getDescription());
            statement.setString(2, publication.getImage());
            statement.setDate(3, date);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Publication publiée avec succès !");
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'insertion de la publication : " + ex.getMessage());
        }
    }


    @Override
    public Publication readById(int id) {
        Publication publication = new Publication();
        String query = "SELECT * FROM Publication WHERE idPublication = ?";
        try {

            pst = connexion.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {

                publication.setImage(rs.getString("image"));
                publication.setDescription(rs.getString("description"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Id: " + publication.getIdPublication());
        return publication;

    }

    @Override
    public void delete(int Id) {

        String requete = "DELETE FROM publication WHERE idpublication = " + Id;

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Publication p) {
        String requete = "UPDATE publication SET `description` = '" + p.getDescription() + "', `datePub` = '" + p.getDatePub() + "' WHERE idpublication = " + p.getIdPublication();

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
