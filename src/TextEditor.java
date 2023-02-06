import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class TextEditor extends JFrame {
    private final JTextArea tA;
    private final JMenuItem MI_NEW;
    private final JMenuItem MI_OPEN;
    private final JMenuItem MI_SAVE;
    private final JMenuItem MI_SAVEAS;
    private final JMenuItem MI_EXIT;
    private final JMenu M_EDIT;
    private final JMenuItem MI_CUT;
    private final JMenuItem MI_COPY;
    private final JMenuItem MI_ERASE;
    private final JMenuItem MI_SLCTALL;
    private final JMenu SM_BGCOLOR;
    private final JMenu SM_TXTCOLOR;
    private final JRadioButtonMenuItem[] couleurFond;
    private final JRadioButtonMenuItem[] couleurTexte;
    private final ButtonGroup groupCouleursFond;
    private final ButtonGroup groupCouleursTexte;
    private final String[] nomCouleur1 = {"Blanc", "Noir", "Rouge", "Vert", "Bleu", "Jaune", "Orange", "Gris"};
    private final String[] nomCouleur2 = {"Blanc", "Noir", "Rouge", "Vert", "Bleu", "Jaune", "Orange", "Gris"};
    private final Color[] couleur1 = {Color.white, Color.black, new Color(150, 0, 0), new Color(0, 150, 0), new Color(0, 0, 150), new Color(225, 225, 0), new Color(175, 100, 0), new Color(100, 100, 100)};
    private final Color[] couleur2 = {Color.white, Color.black, Color.red, Color.green, Color.blue, Color.yellow, Color.orange, Color.gray};
    private final JMenuItem MI_CMT;
    private final JMenuItem MI_CR;
    private final JMenuItem MI_ABOUT;
    private final JPopupMenu PM_EDIT;
    private final JMenuItem PMI_CUT;
    private final JMenuItem PMI_COPY;
    private final JMenuItem PMI_PASTE;
    private final JMenuItem PMI_ERASE;
    private final JMenuItem PMI_SLCTALL;
    private File fichierCourant = null;

    //partie2
    public TextEditor() {
        super("\"You can make anything by writing...let's start now\"");
        GestionEvents evt = new GestionEvents();

        tA = new JTextArea();
        tA.setBackground(couleur1[0]);
        tA.setForeground(couleur2[1]);

        JMenuBar barre = new JMenuBar();

        // Menu 1: Fichier
        JMenu M_FILE = new JMenu("Fichier");
        M_FILE.setMnemonic('F');
        MI_NEW = new JMenuItem("Nouveau");
        MI_NEW.setMnemonic('N');
        MI_NEW.addActionListener(evt);
        M_FILE.add(MI_NEW);
        MI_OPEN = new JMenuItem("Ouvrir...");
        MI_OPEN.setMnemonic('O');
        MI_OPEN.addActionListener(evt);
        M_FILE.add(MI_OPEN);
        MI_SAVE = new JMenuItem("Enregistrer");
        MI_SAVE.setMnemonic('E');
        MI_SAVE.addActionListener(evt);
        M_FILE.add(MI_SAVE);
        MI_SAVEAS = new JMenuItem("Enregistrer sous...");
        MI_SAVEAS.setMnemonic('r');
        MI_SAVEAS.addActionListener(evt);
        M_FILE.add(MI_SAVEAS);
        M_FILE.addSeparator();
        MI_EXIT = new JMenuItem("Quitter");
        MI_EXIT.setMnemonic('Q');
        MI_EXIT.addActionListener(evt);
        M_FILE.add(MI_EXIT);
        barre.add(M_FILE);

        // Menu 2: Edition
        M_EDIT = new JMenu("Edition");
        M_EDIT.setMnemonic('E');
        M_EDIT.addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        updateMenuOptions();
                    }
                }
        );
        MI_CUT = new JMenuItem("Couper");
        MI_CUT.setMnemonic('C');
        MI_CUT.addActionListener(evt);
        M_EDIT.add(MI_CUT);
        MI_COPY = new JMenuItem("Copier");
        MI_COPY.setMnemonic('p');
        MI_COPY.addActionListener(evt);
        M_EDIT.add(MI_COPY);
        JMenuItem MI_PASTE = new JMenuItem("Coller");
        MI_PASTE.setMnemonic('o');
        MI_PASTE.addActionListener(evt);
        M_EDIT.add(MI_PASTE);
        MI_ERASE = new JMenuItem("Effacer");
        MI_ERASE.setMnemonic('E');
        MI_ERASE.addActionListener(evt);
        M_EDIT.add(MI_ERASE);
        M_EDIT.addSeparator();
        MI_SLCTALL = new JMenuItem("Selectionner tout");
        MI_SLCTALL.setMnemonic('t');
        MI_SLCTALL.addActionListener(evt);
        M_EDIT.add(MI_SLCTALL);
        barre.add(M_EDIT);

        // Menu 3: Format
        JMenu M_FORMAT = new JMenu("Format");
        M_FORMAT.setMnemonic('o');
        // Sous-menu 1: Couleur de fond
        SM_BGCOLOR = new JMenu("Couleur du fond");
        SM_BGCOLOR.setMnemonic('f');
        groupCouleursFond = new ButtonGroup();
        couleurFond = new JRadioButtonMenuItem[nomCouleur1.length];
        for (int i = 0; i < nomCouleur1.length; i++) {
            couleurFond[i] = new JRadioButtonMenuItem(nomCouleur1[i]);
            if (couleur1[i] == tA.getBackground())
                couleurFond[i].setSelected(true);
            couleurFond[i].addActionListener(evt);
            groupCouleursFond.add(couleurFond[i]);
            SM_BGCOLOR.add(couleurFond[i]);
        }
        M_FORMAT.add(SM_BGCOLOR);
        // Sous-menu 2: Couleur du texte
        SM_TXTCOLOR = new JMenu("Couleur du texte");
        SM_TXTCOLOR.setMnemonic('t');
        groupCouleursTexte = new ButtonGroup();
        couleurTexte = new JRadioButtonMenuItem[nomCouleur2.length];
        for (int i = 0; i < nomCouleur2.length; i++) {
            couleurTexte[i] = new JRadioButtonMenuItem(nomCouleur2[i]);
            if (couleur2[i] == tA.getForeground())
                couleurTexte[i].setSelected(true);
            couleurTexte[i].addActionListener(evt);
            groupCouleursTexte.add(couleurTexte[i]);
            SM_TXTCOLOR.add(couleurTexte[i]);
        }
        M_FORMAT.add(SM_TXTCOLOR);
        barre.add(M_FORMAT);
        //fin partie2
