import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


class Panneau extends JPanel {
    public static final ArrayList<Panneau> listPanel= new ArrayList<>();


    final DefaultTableModel tableModelArmure;
    final DefaultTableModel defaultTableModelArme;
    final DefaultTableModel tablemodelBarda;

    public static final ArrayList<DefaultTableModel > listofModelArme = new ArrayList<>();
    public static final ArrayList<DefaultTableModel > listofModelArmure = new ArrayList<>();
    public static final ArrayList<DefaultTableModel > listofModelBarda = new ArrayList<>();

    public static final ArrayList<Integer> listID = new ArrayList<>();

    final JLabel labTxtNom=new JLabel();
    final JLabel labTxtOrigine=new JLabel();
    final JLabel labTxtMetier=new JLabel();
    final JLabel labTxtSexe=new JLabel();

    final JLabel[] tabLabStatCaracBase={null, null, null, null, null, null, null, null, null, null};
    final JTextField[] tabTxtCaracMod={null,null,null,null,null,null,null,null,null,null};
    private final JTextField txtBeryl= new JTextField();
    private final JTextField txtThrit= new JTextField();
    private final JTextField txtOr=new JTextField();
    private final JTextField txtArgent= new JTextField();
    private final JTextField txtCuivre=new JTextField();

    final JTextField[] tabTxtArgent = {txtBeryl,txtThrit,txtOr,txtArgent,txtCuivre};

    private final JTextField txtXp=new JTextField();
    private final JTextField txtLvl = new JTextField();
    private final JTextField txtDestin = new JTextField();

    final JTextField[] tabtxtXpLv = {txtXp,txtLvl,txtDestin};

    private final JTextField txtMaxPv=new JTextField();
    private final JTextField txtMaxEa = new JTextField();
    private final JTextField txtActuelPv = new JTextField();
    private final JTextField txtActuelEa = new JTextField();
    final JTextField[] tabTxtStatPvEa = {txtMaxPv,txtMaxEa};
    final JTextField[] tabTxtActualPvEa = {txtActuelPv,txtActuelEa};

    public JLabel getLabTxtNom() {
        return labTxtNom;
    }

