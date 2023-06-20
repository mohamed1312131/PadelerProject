/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Club;
import entite.Disponibiliteterrain;
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
public class DiponibiliteTerrainService implements IDiponibiliteTerrainService<Disponibiliteterrain> {

    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public DiponibiliteTerrainService() {
        connexion = DataSource.getInstance().getCnx();

    }

    @Override
    public void insert(Disponibiliteterrain t) {
        String requete = "insert into disponibiliteterrain (Date,temps1,temps2,temps3,temps4,temps5,temps6,temps7,temps8,temps9,temps10,temps11,temps12,temps13,temps14) values('" + t.getDate() + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "')";

        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(TerrainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Disponibiliteterrain> readAll() {
        String requete = "select * from disponibiliteterrain";
        List<Disponibiliteterrain> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                Terrain t = new Terrain();
                t.setIdTerrain(rs.getInt("idTerrain"));
                Disponibiliteterrain disponibiliteterrain = new Disponibiliteterrain(
                        rs.getInt("idDisponibiliteTerrain"),
                        rs.getDate("Date"),
                        rs.getInt("temps1"),
                        rs.getInt("temps2"),
                        rs.getInt("temps3"),
                        rs.getInt("temps4"),
                        rs.getInt("temps5"),
                        rs.getInt("temps6"),
                        rs.getInt("temps7"),
                        rs.getInt("temps8"),
                        rs.getInt("temps9"),
                        rs.getInt("temps10"),
                        rs.getInt("temps11"),
                        rs.getInt("temps12"),
                        rs.getInt("temps13"),
                        rs.getInt("temps14"),
                        t
                );
                list.add(disponibiliteterrain);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiponibiliteTerrainService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Disponibiliteterrain readById(int id) {
        String requete = "select * from disponibiliteterrain where idDisponibiliteTerrain =" + Integer.toString(id);
        Disponibiliteterrain disponibiliteterrain = null;
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                Terrain t = new Terrain();
                t.setIdTerrain(rs.getInt("idTerrain"));
                disponibiliteterrain = new Disponibiliteterrain(
                        rs.getInt("idDisponibiliteTerrain"),
                        rs.getDate("Date"),
                        rs.getInt("temps1"),
                        rs.getInt("temps2"),
                        rs.getInt("temps3"),
                        rs.getInt("temps4"),
                        rs.getInt("temps5"),
                        rs.getInt("temps6"),
                        rs.getInt("temps7"),
                        rs.getInt("temps8"),
                        rs.getInt("temps9"),
                        rs.getInt("temps10"),
                        rs.getInt("temps11"),
                        rs.getInt("temps12"),
                        rs.getInt("temps13"),
                        rs.getInt("temps14"),
                        t
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiponibiliteTerrainService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return disponibiliteterrain;
    }

    @Override
    public void delete(int id) {
        try {
            String requete = "DELETE FROM disponibiliteterrain WHERE idDisponibiliteTerrain = ?";
            pst = connexion.prepareStatement(requete);
            pst.setInt(1, id);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La disponibilite terrain avec l'ID " + id + " a été supprimée avec succès.");
            } else {
                System.out.println("Aucun disponibilite terrain trouvé avec l'ID " + id + ". La suppression a échoué.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(TerrainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Disponibiliteterrain t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
