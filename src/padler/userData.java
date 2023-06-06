/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padler;

import java.util.Date;

/**
 *
 * @author Esprit
 */
public class userData {
    private Integer idUser;
    private String firstName;
    private String lastName;
    private String gender;
    private String numTel;
    private String image;
    private Date date;

    public userData(int user_id, String firstName, String lastName, String gender, String numTel, String image, Date date) {
        this.idUser = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.numTel = numTel;
        this.image = image;
        this.date = date;
    }

    public int getUser_id() {
        return idUser;
    }

    

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }
    
    
    
}
