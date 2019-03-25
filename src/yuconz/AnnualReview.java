package yuconz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

public class AnnualReview {
	
	public String reviewLogsDir;
	

    public AnnualReview() {
    	
    	
    	
    	
    }

    /*
     * The first line is a File object which creates a text file with fileName as its name 
     * and the destination path is specified in the review object declaration.
     * o.write(cont) writes the string cont in the review object and hence the text file.
     * The fileName should be the name of the student and the content is the review.
     * 
     * 
     * 
     * @param String fileName.
     * @param String cont (contents).
     */

    public void saveNewReview(String fileName, String cont) throws IOException {
    	String reviewLogsDir = System.getProperty("user.dir") + "/src/reviewLogs/";
        File review = new File(reviewLogsDir + fileName + ".txt");
        BufferedWriter o = new BufferedWriter(new FileWriter(review));
        o.write(cont);
        o.newLine();
        o.close();
    }
	public void modifyReview() {
	
	    }

    /*
     * The first variable is a string holding the path of the file 
     * The second variable is also a string. It is used later to format the text like the file.
     * When the length of the string matches the one from the file, it changes line to continue
     * printing the contents. The while loop is used to stop the method from running when 
     * there is nothing more to print. After that the BufferedReader object buff closes.
     *
     *You can redirect System.out messages to a JTextArea or JTextPane.
     *
     * @param String fileName, specifies the name of the folder to be read.
     */

    public String readReview(String fileName) throws IOException {
    	String reviewLogsDir = System.getProperty("user.dir") + "/src/reviewLogs/";
        String file = reviewLogsDir + fileName + ".txt";
        String review = "";
       
        File reviewFile = new File(file);
        boolean exists = reviewFile.exists();
        
        if(exists) {
        	review = new String(Files.readAllBytes(Paths.get(file)));
        	JOptionPane.showMessageDialog(null, "Review has been located.", 
					"Success", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
        	JOptionPane.showMessageDialog(null, "Review could not be located.",
					"Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
      
        
        return review;
		   
    }


    

}