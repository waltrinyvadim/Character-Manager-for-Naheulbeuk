//public class Poubelle {


       /*
//xp
        JPanel panXp = new JPanel();
        panXp.setLayout(new BorderLayout());
        JLabel labXp = new JLabel("Xp  :");
        JTextField txtXp = new JTextField("0");
        txtXp.setHorizontalAlignment(JTextField.CENTER);
        panXp.add(labXp,BorderLayout.WEST);
        panXp.add(txtXp,BorderLayout.CENTER);

        //lvl
        JPanel panLvl = new JPanel();
        panLvl.setLayout(new BorderLayout());
        JLabel labLvl = new JLabel("lvl :");
        JTextField txtLvl = new JTextField("1");
        //permet d'aligner ce label avec les autres pour que sa fasse plus prore
        labLvl.setPreferredSize(new Dimension(labXp.getFontMetrics(labBeryl.getFont()).stringWidth("Xp :")+4,20));
        txtLvl.setHorizontalAlignment(JTextField.CENTER);
        panLvl.add(labLvl,BorderLayout.WEST);
        panLvl.add(txtLvl,BorderLayout.CENTER);

        //pts destin
        JPanel panDestin = new JPanel();
        panDestin.setLayout(new BorderLayout());
        JLabel labDestin = new JLabel("pts :");
        txtDestin = new JTextField();
        txtDestin.setHorizontalAlignment(JTextField.CENTER);
        panDestin.add(labDestin,BorderLayout.WEST);
        panDestin.add(txtDestin,BorderLayout.CENTER);

          //gestion du nom
        JPanel panNom = new JPanel();
        panNom.setBorder(BorderFactory.createTitledBorder(""));
        panNom.setPreferredSize(dimensionPrincipal);
        panNom.setLayout(new BorderLayout());
        labNom= new JLabel("Nom : ");
        labTxtNom=new JLabel();
        labNom.setFont(font);
        labTxtNom.setFont(font);
        panNom.add(labNom,BorderLayout.WEST);
        panNom.add(labTxtNom,BorderLayout.CENTER);

        //gestion de l'origine
        JPanel panOrigine =  new JPanel();
        panOrigine.setBorder(BorderFactory.createTitledBorder(""));
        panOrigine.setPreferredSize(dimensionPrincipal);
        panOrigine.setLayout(new BorderLayout());
        labOrigine= new JLabel("Origine : ");
        labTxtOrigine=new JLabel();
        labOrigine.setFont(font);
        labTxtOrigine.setFont(font);
        panOrigine.add(labOrigine,BorderLayout.WEST);
        panOrigine.add(labTxtOrigine,BorderLayout.CENTER);

        //gestion du metier
        JPanel panMetier =  new JPanel();
        panMetier.setBorder(BorderFactory.createTitledBorder(""));
        panMetier.setPreferredSize(dimensionPrincipal);
        panMetier.setLayout(new BorderLayout());
        labMetier= new JLabel("Metier : ");
        labTxtMetier=new JLabel();
        labMetier.setFont(font);
        labTxtMetier.setFont(font);
        panMetier.add(labMetier,BorderLayout.WEST);
        panMetier.add(labTxtMetier,BorderLayout.CENTER);

        //gestion du sexe
        JPanel panSexe = new JPanel();
        panSexe.setBorder(BorderFactory.createTitledBorder(""));
        panSexe.setPreferredSize(dimensionPrincipal);
        panSexe.setLayout(new BorderLayout());
        labSexe= new JLabel("Sexe : ");
        labTxtSexe = new JLabel();
        labSexe.setFont(font);
        labTxtSexe.setFont(font);
        panSexe.add(labSexe,BorderLayout.WEST);
        panSexe.add(labTxtSexe,BorderLayout.CENTER);


        //COURAGE
        JPanel panCourage = new JPanel();
        panCourage.setLayout(new GridLayout(1,4));
        JLabel labCourageBase = new JLabel("Courage de base :");
        labStatCourageBase = new JLabel();
        labStatCourageBase.setHorizontalAlignment(JTextField.CENTER);
        labStatCourageBase.setBorder(BorderFactory.createTitledBorder(""));
        JLabel labCourageMod = new JLabel(" modifié :");
        labCourageMod.setHorizontalAlignment(JTextField.CENTER);
        JTextField txtCourageMod = new JTextField();
        panCourage.add(labCourageBase);
        panCourage.add(labStatCourageBase);
        panCourage.add(labCourageMod);
        panCourage.add(txtCourageMod);

        //INTELLIGENCE
        JPanel panIntelligence = new JPanel();
        panIntelligence.setLayout(new GridLayout(1,4));
        JLabel labIntelligenceBase = new JLabel("Intelligence de base :");
        labStatIntelligenceBase = new JLabel();
        labStatIntelligenceBase.setHorizontalAlignment(JTextField.CENTER);
        labStatIntelligenceBase.setBorder(BorderFactory.createTitledBorder(""));
        JLabel labIntelligenceMod = new JLabel(" modifié :");
        labIntelligenceMod.setHorizontalAlignment(JTextField.CENTER);
        JTextField txtIntelligenceMod = new JTextField();
        panIntelligence.add(labIntelligenceBase);
        panIntelligence.add(labStatIntelligenceBase);
        panIntelligence.add(labIntelligenceMod);
        panIntelligence.add(txtIntelligenceMod);

        //CHARISME
        JPanel panCharisme = new JPanel();
        panCharisme.setLayout(new GridLayout(1,4));
        JLabel labCharismeBase = new JLabel("Charisme de base :");
        labStatCharismeBase = new JLabel();
        labStatCharismeBase.setHorizontalAlignment(JTextField.CENTER);
        labStatCharismeBase.setBorder(BorderFactory.createTitledBorder(""));
        JLabel labCharismeMod = new JLabel(" modifié :");
        labCharismeMod.setHorizontalAlignment(JTextField.CENTER);
        JTextField txtCharismeMod = new JTextField();
        panCharisme.add(labCharismeBase);
        panCharisme.add(labStatCharismeBase);
        panCharisme.add(labCharismeMod);
        panCharisme.add(txtCharismeMod);

        //ADRESSE
        JPanel panAdresse = new JPanel();
        panAdresse.setLayout(new GridLayout(1,4));
        JLabel labAdresseBase = new JLabel("Adresse de base :");
        labStatAdresseBase = new JLabel();
        labStatAdresseBase.setHorizontalAlignment(JTextField.CENTER);
        labStatAdresseBase.setBorder(BorderFactory.createTitledBorder(""));
        JLabel labAdresseMod = new JLabel(" modifié :");
        labAdresseMod.setHorizontalAlignment(JTextField.CENTER);
        JTextField txtAdresseMod = new JTextField();
        panAdresse.add(labAdresseBase);
        panAdresse.add(labStatAdresseBase);
        panAdresse.add(labAdresseMod);
        panAdresse.add(txtAdresseMod);

        //FORCE
        JPanel panForce = new JPanel();
        panForce.setLayout(new GridLayout(1,4));
        JLabel labForceBase = new JLabel("Force de base :");
        labStatForceBase = new JLabel();
        labStatForceBase.setHorizontalAlignment(JTextField.CENTER);
        labStatForceBase.setBorder(BorderFactory.createTitledBorder(""));
        JLabel labForceMod = new JLabel(" modifié :");
        labForceMod.setHorizontalAlignment(JTextField.CENTER);
        JTextField txtForceMod = new JTextField();
        panForce.add(labForceBase);
        panForce.add(labStatForceBase);
        panForce.add(labForceMod);
        panForce.add(txtForceMod);   */



    /*
        //MAGIE PHYSIQUE
        JPanel panMagiePhy = new JPanel();
        panMagiePhy.setLayout(new GridLayout(1,4));
        JLabel labMagiePhyBase = new JLabel("MagiePhy de base :");
        labStatMagiePhyBase = new JLabel();
        labStatMagiePhyBase.setHorizontalAlignment(JTextField.CENTER);
        labStatMagiePhyBase.setBorder(BorderFactory.createTitledBorder(""));
        JLabel labMagiePhyMod = new JLabel(" modifié :");
        labMagiePhyMod.setHorizontalAlignment(JTextField.CENTER);
        JTextField txtMagiePhyMod = new JTextField();
        panMagiePhy.add(labMagiePhyBase);
        panMagiePhy.add(labStatMagiePhyBase);
        panMagiePhy.add(labMagiePhyMod);
        panMagiePhy.add(txtMagiePhyMod);

        //MAGIE PSY
        JPanel panMagiePsy = new JPanel();
        panMagiePsy.setLayout(new GridLayout(1,4));
        JLabel labMagiePsyBase = new JLabel("MagiePsy de base :");
        labStatMagiePsyBase = new JLabel();
        labStatMagiePsyBase.setHorizontalAlignment(JTextField.CENTER);
        labStatMagiePsyBase.setBorder(BorderFactory.createTitledBorder(""));
        JLabel labMagiePsyMod = new JLabel(" modifié :");
        labMagiePsyMod.setHorizontalAlignment(JTextField.CENTER);
        JTextField txtMagiePsyMod = new JTextField();
        panMagiePsy.add(labMagiePsyBase);
        panMagiePsy.add(labStatMagiePsyBase);
        panMagiePsy.add(labMagiePsyMod);
        panMagiePsy.add(txtMagiePsyMod);


        //RES MAGIE
        JPanel panResMagie = new JPanel();
        panResMagie.setLayout(new GridLayout(1,4));
        JLabel labResMagieBase = new JLabel("ResMagie de base :");
        labStatResMagieBase = new JLabel();
        labStatResMagieBase.setHorizontalAlignment(JTextField.CENTER);
        labStatResMagieBase.setBorder(BorderFactory.createTitledBorder(""));
        JLabel labResMagieMod = new JLabel(" modifié :");
        labResMagieMod.setHorizontalAlignment(JTextField.CENTER);
        JTextField txtResMagieMod = new JTextField();
        panResMagie.add(labResMagieBase);
        panResMagie.add(labStatResMagieBase);
        panResMagie.add(labResMagieMod);
        panResMagie.add(txtResMagieMod);

        //ATTAQUE
        JPanel panAtt = new JPanel();
        panAtt.setLayout(new GridLayout(1,4));
        JLabel labAttBase = new JLabel("Attaque de base :");
        labStatAttBase = new JLabel();
        labStatAttBase.setHorizontalAlignment(JTextField.CENTER);
        labStatAttBase.setBorder(BorderFactory.createTitledBorder(""));
        JLabel labAttMod = new JLabel(" modifié :");
        labAttMod.setHorizontalAlignment(JTextField.CENTER);
        JTextField txtAttMod = new JTextField();
        panAtt.add(labAttBase);
        panAtt.add(labStatAttBase);
        panAtt.add(labAttMod);
        panAtt.add(txtAttMod);

        //PARADE
        JPanel panPar = new JPanel();
        panPar.setLayout(new GridLayout(1,4));
        JLabel labParBase = new JLabel("Parrade de base :");
        labStatParBase = new JLabel();
        labStatParBase.setHorizontalAlignment(JTextField.CENTER);
        labStatParBase.setBorder(BorderFactory.createTitledBorder(""));
        JLabel labParMod = new JLabel(" modifié :");
        labParMod.setHorizontalAlignment(JTextField.CENTER);
        JTextField txtParMod = new JTextField();
        panPar.add(labParBase);
        panPar.add(labStatParBase);
        panPar.add(labParMod);
        panPar.add(txtParMod);


          Pv
    JPanel panPv = new JPanel();
        panPv.setLayout(new GridLayout(1,4));
    JLabel labMaxPv = new JLabel("max Pv :");
    JTextField txtMaxPv = new JTextField();
        txtMaxPv.setHorizontalAlignment(JTextField.CENTER);
    JLabel labActuelPv = new JLabel("now :");
    JTextField txtActuelPv = new JTextField();
        txtActuelPv.setHorizontalAlignment(JTextField.CENTER);
        panPv.add(labMaxPv);panPv.add(txtMaxPv);
        panPv.add(labActuelPv);panPv.add(txtActuelPv);

     //Ea
    JPanel panEa = new JPanel();
       : panEa.setLayout(new GridLayout(1,4));
        :JLabel labMaxEa = new JLabel("max Ea :");
       : JTextField txtMaxEa = new JTextField();
       : txtMaxEa.setHorizontalAlignment(JTextField.CENTER);
    JLabel labActuelEa = new JLabel("now :");
    JTextField txtActuelEa = new JTextField();
        txtActuelEa.setHorizontalAlignment(JTextField.CENTER);
        panEa.add(labMaxEa);panEa.add(txtMaxEa);
        panEa.add(labActuelEa);panEa.add(txtActuelEa);








        //beryliumrR
    JPanel panBeryl = new JPanel();
        panBeryl.setLayout(new BorderLayout());
    JLabel labBeryl = new JLabel("Berylium :");
    JTextField txtBeryl = new JTextField(" 0 ");
        txtBeryl.setHorizontalAlignment(JTextField.CENTER);
        panBeryl.add(labBeryl,BorderLayout.WEST);
        panBeryl.add(txtBeryl,BorderLayout.CENTER);



    //thritil
    JPanel panThrit = new JPanel();
        panThrit.setLayout(new BorderLayout());
    JLabel labThrit = new JLabel("Thritil :");
    JTextField txtThrit = new JTextField(" 0 ");
        labThrit.setPreferredSize(dimensionlabBery);
        txtThrit.setHorizontalAlignment(JTextField.CENTER);
        panThrit.add(labThrit,BorderLayout.WEST);
        panThrit.add(txtThrit,BorderLayout.CENTER);

    //or
    JPanel panOr = new JPanel();
        panOr.setLayout(new BorderLayout());
    JLabel labOr = new JLabel("Or :");
    txtOr = new JTextField("");
        labOr.setPreferredSize(dimensionlabBery);
        txtOr.setHorizontalAlignment(JTextField.CENTER);
        panOr.add(labOr,BorderLayout.WEST);
        panOr.add(txtOr,BorderLayout.CENTER);*/

        /*//argent
        JPanel panArgent = new JPanel();
        panArgent.setLayout(new BorderLayout());
        JLabel labArgent = new JLabel("Argent :");
        JTextField txtArgent = new JTextField(" 0 ");
        labArgent.setPreferredSize(dimensionlabBery);
        txtArgent.setHorizontalAlignment(JTextField.CENTER);
        panArgent.add(labArgent,BorderLayout.WEST);
        panArgent.add(txtArgent,BorderLayout.CENTER);

        //cuivre
        JPanel panCuivre = new JPanel();
        panCuivre.setLayout(new BorderLayout());
        JLabel labCuivre = new JLabel("Cuivre :");
        JTextField txtCuivre = new JTextField(" 0 ");
        labCuivre.setPreferredSize(dimensionlabBery);
        txtCuivre.setHorizontalAlignment(JTextField.CENTER);
        panCuivre.add(labCuivre,BorderLayout.WEST);
        panCuivre.add(txtCuivre,BorderLayout.CENTER);

*/
/*
    //-------------------------------------------------------------------------------------------------------------------//
    //-------------------------------------------------------------------------------------------------------------------//

    //ICI ON MARQUE TOUTES LES RESTRICTIONS DES ORIGINES ET METIER PAR RAPPORT AU STAT
    // ON AFFICHERAS UNE MESSAGE D'ERREURE AVEC L'ERREURE QUI A ÉTAIT COMMISE LORS DE L'ATTRIBUTION DES ORIGINES


    //CAS OU TU NE PEUX PAS ÊTRE BARBARE
                if (info.origine.equals("barbare")&&( info.courage<12 || info.force<13)){
        JOptionPane errorCreationBareBare = new JOptionPane("tu ne remplis pas les conditions nécessaire" +
                " pour être un barebare tu n'es qu'un pleutre.\nIl te faut minumun 12 de courage et 13 de force.\n" +
                "Tu as "+info.courage+" en courage et "+info.force+" en force.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogBarbare = errorCreationBareBare.createDialog("Erreur d'attribution de l'origine");
        dialogBarbare.setVisible(true);
    }

    //CAS OU TU NE PEUX PAS ÊTRE UN NAIN
                else if(info.origine.equals("nain")&& (info.courage<11 || info.force<12)){
        JOptionPane errorCreationNain = new JOptionPane("tu n'es pas digne d'être un nain !\n"
                +"il te faut minimum 11 de Courage et 12 de force\n" +
                "tu as "+info.courage+" en courage et "+info.force+" en force.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogNain = errorCreationNain.createDialog("Erreur d'attribution de l'origine");
        dialogNain.setVisible(true);
    }

    //CAS OU TU NE PEUX PAS ÊTRE UN HAUT ELFE
                else if(info.origine.equals("haut elfe")&&(info.intelligence<11|| info.charisme<12||info.adresse<12||info.force>12)){
        JOptionPane optionPaneErrorHautElfe = new JOptionPane("tu ne correspond pas aux critères pour être un haut elfe\n" +
                "il te faut mini 11 d'intélligence, 12 de charisme, 12 d'adresse et un max de 12 de force\n" +
                "tu as "+info.intelligence+" en intéligence, "+info.charisme+" en charisme, "
                +info.adresse+" d'adresse et tu as "+info.force+" en force",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogHautElfe = optionPaneErrorHautElfe.createDialog("Erreur d'attribution de l'origine");
        dialogHautElfe.setVisible(true);
    }

    //CAS OU TU NE PEUX PAS ÊTRE UN DEMI ELFE
                else if (info.origine.equals("demi elfe")&&( info.charisme<10 || info.adresse<11)){
        JOptionPane optionPaneErrorDemiElfe = new JOptionPane("tu n'as pas les caractéristiques requises pour être demi efle.\n" +
                "Il te faut un minimum de 10 de charisme et de 11 d'adresse.\n" +
                "Tu as "+info.charisme+" de charisme et "+info.adresse+" d'adresse",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogDemiEfle = optionPaneErrorDemiElfe.createDialog("Erreur d'attribution de l'origine");
        dialogDemiEfle.setVisible(true);
    }

    //CAS OU TU NE PEUX PAS ÊTRE UN ELFE SYLVAIN
                else if(info.origine.equals("elfe sylvain")&&(info.charisme<12 || info.adresse<10 || info.force>11)){
        JOptionPane jOptionPaneErrorElfeSylvain = new JOptionPane("tu n'as pas les caractérisques requises pour être un elfe sylvain.\n" +
                "Il te faut un minimum de 12 de charisme, de 10 d'adresse et un maximum de 11 de force.\n" +
                "Tu as "+info.charisme+" de charisme et "+info.adresse+" en adresse et tu as "+info.force+" en force",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogElfeSylvain=jOptionPaneErrorElfeSylvain.createDialog("Erreur d'attribution de l'origine");
        dialogElfeSylvain.setVisible(true);
    }

    //CAS OU TU NE PEUT PAS ÊTRE UN EFLE NOIR
                else if (info.origine.equals("elfe noir")&&(info.intelligence<12||info.adresse<13)){
        JOptionPane jOptionPaneErrorEfleNoir = new JOptionPane("tu n'as pas les caractérisques requises pour être un elfe noir.\n" +
                "Il te faut un minimum de 12 d'intelligence et de 13 d'adresse.\n" +
                "Tu as "+info.intelligence+" d'intelligence et "+info.adresse+" en adresse.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogElfeNoir = jOptionPaneErrorEfleNoir.createDialog("Erreur d'attribution de l'origine");
        dialogElfeNoir.setVisible(true);
    }

    //CAS OU TU NE PEUX PAS ÊTRE UN ORQUE (C'EST TRISTE QUAND MÊME)
                else if(info.origine.equals("orque")&&(info.intelligence>8||info.charisme>10||info.force<12)){
        JOptionPane jOptionPaneErrorOrque = new JOptionPane("tu ne peux même pas être un vil orque ( c'est vachement triste quand meme.)\n"+
                "Il te faut un maximum de 8 d'intelligence et de 10 de charisme ainsi qu'un minimum de 12 de force.\n" +
                "Tu as "+info.intelligence+" d'intelligence, "+info.charisme+" de charisme et "+info.force+" en force",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogOrque = jOptionPaneErrorOrque.createDialog("Erreur d'attribution de l'origine");
        dialogOrque.setVisible(true);
    }

    //CAS OU TU NE PEUX PAS ÊTRE UN DEMI ORQUE
                else if(info.origine.equals("demi orque")&&(info.intelligence>10||info.adresse>11||info.force<12)){
        JOptionPane jOptionPaneErrorDemiOrque = new JOptionPane("tu ne peux même pas être un demi-orque ( c'est vachement triste quand meme.)\n"+
                "Il te faut un maximum de 10 d'intelligence et de de 11 d'adresse ainsi qu'un minimum de 12 de force.\n" +
                "Tu as "+info.intelligence+" d'intelligence, "+info.adresse+" d'adresse et "+info.force+" en force",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogDemiOrque = jOptionPaneErrorDemiOrque.createDialog("Erreur d'attribution de l'origine");
        dialogDemiOrque.setVisible(true);
    }

    //CAS OU TU NE PEUX PAS ÊTRE UN GOBELIN
                else if(info.origine.equals("gobelin")&&(info.courage>10||info.intelligence>10||info.charisme>8||info.force>9)){
        JOptionPane jOptionPaneErrorGobelin = new JOptionPane("tu ne peux même pas être un pauvre gobelin ( c'est vachement triste quand meme.)\n"+
                "Il te faut un maximum de 10 de courage, 10 d'intelligence, 8 de charisme et 9 de force.\n" +
                "Tu as"+info.courage+" de courage, "+info.intelligence+" d'intelligence, "+info.charisme+" de charisme et "+info.force+" en force",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogGobelin = jOptionPaneErrorGobelin.createDialog("Erreur d'attribution de l'origine");
        dialogGobelin.setVisible(true);
    }

    //CAS OU TU NE PEUX PAS ÊTRE UN OGRE
                else if(info.origine.equals("ogre")&&(info.intelligence>9||info.charisme>10||info.adresse>11||info.force<13)){
        JOptionPane jOptionPaneErrorOgre = new JOptionPane("tu ne peux pas être un ogre bien badass.\n"+
                "Il te faut un maximum de 9 d'intelligence, 10 de charisme, 11 d'adresse et d'un minumum de 13 de force.\n" +
                "Tu as "+info.intelligence+" d'intelligence, "+info.charisme+" de charisme, "+info.adresse+" d'adresse et "+info.force+" en force",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogOgre = jOptionPaneErrorOgre.createDialog("Erreur d'attribution de l'origine");
        dialogOgre.setVisible(true);
    }

    //CAS OU TU NE PEUX PAS ÊTRE UN HOBBIT
                else if(info.origine.equals("hobbit")&&(info.courage<12||info.intelligence<10||info.force>10)){
        JOptionPane jOptionPaneErrorHobbit = new JOptionPane("tu ne peux pas être un héroique petit hobbit !!!\n"+
                "Il te faut un minimum de 12 de courage et de 10 d'intelligence ainsi qu'un maximum de 10 de force.\n" +
                "Tu as "+info.courage+" de courage, "+info.intelligence+" d'intelligence et "+info.force+" en force",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogHobbit = jOptionPaneErrorHobbit.createDialog("Erreur d'attribution de l'origine");
        dialogHobbit.setVisible(true);
    }

    //CAS OU TU NE PEUX PAS ÊTRE UN GNÔME
                else if(info.origine.equals("gnôme des fôrets")&&(info.intelligence<10||info.adresse<13||info.force>8)){
        JOptionPane jOptionPaneErrorGnôme = new JOptionPane("tu ne peux pas être un mignon petit gnôme de fôret !!!\n"+
                "Il te faut un minimum de 10 d'intelligence et de 13 d'adresse ainsi qu'un maximum de 8 de force.\n" +
                "Tu as "+info.intelligence+" d'intelligence, "+info.adresse+" d'adresse et "+info.force+" en force",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogGnôme = jOptionPaneErrorGnôme.createDialog("Erreur d'attribution de l'origine");
        dialogGnôme.setVisible(true);
    }

    //-------------------------------------------------------------------------------------------------------------------//
    //-------------------------------------------------------------------------------------------------------------------//

    //ICI ON MARQUE TOUTES LES RESTRICTIONS DES METIERS PAR RAPPORT AU STAT
    // ON AFFICHERAS UNE MESSAGE D'ERREURE AVEC L'ERREURE QUI A ÉTAIT COMMISE LORS DE L'ATTRIBUTION DU METIER

    //CAS OU TU NE PEUX PAS ÊTRE UN GUERRIER
                else if(info.metier.equals("guerrier")&&(info.courage<12||info.force<12)){
        JOptionPane jOptionPaneErrorGuerrierGladiateur = new JOptionPane("tu ne peux pas être un guerrier car tu nes pas assez fort et courageux !! \n"+
                "Il te faut un minimum de 12 de courage et de 12 de force.\n" +
                "Tu as "+info.courage+" de courage et "+info.force+" en force",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogGuerrier = jOptionPaneErrorGuerrierGladiateur.createDialog("Erreur d'attribution du metier");
        dialogGuerrier.setVisible(true);
    }
    //CAS OU TU NE PEUX PAS ÊTRE UN NINJA OU UN ASSASSIN
                else if(info.metier.equals("assassin")&&(info.adresse<13)){
        JOptionPane jOptionPaneErrorAssasinVoleur = new JOptionPane("tu n'es pas assez habile pour être un asssasin.\n" +
                "Il te faut un minimum de 13 d'adresse.\n" +
                "Tu as "+info.adresse+" d'adresse.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogAssassinVoleur = jOptionPaneErrorAssasinVoleur.createDialog("Erreur d'attribution du metier");
        dialogAssassinVoleur.setVisible(true);
    }
    //CAS OU TU NE PEUXPAS ÊTRE UN VOLEUR
                else if(info.metier.equals("voleur")&&(info.adresse<12)){
        JOptionPane jOptionPaneErrorVoleur = new JOptionPane("tu n'es pas assez habile pour être un voleur.\n" +
                "Il te faut un minimum de 12 d'adresse.\n" +
                "Tu as "+info.adresse+" d'adresse.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogVoleur = jOptionPaneErrorVoleur.createDialog("Erreur d'attribution du metier");
        dialogVoleur.setVisible(true);
    }
    //CAS OU TU NE PEUX PAS ÊTRE UN PRÊTRE
                else if(info.metier.equals("prêtre")&&(info.charisme<12)){
        JOptionPane jOptionPaneErrorPretre = new JOptionPane("tu n'es pas assez charismatique pour être un prêtre.\n" +
                "Il te faut un minimum de 12 de charisme.\n" +
                "Tu as "+info.charisme+" de charisme.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogPretre = jOptionPaneErrorPretre.createDialog("Erreur d'attribution du metier");
        dialogPretre.setVisible(true);
    }
    //CAS OU TU NE PEUX PAS ÊTRE UN MAGE
                else if(info.metier.equals("mage")&&(info.intelligence<12)){
        JOptionPane jOptionPaneErrorMage = new JOptionPane("tu n'es pas assez intellignent pour être un mage.\n" +
                "Il te faut un minimum de 12 d'intelligence.\n" +
                "Tu as "+info.intelligence+" d'intelligence.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogMage = jOptionPaneErrorMage.createDialog("Erreur d'attribution du metier");
        dialogMage.setVisible(true);
    }
    //CAS OU TU NE PEUX PAS ÊTRE UN PALADIN
                else if(info.metier.equals("paladin")&&(info.courage<12||info.intelligence<10||info.charisme<11||info.force<9)){
        JOptionPane jOptionPaneErrorPaladin = new JOptionPane("tu n'es pas les caractéristiques requisent pour être un paladin.\n" +
                "Il te faut un minimum de 12 de courage, 10 d'intelligence, 11 de charisme et 9 de force.\n" +
                "Tu as "+info.courage+" de courage, "+info.intelligence+"d'intelligence, "+info.charisme+" de charisme et "+info.force+" de force.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogMPaladin = jOptionPaneErrorPaladin.createDialog("Erreur d'attribution du metier");
        dialogMPaladin.setVisible(true);
    }
    //CAS OU TU NE PEUX PAS ÊTRE UN RANGER
                else if(info.metier.equals("ranger")&&(info.charisme<10||info.adresse<10)){
        JOptionPane jOptionPaneErrorRanger = new JOptionPane("tu n'as pas ce qu'il faut pour être un ranger !!.\n" +
                "Il te faut un minimum de 10 de charisme et d'adresse.\n" +
                "Tu as "+info.charisme+" de charisme et "+info.adresse+" d'adresse.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogRanger = jOptionPaneErrorRanger.createDialog("Erreur d'attribution du metier");
        dialogRanger.setVisible(true);
    }
    //CAS OU TU NE PEUX PAS ÊTRE UN MENESTREL
                else if(info.metier.equals("menestrel")&&(info.charisme<12||info.adresse<11)){
        JOptionPane jOptionPaneErrorMenestrel = new JOptionPane("tu n'as pas ce qu'il faut pour être un Menestrel !!.\n" +
                "Il te faut un minimum de 12 de charisme et de 11 d'adresse.\n" +
                "Tu as "+info.charisme+" de charisme et "+info.adresse+" d'adresse.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogMenestrel = jOptionPaneErrorMenestrel.createDialog("Erreur d'attribution du metier");
        dialogMenestrel.setVisible(true);
    }
    //CAS OU TU NE PEUX PAS ÊTRE UN MARCHAND
                else if(info.metier.equals("marchand")&&(info.intelligence<12||info.charisme<11)){
        JOptionPane jOptionPaneMarchand = new JOptionPane("tu n'as pas ce qu'il faut pour être un Marchand !!.\n" +
                "Il te faut un minimum de 12 d'intelligence et 11 de charisme.\n" +
                "Tu as "+info.intelligence+" d'intelligence et "+info.charisme+" de charisme.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogMarchand = jOptionPaneMarchand.createDialog("Erreur d'attribution du metier");
        dialogMarchand.setVisible(true);
    }
    //CAS OU TU NE PEUX PAS ÊTRE BOURGEOIS/NOBLE
                else if(info.metier.equals("bourgeois")&&(info.intelligence<10||info.charisme<11)){
        JOptionPane jOptionPaneNoble = new JOptionPane("tu n'as pas ce qu'il faut pour être un noble !!.\n" +
                "Il te faut un minimum de 10 d'intelligence et 11 de charisme.\n" +
                "Tu as "+info.intelligence+" d'intelligence et "+info.charisme+" de charisme.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogNoble = jOptionPaneNoble.createDialog("Erreur d'attribution du metier");
        dialogNoble.setVisible(true);
    }
    //CAS OU TU NE PEUX PAS ÊTRE UN PIRATE
                else if(info.metier.equals("pirate")&&(info.courage<11||info.adresse<11)){
        JOptionPane jOptionPanePirate = new JOptionPane("tu n'as pas ce qu'il faut pour être un Pirate !!.\n" +
                "Il te faut un minimum de 11 de courage et 11 d'adresse.\n" +
                "Tu as "+info.courage+" de courage et "+info.adresse+" d'adresse.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogPirate = jOptionPanePirate.createDialog("Erreur d'attribution du metier");
        dialogPirate.setVisible(true);
    }
    //CAS OU TU NE PEUX PAS ÊTRE INGENIEUR
                else if(info.metier.equals("ingénieur")&&(info.adresse<11)){
        JOptionPane jOptionPaneMarchand = new JOptionPane("tu n'as pas ce qu'il faut pour être un bon Ingénieur !!.\n" +
                "Il te faut un minimum de 11 d'adresse.\n" +
                "Tu as "+info.adresse+" d'adresse.",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);

        JDialog dialogMarchand = jOptionPaneMarchand.createDialog("Erreur d'attribution du metier");
        dialogMarchand.setVisible(true);
    }
}
*/