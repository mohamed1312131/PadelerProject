/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import entite.Commentaire;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author safdh
 */
public class Publication {

    private int idPublication;
    private String Description;
    private String image;
    private Date datePub;
    private int reaction;
    private List<Commentaire> commentaire = new ArrayList<Commentaire>(0);
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Publication(String Description, Date datePub) {
        this.Description = Description;
        this.datePub = datePub;
    }

    public Publication(String description, String image) {
        Description = description;
        this.image = image;

    }

    public Publication(int idPublication, String Description, Date datePub, int reaction) {
        this.idPublication = idPublication;
        this.Description = Description;
        this.datePub = datePub;
        this.reaction = reaction;
    }

    public Publication() {
    }

    public Publication(String description) {
        this.Description = description;
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

    public int getReaction() {
        return reaction;
    }

    public void setReaction(int reaction) {
        this.reaction = reaction;
    }

    public List<Commentaire> getCommentaires() {
        return commentaire;
    }

    public void setCommentaire(List<Commentaire> Commentaires) {
        this.commentaire = commentaire;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Commentaire> getCommentaire() {
        return commentaire;
    }

    @Override
    public String toString() {
        return "Publication" + "id=" + idPublication + ", description=" + Description + '}';
    }
    
}
