import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

class DialogAddArme extends JDialog {
    public DialogAddArme(JFrame parent, String ptilte, Boolean modal){
        super(parent,ptilte,modal);
        this.setSize(350,200);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        initComponent();
        this.setVisible(true);
    }

    private void initComponent(){
        GridLayout grid = new GridLayout(4,1);
        JPanel mainPan = new JPanel();
        mainPan.setLayout(grid);
        this.setContentPane(mainPan);

        //PARTIE DU NOM DE L'ARME
        JPanel panelNom = new JPanel();
        panelNom.setBorder(BorderFactory.createTitledBorder(""));
        panelNom.setLayout(new BorderLayout());
        JLabel labelNomArme = new JLabel("nom :");
        JTextField textFieldNomArme = new JTextField();
        textFieldNomArme.setHorizontalAlignment(JTextField.CENTER);
        panelNom.add(labelNomArme,BorderLayout.WEST);
        panelNom.add(textFieldNomArme,BorderLayout.CENTER);

        //partie des dégat de l'arme
        JPanel panelDegat = new JPanel();
        panelDegat.setBorder(BorderFactory.createTitledBorder(""));
        panelDegat.setLayout(new BorderLayout());
        JLabel labelDegatArme = new JLabel("dégat :");
        JTextField textFieldDegatarme = new JTextField();
        textFieldDegatarme.setHorizontalAlignment(JTextField.CENTER);
        panelDegat.add(labelDegatArme,BorderLayout.WEST);
        panelDegat.add(textFieldDegatarme,BorderLayout.CENTER);

        //partie de la rupture de l'arme
        JPanel panelRupture = new JPanel();
        panelRupture.setBorder(BorderFactory.createTitledBorder(""));
        panelRupture.setLayout(new BorderLayout());
        JLabel labelRupture = new JLabel("rupture :");
        JTextField textFieldRupture = new JTextField();
        textFieldRupture.setHorizontalAlignment(JTextField.CENTER);
        panelRupture.add(labelRupture,BorderLayout.WEST);
        panelRupture.add(textFieldRupture,BorderLayout.CENTER);

        //partie ou ya les boutons
        JPanel panelBouton = new JPanel();
        panelBouton.setLayout(new GridLayout(1,1));
        JButton buttonOK = new JButton("OK" );
        JButton buttonCancel = new JButton("cancel");
        panelBouton.add(buttonOK);
        panelBouton.add(buttonCancel);

        buttonCancel.addActionListener(e -> {
            if(e.getSource()==buttonCancel){
                DialogAddArme.super.dispose();
            }
        });

        buttonOK.addActionListener(e -> {
            if(e.getSource()==buttonOK){
                actionSendData(textFieldNomArme.getText(),textFieldDegatarme.getText(),textFieldRupture.getText());
            }
        });

        //ajout des elements au panel principal
        mainPan.add(panelNom);
        mainPan.add(panelDegat);
        mainPan.add(panelRupture);
        mainPan.add(panelBouton);
    }

    private void actionSendData(String nomArme, String degatArme, String ruptureArme){
        //ICI ON AJOUTE AU TABLE MODEL DU TABLEAU D'ARME L'ITEM QUE L'ON VIENT DE CRÉE GRACE AU PANEL DE DIALOGUE
        String[] newLine = {nomArme,degatArme,ruptureArme};
        //ici on selectionne le tablemodel du tableau du joueur dont l'onglet est actif et on lui ajoute la nouvelle ligne
        Panneau.listofModelArme.get(MainS.onglet.getSelectedIndex()).addRow(newLine);


        try {
            Statement state = MainS.conn.createStatement();
            //  MainS.listOfOnglet.get(MainS.onglet.getSelectedIndex())

            //LIGNE MAGIQUE QUI RECUPERE LES DONNER DE LA REQUÊTRES SQL ICI ON RECUPÈRE L'ID DU PERSONNAGE DONT L'ONGLET
            //EST ACTUELLEMENT ACTIF VIA SON NOM
            ResultSet resultSet = state.executeQuery("SELECT id from personnage where  name ='"+MainS.listOfOnglet.get(MainS.onglet.getSelectedIndex())+"'");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //avec cette ligne de code je récupère l'id du personnage de l'onglet actuellement ouvert dans le panel
            int idFK=0;
            while(resultSet.next()){
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
                    idFK=Integer.parseInt(resultSet.getObject(i).toString());
            }

            //LA J'ENVOIS L'ARME DIRECTEMENT À LA BASE DE DONNÉE
            state.executeUpdate("INSERT into arme(fk_perrso, rupture, dégats, nom) values('"+idFK +"','"+ruptureArme+"','"+degatArme+"','"+nomArme+"') ");

        }catch (Exception e1){
            e1.printStackTrace();
        }

        DialogAddArme.super.dispose();

    }


}
