package testsControleurs;

import org.junit.Before;
import org.junit.Test;
import uml.e05.monestier.dezette.controleurs.ControleurAchatVente;
import uml.e05.monestier.dezette.metier.Catalogue;
import uml.e05.monestier.dezette.metier.I_Catalogue;

import javax.swing.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testsControleurAchatVente {

    I_Catalogue cat;
    ControleurAchatVente controleurAchatVente;
    JTextField quantiteSaisie;

    @Before
    public void setUp() {
        cat = new Catalogue();
        cat.clear();
        cat.addProduit("produitDeTest",10,10);
//		Si votre Catalogue est un Singleton, il faut changer la ligne précédente puis vider le Catalogue avec la méthode clear() comme indiqué à la ligne suivante
//		cat.clear();
        controleurAchatVente = new ControleurAchatVente();

        quantiteSaisie = new JTextField();
        quantiteSaisie.setText("10");
    }

    @Test
    public void enregistrerAchat() {
        assertTrue(controleurAchatVente.enregistrerAchat(quantiteSaisie,"produitDeTest",cat));
    }
    @Test
    public void enregistrerAchatQuantiteNulle() {
        quantiteSaisie.setText("0");
        assertFalse(controleurAchatVente.enregistrerAchat(quantiteSaisie,"produitDeTest",cat));
    }
    @Test
    public void enregistrerAchatQuantiteNegative() {
        quantiteSaisie.setText("-15");
        assertFalse(controleurAchatVente.enregistrerAchat(quantiteSaisie,"produitDeTest",cat));
    }
    @Test
    public void enregistrerAchatNomNul() {
        quantiteSaisie.setText("10");
        assertFalse(controleurAchatVente.enregistrerAchat(quantiteSaisie,"",cat));
    }

    @Test
    public void enregistrerVente() {
        quantiteSaisie.setText("1");
        assertTrue(controleurAchatVente.enregistrerVente(quantiteSaisie,"produitDeTest",cat));
    }
    @Test
    public void enregistrerVenteQuantiteNulle() {
        quantiteSaisie.setText("0");
        assertFalse(controleurAchatVente.enregistrerVente(quantiteSaisie,"produitDeTest",cat));
    }
    @Test
    public void enregistrerVenteQuantiteNegative() {
        quantiteSaisie.setText("-20");
        assertFalse(controleurAchatVente.enregistrerVente(quantiteSaisie,"produitDeTest",cat));
    }
    @Test
    public void enregistrerVenteQuantiteTropGrande() {
        quantiteSaisie.setText("2000000");
        assertFalse(controleurAchatVente.enregistrerVente(quantiteSaisie,"produitDeTest",cat));
    }

}
