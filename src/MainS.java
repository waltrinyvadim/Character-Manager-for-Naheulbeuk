import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class MainS {
    //PERMET L'OUVERTURE DE SES VARIABLES VERS DialogBox POUR DONNER LE NOM DU PERSONNAGE AU TITRE DE L'ONGLET
    public static final JTabbedPane onglet = new JTabbedPane();
    public static final ArrayList<String > listOfOnglet = new ArrayList<>();

    public static final ArrayList<String> arrayListNomPersonnage = new ArrayList<>();
    public static java.sql.Connection conn;

    public static void main(String[] args){

        //ActionListener qui permet la suppression du personnage ainsi que dans la database
        ActionListener actionListenerDelettePerso = new ActionListener() {
            String persoToSupprimer;
            @Override
            public void actionPerformed(ActionEvent e) {
                //le try catch est la au cas ou on essaye de supprimer alors qu'il n'ya pas d'element a supprimer
                try {
                    // on récupère tous les nom de personnages dans la database
                    arrayListNomPersonnage.clear();
                    fillArrayWithAllPersoName();

                    // on doit convertir l'array en tableau pour le passer en parametre du JoptionPan
                    String[] tabNomPerso = new String[arrayListNomPersonnage.size()];
                    for (int i = 0; i < arrayListNomPersonnage.size(); i++) {
                        tabNomPerso[i]=arrayListNomPersonnage.get(i);
                    }

                    //les parametres de la methode sont le message dans la box, le titre puis la tableau a passer en paramètre puis l'indice selesctionner par defaut
                    persoToSupprimer = (String) JOptionPane.showInputDialog(null,
                            "Quel personnage voulais vous supprimez ?",
                            "JDR manager",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tabNomPerso,
                            tabNomPerso[0]);

                    //ici on supprime le personnage de la base de donnée
                    try { conn.createStatement().executeUpdate("delete from personnage where name='"+persoToSupprimer+"'"); }
                    catch (Exception e1){ e1.printStackTrace();}

                    //ici on supprime le personnage de l'interface
                    for (int i = 0; i < onglet.getTabCount(); i++) {
                        if (listOfOnglet.get(i).equals(persoToSupprimer)){
                            onglet.remove(i);
                            listOfOnglet.remove(i);
                            break;
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException e1){
                    JOptionPane.showMessageDialog(null, "tu peux pas supprimer si ya rien a supprimer boufon",
                            "JDR manager", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        ActionListener actionListenerSupprimerPartie = new ActionListener() {
            String partieToDelette;
            @Override
            public void actionPerformed(ActionEvent e) {
                //on récupère la liste des personnages
                ArrayList<String> arrayListNamePartie = new ArrayList<>();
                try {
                    ResultSet resultSetNomPartie = conn.createStatement().executeQuery("select nom from partie");
                    while (resultSetNomPartie.next()){
                        arrayListNamePartie.add(resultSetNomPartie.getString("nom"));
                    }

                }catch (Exception e1){e1.printStackTrace();}

                //on convertis la liste des perso en un tableau
                String[] tabNomPerso = new String[arrayListNamePartie.size()];
                for (int i = 0; i < arrayListNamePartie.size(); i++) {
                    tabNomPerso[i]=arrayListNamePartie.get(i);
                }

                try {
                    partieToDelette=(String) JOptionPane.showInputDialog(null,"qu'elle partie desirez vous supprimer ?",
                            "JDR manager", JOptionPane.QUESTION_MESSAGE,null,tabNomPerso,tabNomPerso[0]);

                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,"Il n'y a pas de partie à supprimer");
                }

                ArrayList<Integer> arrayListDesFKpartieDesPerso = new ArrayList<>();
                selectAllForeignKeyOfPersoFromOnglet(arrayListDesFKpartieDesPerso);

                int idPartie=0;
                try {
                    ResultSet resultSetID = conn.createStatement().executeQuery("select idpartie from partie where nom='"+partieToDelette+"'");
                    while (resultSetID.next()){
                        idPartie=resultSetID.getInt("idpartie");
                    }
                } catch (SQLException e1) { e1.printStackTrace(); }

                for (int i = 0; i < listOfOnglet.size(); i++) {
                    for (Integer anArrayListDesFKpartieDesPerso : arrayListDesFKpartieDesPerso) {
                        if (anArrayListDesFKpartieDesPerso == idPartie) {

                            listOfOnglet.remove(i);
                            onglet.remove(i);
                        }
                    }
                }

                // puis on supprime la partie dont le nom à étais selectionner
                try {
                    conn.createStatement().executeUpdate("delete from partie where nom='"+partieToDelette+"'");
                }catch (Exception e1){e1.printStackTrace();}

            }
        };

        //ActionListener qui import tous les personnage
        ActionListener actionListenerImportPerso = new ActionListener() {
            String persoToImport;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    arrayListNomPersonnage.clear();
                    fillArrayWithAllPersoName();

                    String[] tabNomPerso = new String[arrayListNomPersonnage.size()];
                    for (int i = 0; i < arrayListNomPersonnage.size(); i++) {
                        tabNomPerso[i]=arrayListNomPersonnage.get(i);
                    }

                    persoToImport = (String) JOptionPane.showInputDialog(null,
                            "Qui voulais vous afficher à l'ecrand ?",
                            "JDR manager",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tabNomPerso,
                            tabNomPerso[0]);

                    //ici on vérifie d'abord si le personnage n'est pas déjà afficher à l'écrand avant d'importerl le perso
                    boolean ispresent=false;

                    for (String aListOfOnglet : listOfOnglet) {
                        if (aListOfOnglet.equals(persoToImport)) {
                            ispresent = true;
                            JOptionPane.showMessageDialog(null, "Ce perso est déjà afficher à l'écrand", "JDR manager", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    if(!ispresent){
                        importPerso("select * from personnage where name='"+persoToImport+"'");
                    }

                }catch (IndexOutOfBoundsException e1){
                    JOptionPane.showMessageDialog(null, "tu peux pas importer si ya personne à importer boufon",
                            "JDR manager", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        //ActionListener qui permet l'import de tous les peronnages d'une partie
        ActionListener actionListenerImportPartie = new ActionListener() {
            String partieToImport;
            @Override
            public void actionPerformed(ActionEvent e) {

                //on recupère tous les nom de partie puis on convertie la liste en un tableau
                ArrayList<String> arrayListNamePartie = new ArrayList<>();
                try {
                    ResultSet resultSetNom =conn.createStatement().executeQuery("select nom from partie ");
                    while (resultSetNom.next()){
                        arrayListNamePartie.add(resultSetNom.getString("nom"));
                    }
                } catch (SQLException e1) { e1.printStackTrace();}

                String[] tabNomPartie = new String[arrayListNamePartie.size()];
                for (int i = 0; i < arrayListNamePartie.size(); i++) {
                    tabNomPartie[i]=arrayListNamePartie.get(i);
                }

               try {
                   //le JoptionPan qui permet le dialogue et de choisir qu'elle partie on veut importer
                   partieToImport = (String) JOptionPane.showInputDialog(null,
                           "Qu'elle partie voulais vous importer ?",
                           "JDR manager",
                           JOptionPane.QUESTION_MESSAGE,
                           null,
                           tabNomPartie,
                           tabNomPartie[0]);

               }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,"Il n'y à pas de partie à importer");
               }

                //ici on récupère l'id de la partie à partir de la selection faite par l'utiliteur
                int idPartie=0;
                try {
                    ResultSet resultSetID = conn.createStatement().executeQuery("select idpartie from partie where nom='"+partieToImport+"'");
                    while (resultSetID.next()){
                        idPartie=resultSetID.getInt("idpartie");
                        break;
                    }
                } catch (SQLException e1) { e1.printStackTrace(); }

                //avec ces deux blocs on recupère toutes les foreign key différente de joueur et on les met dans un liste
                //puis on supprime de l'onglet tous les perso qui ont la même foreign key que la partie que l'on veut ajouter
                //pour éviter les doublons lors de l'import
                ArrayList<Integer> arrayListDesFKpartieDesPerso = new ArrayList<>();
                selectAllForeignKeyOfPersoFromOnglet(arrayListDesFKpartieDesPerso);

                for (int i = 0; i < listOfOnglet.size(); i++) {
                    for (Integer anArrayListDesFKpartieDesPerso : arrayListDesFKpartieDesPerso) {
                        if (anArrayListDesFKpartieDesPerso == idPartie) {
                            saveIntoDb();
                            listOfOnglet.remove(i);
                            onglet.remove(i);
                        }
                    }
                }
                //puis une fois le champs vider des elements déjà existant on réimporte tout
                importPerso("select * from personnage where fk_partie='"+idPartie+"'");
            }
        };

        //action listener qui va vider l'écrand
        ActionListener actionListenerClearAll = e -> {
            saveIntoDb();
            onglet.removeAll();
            listOfOnglet.clear();
        };

        ActionListener actionListenerClearActivateOnglet = e -> {
            saveIntoDb();
            onglet.remove(onglet.getSelectedComponent());

            if(onglet.getSelectedIndex()<0){
                listOfOnglet.remove(0);
            }else{ listOfOnglet.remove(onglet.getSelectedIndex()); }

        };

        //ligne de code qui permet la connexion a la database
        try {
            Class.forName("org.postgresql.Driver");
            String DB_USER="postgres";
            String DB_URL="jdbc:postgresql://localhost:5432/DB_perso";
            String DB_PASSWORD="31415";

            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        }catch (Exception e1){
            e1.printStackTrace();
        }


        //portion du code qui recupere la dimension de l'écrand pour adapter la taille de la fenêtre à l'écrand
        Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height =(int) dim.getHeight();
        int width =(int) dim.getWidth();

        //le blabla habituel
        JFrame frame = new JFrame("jdr manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(width,height));

        //on créee le bordellayout pour manager les postionnements des composants
        BorderLayout brdLayout = new BorderLayout();

        JPanel panMenuBouton = new JPanel();
        //postion du menu et de la zone ou il ya les pannel
        frame.setLayout(brdLayout);
        frame.add(panMenuBouton, BorderLayout.NORTH);
        frame.add(onglet, BorderLayout.CENTER);

        panMenuBouton.setLayout(new BorderLayout());
        JPanel panMenu = new JPanel();
        JPanel panBouton = new JPanel();

        panMenuBouton.add(panMenu,BorderLayout.NORTH);
        panMenuBouton.add(panBouton,BorderLayout.SOUTH);

        //--------------------------------------------------------------------------------//
        //PARTIE DU MENU

        //setup de la barre de menu
        JMenuBar jMenuBar = new JMenuBar();
        //setup de la partie personnage de la barre de menu
        JMenu jMenuPersonnage = new JMenu("personnage");
        JMenu jMenuCreation = new JMenu("création");
        JMenuItem jMenuItemAuto = new JMenuItem("aléatoire");
        JMenuItem jMenuItemManuel = new JMenuItem("manuel");

        JMenuItem jMenuSupprimerPerso = new JMenuItem("supprimer perso");
        JMenuItem jMenuImportPerso = new JMenuItem("import perso");

        //organisation du menu de personnage
        jMenuCreation.add(jMenuItemAuto);
        jMenuCreation.add(jMenuItemManuel);
        jMenuPersonnage.add(jMenuCreation);
        jMenuPersonnage.add(jMenuImportPerso);
        jMenuPersonnage.add(jMenuSupprimerPerso);

        //menu outil
        JMenu jMenuOutil = new JMenu("outils");
        JMenuItem jMenuItemSauvegarde = new JMenuItem("sauvegarde");
        JMenuItem jMenuItemClearAll = new JMenuItem("clear tous les onglets");
        JMenuItem jMenuItemClearActivate = new JMenuItem("clear onglet actif");
        //organisation du menu outil
        jMenuOutil.add(jMenuItemSauvegarde);
        jMenuOutil.add(jMenuItemClearActivate);
        jMenuOutil.add(jMenuItemClearAll);

        //menu partie
        JMenu jMenuPartie = new JMenu("partie");
        JMenuItem jMenuItemCreateGame = new JMenuItem("nouvelle partie");
        JMenuItem jMenuItemImportGame = new JMenuItem("import partie");
        JMenuItem jMenuItemDeletteGame = new JMenuItem("supprimer partie");

        //organisation menu partie
        jMenuPartie.add(jMenuItemCreateGame);
        jMenuPartie.add(jMenuItemImportGame);
        jMenuPartie.add(jMenuItemDeletteGame);

        //menu compétence
        JMenu jMenuCompetence = new JMenu("Compétence");
        JMenuItem jMenuItemListeCompetence = new JMenuItem("Liste Complète");

        //orga du menu compétence
        jMenuCompetence.add(jMenuItemListeCompetence);

        //menu help
        JMenuItem jMenuItemHelp = new JMenuItem("aide !");

        //on ajoute tous les gros composant à la barre principal
        jMenuBar.add(jMenuPersonnage);
        jMenuBar.add(jMenuCompetence);
        jMenuBar.add(jMenuPartie);
        jMenuBar.add(jMenuOutil);
        jMenuBar.add(jMenuItemHelp);

        //on attribue a tous les menuitem leurs action respective
        jMenuItemAuto.addActionListener(e ->new DialogBox(null,"création Personnage",true) );
        jMenuItemManuel.addActionListener(e -> new DialogBoxManuel(null,"création personnage manuel",true));
        jMenuItemSauvegarde.addActionListener(e -> saveIntoDb());
        jMenuSupprimerPerso.addActionListener(actionListenerDelettePerso);
        jMenuImportPerso.addActionListener(actionListenerImportPerso);
        jMenuItemClearAll.addActionListener(actionListenerClearAll);
        jMenuItemClearActivate.addActionListener(actionListenerClearActivateOnglet);
        jMenuItemCreateGame.addActionListener(e -> createGame(frame));
        jMenuItemImportGame.addActionListener(actionListenerImportPartie);
        jMenuItemDeletteGame.addActionListener(actionListenerSupprimerPartie);
        jMenuItemListeCompetence.addActionListener(e -> new FrameComp());
        jMenuItemHelp.addActionListener(e -> JOptionPane.showMessageDialog(null,"c'est toi qui à tout fait connard ta pas besoin d'aide.\nÇa fait juste plus pro blblblbl.","KDO de moi à moi",JOptionPane.QUESTION_MESSAGE));


        //ici on attribue les raccourcis clavier aux différentes options
        jMenuItemAuto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
        jMenuItemManuel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,KeyEvent.CTRL_DOWN_MASK));
        jMenuImportPerso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,KeyEvent.CTRL_DOWN_MASK));
        jMenuItemClearActivate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        jMenuItemClearAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK+KeyEvent.SHIFT_DOWN_MASK));
        jMenuSupprimerPerso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));
        jMenuItemSauvegarde.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        jMenuItemCreateGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
        jMenuItemImportGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK+KeyEvent.SHIFT_DOWN_MASK));
        jMenuItemDeletteGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.ALT_DOWN_MASK+KeyEvent.SHIFT_DOWN_MASK));
        jMenuItemListeCompetence.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.SHIFT_DOWN_MASK));
        //on ajoute la barre de menu au panel
        panMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
        panMenu.add(jMenuBar);

        //------------------------------------------------------------------------------------//
        //listener qui permert la sauvegarde lors de la fermeture de la fenêtre
        WindowListener windowListener = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                saveIntoDb();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                saveIntoDb();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        };

        //ajout du listener qui permer la sauvegarde à la fenêtre principale
        frame.addWindowListener(windowListener);

        //le blabla habituel
        frame.getContentPane().add(onglet);
        frame.setVisible(true);

    }

    private static void selectAllForeignKeyOfPersoFromOnglet(ArrayList<Integer> arrayListDesFKpartieDesPerso) {
        for (String aListOfOnglet : listOfOnglet) {
            try {
                ResultSet resultSetFkPartieChezPerso = conn.createStatement().executeQuery("select fk_partie from personnage where name='" + aListOfOnglet + "'");
                while (resultSetFkPartieChezPerso.next()) {
                    arrayListDesFKpartieDesPerso.add(resultSetFkPartieChezPerso.getInt("fk_partie"));
                    break;
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private static void fillArrayWithAllPersoName() {
        try {
            ResultSet resultSetNomPersonnage = conn.createStatement().executeQuery("select name from personnage");
            while (resultSetNomPersonnage.next()){
                arrayListNomPersonnage.add(resultSetNomPersonnage.getString("name"));
            }
        }catch (Exception e1){e1.printStackTrace();}
    }

    private static void saveIntoDb() {
        try {
            for (int i = 0; i < onglet.getTabCount(); i++) {

                //on sauvegarde les info concernant les pv
                conn.createStatement().executeUpdate("update personnage set pvmax='"+Panneau.listPanel.get(i).tabTxtStatPvEa[0].getText()+"' where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set pamax='"+Panneau.listPanel.get(i).tabTxtStatPvEa[1].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set pvactuel='"+Panneau.listPanel.get(i).tabTxtActualPvEa[0].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set paactuel='"+Panneau.listPanel.get(i).tabTxtActualPvEa[1].getText()+"'where id='"+Panneau.listID.get(i)+"'");

                //on sauvegarde le lvl,l'xp et les pts de destin
                conn.createStatement().executeUpdate("update personnage set xp='"+Panneau.listPanel.get(i).tabtxtXpLv[0].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set lvl='"+Panneau.listPanel.get(i).tabtxtXpLv[1].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set ptsdestin='"+Panneau.listPanel.get(i).tabtxtXpLv[2].getText()+"'where id='"+Panneau.listID.get(i)+"'");

                //on sauvegarde toutes les valeurs du pognon
                conn.createStatement().executeUpdate("update personnage set berylium='"+Panneau.listPanel.get(i).tabTxtArgent[0].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set thritil='"+Panneau.listPanel.get(i).tabTxtArgent[1].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set gold='"+Panneau.listPanel.get(i).tabTxtArgent[2].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set argent='"+Panneau.listPanel.get(i).tabTxtArgent[3].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set cuivre='"+Panneau.listPanel.get(i).tabTxtArgent[4].getText()+"'where id='"+Panneau.listID.get(i)+"'");

                //ici on sauvegarde les valeurs des carac modifier
                conn.createStatement().executeUpdate("update personnage set couragemod ='"+Panneau.listPanel.get(i).tabTxtCaracMod[0].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set intelligencemod='"+Panneau.listPanel.get(i).tabTxtCaracMod[1].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set charismemod='"+Panneau.listPanel.get(i).tabTxtCaracMod[2].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set adressemod='"+Panneau.listPanel.get(i).tabTxtCaracMod[3].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set forcemod='"+Panneau.listPanel.get(i).tabTxtCaracMod[4].getText()+"'where id='"+Panneau.listID.get(i)+"'");

                //ici on sauvegarde l'attaque et la parade
                conn.createStatement().executeUpdate("update personnage set attaquemod='"+Panneau.listPanel.get(i).tabTxtCaracMod[8].getText()+"'where id='"+Panneau.listID.get(i)+"'");
                conn.createStatement().executeUpdate("update personnage set parademod='"+Panneau.listPanel.get(i).tabTxtCaracMod[9].getText()+"'where id='"+Panneau.listID.get(i)+"'");

                //ici on sauvegarde les modifications de la valeur du nombre d'item apporté aux element existant dans la liste
                for (int j = 0; j < Panneau.listofModelBarda.get(i).getRowCount() ; j++) {
                    conn.createStatement().executeUpdate("update barda set nombre='"+Panneau.listofModelBarda.get(i).getValueAt(j,1)+"' where fk_perso='"+Panneau.listID.get(i)+"'");
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private static void createGame(Frame _frame){
        //on ajoute la partie à la database
        try {
            String name = JOptionPane.showInputDialog(_frame,"nom de la partie :");
            if(name!=null) {
                conn.createStatement().executeUpdate("INSERT into partie (nom) values ('" + name + "')");
            }

        }catch (Exception e1){
            e1.printStackTrace();
        }
    }

    private static void importPerso(String sql){
        try {
            Statement state = conn.createStatement();
            //on recupère toutes les données selon la requete sql
            ResultSet resultSetPerso = state.executeQuery(sql);

            while (resultSetPerso.next()){
                int id= resultSetPerso.getInt("id");
                Panneau.listID.add(id);

                //on crée une nouvelle instance de panneau on rajoute cette instance à une liste de panneau pour avoir quand même accès
                //au contenu du panneau même si la variable est anonyme
                Panneau panneau = new Panneau();
                Panneau.listPanel.add(panneau);
                onglet.addTab(resultSetPerso.getString("name"), panneau);
                listOfOnglet.add(resultSetPerso.getString("name"));

                //gros bloc de blabla ou on remplis la feuille avec les données DES PERSONNAGE présentes sur la database
                panneau.labTxtNom.setText(resultSetPerso.getString("name"));
                panneau.labTxtSexe.setText(resultSetPerso.getString("sexe"));
                panneau.labTxtOrigine.setText(resultSetPerso.getString("origine"));
                panneau.labTxtMetier.setText(resultSetPerso.getString("metier"));
                panneau.tabTxtStatPvEa[0].setText(resultSetPerso.getString("pvmax"));
                panneau.tabTxtActualPvEa[0].setText(resultSetPerso.getString("pvactuel"));
                panneau.tabTxtStatPvEa[1].setText(resultSetPerso.getString("pamax"));
                panneau.tabTxtActualPvEa[1].setText(resultSetPerso.getString("paactuel"));
                panneau.tabtxtXpLv[0].setText(resultSetPerso.getString("xp"));
                panneau.tabtxtXpLv[1].setText(resultSetPerso.getString("lvl"));
                panneau.tabtxtXpLv[2].setText(resultSetPerso.getString("ptsdestin"));
                panneau.tabTxtArgent[0].setText(resultSetPerso.getString("berylium"));
                panneau.tabTxtArgent[1].setText(resultSetPerso.getString("thritil"));
                panneau.tabTxtArgent[2].setText(resultSetPerso.getString("gold"));
                panneau.tabTxtArgent[3].setText(resultSetPerso.getString("argent"));
                panneau.tabTxtArgent[4].setText(resultSetPerso.getString("cuivre"));
                panneau.tabLabStatCaracBase[0].setText(resultSetPerso.getString("courage"));
                panneau.tabLabStatCaracBase[1].setText(resultSetPerso.getString("intelligence"));
                panneau.tabLabStatCaracBase[2].setText(resultSetPerso.getString("charisme"));
                panneau.tabLabStatCaracBase[3].setText(resultSetPerso.getString("adresse"));
                panneau.tabLabStatCaracBase[4].setText(resultSetPerso.getString("force"));
                panneau.tabLabStatCaracBase[5].setText(Integer.toString((resultSetPerso.getInt("adresse")+
                        resultSetPerso.getInt("intelligence"))/2));
                panneau.tabLabStatCaracBase[6].setText(Integer.toString((resultSetPerso.getInt("intelligence")+
                        resultSetPerso.getInt("charisme"))/2));
                panneau.tabLabStatCaracBase[7].setText(Integer.toString((resultSetPerso.getInt("intelligence")+
                        resultSetPerso.getInt("courage")+
                        resultSetPerso.getInt("force"))/3));
                panneau.tabLabStatCaracBase[8].setText(resultSetPerso.getString("attaque"));
                panneau.tabLabStatCaracBase[9].setText(resultSetPerso.getString("parade"));


                //on check d'abord si les valeurs des carac modifié sont nul si elle le sont on affiche un ligne vide
                //sinon on ajoute la valeur

                panneau.tabTxtCaracMod[0].setText(resultSetPerso.getString("couragemod"));
                panneau.tabTxtCaracMod[1].setText(resultSetPerso.getString("intelligencemod"));
                panneau.tabTxtCaracMod[2].setText(resultSetPerso.getString("charismemod"));
                panneau.tabTxtCaracMod[3].setText(resultSetPerso.getString("adressemod"));
                panneau.tabTxtCaracMod[4].setText(resultSetPerso.getString("forcemod"));
                panneau.tabTxtCaracMod[8].setText(resultSetPerso.getString("attaquemod"));
                panneau.tabTxtCaracMod[9].setText(resultSetPerso.getString("parademod"));



                //ici on essaye de remplir le champs de magie phy des lors qu'il ya une valeur d'adresse et d'intelligence modifier
                if(!resultSetPerso.getString("adressemod").equals("")&&!resultSetPerso.getString("intelligencemod").equals("")){
                    int value1=Integer.parseInt(resultSetPerso.getString("adressemod"));
                    int value2=Integer.parseInt(resultSetPerso.getString("intelligencemod"));
                    int moyenne=(value1+value2)/2;
                    panneau.tabTxtCaracMod[5].setText(Integer.toString(moyenne));
                }

                //de même pour magie psy avec charisme et inlligence mod
                if(!resultSetPerso.getString("charismemod").equals("")&&!resultSetPerso.getString("intelligencemod").equals("")){
                    int value1=Integer.parseInt(resultSetPerso.getString("charismemod"));
                    int value2=Integer.parseInt(resultSetPerso.getString("intelligencemod"));
                    int moyenne=(value1+value2)/2;
                    panneau.tabTxtCaracMod[6].setText(Integer.toString(moyenne));
                }

                //même raisonnement avec force intelligence et courrage pour la resistance à la magie
                if(!resultSetPerso.getString("forcemod").equals("")&&!resultSetPerso.getString("intelligencemod").equals("")&&!resultSetPerso.getString("couragemod").equals("")){
                    int value1=Integer.parseInt(resultSetPerso.getString("forcemod"));
                    int value2=Integer.parseInt(resultSetPerso.getString("intelligencemod"));
                    int value3=Integer.parseInt(resultSetPerso.getString("couragemod"));
                    int moyenne=(value1+value2+value3)/3;
                    panneau.tabTxtCaracMod[7].setText(Integer.toString(moyenne));
                }

                //ligne de code qui recupère le ou les armes qui ont un foreign key avec l'id du personnage que
                //l'on est en train d'importer
                ResultSet resultSetArme=conn.createStatement().executeQuery("SELECT * FROM arme WHERE fk_perrso='"+resultSetPerso.getInt("id")+"'");
                //avec cette methode la on obtient une erreure car il ferme le statement après la première itération de personnage alors que sur celle
                //du dessus on obtiens tous les resultats voulu
                //resultSetArme = state.executeQuery("select * from arme where fk_perrso='"+resultSetPerso.getInt("id")+"'");

                while (resultSetArme.next()){
                    String[] lineAdd = {resultSetArme.getString("nom"),resultSetArme.getString("dégats"),
                            resultSetArme.getString("rupture")};
                    panneau.defaultTableModelArme.addRow(lineAdd);
                }

                //partie ou on recupère pour le personnage son armure
                ResultSet resultSetArmure=conn.createStatement().executeQuery("SELECT * from armure where fk_perso='"+resultSetPerso.getInt("id")+"'");
                while (resultSetArmure.next()){
                    String[] lineAdd = {resultSetArmure.getString("nom"),resultSetArmure.getString("protection"),
                            resultSetArmure.getString("rupture")};
                    panneau.tableModelArmure.addRow(lineAdd);
                }

                //partie ou on recupère pour le personnage son barda
                ResultSet resultSetBarda = conn.createStatement().executeQuery("SELECT * from barda where fk_perso='"+resultSetPerso.getInt("id")+"'");
                while (resultSetBarda.next()){
                    String[] lineAdd = {resultSetBarda.getString("nom"),resultSetBarda.getString("nombre")};
                    panneau.tablemodelBarda.addRow(lineAdd);
                }

                saveIntoDb();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
