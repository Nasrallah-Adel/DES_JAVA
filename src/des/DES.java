
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nasrallah
 */
public class DES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File file = new File("data.DES");
            Scanner sc = new Scanner(file);
          while (sc.hasNextLine()) {
                System.out.println(sc.nextInt());
            }

// TODO code application logic here
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
