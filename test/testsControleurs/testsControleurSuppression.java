package testsControleurs;

import org.junit.Before;
import org.junit.Test;
import uml.e05.monestier.dezette.controleurs.ControleurSuppression;
import uml.e05.monestier.dezette.metier.Catalogue;
import uml.e05.monestier.dezette.metier.I_Catalogue;

import javax.swing.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testsControleurSuppression {

    I_Catalogue cat;
    ControleurSuppression controleurSuppression;
    JComboBox nomSaisi;

    @Before
    public void setUp() {
        cat = new Catalogue();
        cat.clear();
        cat.addProduit("produitDeTest",10,10);
        controleurSuppression = new ControleurSuppression();

        nomSaisi = new JComboBox();

    }
    @Test
    public void supprimerProduitCatalogue() {
        nomSaisi.setEditable(true);
        nomSaisi.setSelectedItem("produitDeTest");
        assertTrue(controleurSuppression.supprimerProduit(nomSaisi,cat));
    }
    @Test
    public void supprimerProduitInexistantCatalogue() {
        nomSaisi.setEditable(true);
        nomSaisi.setSelectedItem("produitInexistant");
        assertFalse(controleurSuppression.supprimerProduit(nomSaisi,cat));
    }

}
