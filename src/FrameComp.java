import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

class FrameComp extends JFrame {

    public FrameComp(){
        this.setSize(new Dimension(900,850));
        this.setTitle("Compétences");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        JPanel panelListe = new JPanel();
        this.setContentPane(panelListe);

        GridLayout gridLayout = new GridLayout(4,4);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        this.setLayout(gridLayout);


        HashSet<String> hashSetLettre = new HashSet<>();
        ArrayList<String>  arrayListLettre = new ArrayList<>();
        ArrayList<String> arrayListCompetence = new ArrayList<>();

        try {
            ResultSet resultSet =MainS.conn.createStatement().executeQuery("select * from competence ");
            while (resultSet.next()){
                String nomCompetence = resultSet.getString("nom");
                arrayListCompetence.add(nomCompetence);
                String lettre = nomCompetence.substring(0,1);
                hashSetLettre.add(lettre);
            }
        } catch (SQLException e) { e.printStackTrace(); }

        Iterator it = hashSetLettre.iterator();
        while (it.hasNext()){
            arrayListLettre.add((String)it.next());
        }

        for (String anArrayListLettre : arrayListLettre) {
            JPanel subPanelCompetence = new JPanel();
            subPanelCompetence.setBackground(Color.gray);
            subPanelCompetence.setLayout(new BorderLayout());

            JTextArea textArea = new JTextArea();
            textArea.append("                         " + anArrayListLettre);
            subPanelCompetence.add(textArea, BorderLayout.NORTH);

            Object[][] data = {};
            String[] columnTitle = {"Nom compétence"};
            DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnTitle);

            JTable table = new JTable(defaultTableModel);
            JScrollPane jScrollPane = new JScrollPane(table);

            for (String nomCompetence : arrayListCompetence) {
                if (nomCompetence.substring(0, 1).equals(anArrayListLettre)) {
                    Object[] liste = {nomCompetence};
                    defaultTableModel.addRow(liste);
                }
            }

            JButton buttonInfo = new JButton("Description");
            buttonInfo.addActionListener(e -> {
                String nomCompetence = (String) table.getValueAt(table.getSelectedRow(), 0);
                String description = "";
                try {
                    ResultSet resultSet = MainS.conn.createStatement().executeQuery("select * from competence where nom='" + nomCompetence + "'");

                    while (resultSet.next()) {
                        description = resultSet.getString("description");
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, description, "Description de : " + nomCompetence, JOptionPane.QUESTION_MESSAGE);
            });

            subPanelCompetence.add(buttonInfo, BorderLayout.SOUTH);

            subPanelCompetence.add(jScrollPane, BorderLayout.CENTER);
            panelListe.add(subPanelCompetence);


        }


    }
}