//partie3
        // Menu 4: apropos
        JMenu M_INFO = new JMenu("A Propos");
        M_INFO.setMnemonic('P');
        MI_CMT = new JMenuItem("Commentaires");
        MI_CMT.setMnemonic('C');
        MI_CMT.addActionListener(evt);
        M_INFO.add(MI_CMT);
        MI_CR = new JMenuItem("Droit d'auteur");
        MI_CR.setMnemonic('D');
        MI_CR.addActionListener(evt);
        M_INFO.add(MI_CR);
        MI_ABOUT = new JMenuItem("A Propos");
        MI_ABOUT.setMnemonic('P');
        MI_ABOUT.addActionListener(evt);
        M_INFO.add(MI_ABOUT);
        barre.add(M_INFO);

        setJMenuBar(barre);

        // Popup (Copier-Coller etc...)
        PM_EDIT = new JPopupMenu();
        PMI_CUT = new JMenuItem("Couper");
        PMI_CUT.setMnemonic('C');
        PMI_CUT.addActionListener(evt);
        PM_EDIT.add(PMI_CUT);
        PMI_COPY = new JMenuItem("Copier");
        PMI_COPY.setMnemonic('p');
        PMI_COPY.addActionListener(evt);
        PM_EDIT.add(PMI_COPY);
        PMI_PASTE = new JMenuItem("Coller");
        PMI_PASTE.setMnemonic('o');
        PMI_PASTE.addActionListener(evt);
        PM_EDIT.add(PMI_PASTE);
        PMI_ERASE = new JMenuItem("Effacer");
        PMI_ERASE.setMnemonic('E');
        PMI_ERASE.addActionListener(evt);
        PM_EDIT.add(PMI_ERASE);
        PM_EDIT.addSeparator();
        PMI_SLCTALL = new JMenuItem("Selectionner tout");
        PMI_SLCTALL.setMnemonic('t');
        PMI_SLCTALL.addActionListener(evt);
        PM_EDIT.add(PMI_SLCTALL);

        lireFichier(new File("raccourcis.txt"));
        tA.addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        checkTrigger(e);
                    }

                    public void mouseReleased(MouseEvent e) {
                        checkTrigger(e);
                    }

                    private void checkTrigger(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            updatePopupOptions();
                            PM_EDIT.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
        );
        JScrollPane jsp = new JScrollPane(tA);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add("Center", jsp);

        setSize(600, 500);
        setLocation(200, 100);
        show();
    }

    private void updatePopupOptions() {
        if (tA.getSelectedText() == null) {
            PMI_CUT.setEnabled(false);
            PMI_COPY.setEnabled(false);
            PMI_ERASE.setEnabled(false);
        } else {
            PMI_CUT.setEnabled(true);
            PMI_COPY.setEnabled(true);
            PMI_ERASE.setEnabled(true);
        }
    }

    private void updateMenuOptions() {
        if (tA.getSelectedText() == null) {
            MI_CUT.setEnabled(false);
            MI_COPY.setEnabled(false);
            MI_ERASE.setEnabled(false);
        } else {
            MI_CUT.setEnabled(true);
            MI_COPY.setEnabled(true);
            MI_ERASE.setEnabled(true);
        }
    }
    //fin partie 3
