/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdea_script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ordinateur
 */
public class Mdea_script {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] ins = {"scaffold"};
        String[] gens = {"500"};
        String[] muts = {"0.005"};
        String[] cxs = {"intra","inter"};
        String[] nbs = {"2","5","10","25"};
        String[] dists = {"hamming","levenshtein","cosine","centrality","smartLevenshtein"};
        String[] fitnesss = {"min","avg","minavg","minavgs","dist"};
        
        for(String in : ins){
            for(String gen : gens){
                for(String mut : muts){
                    for(String nb : nbs){
                        for(String cx : cxs){
                            for(String dist : dists){
                                for(String fitness : fitnesss){
                                    try {
                                        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("scripts/script-"+in+"-"+cx+"-"+dist+"-"+fitness+"-"+gen+"-"+mut+"-"+nb+".sh")));
                                        // normalement si le fichier n'existe pas, il est crée à la racine du projet
                                        String name = in+"-"+cx+"-"+dist+"-"+fitness+"-"+gen+"-"+mut+"-"+nb+".txt";
                                        writer.write("cd ..\n{ time ./mdea -in "+in+" -cx "+cx+" -dist "+dist+" -g "+gen+" -m "+mut+" -nb "+nb+" -out \"\" >> resultat_script/"+name+" ; } 2> resultat_script/"+name);
                                        writer.close();
                                    }
                                    catch (IOException e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
}
