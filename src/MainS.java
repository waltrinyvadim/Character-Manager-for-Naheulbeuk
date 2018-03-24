import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
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
                      System.out.println("le perso "+resultSetPerso.getString("name")+" à l'id numéro : "+id);
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

                        //test pour courage mod
                        if(resultSetPerso.getString("couragemod")==null){
                            panneau.tabTxtCaracMod[0].setText("");
                        }
                        else{panneau.tabTxtCaracMod[0].setText(resultSetPerso.getString("courageMod")); }

                        //test pour intelligence mod
                        if(resultSetPerso.getString("intelligencemod")==null){
                            panneau.tabTxtCaracMod[1].setText("");
                        }else{panneau.tabTxtCaracMod[1].setText(resultSetPerso.getString("intelligenceMod"));}

                        //test pour charisme mod
                        if(resultSetPerso.getString("charismemod")==null){
                            panneau.tabTxtCaracMod[2].setText("");
                        }else {panneau.tabTxtCaracMod[2].setText(resultSetPerso.getString("charismeMod"));}

                        //test pour adresse mod
                        if(resultSetPerso.getString("adressemod")==null){
                            panneau.tabTxtCaracMod[3].setText("");
                        }else{panneau.tabTxtCaracMod[3].setText(resultSetPerso.getString("adresseMod"));}

                        //test pour force mod
                        if(resultSetPerso.getString("forcemod")==null){
                            panneau.tabTxtCaracMod[4].setText("");
                        }else{panneau.tabTxtCaracMod[4].setText(resultSetPerso.getString("forceMod"));}

                        //test pour attaque mod
                        if(resultSetPerso.getString("attaquemod")==null){
                            panneau.tabTxtCaracMod[8].setText("");
                        }else{panneau.tabTxtCaracMod[8].setText(resultSetPerso.getString("attaqueMod"));}

                        //test pour parade mod
                        if(resultSetPerso.getString("parademod")==null){
                            panneau.tabTxtCaracMod[9].setText("");
                        }else{panneau.tabTxtCaracMod[9].setText(resultSetPerso.getString("paradeMod"));}

                        //on essaye de remplir le champs de magie phy si il ya des valeurs d'adresse et d'intelligence
                        if((resultSetPerso.getString("adressemod")!=null)&&(resultSetPerso.getString("intelligencemod")!=null)){
                            int value1=Integer.parseInt(resultSetPerso.getString("adressemod"));
                            int value2=Integer.parseInt(resultSetPerso.getString("intelligencemod"));
                            panneau.tabTxtCaracMod[5].setText(Integer.toString((value1+value2)/2));
                        }else{panneau.tabTxtCaracMod[5].setText("");}


                        //on essaye de remplir le champde magie psy si il ya des valeurs de charisme et d'intelligence
                        if(resultSetPerso.getString("charismeMod")!=null&&resultSetPerso.getString("intelligenceMod")!=null){
                            int value3 = Integer.parseInt(resultSetPerso.getString("charismemod"));
                            int value4 = Integer.parseInt(resultSetPerso.getString("intelligencemod"));
                            panneau.tabTxtCaracMod[6].setText(Integer.toString((value3+value4)/2));
                        }else{panneau.tabTxtCaracMod[6].setText("");}

                        //force courage intelligence

                        if(resultSetPerso.getString("charismeMod")!=null&&resultSetPerso.getString("forceMod")!=null&&resultSetPerso.getString("intelligenceMod")!=null){
                            int value5 = Integer.parseInt(resultSetPerso.getString("charismemod"));
                            int value6 = Integer.parseInt(resultSetPerso.getString("forcemod"));
                            int value7 = Integer.parseInt(resultSetPerso.getString("intelligencemod"));
                            panneau.tabTxtCaracMod[7].setText(Integer.toString((value5+value6+value7)/3));
                        }else{panneau.tabTxtCaracMod[7].setText("");}


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

                }
                }
        };

        //ICI LE FONCTION DE SAUVEGARDE DE L'ETAT DES PERSONNAGES VERS LA DATABASE
        ActionListener actSave = e -> {
            try {
                for (int i = 0; i < onglet.getTabCount(); i++) {
                   /* //requette pour la sauvegarde des pv et des pa
                    PreparedStatement preparedStatementPvEa = conn.prepareStatement("update personnage set pvmax=?,pvactuel=?,pamax=?,paactuel=? where name='"+listOfOnglet.get(i)+"'");
                    preparedStatementPvEa.setString(1,Panneau.listPanel.get(i).tabTxtStatPvEa[0].getText());
                    preparedStatementPvEa.setString(2,Panneau.listPanel.get(i).tabTxtActualPvEa[0].getText());
                    preparedStatementPvEa.setString(3,Panneau.listPanel.get(i).tabTxtStatPvEa[1].getText());
                    preparedStatementPvEa.setString(4,Panneau.listPanel.get(i).tabTxtActualPvEa[1].getText());
                    preparedStatementPvEa.execute();
                    */

                   conn.createStatement().executeUpdate("update personnage set pvmax='"+Panneau.listPanel.get(i).tabTxtStatPvEa[0].getText()+"' where id='"+Panneau.listID.get(i)+"'");

                    System.out.println("ohlo");
                   /*PreparedStatement preparedStatementBarda = conn.prepareStatement("update barda set nombre=? where fk_perso='"+Panneau.listID.get(i)+"' ");
                    System.out.println(Panneau.listofModelArme.get(0).getValueAt(0,0));
                    for (int j = 0; j < Panneau.listofModelBarda.size() ; j++) {
                       preparedStatementCarac.setString(1,(String)Panneau.listofModelBarda.get(i).getValueAt(j,0));
                    }*/
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        };



        //ajout du listener sur les boutons
        butAddPLayer.addActionListener(act);
        butDelette.addActionListener(act);
        butAddPLayerManuel.addActionListener(act);
        butImportFromDb.addActionListener(actImport);
        butSave.addActionListener(actSave);


        //le blabla habituel
        frame.getContentPane().add(onglet);
        frame.setVisible(true);
    }
}