    public Panneau(){
        ///////VARIABLE VARIABLE VARIABLE
        //on declare toute nos variable d'un coup pour pouvoir les utiliser comme bon nous semble par la suite
        JPanel panCourage= new JPanel(),panIntelligence = new JPanel(),panCharisme = new JPanel()
                ,panAdresse = new JPanel(),panForce = new JPanel(),panMagiePhy = new JPanel(),panMagiePsy = new JPanel()
                ,panResMagie = new JPanel(),panAtt = new JPanel(),panPar = new JPanel();

        JLabel  labCourageBase = new JLabel(),labIntelligenceBase = new JLabel(),labCharismeBase = new JLabel(),labAdresseBase = new JLabel()
                ,labForceBase = new JLabel(),labMagiePhyBase = new JLabel(),labMagiePsyBase = new JLabel(),labResMagieBase = new JLabel(),
                labAttBase = new JLabel(),labParBase = new JLabel();

        JLabel  labCourageMod = new JLabel(),labIntelligenceMod = new JLabel(),labCharismeMod = new JLabel(),labAdresseMod = new JLabel(),labForceMod = new JLabel(),
                labMagiePhyMod = new JLabel(),labMagiePsyMod = new JLabel(),labResMagieMod = new JLabel(),labAttMod = new JLabel(),labParMod = new JLabel();

        JTextField txtCourageMod = new JTextField(),txtIntelligenceMod = new JTextField(),txtCharismeMod = new JTextField(),txtAdresseMod = new JTextField(),
                txtForceMod = new JTextField(),txtMagiePhyMod = new JTextField(),txtMagiePsyMod = new JTextField(),txtResMagieMod = new JTextField(),
                txtAttMod = new JTextField(),txtParMod = new JTextField();

        JPanel panPv = new JPanel();JPanel panEa = new JPanel();
        JLabel labMaxPv=new JLabel();JLabel labMaxEa=new JLabel();JLabel labActuelPv=new JLabel();JLabel labActuelEa=new JLabel();

        JPanel panBeryl=new JPanel();JPanel panThrit=new JPanel();JPanel panOr=new JPanel();JPanel panArgent=new JPanel();JPanel panCuivre=new JPanel();
        JLabel labBeryl=new JLabel();JLabel labThrit=new JLabel();JLabel labOr=new JLabel();JLabel labArgent=new JLabel();JLabel labCuivre=new JLabel();

        JPanel panNom=new JPanel();JPanel panOrigine=new JPanel();JPanel panMetier=new JPanel();JPanel panSexe = new JPanel();
        JLabel labNom = new JLabel();JLabel labOrigine=new JLabel();JLabel labMetier=new JLabel();JLabel labSexe=new JLabel();

        JPanel panXp=new JPanel();JPanel panLvl=new JPanel();JPanel panDestin=new JPanel();
        JLabel labXp=new JLabel();JLabel labLvl=new JLabel();JLabel labDestin=new JLabel();

        JPanel panDescription,panPognon,panLvlDestinXp,panPvEa;

        Font font = new Font("Arial",Font.PLAIN,18);
        Dimension dimensionPrincipal = new Dimension(195,45);
        Dimension dimensionPanelSecondaire = new Dimension(250,200);
        Dimension dimensionCaracPrincipal1 = new Dimension(600,280);

        panPognon = new JPanel();
        panDescription=new JPanel(); panLvlDestinXp = new JPanel();
        panPvEa = new JPanel();

        //--------------------------------------------------------------------------------------//

        //CARAC PRINCIPALE nom/metier/origine/sexe
        panDescription.setPreferredSize(new Dimension(600,200));
        panDescription.setBorder(BorderFactory.createTitledBorder("description général"));
        panDescription.setLayout(new GridLayout(2,2));

        JPanel[] tabPanGeneral = {panNom,panOrigine,panMetier,panSexe};
        JLabel[] tabLabGeneral = {labNom,labOrigine,labMetier,labSexe};
        String[] tabStringTxtGeneral = {"Nom :","Origine :","Metier :","Sexe :"};

        for (int i = 0; i < tabPanGeneral.length; i++) {
            tabPanGeneral[i].setBorder(BorderFactory.createTitledBorder(""));
            tabPanGeneral[i].setLayout(new BorderLayout());
            tabPanGeneral[i].setPreferredSize(dimensionPrincipal);
            tabLabGeneral[i].setText(tabStringTxtGeneral[i]);
            tabLabGeneral[i].setFont(font);
            JLabel[] tabLabTxtGeneral = {labTxtNom, labTxtOrigine, labTxtMetier, labTxtSexe};
            tabLabTxtGeneral[i].setFont(font);
            tabLabTxtGeneral[i].setHorizontalAlignment(JTextField.CENTER);
            tabPanGeneral[i].add(tabLabGeneral[i],BorderLayout.WEST);
            tabPanGeneral[i].add(tabLabTxtGeneral[i],BorderLayout.CENTER);
        }

        //ajout des different element a mettre dans la panneau de caractéristique principal
        for (JPanel aTabPanGeneral : tabPanGeneral) {
            panDescription.add(aTabPanGeneral);
        }

        //-----------------------------------------------------------------------------//

        //PANEL OU TOUT L'ARGENT EST GERER berylium/thritil/or/argent/cuivre
        panPognon.setPreferredSize(dimensionPanelSecondaire);
        panPognon.setBorder(BorderFactory.createTitledBorder("ton argent "));
        panPognon.setLayout(new GridLayout(5,1));

        //ligne de code qui permet de récuperer la taille du label avec la plus grande string pour " egaliser " le rendu graphique en permettant que tous les labels
        //gardent la même taille pour aligner les textField juste après
        Dimension dimensionlabBery = new Dimension(labBeryl.getFontMetrics(labBeryl.getFont()).stringWidth("Berylium")+8,20);

        JPanel[] tabPanArgent = { panBeryl,panThrit,panOr,panArgent,panCuivre};
        JLabel[] tabLabArgent = {labBeryl,labThrit,labOr,labArgent,labCuivre};
        String[] tabStringLabArgent = {"Berylium :","Thritil :","Or :","Argent :","Cuivre :"};

        for (int i = 0; i <tabPanArgent.length ; i++) {
            tabPanArgent[i].setLayout(new BorderLayout());
            tabLabArgent[i].setText(tabStringLabArgent[i]);
            tabTxtArgent[i].setHorizontalAlignment(JTextField.CENTER);
            tabTxtArgent[i].setText("0");
            tabLabArgent[i].setPreferredSize(dimensionlabBery);
        }

        //on remplit chaque pannel d'argent ( de chaque monaie ) par leur label et textfield respectif
        for (int i = 0; i <tabPanArgent.length ; i++) {
            tabPanArgent[i].add(tabLabArgent[i],BorderLayout.WEST);
            tabPanArgent[i].add(tabTxtArgent[i],BorderLayout.CENTER);
        }

        //ajout des differents composant de la richesse dans le panel pognon
        for (JPanel aTabPanArgent : tabPanArgent) {
            panPognon.add(aTabPanArgent);
        }

        //----------------------------------------------------------------------------------//

        //PAN LVL/xp/ptsDestin
        panLvlDestinXp.setPreferredSize(new Dimension(dimensionPanelSecondaire));
        panLvlDestinXp.setBorder(BorderFactory.createTitledBorder(" lvl / xp / pts Destin"));
        panLvlDestinXp.setLayout(new GridLayout(3,1));

        Dimension dimXpLv = new Dimension(labXp.getFontMetrics(labBeryl.getFont()).stringWidth("Pts :"),20);

        JPanel[] tabPanXpLv = {panXp,panLvl,panDestin};
        JLabel[] tabLabXpLv= {labXp,labLvl,labDestin};
        String[] tabStringLabXpLV= {"Xp :","Lvl :","Pts :"};

        for (int i = 0; i < tabPanXpLv.length; i++) {
            tabPanXpLv[i].setLayout(new BorderLayout());
            tabLabXpLv[i].setText(tabStringLabXpLV[i]);
            tabtxtXpLv[i].setHorizontalAlignment(JTextField.CENTER);
            tabPanXpLv[i].add(tabLabXpLv[i],BorderLayout.WEST);
            tabPanXpLv[i].add(tabtxtXpLv[i],BorderLayout.CENTER);
        }

        //maraboutage pour aligner tous les composants
        tabLabXpLv[0].setPreferredSize(dimXpLv);
        tabLabXpLv[1].setPreferredSize(dimXpLv);
        //ajout des elements lvl xp et pts destin au panel principal
        for (JPanel aTabPanXpLv : tabPanXpLv) {
            panLvlDestinXp.add(aTabPanXpLv);
        }

        //---------------------------------------------------------------------//
        //panel ou il ya PV et Ea
        panPvEa.setPreferredSize(new Dimension(dimensionPanelSecondaire));
        panPvEa.setBorder(BorderFactory.createTitledBorder(" Pv / Pa"));
        panPvEa.setLayout(new GridLayout(2,1));

        JPanel[] tabPanPvEa = {panPv,panEa};
        JLabel[] tabLabPvEa = {labMaxPv,labMaxEa};
        String[] tabLabPvEaContain = {"Max Pv :","Max Pa :"};
        JLabel[] tabLabActualPvEa = {labActuelPv,labActuelEa};

        for (int i = 0; i<tabPanPvEa.length ; i++) {
            tabPanPvEa[i].setLayout(new GridLayout(1,4));
            tabLabPvEa[i].setText(tabLabPvEaContain[i]);
            tabTxtStatPvEa[i].setHorizontalAlignment(JTextField.CENTER);
            tabLabActualPvEa[i].setText("Now :");
            tabLabActualPvEa[i].setHorizontalAlignment(JTextField.CENTER);
            tabTxtActualPvEa[i].setHorizontalAlignment(JTextField.CENTER);
        }

        for (int i = 0; i < 2; i++) {
            tabPanPvEa[i].add(tabLabPvEa[i]);tabPanPvEa[i].add(tabTxtStatPvEa[i]);tabPanPvEa[i].add(tabLabActualPvEa[i]);tabPanPvEa[i].add(tabTxtActualPvEa[i]);
            panPvEa.add(tabPanPvEa[i]);
        }

        //-------------------------------------------------------------//

        //PARTIE CONCERNANT LES CARASTÉRISTIQUE PRINCIPAL

        //caracteristiques Principales
        JPanel panCaracPrincipal = new JPanel();
        panCaracPrincipal.setPreferredSize(dimensionCaracPrincipal1);
        panCaracPrincipal.setBorder(BorderFactory.createTitledBorder(" Stats principales "));
        panCaracPrincipal.setLayout(new GridLayout(5,1));

        //PANNEAU DE CARACTERISTIQUE SECONDAIRE
        JPanel panCaracSecondaire = new JPanel();
        panCaracSecondaire.setPreferredSize(dimensionCaracPrincipal1);
        panCaracSecondaire.setBorder(BorderFactory.createTitledBorder(" Stats Secondaires "));
        panCaracSecondaire.setLayout(new GridLayout(5,1));


        // creation de tous les éléments nécessaire pour faire une boucle
        JPanel[] tabPanelCarac={panCourage,panIntelligence,panCharisme,panAdresse,panForce,panMagiePhy,panMagiePsy,panResMagie,panAtt,panPar};

        JLabel[] tabLabCaracBase={labCourageBase,labIntelligenceBase,labCharismeBase,labAdresseBase
                                ,labForceBase,labMagiePhyBase,labMagiePsyBase,labResMagieBase,labAttBase,labParBase};

        String[] tabLabCaracBaseContain={"Courage de base :","Intelligence de base :","Charisme de base :","Adresse de base :","Force de base :","MagiePhy de base :",
                                        "MagiePsy de base :","ResMagie de Base :","Attaque de base :","Parade de base"};

        JLabel[] tabLabCaracMod={labCourageMod,labIntelligenceMod,labCharismeMod,labAdresseMod,labForceMod,
                                    labMagiePhyMod,labMagiePsyMod,labResMagieMod,labAttMod,labParMod};

        //BOUCLE QUI permet de crée tous les pannel de caractéristique vu qu'ils ont tous les même proprieté
        for (int i = 0; i < tabLabCaracBase.length; i++) {
            tabPanelCarac[i].setLayout(new GridLayout(1,4));
            tabLabCaracBase[i] = new JLabel(tabLabCaracBaseContain[i]);
            tabLabStatCaracBase[i] = new JLabel();
            tabLabStatCaracBase[i].setHorizontalAlignment(JTextField.CENTER);
            tabLabStatCaracBase[i].setBorder(BorderFactory.createTitledBorder(""));
            tabLabCaracMod[i].setText("modifié :");
            tabLabCaracMod[i].setHorizontalAlignment(JTextField.CENTER);
            tabTxtCaracMod[i]=new JTextField();
            tabTxtCaracMod[i].setHorizontalAlignment(JTextField.CENTER);
            tabPanelCarac[i].add(tabLabCaracBase[i]);
            tabPanelCarac[i].add(tabLabStatCaracBase[i]);
            tabPanelCarac[i].add(tabLabCaracMod[i]);
            tabPanelCarac[i].add(tabTxtCaracMod[i]);
        }
        //avec ses deux boucle on remplit chaqu'un a leur tour les panel des carac princpales 1 & 2
        for (int i =5 ; i <10 ; i++) {
            panCaracSecondaire.add(tabPanelCarac[i]);
            panCaracPrincipal.add(tabPanelCarac[i-5]);
        }

        //---------------------------------------------------------------------------------//
        /////------------------------------------------------------------------------------//
        //          GESTION DE LA PARTIE BASSE DE LA FEUILLE DE PERSO
        //              ARMURE ARME ET EQUIPEMENT EN FOURE TOUT

        Dimension dimTierPartieBasse = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int widhtTierScreen = (dimTierPartieBasse.width/3)-20;

        Dimension dimPanelPartieBasse = new Dimension(widhtTierScreen,270);

        //---------------------------------------------------------------------//
        //ARME

        JPanel panArme = new JPanel();
        panArme.setPreferredSize(dimPanelPartieBasse);
        panArme.setBorder(BorderFactory.createTitledBorder(" Armes "));

        panArme.setLayout(new BorderLayout());

        String[] nomCollumArme = {"nom","dégats (en D6)","rupture"};
        Object[][] arme ={};

        defaultTableModelArme = new DefaultTableModel(arme,nomCollumArme);
        //on ajoute le tablemodel a une liste qu'on utiliseras ensuite pour modifier l'equipement du perso qu'on veux
        listofModelArme.add(defaultTableModelArme);
        JTable tableArme = new JTable(defaultTableModelArme);

        JPanel panButtonAddRemoveRow2 = new JPanel();
        panButtonAddRemoveRow2.setLayout(new GridLayout(1,2));
        JButton butAddRow2 = new JButton("add row");
        JButton butRemoveRow2 = new JButton("remove row");
        panButtonAddRemoveRow2.add(butAddRow2);
        panButtonAddRemoveRow2.add(butRemoveRow2);



        butAddRow2.addActionListener(e -> {
            DialogAddArme dialogArme = new DialogAddArme(null,"ajout d'arme",true);
        });

        butRemoveRow2.addActionListener(e -> defaultTableModelArme.removeRow(tableArme.getSelectedRow()));

        panArme.add(new JScrollPane(tableArme),BorderLayout.CENTER);
        panArme.add(panButtonAddRemoveRow2,BorderLayout.SOUTH);

        //----------------------------------------------------------------------//
        //EQUIPEMENT
        JPanel panArmure = new JPanel();
        panArmure.setPreferredSize(dimPanelPartieBasse);
        panArmure.setBorder(BorderFactory.createTitledBorder(" Armures "));
        panArmure.setLayout(new BorderLayout());

        //initalisation des premières donnée du tableau
        Object[][] data={};
        String[] collumnTitle={"Armure&Protection","Protection","Rupture"};

        //d'abord on crée un modele de donné puis on donne a la jtable en source ce tablemodel
        tableModelArmure =  new DefaultTableModel(data,collumnTitle);
        //on ajoute le tablemodel a une liste qu'on utiliseras ensuite pour modifier l'equipement du perso qu'on veux
        listofModelArmure.add(tableModelArmure);
        JTable jtabArmure = new JTable(tableModelArmure);

        //du blabla habituel pour la création et le positionnement des éléments
        panArmure.add(new JScrollPane(jtabArmure),BorderLayout.CENTER);
        JPanel panButtonAddRemoveRow = new JPanel();
        panButtonAddRemoveRow.setLayout(new GridLayout(1,2));
        JButton butAddRow = new JButton("add row");
        JButton butRemoveRow = new JButton("remove row");
        panButtonAddRemoveRow.add(butAddRow);
        panButtonAddRemoveRow.add(butRemoveRow);
        panArmure.add(panButtonAddRemoveRow,BorderLayout.SOUTH);

        //permet d'acceder au donnée du tableau
        //DefaultTableModel tableModel = (DefaultTableModel) jtabArmure.getModel();

        butAddRow.addActionListener(e -> {
            DialogAddArmure dialogAddArmure = new DialogAddArmure(null,"ajout armure",true);

        });

        butRemoveRow.addActionListener(e -> tableModelArmure.removeRow(jtabArmure.getSelectedRow()));

        //------------------------------------------------------------------------//
        //FOURE TOUT(EQUIPEMENT LAMBA GENRE FLECHE ETCC )
        JPanel panFoureTout = new JPanel();
        panFoureTout.setPreferredSize(dimPanelPartieBasse);
        panFoureTout.setBorder(BorderFactory.createTitledBorder(" Barda "));
        panFoureTout.setLayout(new BorderLayout());

        String[] listeBarda = {"babiolles","nombre"};
        Object[][] barda = {};
        tablemodelBarda = new DefaultTableModel(barda,listeBarda);
        //on ajoute le tablemodel a une liste qu'on utiliseras ensuite pour modifier l'equipement du perso qu'on veux
        listofModelBarda.add(tablemodelBarda);
        JTable tableBarda = new JTable(tablemodelBarda);

        JPanel panButtonAddRemoveRow0 = new JPanel();
        panButtonAddRemoveRow0.setLayout(new GridLayout(1,2));
        JButton butAddRow0 = new JButton("add row");
        JButton butRemoveRow0 = new JButton("remove row");
        panButtonAddRemoveRow0.add(butAddRow0);
        panButtonAddRemoveRow0.add(butRemoveRow0);

        butAddRow0.addActionListener(e -> {
            DialogAddBarda dialogAddBarda = new DialogAddBarda(null,"ajout objet",true);
        });

        butRemoveRow0.addActionListener(e -> tablemodelBarda.removeRow(tableBarda.getSelectedRow()));


        panFoureTout.add(new JScrollPane(tableBarda),BorderLayout.CENTER);
        panFoureTout.add(panButtonAddRemoveRow0,BorderLayout.SOUTH);

        //------------------------------------------------------------------------//
        //on change le fond du panneau avec une couleur random pour bien différencier chaque fiche perso
        int r,v,b;
        r = 1 + (int)(Math.random() * ((255 - 1) + 1));
        v = 1 + (int)(Math.random() * ((255 - 1) + 1));
        b = 1 + (int)(Math.random() * ((255 - 1) + 1));
        this.setBackground(new Color(r,v,b));

        //ajout des panneau groupant certaine caractéristique
        this.add(panDescription);
        this.add(panPvEa);
        this.add(panLvlDestinXp);
        this.add(panPognon);
        this.add(panCaracPrincipal);
        this.add(panCaracSecondaire);
        this.add(panArme);
        this.add(panArmure);
        this.add(panFoureTout);

    }
}
