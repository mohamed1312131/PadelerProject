/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Club;
import entite.Terrain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author oussama.hadjahmed
 */
public class TerrainService implements Iservice<Terrain> {

    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public TerrainService() {
        connexion = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Terrain t) {
        String requete = "insert into Terrain (name,status,idClub) values('" + t.getName() + "','" + t.getStatus() + "','" + t.getClub().getIdClub() + "')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(TerrainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Terrain> readAll() {
        String requete = "select * from terrain";
        List<Terrain> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                Club c = new Club();
                c.setIdClub(rs.getInt("idClub"));
                Terrain terrain = new Terrain(rs.getInt("idTerrain"), rs.getString("name"), rs.getInt("status"), c);
                list.add(terrain);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TerrainService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Terrain readById(int id) {
        Terrain t = new Terrain();

        try {

            String requete = "SELECT * FROM terrain WHERE idTerrain=" + Integer.toString(id);
            pst = connexion.prepareStatement(requete);
            rs = pst.executeQuery(requete);

            while (rs.next()) {
                t.setIdTerrain(rs.getInt("idTerrain"));
                t.setName(rs.getString("name"));
                t.setStatus(rs.getInt("status"));
                Club c = new Club();
                c.setIdClub(rs.getInt("idClub"));
                t.setClub(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TerrainService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public void delete(int id) {
        try {
            String requete = "DELETE FROM terrain WHERE idTerrain = ?";
            pst = connexion.prepareStatement(requete);
            pst.setInt(1, id);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Le Terrain avec l'ID " + id + " a été supprimé avec succès.");
            } else {
                System.out.println("Aucun Terrain trouvé avec l'ID " + id + ". La suppression a échoué.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(TerrainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Terrain t) {
        throw new UnsupportedOperationException("Not oussama yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
