/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import entite.Terrain ;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oussama.hadjahmed
 */
public class Club {

    private int idClub ;
    private String name;
    private String adresse;
    private List<Terrain> terrains = new ArrayList<Terrain>(0);

    
    
     public Club() {
    }

    public Club(int idClub , String name, String adresse) {
        this.idClub  = idClub ;
        this.name = name;
        this.adresse = adresse;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public List<Terrain> getTerrains() {
        return terrains;
    }

    public void setTerrains(List<Terrain> terrains) {
        this.terrains = terrains;
    }


    @Override
    public String toString() {
        return "Club{" + "idClub=" + idClub + ", name=" + name + ", adresse=" + adresse + '}';
    }

  
     
     
     

}
