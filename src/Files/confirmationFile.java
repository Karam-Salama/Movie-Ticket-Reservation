/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import static Screens.confirmationScreen.selectedSeats;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import models.Reservation;

/**
 *
 * @author lenovo gaming3
 */
public class confirmationFile {

    private static final String FILE_NAME = "Invoice.txt";
    private reservationFile reFile = new reservationFile();
    public ArrayList<String> selectedSeats;

    public confirmationFile(ArrayList<String> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    // Method to save booking details to a file
    public void saveConfirmationToFile() {
        // Retrieve booking details
        Reservation res = reFile.getLastBooking();

        StringBuilder details = new StringBuilder();
        details.append("Invoice: \n")
                .append("-----------------------------\n")
                .append("Dear: ").append(res.getUsername()).append("\n")
                .append("Movie Title: ").append(res.getMovieTitle()).append("\n")
                .append("Showtime: ").append(res.getShowtime()).append("\n")
                .append("Selected Seats: ");

        // Append each seat
        for (String seat : res.getSelectedSeats()) {
            details.append(seat).append(", ");
        }

        // Remove the trailing comma and space
        if (details.toString().endsWith(", ")) {
            details.setLength(details.length() - 2);
        }

        details.append("\n")
                .append("Total Price: ").append(res.getSelectedSeats().size() * 100).append("\n")
                .append("-----------------------------\n")
                .append("Thank you for your booking!\n");

        // Specify the file path
        String filePath = FILE_NAME;

        // Write details to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(details.toString());
            System.out.println("Invoice saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving invoice: " + e.getMessage());
        }
    }
}
