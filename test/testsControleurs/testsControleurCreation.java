package testsControleurs;

import org.junit.Before;
import org.junit.Test;
import uml.e05.monestier.dezette.controleurs.ControleurCreation;
import uml.e05.monestier.dezette.metier.Catalogue;
import uml.e05.monestier.dezette.metier.I_Catalogue;

import javax.swing.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class testsControleurCreation {
    I_Catalogue cat;
    ControleurCreation controleurCreation;
    JTextField quantiteSaisie;
    JTextField nomSaisie;
    JTextField prixSaisie;

    @Before
    public void setUp() {
        cat = new Catalogue();
        cat.clear();
        cat.addProduit("produitDeTest",10,10);
        controleurCreation = new ControleurCreation();

        quantiteSaisie = new JTextField();
        prixSaisie = new JTextField();
        nomSaisie = new JTextField();



    }

    @Test
    public void creerNouveauProduit() {
        nomSaisie.setText("Nouveau produit");
        prixSaisie.setText("10");
        quantiteSaisie.setText("3");

        assertTrue(controleurCreation.creerPdt(nomSaisie,prixSaisie,quantiteSaisie,cat));
    }
    @Test
    public void creerProduitDejaExistant() {
        nomSaisie.setText("produitDeTest");
        prixSaisie.setText("10");
        quantiteSaisie.setText("3");

        assertFalse(controleurCreation.creerPdt(nomSaisie,prixSaisie,quantiteSaisie,cat));
    }
    @Test
    public void creerProduitQuantiteNulle() {
        nomSaisie.setText("produitQteNulle");
        prixSaisie.setText("10");
        quantiteSaisie.setText("0");

        assertFalse(controleurCreation.creerPdt(nomSaisie,prixSaisie,quantiteSaisie,cat));
    }
    @Test
    public void creerProduitQuantiteNegative() {
        nomSaisie.setText("produitQteNeg");
        prixSaisie.setText("10");
        quantiteSaisie.setText("-40");

        assertFalse(controleurCreation.creerPdt(nomSaisie,prixSaisie,quantiteSaisie,cat));
    }
    @Test
    public void creerProduitPrixNul() {
        nomSaisie.setText("produitQteNulle");
        prixSaisie.setText("0");
        quantiteSaisie.setText("10");

        assertFalse(controleurCreation.creerPdt(nomSaisie,prixSaisie,quantiteSaisie,cat));
    }
    @Test
    public void creerProduitPrixNeg() {
        nomSaisie.setText("produitQteNeg");
        prixSaisie.setText("-1");
        quantiteSaisie.setText("10");

        assertFalse(controleurCreation.creerPdt(nomSaisie,prixSaisie,quantiteSaisie,cat));
    }

}
