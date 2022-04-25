/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTI_Projekti;
import java.sql.*;

/**
 *
 * @author Elina
 */
public class Palveluntarjoaja {
    public static Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jbdc::mysql://localhost:3306/OTI_Mokki", "root", "toimi");
            return con;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
