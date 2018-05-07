/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taln_jeux_de_mots;

import RequeterRezo.Mot;
import RequeterRezo.RequeterRezo;
import RequeterRezo.Terme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ordinateur
 */
public class ActionResearchListener implements ActionListener {
    JTextField word;
    JPanel res;
    Interface f;
    ArrayList<Regle> dico_regle;

    public ActionResearchListener(JTextField word,JPanel res,ArrayList<Regle> dico_regle,Interface f) {
        super();
        this.word=word;
        this.res=res;
        this.dico_regle=dico_regle;
        this.f=f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        f.getErrorLabel().setText("");
        res.removeAll();
        File file = new File("src/taln_jeux_de_mots/regles.txt");
        try{
            String[] infos = getInfoRezo(word.getText());
            for(String info : infos){
                for(Regle r : dico_regle){
                    if(r.geteInfo().equals(info) && word.getText().endsWith(r.geteSuffixe())){
                        res.add(new JLabel(word.getText()));
                        res.add(new JLabel(r.geteInfo()));
                        res.add(new JLabel(r.geteSuffixe()+"->"+r.getsSuffixe()));
                        res.add(new JLabel(r.getsInfo()));
                        String newWord = changeWord(word.getText(), r);
                        res.add(new JLabel(newWord));
                        String[] newWordInfos = getInfoRezo(newWord);
                        boolean wordFound = false;
                        if(newWordInfos != null){
                            for(String newWordInfo : newWordInfos){
                                if(r.getsInfo().equals(newWordInfo) && !wordFound){ //eviter ligne vide et problème parsage html
                                    res.add(new JLabel("existe"));
                                    wordFound = true;
                                }
                            }
                        }
                        if(!wordFound){
                            res.add(new JLabel("n'existe pas"));
                        }
                    }
                }
            }
            if(res.getComponentCount()<=0){
                f.getErrorLabel().setText(word.getText()+" a été trouvé mais aucune règle ne lui correspond");
            }
        }
        catch(Exception notfound){
            System.out.println(notfound);
            f.getErrorLabel().setText(word.getText()+" : mot non trouvé");
        }
        f.pack();
    }
    
    private String changeWord(String s, Regle r){
        if(!s.endsWith(r.geteSuffixe())){
            return "erreur lors de l'application de la règle : "+r.geteSuffixe()+"->"+r.getsSuffixe();
        }
        else{
            String tmp = s.substring(0,s.lastIndexOf(r.geteSuffixe()));
            return tmp+r.getsSuffixe();
        }
    }
    
    private String[] getInfoRezo(String word) throws IOException, MalformedURLException, InterruptedException{
        RequeterRezo systeme = new RequeterRezo("36h", 3000);
        Mot mot = systeme.requete(word);
        if(mot != null){
            HashMap<String, ArrayList<Terme>> rs = mot.getRelations_sortantes();
            Iterator it = rs.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                if(pair.getKey().equals("r_pos")){
                    String[] info = pair.getValue().toString().substring(1, pair.getValue().toString().length()-1).split(", ");
                    String[] res = new String[info.length];
                    int i=0;
                    for(String s : info){
                        res[i]=s.substring(0, s.indexOf("="));
                        i++;
                    }
                    return res;
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
        return null;
    }
}
