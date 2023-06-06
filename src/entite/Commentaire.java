/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author safdh
 */
public class Commentaire {

    private int idComment;
    private String description;
    private int idUser;
    private Publication pub;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Commentaire(int idComment, String description) {
        this.idComment = idComment;
        this.description = description;
           }

    public Commentaire() {
    }

    public Commentaire(String description) {
        this.description = description;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

 
    @Override
    public String toString() {
        return "Commentaire{" + "idComment=" + idComment + ", description=" + description + '}';
    }

}
