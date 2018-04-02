import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

class MainS {
    //PERMET L'OUVERTURE DE SES VARIABLES VERS DialogBox POUR DONNER LE NOM DU PERSONNAGE AU TITRE DE L'ONGLET
    public static final JTabbedPane onglet = new JTabbedPane();
    public static final ArrayList<String > listOfOnglet = new ArrayList<>();

    public static java.sql.Connection conn;

    public static void main(String[] args){
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
        JPanel panMenu = new JPanel();

        //postion du menu et de la zone ou il ya les pannel
        frame.setLayout(brdLayout);
        frame.add(panMenu, BorderLayout.NORTH);
        frame.add(onglet, BorderLayout.CENTER);

        //ajout des boutons add et remove player
        JButton butAddPLayer= new JButton("ajout perso aléatoire");
        JButton butDelette = new JButton("Delette Player");
        JButton butAddPLayerManuel = new JButton("ajout perso manuel ");
        JButton butImportFromDb = new JButton("import");
        JButton butSave = new JButton("sauvegarder");

        panMenu.add(butAddPLayer);
        panMenu.add(butAddPLayerManuel);
        panMenu.add(butDelette);
        panMenu.add(butSave);
        panMenu.add(butImportFromDb);

        //action listener des boutons d'ajout auto/manuel et de suppression
        ActionListener act = new ActionListener() {
            String persoToSupprimer;
            @Override
            public void actionPerformed(ActionEvent e) {

                //CAS OU LE BOUTON SUR LEQUEL ON APPUYE ET ADD
                if (e.getSource()== butAddPLayer){
                   DialogBox dialogBox= new DialogBox(null,"création Personnage",true);
                }
                //CAS OU ON VEUX RENTRER UN PERSONNAGE MANUELLEMENT
                if(e.getSource()==butAddPLayerManuel){
                    DialogBoxManuel dialogBoxManuel = new DialogBoxManuel(null,"création personnage manuel",true);
                }
                //CAS OU ON APPUYE SUR LE BOUTON DEL PLAYER
                if(e.getSource()==butDelette){
                    //le try catch est la au cas ou on essaye de supprimer alors qu'il n'ya pas d'element a supprimer
                    try {
                        //convertie l'array d'onglet en un tableau pour pouvoir le passer en paramètre de la fonction
                        //cela permet de crée l'optionPane avec en parametre le tableau d'onglet
                        String[] tabOnglet = new String[listOfOnglet.size()];
                        tabOnglet = listOfOnglet.toArray(tabOnglet);

                        //les parametres de la methode sont le message dans la box, le titre puis la tableau a passer en paramètre puis l'indice selesctionner par defaut
                         persoToSupprimer = (String) JOptionPane.showInputDialog(null,
                                "choisir l'onglet à supprimer",
                                "JDR manager",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                tabOnglet,
                                tabOnglet[0]);

                        //ici on supprime le personnage de la base de donnée
                        try {
                            conn.createStatement().executeUpdate("delete from personnage where name='"+persoToSupprimer+"'");
                        }
                        catch (Exception e1){
                            e1.printStackTrace();
                        }

                        //ici on supprime le personnage de l'interface
                        for (int i = 0; i < tabOnglet.length; i++) {
                            if (tabOnglet[i].equals(persoToSupprimer)){
                                onglet.remove(i);
                                listOfOnglet.remove(i);
                                break;
                            }
                        }
                    }catch (ArrayIndexOutOfBoundsException e1){
                        JOptionPane errorDel = new JOptionPane();
                        JOptionPane.showMessageDialog(null, "tu peux pas supprimer si ya rien a supprimer boufon",
                                                    "JDR manager", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };

        //action listener du bouton pour l'import
        ActionListener actImport = e -> {
            if(e.getSource()==butImportFromDb){
                try {
                    Statement state = conn.createStatement();
                    //on recupère toutes les données de la table personnage
                    ResultSet resultSetPerso = state.executeQuery("select * from personnage");

                    while (resultSetPerso.next()){

                        //a faire après

                      int id= resultSetPerso.getInt("id");
                      Panneau.listID.add(id);


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
                    }
                }catch (Exception e1){
                    e1.printStackTrace();
                }
                }
        };

        //ICI LE FONCTION DE SAUVEGARDE DE L'ETAT DES PERSONNAGES VERS LA DATABASE
        ActionListener actSave = e -> saveIntoDb();

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

        //ajout du listener sur les boutons
        butAddPLayer.addActionListener(act);
        butDelette.addActionListener(act);
        butAddPLayerManuel.addActionListener(act);
        butImportFromDb.addActionListener(actImport);
        butSave.addActionListener(actSave);

        //ajout du listener qui permer la sauvegarde à la fenêtre principale
        frame.addWindowListener(windowListener);

        //le blabla habituel
        frame.getContentPane().add(onglet);
        frame.setVisible(true);
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
}
