import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

class DialogAddBarda extends JDialog {
    public DialogAddBarda(JFrame parent,String title,Boolean modal){
        super(parent,title,modal);
        this.setSize(350,150);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        initComponent();
        this.setVisible(true);

    }
    private void initComponent(){
        GridLayout grid = new GridLayout(3,1);
        JPanel mainPan = new JPanel();
        mainPan.setLayout(grid);
        this.setContentPane(mainPan);

        //PARTIE DU NOM DE L'OBJET
        JPanel panelNom = new JPanel();
        panelNom.setBorder(BorderFactory.createTitledBorder(""));
        panelNom.setLayout(new BorderLayout());
        JLabel labelNomobjet = new JLabel("nom :");
        JTextField textFieldNomobjet = new JTextField();
        textFieldNomobjet.setHorizontalAlignment(JTextField.CENTER);
        panelNom.add(labelNomobjet,BorderLayout.WEST);
        panelNom.add(textFieldNomobjet,BorderLayout.CENTER);

        //nombre de cet item
        JPanel panelNombre = new JPanel();
        panelNombre.setBorder(BorderFactory.createTitledBorder(""));
        panelNombre.setLayout(new BorderLayout());
        JLabel labelNombreobjet = new JLabel("nombre :");
        JTextField textFieldNombreobjet = new JTextField();
        textFieldNombreobjet.setHorizontalAlignment(JTextField.CENTER);
        panelNombre.add(labelNombreobjet,BorderLayout.WEST);
        panelNombre.add(textFieldNombreobjet,BorderLayout.CENTER);

        //partie ou ya les boutons
        JPanel panelBouton = new JPanel();
        panelBouton.setLayout(new GridLayout(1,1));
        JButton buttonOK = new JButton("OK" );
        JButton buttonCancel = new JButton("cancel");
        panelBouton.add(buttonOK);
        panelBouton.add(buttonCancel);

        buttonCancel.addActionListener(e -> {
            if(e.getSource()==buttonCancel){
                DialogAddBarda.super.dispose();
            }
        });

        buttonOK.addActionListener(e -> {
            if(e.getSource()==buttonOK){
                String[] newLine = {textFieldNomobjet.getText(),textFieldNombreobjet.getText()};
                //ici on selectionne le tablemodel du tableau du joueur dont l'onglet est actif et on lui ajoute la nouvelle ligne
                Panneau.listofModelBarda.get(MainS.onglet.getSelectedIndex()).addRow(newLine);

                try {
                    Statement state = MainS.conn.createStatement();

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

                    //la on envois les informations sur l'item à la base de donnée
                    state.executeUpdate("INSERT into barda(nom, nombre, fk_perso) values ('"+textFieldNomobjet.getText()+"','"+textFieldNombreobjet.getText()+"','"+idFK+"')");

                }catch (Exception e1){
                    e1.printStackTrace();
                }


                DialogAddBarda.super.dispose();
            }
        });


        //ajout des elements au panel principal
        mainPan.add(panelNom);
        mainPan.add(panelNombre);
        mainPan.add(panelBouton);
        
    }
}
