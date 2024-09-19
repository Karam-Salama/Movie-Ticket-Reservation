package Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class authenticationFile {
    
    private static final String FILE_NAME = "users.txt";
    
    //check if the email is already registered
    public boolean isEmailRegistered(String email) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                
                // make sure there's at least 2 elements
                if (parts.length >= 2) {
                    String[] emailParts = parts[0].split(": ");
                    if (emailParts.length >= 2) {
                        String fileEmail = emailParts[1];
                        if (fileEmail.equals(email)) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(authenticationFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(authenticationFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

 
    public void writeToFile(String data) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            writer.write(data);
            writer.newLine();
        } catch (IOException ex) {
            Logger.getLogger(authenticationFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(authenticationFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // validate user login credentials
    public boolean validateLogin(String username, String password) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                
                // make sure there's at least 2 elements
                if (parts.length >= 2) {
                    String[] usernameParts = parts[0].split(": ");
                    String[] passwordParts = parts[1].split(": ");
                    
                    // Check if both username and password parts have at least 2 elements
                    if (usernameParts.length >= 2 && passwordParts.length >= 2) {
                        String fileUsername = usernameParts[1];
                        String filePassword = passwordParts[1];
                        if (fileUsername.equals(username) && filePassword.equals(password)) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(authenticationFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(authenticationFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false; // Return false if no match is found
    }
}
