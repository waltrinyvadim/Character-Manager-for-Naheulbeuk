import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;

class DialogBoxManuel extends JDialog {
    private JTextField nom;
    private JComboBox  jboxOrigine,jboxMetier,jboxSexe;
    private JButton okbutton,cancelButton;
    private Collecteur info;
    private CollecteurManuel infoBis;

    public DialogBoxManuel(JFrame parent,String title,boolean modal){
        super(parent,title,modal);
        this.setSize(500,705);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.initComponent();
        this.setVisible(true);
    }

    private void initComponent(){
        Dimension classicDimension = new Dimension(240,60);
        // NOM
        JPanel panNom=new JPanel();
        panNom.setLayout(new BorderLayout());
        panNom.setPreferredSize(classicDimension);
        nom = new JTextField();
        panNom.setBorder(BorderFactory.createTitledBorder("nom du personnage"));
        JLabel nomLabel = new JLabel("saisir un nom");
        nom.setHorizontalAlignment(JTextField.CENTER);
        panNom.add(nomLabel,BorderLayout.WEST);
        panNom.add(nom,BorderLayout.CENTER);

        //ORIGINE
        JPanel panOrigne = new JPanel();
        panOrigne.setBorder(BorderFactory.createTitledBorder("origine du personnage"));
        panOrigne.setPreferredSize(classicDimension);
        jboxOrigine = new JComboBox();
        ArrayList<String> arrayOrigine= new ArrayList<>();
        arrayOrigine.add("humain");arrayOrigine.add("barebare");arrayOrigine.add("Nain");arrayOrigine.add("haut elfe");
        arrayOrigine.add("demi elfe");arrayOrigine.add("elfe sylvain");arrayOrigine.add("elfe noir");arrayOrigine.add("orque");
        arrayOrigine.add("demi orque");arrayOrigine.add("gobelin");arrayOrigine.add("ogre");arrayOrigine.add("hobbit");
        arrayOrigine.add("gnôme des fôrets");arrayOrigine.add("nain de la mafia");arrayOrigine.add("amazone");
        JLabel labelOrigine = new JLabel("origine :");
        panOrigne.add(labelOrigine);
        panOrigne.add(jboxOrigine);
        for (String anArrayOrigine: arrayOrigine) {
            jboxOrigine.addItem(anArrayOrigine);
        }

        //METIER
        JPanel panMetier= new JPanel();
        panMetier.setBorder(BorderFactory.createTitledBorder("metier du personnage"));
        panMetier.setPreferredSize(classicDimension);
        jboxMetier = new JComboBox();
        ArrayList<String> arrayMetier = new ArrayList<>();
        arrayMetier.add("guerrier");arrayMetier.add("assassin");arrayMetier.add("voleur");arrayMetier.add("prêtre");
        arrayMetier.add("mage");arrayMetier.add("paladin");arrayMetier.add("ranger");arrayMetier.add("menestrel");
        arrayMetier.add("marchand");arrayMetier.add("ingenieur");arrayMetier.add("pirate");arrayMetier.add("noble");
        arrayMetier.add("bourreau");arrayMetier.add("sbire");
        JLabel labelMetier = new JLabel("métier :");
        panMetier.add(labelMetier);
        panMetier.add(jboxMetier);
        for (String anArrayMetier: arrayMetier) {
            jboxMetier.addItem(anArrayMetier);
        }

        //SEXE
        JPanel panSexe = new JPanel();
        panSexe.setPreferredSize(classicDimension);
        panSexe.setBorder(BorderFactory.createTitledBorder("sexe du personnage"));
        jboxSexe = new JComboBox();
        String[] listeSexe = {"homme","femme","?"};
        for (String aListeSexe : listeSexe) {
            jboxSexe.addItem(aListeSexe);
        }
        JLabel labelSexe = new JLabel("sexe :");
        panSexe.add(labelSexe);
        panSexe.add(jboxSexe);

        //pvMax
        JPanel panpvMax = new JPanel();
        panpvMax.setLayout(new BorderLayout());
        panpvMax.setBorder(BorderFactory.createTitledBorder("tes Pv max"));
        panpvMax.setPreferredSize(classicDimension);
        JLabel labelpvMax = new JLabel("pvMax :");
        JTextField pvMax = new JTextField();
        pvMax.setHorizontalAlignment(JTextField.CENTER);
        panpvMax.add(labelpvMax,BorderLayout.WEST);
        panpvMax.add(pvMax,BorderLayout.CENTER);

        //pvActuel
        JPanel panpvActuel = new JPanel();
        panpvActuel.setLayout(new BorderLayout());
        panpvActuel.setBorder(BorderFactory.createTitledBorder("tes Pv actuels"));
        panpvActuel.setPreferredSize(classicDimension);
        JLabel labelpvActuel = new JLabel("pvActuel :");
        JTextField pvActuel = new JTextField();
        pvActuel.setHorizontalAlignment(JTextField.CENTER);
        panpvActuel.add(labelpvActuel,BorderLayout.WEST);
        panpvActuel.add(pvActuel,BorderLayout.CENTER);

        //eaMax
        JPanel paneaMax = new JPanel();
        paneaMax.setLayout(new BorderLayout());
        paneaMax.setBorder(BorderFactory.createTitledBorder("ton Ea Max"));
        paneaMax.setPreferredSize(classicDimension);
        JLabel labeleaMax = new JLabel("eaMax :");
        JTextField eaMax = new JTextField();
        eaMax.setHorizontalAlignment(JTextField.CENTER);
        paneaMax.add(labeleaMax,BorderLayout.WEST);
        paneaMax.add(eaMax,BorderLayout.CENTER);

        //eaActuel
        JPanel paneaActuel = new JPanel();
        paneaActuel.setLayout(new BorderLayout());
        paneaActuel.setBorder(BorderFactory.createTitledBorder("Ea Actuels"));
        paneaActuel.setPreferredSize(classicDimension);
        JLabel labeleaActuel = new JLabel("ton eaActuel :");
        JTextField eaActuel = new JTextField();
        eaActuel.setHorizontalAlignment(JTextField.CENTER);
        paneaActuel.add(labeleaActuel,BorderLayout.WEST);
        paneaActuel.add(eaActuel,BorderLayout.CENTER);
        
        //COURAGE
        JPanel panCourage = new JPanel();
        panCourage.setLayout(new BorderLayout());
        panCourage.setBorder(BorderFactory.createTitledBorder("score de courage"));
        panCourage.setPreferredSize(classicDimension);
        JLabel labelCourage = new JLabel("courage :");
        JTextField labelScoreCourage = new JTextField();
        labelScoreCourage.setHorizontalAlignment(JTextField.CENTER);
        panCourage.add(labelCourage,BorderLayout.WEST);
        panCourage.add(labelScoreCourage,BorderLayout.CENTER);

        //FORCE
        JPanel panForce = new JPanel();
        panForce.setLayout(new BorderLayout());
        panForce.setBorder(BorderFactory.createTitledBorder("score de Force"));
        panForce.setPreferredSize(classicDimension);
        JLabel labelForce = new JLabel("Force :");
        JTextField labelScoreForce = new JTextField();
        labelScoreForce.setHorizontalAlignment(JTextField.CENTER);
        panForce.add(labelForce,BorderLayout.WEST);
        panForce.add(labelScoreForce,BorderLayout.CENTER);

        //INTELLIGENCE
        JPanel panIntelligence = new JPanel();
        panIntelligence.setLayout(new BorderLayout());
        panIntelligence.setBorder(BorderFactory.createTitledBorder("score de Intelligence"));
        panIntelligence.setPreferredSize(classicDimension);
        JLabel labelIntelligence = new JLabel("Intelligence :");
        JTextField labelScoreIntelligence = new JTextField();
        labelScoreIntelligence.setHorizontalAlignment(JTextField.CENTER);
        panIntelligence.add(labelIntelligence,BorderLayout.WEST);
        panIntelligence.add(labelScoreIntelligence,BorderLayout.CENTER);

        //ADRESSE
        JPanel panAdresse = new JPanel();
        panAdresse.setLayout(new BorderLayout());
        panAdresse.setBorder(BorderFactory.createTitledBorder("score de Adresse"));
        panAdresse.setPreferredSize(classicDimension);
        JLabel labelAdresse = new JLabel("Adresse :");
        JTextField labelScoreAdresse = new JTextField();
        labelScoreAdresse.setHorizontalAlignment(JTextField.CENTER);
        panAdresse.add(labelAdresse,BorderLayout.WEST);
        panAdresse.add(labelScoreAdresse,BorderLayout.CENTER);

        //CHARISME
        JPanel panCharisme = new JPanel();
        panCharisme.setLayout(new BorderLayout());
        panCharisme.setBorder(BorderFactory.createTitledBorder("score de Charisme"));
        panCharisme.setPreferredSize(classicDimension);
        JLabel labelCharisme = new JLabel("Charisme :");
        JTextField labelScoreCharisme = new JTextField();
        labelScoreCharisme.setHorizontalAlignment(JTextField.CENTER);
        panCharisme.add(labelCharisme,BorderLayout.WEST);
        panCharisme.add(labelScoreCharisme,BorderLayout.CENTER);

        //POGNON
        JPanel panPognon = new JPanel();
        panPognon.setLayout(new BorderLayout());
        panPognon.setBorder(BorderFactory.createTitledBorder("ton pognon"));
        panPognon.setPreferredSize(classicDimension);
        JLabel labelPognon = new JLabel("ton pécule :");
        JTextField pecule = new JTextField();
        pecule.setHorizontalAlignment(JTextField.CENTER);
        panPognon.add(labelPognon,BorderLayout.WEST);
        panPognon.add(pecule,BorderLayout.CENTER);

        //POINT DE DESTIN
        JPanel panelDestin = new JPanel();
        panelDestin.setLayout(new BorderLayout());
        panelDestin.setPreferredSize(new Dimension(485,60));
        panelDestin.setBorder(BorderFactory.createTitledBorder("point(s) de Destin"));
        JLabel labelDestin = new JLabel("tes points :");
        JTextField destin = new JTextField();
        destin.setHorizontalAlignment(JTextField.CENTER);
        panelDestin.add(labelDestin,BorderLayout.WEST);
        panelDestin.add(destin,BorderLayout.CENTER);

        //xp
        JPanel panXp = new JPanel();
        panXp.setLayout(new BorderLayout());
        panXp.setBorder(BorderFactory.createTitledBorder("ton experience"));
        panXp.setPreferredSize(classicDimension);
        JLabel labelXP = new JLabel("ton xp :");
        JTextField XP = new JTextField();
        XP.setHorizontalAlignment(JTextField.CENTER);
        panXp.add(labelXP,BorderLayout.WEST);
        panXp.add(XP,BorderLayout.CENTER);
        
        //lvl
        JPanel panLVL = new JPanel();
        panLVL.setLayout(new BorderLayout());
        panLVL.setBorder(BorderFactory.createTitledBorder("ton niveau"));
        panLVL.setPreferredSize(classicDimension);
        JLabel labelLVl = new JLabel("ton lvl :");
        JTextField lvl = new JTextField();
        lvl.setHorizontalAlignment(JTextField.CENTER);
        panLVL.add(labelLVl,BorderLayout.WEST);
        panLVL.add(lvl,BorderLayout.CENTER);

        //attaque
        JPanel panAttaque = new JPanel();
        panAttaque.setLayout(new BorderLayout());
        panAttaque.setBorder(BorderFactory.createTitledBorder("ton attaque"));
        panAttaque.setPreferredSize(classicDimension);
        JLabel labelAttaque = new JLabel("attaque :");
        JTextField att = new JTextField();
        att.setHorizontalAlignment(JTextField.CENTER);
        panAttaque.add(labelAttaque,BorderLayout.WEST);
        panAttaque.add(att,BorderLayout.CENTER);

        //parrade
        JPanel panParade = new JPanel();
        panParade.setLayout(new BorderLayout());
        panParade.setBorder(BorderFactory.createTitledBorder("ta parade"));
        panParade.setPreferredSize(classicDimension);
        JLabel labelParade = new JLabel("parade :");
        JTextField parade = new JTextField();
        parade.setHorizontalAlignment(JTextField.CENTER);
        panParade.add(labelParade,BorderLayout.WEST);
        panParade.add(parade,BorderLayout.CENTER);


        //BOUTON DE CONTROLE
        okbutton = new JButton("OK");
        cancelButton = new JButton("cancel");

        JPanel content=new JPanel();
        this.setContentPane(content);

        //AJOUT DES DIFFERENT ELEMENT DANS LE PANEL PRINCIPAL
        content.add(panNom);content.add(panSexe);content.add(panpvMax);content.add(panpvActuel);
        content.add(paneaMax);content.add(paneaActuel);content.add(panCourage);content.add(panForce);
        content.add(panIntelligence);content.add(panAdresse);content.add(panCharisme);content.add(panPognon);
        content.add(panelDestin);content.add(panXp);content.add(panLVL);content.add(panAttaque);
        content.add(panParade);content.add(panOrigne);content.add(panMetier);content.add(okbutton);
        content.add(cancelButton);

        ActionListener controlListener = e -> {
            if (e.getSource()==cancelButton){
                DialogBoxManuel.super.dispose();
            }

            if (e.getSource()==okbutton){
                Panneau pan = new Panneau();

                //recuperation de toute les variable dans un seul objet " collecteur"
                info = new Collecteur(nom.getText(),(String)jboxMetier.getSelectedItem(),(String)jboxOrigine.getSelectedItem(),
                        (String) jboxSexe.getSelectedItem(),pvMax,pvActuel,eaMax,
                        eaActuel,Integer.parseInt(labelScoreCourage.getText()),Integer.parseInt(labelScoreForce.getText()),Integer.parseInt(labelScoreIntelligence.getText()),
                        Integer.parseInt(labelScoreCharisme.getText()),Integer.parseInt(labelScoreAdresse.getText()),Integer.parseInt(pecule.getText())
                        ,Integer.parseInt(destin.getText()));

                infoBis= new CollecteurManuel(Integer.parseInt(XP.getText()),Integer.parseInt(lvl.getText()),Integer.parseInt(att.getText()),Integer.parseInt(parade.getText()));

                //variable qui viennent de Main
                //on ajoute un onglet au tableau d'onglet et on ajoute le nom du personnage a une liste de personnage !
                MainS.onglet.addTab(info.nom, pan);
                MainS.listOfOnglet.add(info.nom);

                //chacune des ses lignes écris une information sur la panel
                //les valeurs viennents de la dialogbox et sont envoyer au panel
                pan.labTxtNom.setText(info.nom);
                pan.labTxtOrigine.setText(info.origine);
                pan.labTxtMetier.setText(info.metier);
                pan.labTxtSexe.setText(info.sexe);
                pan.tabTxtStatPvEa[0].setText(info.pvMax.getText());
                pan.tabTxtActualPvEa[0].setText(info.pvActuel.getText());
                pan.tabTxtStatPvEa[1].setText(info.eaMax.getText());
                pan.tabTxtActualPvEa[1].setText(info.eaActuel.getText());
                pan.tabTxtArgent[2].setText(Integer.toString(info.pognon));
                pan.tabtxtXpLv[0].setText(Integer.toString(infoBis.xp));
                pan.tabtxtXpLv[1].setText(Integer.toString(infoBis.lvl));
                pan.tabtxtXpLv[2].setText(Integer.toString(info.ptsDestin));
                pan.tabLabStatCaracBase[0].setText(Integer.toString(info.courage));
                pan.tabLabStatCaracBase[1].setText(Integer.toString(info.intelligence));
                pan.tabLabStatCaracBase[2].setText(Integer.toString(info.charisme));
                pan.tabLabStatCaracBase[3].setText(Integer.toString(info.adresse));
                pan.tabLabStatCaracBase[4].setText(Integer.toString(info.force));
                pan.tabLabStatCaracBase[5].setText(Integer.toString(((info.adresse + info.intelligence)) / 2));
                pan.tabLabStatCaracBase[6].setText(Integer.toString((info.intelligence + info.charisme) / 2));
                pan.tabLabStatCaracBase[7].setText(Integer.toString((info.courage + info.intelligence + info.force) / 3));
                pan.tabLabStatCaracBase[8].setText(Integer.toString(infoBis.attaque));
                pan.tabLabStatCaracBase[9].setText(Integer.toString(infoBis.parade));

                /////-------------------//////
                //ON ENVOIS LES DONNÉES A LA DATA BASE
                try {
                    Statement state = MainS.conn.createStatement();

                    //ligne qui envoit toutes les informations du personnage  à la putin de fucking base de donner
                    state.executeUpdate("INSERT INTO personnage (name, origine, metier, sexe, pvmax, pvactuel, pamax, paactuel, xp, lvl," +
                            " ptsdestin, berylium, thritil, gold, argent, cuivre, courage, intelligence, charisme, adresse, force, attaque," +
                            " parade) values('" + info.nom + "','" + info.origine + "','" + info.metier + "','" + info.sexe + "'" +
                            ",'"+info.pvMax.getText()+"','"+info.pvActuel.getText()+"','"+info.eaMax.getText()+"','"+info.eaActuel.getText()+"'" +
                            ",'"+infoBis.xp+"','"+infoBis.lvl+"','"
                            + info.ptsDestin + "',0,0,'" + info.pognon + "',0,0,'" + info.courage + "','" + info.intelligence + "','" + info.charisme + "'" +
                            ",'" + info.adresse + "','" + info.force + "','"+infoBis.attaque+"','"+infoBis.parade+"')");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                DialogBoxManuel.super.dispose();
            }
        };
        cancelButton.addActionListener(controlListener);
        okbutton.addActionListener(controlListener);

    }



}
