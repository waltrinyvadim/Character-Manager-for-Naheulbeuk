import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class DialogBoxManuel extends JDialog {
    private JTextField nom;
    private JComboBox  jboxOrigine,jboxMetier,jboxSexe;
    private JButton okbutton,cancelButton;
    private Collecteur info;
    private CollecteurBis infoBis;

    public DialogBoxManuel(JFrame parent,String title,boolean modal){
        super(parent,title,modal);
        this.setSize(500,770);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.initComponent();
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponent(){
        Dimension classicDimension = new Dimension(240,60);
        // NOM
        JPanel panNom=new JPanel();
        nom = new JTextField();
        JLabel nomLabel = new JLabel("saisir un nom");
        createStandarsGUIforAttribute(classicDimension,panNom,nomLabel,nom,"nom du Personnage");

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
        JLabel labelpvMax = new JLabel("pvMax :");
        JTextField pvMax = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panpvMax,labelpvMax,pvMax,"tes Pv max");

        //pvActuel
        JPanel panpvActuel = new JPanel();
        JLabel labelpvActuel = new JLabel("pvActuel :");
        JTextField pvActuel = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panpvActuel,labelpvActuel,pvActuel,"tes Pv Actuel(s)");

        //eaMax
        JPanel paneaMax = new JPanel();
        JLabel labeleaMax = new JLabel("eaMax :");
        JTextField eaMax = new JTextField();
        createStandarsGUIforAttribute(classicDimension,paneaMax,labeleaMax,eaMax,"EA Max");

        //eaActuel
        JPanel paneaActuel = new JPanel();
        JLabel labeleaActuel = new JLabel("ton eaActuel :");
        JTextField eaActuel = new JTextField();
        createStandarsGUIforAttribute(classicDimension,paneaActuel,labeleaActuel,eaActuel,"EA Actuel(s)");
        
        //COURAGE
        JPanel panCourage = new JPanel();
        JLabel labelCourage = new JLabel("courage :");
        JTextField labelScoreCourage = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panCourage,labelCourage,labelScoreCourage,"score de Courage");

        //FORCE
        JPanel panForce = new JPanel();
        JLabel labelForce = new JLabel("Force :");
        JTextField labelScoreForce = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panForce,labelForce,labelScoreForce,"score de Force");

        //INTELLIGENCE
        JPanel panIntelligence = new JPanel();
        JLabel labelIntelligence = new JLabel("Intelligence :");
        JTextField labelScoreIntelligence = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panIntelligence,labelIntelligence,labelScoreIntelligence,"score d'Intelligence");

        //ADRESSE
        JPanel panAdresse = new JPanel();
        JLabel labelAdresse = new JLabel("Adresse :");
        JTextField labelScoreAdresse = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panAdresse,labelAdresse,labelScoreAdresse,"score d'Adresse");

        //CHARISME
        JPanel panCharisme = new JPanel();
        JLabel labelCharisme = new JLabel("Charisme :");
        JTextField labelScoreCharisme = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panCharisme,labelCharisme,labelScoreCharisme,"score de Charisme");

        //POGNON
        JPanel panPognon = new JPanel();
        JLabel labelPognon = new JLabel("ton pécule :");
        JTextField pecule = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panPognon,labelPognon,pecule,"ton pognon");

        //POINT DE DESTIN
        JPanel panelDestin = new JPanel();
        JLabel labelDestin = new JLabel("tes points :");
        JTextField destin = new JTextField();
        createStandarsGUIforAttribute(new Dimension(485,60),panelDestin,labelDestin,destin,"point(s) de Destin");

        //xp
        JPanel panXp = new JPanel();
        JLabel labelXp = new JLabel("ton xp");
        JTextField XP = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panXp,labelXp,XP,"ton experience");

        //lvl
        JPanel panLVL = new JPanel();
        JLabel labelLVl = new JLabel("ton lvl :");
        JTextField lvl = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panLVL,labelLVl,lvl,"ton niveau");

        //attaque
        JPanel panAttaque = new JPanel();
        JLabel labelAttaque = new JLabel("attaque :");
        JTextField att = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panAttaque,labelAttaque,att,"ton attaque");

        //parrade
        JPanel panParade = new JPanel();
        JLabel labelParade = new JLabel("parade :");
        JTextField parade = new JTextField();
        createStandarsGUIforAttribute(classicDimension,panParade,labelParade,parade,"ta parade");

        //partie à choisir
        JPanel jPanelGame = new JPanel();
        jPanelGame.setPreferredSize(new Dimension(485,60));
        jPanelGame.setBorder(BorderFactory.createTitledBorder("choisir la partie"));
        JComboBox<String> jComboBoxGame = new JComboBox<>();
        JLabel jLabelGame = new JLabel("nom : ");
        getPartieToMenu(jComboBoxGame);
        jPanelGame.add(jLabelGame);
        jPanelGame.add(jComboBoxGame);

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
        content.add(panParade);content.add(panOrigne);content.add(panMetier);content.add(jPanelGame);
        content.add(okbutton);content.add(cancelButton);

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

                infoBis= new CollecteurBis(Integer.parseInt(XP.getText()),Integer.parseInt(lvl.getText()),Integer.parseInt(att.getText()),Integer.parseInt(parade.getText()));

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
                    int idPartie=0;
                    ResultSet resultSetIDGame = state.executeQuery("SELECT idpartie from partie where nom='"+jComboBoxGame.getSelectedItem()+"'");
                    while(resultSetIDGame.next()){
                        idPartie=resultSetIDGame.getInt("idpartie");
                    }

                    //ligne qui envoit toutes les informations du personnage  à la putin de fucking base de donner
                    state.executeUpdate("INSERT INTO personnage (name, origine, metier, sexe, pvmax, pvactuel, pamax, paactuel, xp, lvl," +
                            " ptsdestin, berylium, thritil, gold, argent, cuivre, courage, intelligence, charisme, adresse, force, attaque," +
                            " parade,fk_partie,couragemod,intelligencemod,charismemod,adressemod,forcemod,attaquemod,parademod) values('" + info.nom + "','" + info.origine + "','" + info.metier + "','" + info.sexe + "'" +
                            ",'"+info.pvMax.getText()+"','"+info.pvActuel.getText()+"','"+info.eaMax.getText()+"','"+info.eaActuel.getText()+"'" +
                            ",'"+infoBis.xp+"','"+infoBis.lvl+"','"
                            + info.ptsDestin + "',0,0,'" + info.pognon + "',0,0,'" + info.courage + "','" + info.intelligence + "','" + info.charisme + "'" +
                            ",'" + info.adresse + "','" + info.force + "','"+infoBis.attaque+"','"+infoBis.parade+"','"+idPartie+"','"+""+"','"+""+"','"+""+"','"+""+"','"+""+"','"+""+"','"+""+"')");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }


                //ici la fonction ne fonctionne que pour la partie base de donnée donc on va par la suite ajouter à l'écrand les compétences directement
                //depuis la bdd
                DialogBox.setCompetenceBaseMetier(info.metier,info.nom);
                DialogBox.setCompetenceBaseOrigine(info.origine,info.nom);


                // ici on affiche toutes les compétences du perso enregistrer dans la db à l'écrand
                int idPerso=0;
                try {
                    ResultSet resultSetidPerso=MainS.conn.createStatement().executeQuery("select id from personnage where name='"+info.nom+"'");
                    while (resultSetidPerso.next()){
                        idPerso=resultSetidPerso.getInt("id");
                    }

                }catch (Exception e1){e1.printStackTrace();}

                //recuperer  les id de toutes les compétences du perso
                try {
                    ResultSet resultSetIdCompetence = MainS.conn.createStatement().executeQuery("select * from tablepersocompetence where idPerso='"+idPerso+"'");

                    while (resultSetIdCompetence.next()){
                        ResultSet resultSetNomCompétence = MainS.conn.createStatement().executeQuery("select nom from competence where id='"+resultSetIdCompetence.getInt("idCompetence")+"'");
                        if(resultSetNomCompétence.next()){

                            String[] LineForTable = {resultSetNomCompétence.getString("nom")};
                            pan.defaultTableModelCompetence.addRow(LineForTable);
                        }
                    }
                } catch (SQLException e1) { e1.printStackTrace(); }




                DialogBoxManuel.super.dispose();
            }
        };
        cancelButton.addActionListener(controlListener);
        okbutton.addActionListener(controlListener);

    }

    static void getPartieToMenu(JComboBox<String> jComboBoxGame) {
        try{
            //ici on récupère toutes les parties dans la db
            Statement statementGame = MainS.conn.createStatement();
            ResultSet resultSetGame =statementGame.executeQuery("SELECT * from partie");

            while (resultSetGame.next()){
                jComboBoxGame.addItem(resultSetGame.getString("nom"));
            }

        }catch (Exception e1){
            e1.printStackTrace();
        }
    }

    private static void createStandarsGUIforAttribute(Dimension dimension, JPanel panel, JLabel label, JTextField textField, String border) {
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(border));
        panel.setPreferredSize(dimension);
        textField.setHorizontalAlignment(JTextField.CENTER);
        panel.add(label,BorderLayout.WEST);
        panel.add(textField,BorderLayout.CENTER);
    }
}
