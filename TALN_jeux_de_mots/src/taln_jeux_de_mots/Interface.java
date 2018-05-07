/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taln_jeux_de_mots;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ordinateur
 */
public class Interface extends JFrame {
    JLabel errorLabel;

    public Interface(ArrayList<Regle> dico_regle) {
        super("Jeux de mots");
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        JLabel instruct = new JLabel("entrez le mot :");
        instruct.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(instruct);
        
        JPanel wordSelect = new JPanel();
        wordSelect.setLayout(new BoxLayout(wordSelect,BoxLayout.X_AXIS));
        
        wordSelect.add(Box.createHorizontalGlue());
        
        JTextField word = new JTextField();
        word.setPreferredSize(new Dimension(500,word.getPreferredSize().height));
        word.setMaximumSize(new Dimension(500,word.getPreferredSize().height));
        wordSelect.add(word);
        
        JPanel result = new JPanel();
        
        JButton select = new JButton("valider");
        select.addActionListener(new ActionResearchListener(word,result,dico_regle,this));
        wordSelect.add(select);
        
        wordSelect.add(Box.createHorizontalGlue());
        
        panel.add(wordSelect);
        
        result.setLayout(new GridLayout(0,6));
        
        panel.add(result);
        
        JPanel errorPanel = new JPanel();
        JLabel errorLabel = new JLabel("");
        this.errorLabel = errorLabel;
        errorPanel.add(errorLabel);
        panel.add(errorPanel);
        
        this.add(panel);
        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public JLabel getErrorLabel(){
        return errorLabel;
    }
    
}