//partie 4
    private void nouveauFichier() {
        fichierCourant = null;
        tA.setText("");
    }

    private void ouvrirFichier() {
        JFileChooser jfc = new JFileChooser();

        int resultat = jfc.showOpenDialog(this);
        if (resultat == JFileChooser.APPROVE_OPTION) {
            fichierCourant = jfc.getSelectedFile();
            lireFichier(fichierCourant);
        } else {
            fichierCourant = null;
        }
    }

    private void enregistrerFichier() {
        if (fichierCourant == null) {
            afficheMessage("Aucun emplacement n'a t specifier, un fichier nomme \"Nouveau Document Text.txt\" insera créé dans le répertoire  est le fichier JAR.", "Information");
            ecrireFichier(new File("Nouveau Document Texte.txt"));
        } else
            ecrireFichier(fichierCourant);
    }

    private void enregistrerFichierSous() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Enregistrer sous...");
        jfc.setApproveButtonText("Enregistrer");
        jfc.setApproveButtonToolTipText("Enregistrer le fichier a cet emplacement");

        int resultat = jfc.showOpenDialog(this);
        if (resultat == JFileChooser.APPROVE_OPTION)
            ecrireFichier(jfc.getSelectedFile());
    }

    private void lireFichier(File fichier) {
        String ligne;
        StringBuffer buf = new StringBuffer();
        try {
            tA.setText("");
            BufferedReader in = new BufferedReader(
                    new FileReader(fichier));
            while ((ligne = in.readLine()) != null)
                buf.append(ligne + '\n');
            tA.setText(buf.toString());
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private void ecrireFichier(File fichier) {
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fichier));
            out.write(tA.getText());
            out.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private void afficheMessage(String message, String titre) {
        JOptionPane.showMessageDialog(null, message,
                titre, JOptionPane.INFORMATION_MESSAGE);
    }

    public String getComments() {
        return "J'ai fais cette application dans le but d'un projet java .\n      -Group";
    }

    public String getCR() {
        return "@author: Group \n date: **/19/12/2022 \nDerniere modification: 22/12/2022 \n-Tout droits réservés-";
    }

    public String getAbout() {
        return "Vous pouvez modifier le contenu de ce programme  \nModifie par: [votre nom]} \ndate: [date (jj/mm/aa)].\n\n   Merci, \n      -Group";
    }


    private class GestionEvents implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (MI_NEW == e.getSource())
                nouveauFichier();

            if (MI_OPEN == e.getSource())
                ouvrirFichier();

            if (MI_SAVE == e.getSource())
                enregistrerFichier();

            if (MI_SAVEAS == e.getSource())
                enregistrerFichierSous();

            if (MI_EXIT == e.getSource())
                System.exit(0);

            if (M_EDIT == e.getSource())
                updateMenuOptions();

            if ("Couper" == e.getActionCommand())
                tA.cut();

            if ("Copier" == e.getActionCommand())
                tA.copy();

            if ("Coller" == e.getActionCommand())
                tA.paste();

            if ("Supprimer" == e.getActionCommand())
                tA.replaceSelection("");

            if ("Selectionner tout" == e.getActionCommand())
                tA.selectAll();

            for (int i = 0; i < nomCouleur1.length; i++) {
                if (couleurFond[i] == e.getSource())
                    tA.setBackground(couleur1[i]);
            }

            for (int i = 0; i < nomCouleur2.length; i++) {
                if (couleurTexte[i] == e.getSource())
                    tA.setForeground(couleur2[i]);
            }

            if (MI_CMT == e.getSource())
                afficheMessage(getComments(), "Commentaires");

            if (MI_CR == e.getSource())
                afficheMessage(getCR(), "Droit d'auteur");

            if (MI_ABOUT == e.getSource())
                afficheMessage(getAbout(), "A propos");
        }
    }
    //fin partie 4
    public static void main(String[] s) {
        TextEditor app = new TextEditor();
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}