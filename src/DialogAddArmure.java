import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

class DialogAddArmure extends JDialog {
    public DialogAddArmure(JFrame parent,String title,Boolean modal){
        super(parent,title,modal);
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

        //PARTIE DU NOM DE L'armure
        JPanel panelNom = new JPanel();
        panelNom.setBorder(BorderFactory.createTitledBorder(""));
        panelNom.setLayout(new BorderLayout());
        JLabel labelNomArmure = new JLabel("nom :");
        JTextField textFieldNomArmure = new JTextField();
        textFieldNomArmure.setHorizontalAlignment(JTextField.CENTER);
        panelNom.add(labelNomArmure,BorderLayout.WEST);
        panelNom.add(textFieldNomArmure,BorderLayout.CENTER);

        //partie de la protection de l'armure 
        JPanel panelProtection = new JPanel();
        panelProtection.setBorder(BorderFactory.createTitledBorder(""));
        panelProtection.setLayout(new BorderLayout());
        JLabel labelProtectionArmure = new JLabel("protection :");
        JTextField textFieldProtectionArmure = new JTextField();
        textFieldProtectionArmure.setHorizontalAlignment(JTextField.CENTER);
        panelProtection.add(labelProtectionArmure,BorderLayout.WEST);
        panelProtection.add(textFieldProtectionArmure,BorderLayout.CENTER);

        //partie de la rupture de l'Armure
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
                DialogAddArmure.super.dispose();
            }
        });

        buttonOK.addActionListener(e -> {
            if(e.getSource()==buttonOK){
                String[] newLine = {textFieldNomArmure.getText(),textFieldProtectionArmure.getText(),textFieldRupture.getText()};
                //ici on selectionne le tablemodel du tableau du joueur dont l'onglet est actif et on lui ajoute la nouvelle ligne
                Panneau.listofModelArmure.get(MainS.onglet.getSelectedIndex()).addRow(newLine);

                try {
                    Statement state = MainS.conn.createStatement();

                    //LIGNE MAGIQUE QUI RECUPERE LES DONNER DE LA REQUÊTRES SQL ICI ON RECUPÈRE L'ID DU PERSONNAGE DONT L'ONGLET
                    //EST ACTUELLEMENT ACTIF VIA SON NOM
                    ResultSet resultSet = state.executeQuery("SELECT id from personnage where  name ='"+MainS.listOfOnglet.get(MainS.onglet.getSelectedIndex())+"'");
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                    int idFK=0;
                    while(resultSet.next()){
                        for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
                            idFK=Integer.parseInt(resultSet.getObject(i).toString());
                    }

                    state.executeUpdate("INSERT into armure (nom, protection, rupture, fk_perso) VALUES('"+textFieldNomArmure.getText()+"','"+textFieldProtectionArmure.getText()+"','"+textFieldRupture.getText()+"','"+idFK+"') ");


                }catch (Exception e1){
                    e1.printStackTrace();
                }


                DialogAddArmure.super.dispose();
            }
        });

        //ajout des elements au panel principal
        mainPan.add(panelNom);
        mainPan.add(panelProtection);
        mainPan.add(panelRupture);
        mainPan.add(panelBouton);
    }
}
