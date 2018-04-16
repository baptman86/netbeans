/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taln_jeux_de_mots;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JFrame;
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
    JFrame f;

    public ActionResearchListener(JTextField word,JPanel res,JFrame f) {
        super();
        this.word=word;
        this.res=res;
        this.f=f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        res.removeAll();
        File file = new File("src/taln_jeux_de_mots/regles.txt");
        try {
            URL url = new URL("http://www.jeuxdemots.org/rezo.php?");

            Map<String,Object> params = new LinkedHashMap<>();
                params.put("gotermrel",word.getText());
                params.put("onlyrels",4);
                params.put("output","cloud");

            StringBuilder postData = new StringBuilder();
            
            if(params != null){
                //conversion des parametres en un format exploitable

                for (Map.Entry<String,Object> param : params.entrySet()) {
                    //si il reste encore des parametres a ajouter
                    if (postData.length() != 0){
                        postData.append('&');
                    }
                    //ajout du parametre sous la forme "key = value" encodé en utf-8
                    postData.append(param.getKey());
                    postData.append('=');
                    postData.append(param.getValue());
                }
            }

            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            //connection au serveur et envoie de la requete
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

            Reader Rin = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String output = "";
            for (int c; (c = Rin.read()) >= 0;){
                output=output+((char)c);
            }
            
            try{
                String[] infos = output.split("relations sortantes")[2].split("</a>");
                for(String info : infos){
                    System.out.println(info.split(">")[info.split(">").length-1]);
                }

                InputStream Iin = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(Iin));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    res.add(new JLabel(line));
                }
                
            }
            catch(ArrayIndexOutOfBoundsException notfound){
                res.add(new JLabel("mot non trouvé"));
            }
            
            
            
            
            f.pack();
            
        }
        catch (Exception err) {
            System.err.println(err);
        }
    }
    
}
