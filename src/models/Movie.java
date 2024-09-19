/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import javax.swing.ImageIcon;

/**
 *
 * @author T-Man
 */
public class Movie {
    private String title;
    private String discription;
    private String category;
    private ImageIcon image;
    private String duration; // in minutes
//  private List<Showtime> showtimes;
    
    
    public Movie(String title, String discription, String category, ImageIcon image, String duration) {
        this.title = title;
        this.discription = discription;
        this.category = category;
        this.image = image;
        this.duration = duration;
    }
    public Movie(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ImageIcon getThumpnail() {
        return image;
    }

    public void setThumpnail(ImageIcon image) {
        this.image = image;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
