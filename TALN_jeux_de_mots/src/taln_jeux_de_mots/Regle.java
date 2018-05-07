/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taln_jeux_de_mots;

/**
 *
 * @author ordinateur
 */
public class Regle {
    private String eInfo;
    private String eSuffixe;
    private String sInfo;
    private String sSuffixe;

    public Regle(String eInfo, String eSuffixe, String sInfo, String sSuffixe) {
        this.eInfo = eInfo;
        this.eSuffixe = eSuffixe;
        this.sInfo = sInfo;
        this.sSuffixe = sSuffixe;
    }

    public String geteInfo() {
        return eInfo;
    }

    public String geteSuffixe() {
        return eSuffixe;
    }

    public String getsInfo() {
        return sInfo;
    }

    public String getsSuffixe() {
        return sSuffixe;
    }

    
    
    
}
