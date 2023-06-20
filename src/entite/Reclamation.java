/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;

public class Reclamation {

    private int id;
    private int idUser;
    private int idClub;
    private String email;
    private Date date;
    private String Reponse;
    private String statut;
    private String description;
    private String categorie;

    public Reclamation(int id, int idUser, int idClub, String email, Date date, String Reponse, String statut, String description, String categorie) {
        this.id = id;
        this.idUser = idUser;
        this.idClub = idClub;
        this.email = email;
        this.date = date;
        this.Reponse = Reponse;
        this.statut = statut;
        this.description = description;
        this.categorie = categorie;
    }

    public Reclamation(int id, int idUser, int idClub, String email, Date date, String Reponse, String statut, String description) {
        this.id = id;
        this.idUser = idUser;
        this.idClub = idClub;
        this.email = email;
        this.date = date;
        this.Reponse = Reponse;
        this.statut = statut;
        this.description = description;
    }

    public Reclamation(int idUser, int idClub, String Reponse, String description) {
        this.idUser = idUser;
        this.idClub = idClub;
        this.Reponse = Reponse;
        this.description = description;
    }

    public Reclamation(int id, int idUser, int idClub, String email, Date date, String Reponse, String statut) {
        this.id = id;
        this.idUser = idUser;
        this.idClub = idClub;
        this.email = email;
        this.date = date;
        this.Reponse = Reponse;
        this.statut = statut;
    }

    public Reclamation(String description) {
        this.description = description;
    }

    public Reclamation() {
    }

    public Reclamation(int id, int idUser, String email, Date date, String statut, String description) {
        this.id = id;
        this.idUser = idUser;
        this.email = email;
        this.date = date;
        this.statut = statut;
        this.description = description;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReponse() {
        return Reponse;
    }

    public void setReponse(String Reponse) {
        this.Reponse = Reponse;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

}
