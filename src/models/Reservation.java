/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Files.reservationFile;
import Screens.Booking;
import Screens.confirmationScreen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author lenovo gaming3
 */
public class Reservation {

    reservationFile resFile = new reservationFile();

    private ArrayList<String> selectedSeats = new ArrayList<>();
    private String movieTitle;
    private String showtime;
    private String username;
    private boolean isBooked;

    // Constructor
    public Reservation(String movieTitle, String showtime, String username, ArrayList<String> selectedSeats) {
        this.selectedSeats = selectedSeats;
        this.movieTitle = movieTitle;
        this.showtime = showtime;
        this.username = username;
        this.isBooked = false;
    }

    // Getters and setters
    public ArrayList<String> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(ArrayList<String> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    // Process booking
    public boolean processBooking() {
        if (!isBooked && !selectedSeats.isEmpty()) {
            setBooked(true);
            resFile.saveBooking(movieTitle, showtime, username, selectedSeats);

            // Change the seat image
            for (String seat : selectedSeats) {
                JButton seatComponent = new JButton(seat); // cast string name to button
                if (seatComponent != null) {
                    seatComponent.setIcon(new ImageIcon("src\\seats\\reservedSeat.png"));
                }
            }

            System.out.println("done");

            System.out.println("Booking successful for " + username);
            return true;

        } else {
            System.out.println("Booking failed.");
            return false;
        }
    }

}
