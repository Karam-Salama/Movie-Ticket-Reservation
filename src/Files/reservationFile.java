/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import models.Reservation;

/**
 *
 * @author lenovo gaming3
 */
public class reservationFile {

    private static final String FILE_NAME = "bookings.txt";

    // Save booking to file
    public void saveBooking(String movieTitle, String showtime, String customerName, ArrayList<String> selectedSeats) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bookings.txt", true))) {
            writer.write("Movie: " + movieTitle + "; Showtime: " + showtime + "; Customer Name: " + customerName + "; Seats: " + selectedSeats.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// Load bookings from file
    public static ArrayList<String> getReservedSeats(String movieTitle, String showtime) {
        ArrayList<String> reservedSeats = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("; ");

                if (parts.length < 4) {
                    continue;
                }

                String fileMovieTitle = parts[0].split(": ")[1];
                String fileShowtime = parts[1].split(": ")[1];

                if (fileMovieTitle.equals(movieTitle) && fileShowtime.equals(showtime)) {
                    String seatsPart = parts[3];
                    String seats = seatsPart.split(": ")[1];

                    seats = seats.replace("[", "").replace("]", "");
                    for (String seat : seats.split(", ")) {
                        reservedSeats.add(seat);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservedSeats;
    }

    public static ArrayList<Reservation> loadBookings() {
        ArrayList<Reservation> bookings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("; ");

                if (parts.length < 4) {
                    continue;
                }

                String movieTitle = parts[0].split(": ")[1];
                String showtime = parts[1].split(": ")[1];
                String username = parts[2].split(": ")[1];
                String seats = parts[3].split(": ")[1];
                ArrayList<String> seatNames = new ArrayList<>();
                for (String seat : seats.replace("[", "").replace("]", "").split(", ")) {
                    seatNames.add(seat);
                }
                Reservation booking = new Reservation(movieTitle, showtime, username, seatNames);
                bookings.add(booking);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public Reservation getLastBooking() {
        ArrayList<Reservation> bookings = loadBookings();
        if (!bookings.isEmpty()) {
            return bookings.get(bookings.size() - 1);
        }
        return null;
    }

    public static ArrayList<String> getNoOfSoldSeats(String movieTitle) {
        ArrayList<String> reservedSeats = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("; ");

                if (parts.length < 4) {
                    continue;
                }

                String fileMovieTitle = parts[0].split(": ")[1];

                if (fileMovieTitle.equals(movieTitle)) {
                    String seatsPart = parts[3];//"Seats: [C5, C6]"
                    String seats = seatsPart.split(": ")[1]; //"[C5, C6]"

                    seats = seats.replace("[", "").replace("]", "");
                    for (String seat : seats.split(", ")) {
                        reservedSeats.add(seat);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservedSeats;
    }

}
