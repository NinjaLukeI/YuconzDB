package yuconz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AuthenticationServer {
    
    File users = new File("users.txt");

    public AuthenticationServer() {
    	
    	
    }

    public void Authenticate(JTextField txtUsr, JPasswordField txtPwd) {

        try {
            Scanner in = new Scanner(users);


            while(in.hasNextLine()) {
                String s = in.nextLine();
                String[] sArray = s.split(",");

                String usrNameInp = txtUsr.getText();
                String usrPwdInp = String.valueOf(txtPwd.getPassword());


                System.out.println(sArray[0]); 
                System.out.println(sArray[1]); 

                if(usrNameInp.trim().equals(sArray[0].trim()) && usrPwdInp.trim().equals(sArray[1])) {
                    JOptionPane.showMessageDialog(null, "Login has been successful.", 
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    if(in.hasNextLine() == false) {
                        JOptionPane.showMessageDialog(null, "Invalid username or password.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            in.close();

        }catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "User DB not found.", "Error", JOptionPane.ERROR_MESSAGE);;
        }
    }
};
