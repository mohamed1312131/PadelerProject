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
        System.out.println("size"+cs.readAll().size());
        
        Club club = new Club() ;
        club.setName("club2");
        club.setAdresse("tunis");
        cs.insert(club);

        cs.readAll().forEach(System.out::println);
        
        cs.readById(1) ;
         Club club2 = new Club() ;
         club2=cs.readById(1) ;
       
        System.out.println("object from DB: "+ club2);
        
        TerrainService tr = new TerrainService() ;
        
        tr.readAll().forEach(System.out::println) ;
        
        System.out.println("test fin"+tr.readById(1));
        
        
        Terrain t1 = new Terrain (); 
        t1.setName("terrain 2 ");
        t1.setStatus(1);
        Club club1 = new Club() ;
        club1.setIdClub(1);
        t1.setClub(club1);
        
        tr.insert(t1);
        
        
        
        
        
        
        
    }

}
