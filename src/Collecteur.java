import javax.swing.*;

class Collecteur {
    final String nom;
    final String metier;
    final String origine;
    final String sexe;
    final int courage;
    final int force;
    final int intelligence;
    final int charisme;
    final int adresse;
    final int pognon;
    final int ptsDestin;
    final JTextField pvMax;
    final JTextField pvActuel;
    final JTextField eaMax;
    final JTextField eaActuel;

    public Collecteur(String pNom, String pMetier, String pOrigine, String pSexe,JTextField pPvMax,JTextField pPvActuel
                        ,JTextField pEaMax,JTextField pEaActuel,int pcourage, int pForce, int pIntelligence, int pCharisme,
                      int pAdresse, int pPognon, int pPtsDestin){
        this.nom=pNom;
        this.metier=pMetier;
        this.origine=pOrigine;
        this.sexe=pSexe;
        this.pvMax=pPvMax;
        this.pvActuel=pPvActuel;
        this.eaMax=pEaMax;
        this.eaActuel=pEaActuel;
        this.courage=pcourage;
        this.force=pForce;
        this.intelligence=pIntelligence;
        this.charisme=pCharisme;
        this.adresse=pAdresse;
        this.pognon=pPognon;
        this.ptsDestin=pPtsDestin;
    }
}
