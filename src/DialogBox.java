import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

class DialogBox extends JDialog {

    private JTextField nom;
    private JComboBox  jboxOrigine,jboxMetier,jboxSexe;
    private JButton okbutton,cancelButton;
    private Collecteur info;
    static Panneau pan = new Panneau();

    public DialogBox(JFrame parent,String title,boolean modal){
        super(parent,title,modal);
        this.setSize(500,515);
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
        panNom.setPreferredSize(classicDimension);
        nom = new JTextField();
        nom.setPreferredSize(new Dimension(100,30));
        panNom.setBorder(BorderFactory.createTitledBorder("nom du personnage"));
        JLabel nomLabel = new JLabel("saisir un nom");
        panNom.add(nomLabel);
        panNom.add(nom);

        //ORIGINE
        JPanel panOrigne = new JPanel();
        panOrigne.setBorder(BorderFactory.createTitledBorder("origine du personnage"));
        panOrigne.setPreferredSize(classicDimension);
        jboxOrigine = new JComboBox();
        ArrayList<String> arrayOrigine= new ArrayList<>();
        arrayOrigine.add("humain");
        JLabel labelOrigine = new JLabel("origine :");
        panOrigne.add(labelOrigine);
        panOrigne.add(jboxOrigine);

        //METIER
        JPanel panMetier= new JPanel();
        panMetier.setBorder(BorderFactory.createTitledBorder("metier du personnage"));
        panMetier.setPreferredSize(classicDimension);
        jboxMetier = new JComboBox();
        ArrayList<String> arrayMetier = new ArrayList<>();
        arrayMetier.add("bourreau");
        JLabel labelMetier = new JLabel("métier :");
        panMetier.add(labelMetier);
        panMetier.add(jboxMetier);

        //SEXE
        JPanel panSexe = new JPanel();
        panSexe.setPreferredSize(classicDimension);
        panSexe.setBorder(BorderFactory.createTitledBorder("sexe du personnage"));
        jboxSexe = new JComboBox();
        String[] listeSexe = {"homme","femme","?"};
        for (String aListeSexe : listeSexe) {
            //noinspection unchecked
            jboxSexe.addItem(aListeSexe);
        }
        JLabel labelSexe = new JLabel("sexe :");
        panSexe.add(labelSexe);
        panSexe.add(jboxSexe);

        //COURAGE
        JPanel panCourage = new JPanel();
        panCourage.setBorder(BorderFactory.createTitledBorder("score de courage"));
        panCourage.setPreferredSize(classicDimension);
        //donne un nombre aléatoire
        int nombreAleatoire = 1 + (int)(Math.random() * ((6 - 1) + 1));
        int scoreCourage=nombreAleatoire+7;
        JLabel labelCourage = new JLabel("courage :");
        JLabel labelScoreCourage = new JLabel("" + scoreCourage);
        panCourage.add(labelCourage);
        panCourage.add(labelScoreCourage);

        //FORCE
        JPanel panForce = new JPanel();
        panForce.setBorder(BorderFactory.createTitledBorder("score de Force"));
        panForce.setPreferredSize(classicDimension);
        //donne un nombre aléatoire
        nombreAleatoire = 1 + (int)(Math.random() * ((6 - 1) + 1));
        int scoreForce=nombreAleatoire+7;
        JLabel labelForce = new JLabel("Force :");
        JLabel labelScoreForce = new JLabel("" + scoreForce);
        panForce.add(labelForce);
        panForce.add(labelScoreForce);

        //INTELLIGENCE
        JPanel panIntelligence = new JPanel();
        panIntelligence.setBorder(BorderFactory.createTitledBorder("score de Intelligence"));
        panIntelligence.setPreferredSize(classicDimension);
        //donne un nombre aléatoire
        nombreAleatoire = 1 + (int)(Math.random() * ((6 - 1) + 1));
        int scoreIntelligence=nombreAleatoire+7;
        JLabel labelIntelligence = new JLabel("Intelligence :");
        JLabel labelScoreIntelligence = new JLabel("" + scoreIntelligence);
        panIntelligence.add(labelIntelligence);
        panIntelligence.add(labelScoreIntelligence);

        //ADRESSE
        JPanel panAdresse = new JPanel();
        panAdresse.setBorder(BorderFactory.createTitledBorder("score de Adresse"));
        panAdresse.setPreferredSize(classicDimension);
        //donne un nombre aléatoire
        nombreAleatoire = 1 + (int)(Math.random() * ((6 - 1) + 1));
        int scoreAdresse=nombreAleatoire+7;
        JLabel labelAdresse = new JLabel("Adresse :");
        JLabel labelScoreAdresse = new JLabel("" + scoreAdresse);
        panAdresse.add(labelAdresse);
        panAdresse.add(labelScoreAdresse);

        //CHARISME
        JPanel panCharisme = new JPanel();
        panCharisme.setBorder(BorderFactory.createTitledBorder("score de Charisme"));
        panCharisme.setPreferredSize(classicDimension);
        //donne un nombre aléatoire
        nombreAleatoire = 1 + (int)(Math.random() * ((6 - 1) + 1));
        int scoreCharisme=nombreAleatoire+7;
        JLabel labelCharisme = new JLabel("Charisme :");
        JLabel labelScoreCharisme = new JLabel("" + scoreCharisme);
        panCharisme.add(labelCharisme);
        panCharisme.add(labelScoreCharisme);

        //POGNON
        JPanel panPognon = new JPanel();
        panPognon.setBorder(BorderFactory.createTitledBorder("ton pognon"));
        panPognon.setPreferredSize(classicDimension);
        nombreAleatoire = 1 + (int)(Math.random() * ((6 - 1) + 1));
        int pognon = (2*nombreAleatoire)*10;
        JLabel labelPognon = new JLabel("ton pécule :");
        JLabel pecule = new JLabel("" + pognon);
        panPognon.add(labelPognon);
        panPognon.add(pecule);

        //POINT DE DESTIN
        JPanel panelDestin = new JPanel();
        panelDestin.setPreferredSize(new Dimension(485,60));
        panelDestin.setBorder(BorderFactory.createTitledBorder("point(s) de Destin"));
        nombreAleatoire = 1 + (int)(Math.random() * ((4 - 1) + 1));
        int ptsDestin = nombreAleatoire-1;
        JLabel labelDestin = new JLabel("tes points :");
        JLabel destin = new JLabel("" + ptsDestin);
        panelDestin.add(labelDestin);
        panelDestin.add(destin);

        //partie à choisir
        JPanel jPanelGame = new JPanel();
        jPanelGame.setPreferredSize(new Dimension(485,60));
        jPanelGame.setBorder(BorderFactory.createTitledBorder("choisir la partie"));
        JComboBox<String> jComboBoxGame = new JComboBox<>();
        JLabel jLabelGame = new JLabel("nom : ");

        DialogBoxManuel.getPartieToMenu(jComboBoxGame);
        jPanelGame.add(jLabelGame);
        jPanelGame.add(jComboBoxGame);

        checkRestriction(scoreCourage,scoreIntelligence,scoreCharisme,scoreAdresse,scoreForce,arrayOrigine,arrayMetier,jboxMetier,jboxOrigine);

        //BOUTON DE CONTROLE
        okbutton = new JButton("OK");
        cancelButton = new JButton("cancel");

        JPanel content=new JPanel();
        this.setContentPane(content);

        //AJOUT DES DIFFERENT ELEMENT DANS LE PANEL PRINCIPAL
        content.add(panNom);
        content.add(panSexe);
        content.add(panCourage);
        content.add(panForce);
        content.add(panIntelligence);
        content.add(panAdresse);
        content.add(panCharisme);
        content.add(panPognon);
        content.add(panelDestin);
        content.add(panOrigne);
        content.add(panMetier);
        content.add(jPanelGame);
        content.add(okbutton);
        content.add(cancelButton);

        ActionListener controlListener = e -> {
            if (e.getSource()==cancelButton){
                DialogBox.super.dispose();
            }

            if (e.getSource()==okbutton){

                MainS.arrayListNomPersonnage.add(nom.getText());

                //recuperation de toute les variable dans un seul objet parce que c'est plus simple ohlo
                info = new Collecteur(nom.getText(),(String)jboxMetier.getSelectedItem(),(String)jboxOrigine.getSelectedItem(),
                        (String) jboxSexe.getSelectedItem(),pan.tabTxtStatPvEa[0],pan.tabTxtActualPvEa[0],pan.tabTxtStatPvEa[1],
                        pan.tabTxtActualPvEa[1],scoreCourage,scoreForce,scoreIntelligence,scoreCharisme,scoreAdresse,pognon,ptsDestin);

                //ici on donne les pv et l'energie astrale au perso en fonction de leur origine/metier et on rentre cette valeur dans les champs de texte
                checkForPvEa(info.origine,info.metier,info.pvMax,info.pvActuel,info.eaMax,info.eaActuel);

                //variable qui viennent de Main
                //on ajoute un onglet au tableau d'onglet et on ajoute le nom du personnage a une liste de personnage !
                MainS.onglet.addTab(info.nom, pan);
                MainS.listOfOnglet.add(info.nom);

                pan.labTxtNom.setText(info.nom);
                pan.labTxtOrigine.setText(info.origine);
                pan.labTxtMetier.setText(info.metier);
                pan.labTxtSexe.setText(info.sexe);
                pan.tabTxtArgent[2].setText(Integer.toString(info.pognon));
                pan.tabtxtXpLv[0].setText("0");
                pan.tabtxtXpLv[1].setText("1");
                pan.tabtxtXpLv[2].setText(Integer.toString(info.ptsDestin));
                pan.tabLabStatCaracBase[0].setText(Integer.toString(info.courage));
                pan.tabLabStatCaracBase[1].setText(Integer.toString(info.intelligence));
                pan.tabLabStatCaracBase[2].setText(Integer.toString(info.charisme));
                pan.tabLabStatCaracBase[3].setText(Integer.toString(info.adresse));
                pan.tabLabStatCaracBase[4].setText(Integer.toString(info.force));
                pan.tabLabStatCaracBase[5].setText(Integer.toString(((info.adresse + info.intelligence)) / 2));
                pan.tabLabStatCaracBase[6].setText(Integer.toString((info.intelligence + info.charisme) / 2));
                pan.tabLabStatCaracBase[7].setText(Integer.toString((info.courage + info.intelligence + info.force) / 3));
                pan.tabLabStatCaracBase[8].setText("8");
                pan.tabLabStatCaracBase[9].setText("10");

                /////-------------------//////
                //ON ENVOIS LES DONNÉES A LA DATA BASE
                try {
                    Statement state = MainS.conn.createStatement();

                    //ici on récupère l'id de la partie selectionner pour l'utiliser comme foreign key pour la table personnage
                    ResultSet resultSetIDGame = state.executeQuery("SELECT idpartie from partie where nom='"+jComboBoxGame.getSelectedItem()+"'");
                    int idPartie=0;
                    while(resultSetIDGame.next()){
                        idPartie=resultSetIDGame.getInt("idpartie");
                    }

                    String chaineVide ="";
                    //ligne qui envoit toutes les informations du personnage  à la putin de fucking base de donner
                    state.executeUpdate("INSERT INTO personnage (name, origine, metier, sexe, pvmax, pvactuel, pamax, paactuel, xp, lvl," +
                            " ptsdestin, berylium, thritil, gold, argent, cuivre, courage, intelligence, charisme, adresse, force, attaque," +
                            " parade,fk_partie,couragemod,intelligencemod,charismemod,adressemod,forcemod,attaquemod,parademod) values('" + info.nom + "','" + info.origine + "','" + info.metier + "','" + info.sexe + "'" +
                            ",'"+info.pvMax.getText()+"','"+info.pvActuel.getText()+"','"+info.eaMax.getText()+"','"+info.eaActuel.getText()+"',0,1,'"
                            + info.ptsDestin + "',0,0,'" + info.pognon + "',0,0,'" + info.courage + "','" + info.intelligence + "','" + info.charisme + "'" +
                            ",'" + info.adresse + "','" + info.force + "',8,10,'"+idPartie+"','"+chaineVide+"','"+chaineVide+"','"+chaineVide+"','"+chaineVide+"','"+chaineVide+"','"+chaineVide+"','"+chaineVide+"')");

                    //petite ligne pour ne pas laisser de valeur null mais plustot une chaine de caratère vide dans la bdd


                    //on ajoute l'id du personnage qu ivient d'etre créer a la liste des id
                    ResultSet resultSetid =state.executeQuery("select * from personnage_id_seq");
                    int id=0;
                    while (resultSetid.next()){
                        id=resultSetid.getInt(1);
                    }
                    Panneau.listID.add(id);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                setCompetenceBaseOrigine(info.origine,info.nom);
                setCompetenceBaseMetier(info.metier,info.nom);
                setAttPar(info.origine,info.metier,pan.tabLabStatCaracBase[8],pan.tabLabStatCaracBase[9]);
                Panneau.listPanel.add(pan);
                DialogBox.super.dispose();

                MainS.onglet.setSelectedIndex(MainS.onglet.getTabCount()-1);
            }
        };


        cancelButton.addActionListener(controlListener);
        okbutton.addActionListener(controlListener);

    }

    //dans  cette methode on va changer les valeurs des caractéristique spécifique à chaque origine/metier
    private void checkForPvEa(String origine, String metier, JTextField pvMax, JTextField pvActuel, JTextField eaMax, JTextField eaActuel){
        int pv=0;
        int ea=0;
        //toutes les stats de pv et ea pour l'humain
        if(origine.equals("humain")){ pv=30;ea=0; }
        if(origine.equals("humain")&& metier.equals("guerrier")){ pv=35;ea=0; }
        if(origine.equals("humain")&& metier.equals("mage")){ pv=20;ea=30; }
        if(origine.equals("humain")&& metier.equals("paladin")){ pv=32;ea=10; }
        if(origine.equals("humain")&& metier.equals("bourreau")){ pv=32;ea=0; }

        //toutes les stast de pv et ea pour le barebare
        if(origine.equals("barebare")){pv=35;ea=0;}
        if(origine.equals("barebare")&& metier.equals("guerrier")){pv=35;ea=0;}
        if(origine.equals("barebare")&& metier.equals("bourreau")){pv=37;ea=0;}

        //toutes les stast de pv et ea pour le nain
        if(origine.equals("nain")){pv=35;ea=0;}
        if(origine.equals("nain")&& metier.equals("guerrier")){pv=40;ea=0;}
        if(origine.equals("nain")&& metier.equals("bourreau")){pv=37;ea=0;}

        //toutes les stast de pv et ea pour le haut elfe
        if(origine.equals("haut elfe")){pv=25;ea=0;}
        if(origine.equals("haut elfe")&&metier.equals("mage")){pv=18;ea=30;}
        if(origine.equals("haut elfe")&&metier.equals("paladin")){pv=27;ea=10;}
        if(origine.equals("haut elfe")&&metier.equals("bourreau")){pv=27;ea=0;}

        //toutes les stast pour un demi elfe
        if(origine.equals("demi elfe")){pv=28;ea=0;}
        if(origine.equals("demi elfe")&&metier.equals("guerrier")){pv=33;ea=0;}
        if(origine.equals("demi elfe")&&metier.equals("mage")){pv=20;ea=30;}
        if(origine.equals("demi elfe")&&metier.equals("paladin")){pv=30;ea=10;}
        if(origine.equals("demi elfe")&&metier.equals("bourreau")){pv=30;ea=0;}

        //toutes les stats pour un elfe sylvain
        if(origine.equals("elfe sylvain")){pv=25;ea=0;}
        if(origine.equals("elfe sylvain")&&metier.equals("mage")){pv=18;ea=30;}
        if(origine.equals("elfe sylvain")&&metier.equals("paladin")){pv=27;ea=10;}
        if(origine.equals("elfe sylvain")&&metier.equals("bourreau")){pv=27;ea=0;}

        //toutes les stats pour un elfe noir
        if(origine.equals("elfe noir")){pv=25;ea=0;}
        if(origine.equals("elfe noir")&&metier.equals("guerrier")){pv=30;ea=0;}
        if(origine.equals("elfe noir")&&metier.equals("mage")){pv=18;ea=30;}
        if(origine.equals("elfe noir")&&metier.equals("paladin")){pv=27;ea=10;}
        if(origine.equals("elfe noir")&&metier.equals("bourreau")){pv=27;ea=0;}

        //toutes les stats pour un orque
        if(origine.equals("orque")){pv=35;ea=0;}
        if(origine.equals("orque")&&metier.equals("guerrier")){pv=40;ea=0;}
        if(origine.equals("orque")&&metier.equals("bourreau")){pv=37;ea=0;}

        //toutes les stats pour un demi orque
        if(origine.equals("demi orque")){pv=35;ea=0;}
        if(origine.equals("demi orque")&&metier.equals("guerrier")){pv=40;ea=0;}
        if(origine.equals("demi orque")&&metier.equals("bourreau")){pv=37;ea=0;}
        

        //toutes les stats pour un gobelin
        if(origine.equals("gobelin")){pv=20;ea=0;}
        if(origine.equals("gobelin")&&metier.equals("bourreau")){pv=22;ea=0;}

        //toutes les stats pour un ogre
        if(origine.equals("ogre")){pv=45;ea=0;}
        if(origine.equals("ogre")&&metier.equals("guerrier")){pv=50;ea=0;}
        if(origine.equals("ogre")&&metier.equals("bourreau")){pv=47;ea=0;}

        //toutes les stats du hobbits
        if(origine.equals("hobbit")){pv=25;ea=0;}
        if(origine.equals("hobbit")&&metier.equals("paladin")){pv=27;ea=10;}
        if(origine.equals("hobbit")&&metier.equals("bourreau")){pv=27;ea=0;}

        //toutes les stats du gnôme des fôrets
        if(origine.equals("gnôme des fôrets")){pv=15;ea=0;}

        //toutes les stats du nain de la mafia
        if(origine.equals("nain de la mafia")){pv=38;ea=0;}
        if(origine.equals("nain de la mafia")&&metier.equals("guerrier")){pv=43;ea=0;}
        if(origine.equals("nain de la mafia")&&metier.equals("bourreau")){pv=40;ea=0;}

        //toutes les stats du amazone
        if(origine.equals("amazone")){pv=38;ea=0;}
        if(origine.equals("amazone")&&metier.equals("guerrier")){pv=43;ea=0;}
        if(origine.equals("amazone")&&metier.equals("bourreau")){pv=40;ea=0;}

        pvMax.setText(Integer.toString(pv));
        pvActuel.setText(Integer.toString(pv));
        eaMax.setText(Integer.toString(ea));
        eaActuel.setText(Integer.toString(ea));
    }

    //methode qui check les restriction lié au metier et à l'origine
    private void checkRestriction(int scoreCourage, int scoreIntelligence, int scoreCharisme, int scoreAdresse,
                                  int scoreForce, ArrayList<String> arrayOrigine, ArrayList<String> arrayMetier,
                                  JComboBox jboxMetier, JComboBox jboxOrigine){
        //CAS OU TU  PEUX  ÊTRE BARBARE
        if (( scoreCourage>=12 && scoreForce>=13)){
            arrayOrigine.add("barebare");
        }

        //CAS OU TU  PEUX  ÊTRE UN NAIN
        if((scoreCourage>=11 && scoreForce>=12)){
            arrayOrigine.add("nain");
        }

        //CAS OU TU  PEUX  ÊTRE UN HAUT ELFE
        if((scoreIntelligence>=11&& scoreCharisme>=12&&scoreAdresse>=12&&scoreForce<=12)){
            arrayOrigine.add("haut elfe");
        }

        //CAS OU TU  PEUX  ÊTRE UN DEMI ELFE
        if (( scoreCharisme>=10 && scoreAdresse>=11)){
            arrayOrigine.add("demi elfe");
        }

        //CAS OU TU  PEUX  ÊTRE UN ELFE SYLVAIN
        if((scoreCharisme>=12 && scoreAdresse>=10 && scoreForce<=11)){
            arrayOrigine.add("elfe sylvain");
        }

        //CAS OU TU  PEUT  ÊTRE UN EFLE NOIR
        if ((scoreIntelligence>=12&&scoreAdresse>=13)){
            arrayOrigine.add("elfe noir");
        }

        //CAS OU TU  PEUX  ÊTRE UN ORQUE (C'EST TRISTE QUAND MÊME)
        if((scoreIntelligence<=8&&scoreCharisme<=10&&scoreForce>=12)){
            arrayOrigine.add("orque");
        }

        //CAS OU TU  PEUX  ÊTRE UN DEMI ORQUE
        if((scoreIntelligence<=10&&scoreAdresse<=11&&scoreForce>=12)){
            arrayOrigine.add("demi orque");
        }

        //CAS OU TU  PEUX  ÊTRE UN GOBELIN
        if((scoreCourage<=10&&scoreIntelligence<=10&&scoreCharisme<=8&&scoreForce<=9)){
            arrayOrigine.add("gobelin");
        }

        //CAS OU TU  PEUX  ÊTRE UN OGRE
        if((scoreIntelligence<=9&&scoreCharisme<=10&&scoreAdresse<=11&&scoreForce>=13)){
            arrayOrigine.add("ogre");
        }

        //CAS OU TU  PEUX  ÊTRE UN HOBBIT
        if((scoreCourage>=12&&scoreIntelligence>=10&&scoreForce<=10)){
            arrayOrigine.add("hobbit");
        }

        //CAS OU TU  PEUX  ÊTRE UN GNÔME
        if((scoreIntelligence>=10&&scoreAdresse>=13&&scoreForce<=8)){
            arrayOrigine.add("gnôme des fôrets");
        }

        //ICI ON MARQUE TOUTES LES RESTRICTIONS DES METIERS PAR RAPPORT AU STAT
        //CAS OU TU  PEUX  ÊTRE UN GUERRIER
        if((scoreCourage>=12&&scoreForce>=12)){
            arrayMetier.add("guerrier");
        }
        //CAS OU TU  PEUX  ÊTRE UN NINJA OU UN ASSASSIN
        if((scoreAdresse>=13)){
            arrayMetier.add("assassin");
        }
        //CAS OU TU  PEUX ÊTRE UN VOLEUR
        if((scoreAdresse>=12)){
            arrayMetier.add("voleur");
        }
        //CAS OU TU  PEUX  ÊTRE UN PRÊTRE
        if((scoreCharisme>=12)){
            arrayMetier.add("prêtre");
        }
        //CAS OU TU  PEUX  ÊTRE UN MAGE
        if((scoreIntelligence>=12)){
            arrayMetier.add("mage");
        }
        //CAS OU TU  PEUX  ÊTRE UN PALADIN
        if((scoreCourage>=12&&scoreIntelligence>=10&&scoreCharisme>=11&&scoreForce>=9)){
            arrayMetier.add("paladin");
        }
        //CAS OU TU  PEUX  ÊTRE UN RANGER
        if((scoreCharisme>=10&&scoreAdresse>=10)){
            arrayMetier.add("ranger");
        }
        //CAS OU TU  PEUX  ÊTRE UN MENESTREL
        if((scoreCharisme>=12&&scoreAdresse>=11)){
            arrayMetier.add("menestrel");
        }
        //CAS OU TU  PEUX  ÊTRE UN MARCHAND
        if((scoreIntelligence>=12&&scoreCharisme>=11)){
            arrayMetier.add("marchand");
        }
        //CAS OU TU  PEUX  ÊTRE BOURGEOIS/NOBLE
        if((scoreIntelligence>=10&&scoreCharisme>=11)){
            arrayMetier.add("noble");
        }
        //CAS OU TU  PEUX  ÊTRE UN PIRATE
        if((scoreCourage>=11&&scoreAdresse>=11)){
            arrayMetier.add("pirate");
        }
        //CAS OU TU  PEUX  ÊTRE INGENIEUR
        if((scoreAdresse>=11)){
            arrayMetier.add("ingenieur");
        }
        //CAS OU TU PEUX ÊTRE UN NAIN DE LA MAFIA
        if(scoreCourage>=10&&scoreIntelligence>=11&&scoreAdresse>=12&&scoreForce>=11){
            arrayOrigine.add("nain de la mafia");
        }
        //CAS OU TU PEUX ÊTRE UN SBIRE
        if(scoreCourage<=10&&scoreIntelligence<=11&&scoreCharisme<=11){
            arrayMetier.add("sbire");
        }
        //CAS OU TU PEUX ÊTRE UNE AMAZONE SYLDERIENNE
        if(scoreCourage>=12&&scoreCharisme>=12&&scoreAdresse>=11&&scoreForce>=12){
            arrayOrigine.add("amazone");
        }

        //et la on remplit les combo box avec leselements possible
        for (String anArrayOrigine : arrayOrigine) {
            //noinspection unchecked
            jboxOrigine.addItem(anArrayOrigine);
        }

        for (String anArrayMetier : arrayMetier) {
            //noinspection unchecked
            jboxMetier.addItem(anArrayMetier);
        }
    }

    //à faire le set d'attaque et parade pour tous les metier/origines
    //des choix devront être fait par les joueurs par le biais de dialogbox à mon avis
    private void setAttPar(String _origine,String _metier,JLabel _att,JLabel _parr){
        if(_origine.equals("barebare")||_origine.equals("orque")||_origine.equals("ogre")){
            _att.setText("9");
            _parr.setText("9");
        }

        if(_origine.equals("gnômes des fôrets")){
            _att.setText("10");
            _parr.setText("8");
        }

        if(_metier.equals("assassin")){
            _att.setText("11");
            _parr.setText("8");
        }

    }

    public static void insertPersonnalCompetenceIntoDB(String nomComp, String nomPerso,String[] _LineForTable){

        int idComp= 0,idPerso = 0;

        try {
            //on récupère l'id de la compétence
            ResultSet resultSetIDComp = MainS.conn.createStatement().executeQuery("select id from competence where nom='"+nomComp+"'");
            while(resultSetIDComp.next()){
                idComp=resultSetIDComp.getInt("id");
            }
            resultSetIDComp.close();
            //on récupère l'id du perso
            ResultSet resultSetIDPerso = MainS.conn.createStatement().executeQuery("select id from personnage where name='"+nomPerso+"'");
            while(resultSetIDPerso.next()){
                idPerso=resultSetIDPerso.getInt("id");
            }
            resultSetIDPerso.close();

            //on test si le couple de compétence/perso n'existe pas déjà dans la db, car un message d'erreure àpparait en cas de doublon
            //si le couple n'existe pas déjà on le rajoute à la liste
            ResultSet resultSetDoublon = MainS.conn.createStatement().executeQuery("SELECT * from tablepersocompetence where idPerso='"+idPerso+"'and idCompetence='"+idComp+"'");

            if(resultSetDoublon.next()){
                System.out.println("il y a un doublon, la commande ne seras pas prise en comptes");
            }else{
                MainS.conn.createStatement().executeUpdate("insert into tablepersocompetence (idPerso, idCompetence) values ('"+idPerso+"','"+idComp+"')");
                pan.defaultTableModelCompetence.addRow(_LineForTable);
            }



        }catch (Exception e1){e1.printStackTrace();}
    }

    public static void setCompetenceBaseOrigine(String _origine,String nomPerso){

        if(_origine.equals("nain")){
            String newLine = "APPEL DU TONNEAU (INT) ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INSTINCT DU TRESOR (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "PÉNIBLE (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "RADIN (CHA, INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("barebare")){
            String newLine = "AMBIDEXTRIE (AD) ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHERCHER DES NOISES ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "SENTIR DES PIEDS ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TÊTE VIDE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("gobelin")){
            String newLine = "AGORAPHOBIE (INT) ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "APPEL DES RENFORTS ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "APPEL DU SAUVAGE (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ATTIRE LES MONSTRES ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INSTINCT DE SURVIE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INSTINCT DU TRESOR (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "SENTIR DES PIEDS ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TÊTE VIDE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("demi elfe")){
            String newLine = "APPEL DES RENFORTS ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "DÉTECTER (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHOURAVER (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "MÉFIANCE (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("elfe noir")){
            String newLine = "AGORAPHOBIE (INT) ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "DÉPLACEMENT SILENCIEUX (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "DÉTECTER (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TIRER CORRECTEMENT (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            
        }

        if(_origine.equals("gnôme des fôrets")){
            String newLine = "AMBIDEXTRIE (AD) ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "APPEL DES RENFORTS ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHEVAUCHER (AD, CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHOURAVER (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "DÉPLACEMENT SILENCIEUX (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INSTINCT DU TRÉSOR (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("hobbit")){
            String newLine = "APPEL DU TONNEAU (INT) ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CUISTOT (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INSTINCT DE SURVIE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ATTIRE LES MONSTRES ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "RESSEMBLE À RIEN ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("orque")){
            String newLine = "AGORAPHOBIE (INT) ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "APPEL DU SAUVAGE (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "APPEL DU TONNEAU (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "BOURRE-PIF ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INSTINCT DE SURVIE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "SENTIR DES PIEDS ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TÊTE VIDE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("haut elfe")){
            String newLine = "ÉRUDITION (INT) ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "RUNES BIZARRES (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TOMBER DANS LES PIÈGES ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("demi orque")){
            String newLine = "AGORAPHOBIE (INT) ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHERCHER DES NOISES ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INSTINCT DE SURVIE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "SENTIR DES PIEDS ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TÊTE VIDE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("elfe sylvain")){
            String newLine = "CHEVAUCHER (AD, CHA) ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "NAÏVETÉ TOUCHANTE (CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "PREMIERS SOINS (AD, INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TIRER CORRECTEMENT (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TOMBER DANS LES PIÈGES ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("ogre")){
            String newLine = "ARMES DE BOURRIN ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "APPEL DU TONNEAU (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "APPEL DU VENTRE (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INSTINCT DE SURVIE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INTIMIDER (COU, CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "SENTIR DES PIEDS ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TÊTE VIDE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("amazone")){
            String newLine = "ATTIRE LES MONSTRES ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHERCHER DES NOISES ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "BOURRE-PIF ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TÊTE VIDE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHEVAUCHER (AD, CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TRUC DE MAUVIETTE (PR) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ARMES LONGUES (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "SURVIVANTE (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "BEAUTÉ FÉROCE (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "IGNORANTE (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_origine.equals("nain de la mafia")){
            String newLine = "APPEL DU TONNEAU (INT) ";
            String []LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "AMBIDEXTRIE (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "BOURRE-PIF ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHOURAVER (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INSTINCT DU TRESOR (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "RADIN (CHA, INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "RESSEMBLE À RIEN ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ROUBLARD (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "BOND DATTAQUE (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

    }

    public static void setCompetenceBaseMetier(String _metier,String nomPerso){

        if(_metier.equals("assassin")){
            String newLine = "TIRER CORRECTEMENT (AD) ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "FRAPPER LÂCHEMENT (AT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "DÉPLACEMENT SILENCIEUX (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_metier.equals("noble")){
            String newLine = "APPEL DES RENFORTS ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ATTIRE LES MONSTRES ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHEVAUCHER (AD, CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ÉRUDITION (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "PÉNIBLE (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_metier.equals("bourreau")){
            String newLine = "APPEL DU TONNEAU (INT) ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ARMES DE BOURRIN ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "FRAPPER LÂCHEMENT (AT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INTIMIDER (COU, CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "LANGUES DES MONSTRES ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "MÉFIANCE (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "POIGNE DE FER (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "TORTIONNAIRE (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "INFÂME (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_metier.equals("guerrier")) {
            String newLine = "ARMES DE BOURRIN ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine, nomPerso, LineForTable);
            newLine = "BOURRE-PIF ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine, nomPerso, LineForTable);
        }

        if(_metier.equals("ingenieur")){
            String newLine = "BRICOLO DU DIMANCHE (AD) ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "DÉSAMORCER (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "FORGERON (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "RESSEMBLE À RIEN ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "SERRURIER (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_metier.equals("mage")) {
            String newLine = "ÉRUDITION (INT) ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine, nomPerso, LineForTable);
            newLine = "RÉCUPÉRATION (PA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine, nomPerso, LineForTable);
            newLine = "RUNES BIZARRES (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine, nomPerso, LineForTable);
        }

        if(_metier.equals("marchand")){
            String newLine = "APPEL DES RENFORTS ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ARNAQUE ET CARAMBOUILLE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ÉRUDITION (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "FARIBOLES (INT, CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "MÉFIANCE (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_metier.equals("menestrel")){
            String newLine = "ATTIRE LES MONSTRES ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHEVAUCHER (AD, CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ÉRUDITION (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "FARIBOLES (INT, CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "JONGLAGE ET DANSE (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "NAÏVETÉ TOUCHANTE (CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_metier.equals("paladin")) {
            String newLine = "CHEVAUCHER (AD, CHA) ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine, nomPerso, LineForTable);
            newLine = "INTIMIDER (COU, CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine, nomPerso, LineForTable);
            newLine = "RÉCUPÉRATION (PA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine, nomPerso, LineForTable);

        }

        if(_metier.equals("pirate")){
            String newLine = "APPEL DES RENFORTS ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "APPEL DU TONNEAU (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ARNAQUE ET CARAMBOUILLE ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHOURAVER (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "ESCALADER (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "NAGER (AD, FO) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_metier.equals("prêtre")) {
            String newLine = "ÉRUDITION (INT) ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine, nomPerso, LineForTable);
            newLine = "MÉFIANCE (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine, nomPerso, LineForTable);
            newLine = "RÉCUPÉRATION (PA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine, nomPerso, LineForTable);
        }

        if(_metier.equals("ranger")){
            String newLine = "DÉPLACEMENT SILENCIEUX (AD) ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "DÉTECTER (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHEVAUCHER (AD, CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "NAGER (AD, FO) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "PISTER (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_metier.equals("sbire")){
            String newLine = "APPEL DES RENFORTS ";
            String[] LineForTable = {newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "FARIBOLES (INT, CHA) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "FOUILLER DANS LES POUBELLES ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "FRAPPER LÂCHEMENT (AT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "LANGUES DES MONSTRES ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "MÉFIANCE (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "MENDIER ET PLEURNICHER (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "RESSEMBLE À RIEN ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "MISÉRABLE (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "SURVIVALISTE (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "PLEUTRE (spécial) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }

        if(_metier.equals("voleur")){
            String newLine= "APPEL DES RENFORTS ";
            String[]  LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "CHOURAVER (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine = "DÉPLACEMENT SILENCIEUX (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "DÉTECTER (INT) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
            newLine= "SERRURIER (AD) ";
            LineForTable = new String[]{newLine};
            insertPersonnalCompetenceIntoDB(newLine,nomPerso,LineForTable);
        }


    }

}
