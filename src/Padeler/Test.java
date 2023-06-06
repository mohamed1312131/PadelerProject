/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Padeler;

import entite.Club;
import entite.Terrain;

import java.util.List;


import service.ClubService;
import service.TerrainService;
import utils.DataSource;
/**
 *
 * @author wiemhjiri
 */
public class Test {

    public static void main(String[] args) {
        DataSource ds1=DataSource.getInstance();



        List<Club> l = null ;
        ClubService cs = new ClubService(); 
        
        Club club = new Club() ;
        club.setIdClub(14);
        club.setName("testUpdate");
        club.setAdresse("testUpdate");
        cs.update(club);
        
        
        
    }

}
