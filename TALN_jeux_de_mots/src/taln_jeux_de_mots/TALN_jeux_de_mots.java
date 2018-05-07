/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taln_jeux_de_mots;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author ordinateur
 */
public class TALN_jeux_de_mots {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        ArrayList<Regle> dico_regles = new ArrayList<>();
        
        File file = new File("src/taln_jeux_de_mots/regles.txt");
        
        InputStream Iin = new FileInputStream(file);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(Iin));
        String line = null;
        int phase = 0;
        while ((line = reader.readLine()) != null) {
            switch(line){
                case "Regles :":
                    phase = 1;
                    break;
                default:
                    if(phase==1 && !line.equals("")){
                        String[] e_s = line.split(";");
                        String[] eInfo_eSuffixe = e_s[0].split(">");
                        String[] sInfo_sSuffixe = e_s[1].split(">");
                        dico_regles.add(new Regle(eInfo_eSuffixe[0],eInfo_eSuffixe[1],sInfo_sSuffixe[0],sInfo_sSuffixe[1]));
                    }
                    break;
            }
        }
        
        Interface interf = new Interface(dico_regles);
    }
    
}
