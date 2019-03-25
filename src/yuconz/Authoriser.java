package yuconz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;


import javax.swing.JOptionPane;

public class Authoriser {

	public Authoriser() {
		
	}
	
	public void logAttempt(String employeeName, String attempt) throws IOException {
		
		String empattempt = "Employee: " + employeeName + " attempted to " + attempt + ", on: " + LocalDateTime.now();
		JOptionPane.showMessageDialog(null, "Failed Authorisation Check.",
				"Error", JOptionPane.ERROR_MESSAGE);
		String authLogsDir = System.getProperty("user.dir") + "/src/authorisationLogs/";
        File authLogs = new File(authLogsDir + "authlogs.txt");
        BufferedWriter o = new BufferedWriter(new FileWriter(authLogs, true));
        o.write(empattempt);
        o.newLine();
        o.close();
		
	}
	
	
}
