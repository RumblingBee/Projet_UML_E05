package testsControleurs;

import org.junit.Before;
import org.junit.Test;
import uml.e05.monestier.dezette.controleurs.ControleurPrincipal1;
import uml.e05.monestier.dezette.metier.Catalogue;
import uml.e05.monestier.dezette.metier.I_Catalogue;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testsControleurPrincipal {
    I_Catalogue cat;
    ControleurPrincipal1 controleurPrincipal;
    JTextField quantiteSaisie;
    JTextField nomSaisie;
    JTextField prixSaisie;

    @Before
    public void setUp() {

        quantiteSaisie = new JTextField();
        prixSaisie = new JTextField();
        nomSaisie = new JTextField();


        cat = new Catalogue();
        cat.initialisationCatalogue();

        cat.addProduit("produitDeTest",10,10);
        cat.addProduit("produitDeTest2",20,30);

        controleurPrincipal= ControleurPrincipal1.getInstance();

        ajouterProduitCatalogueDuControleur("produitDeTest", "10", "10");
        ajouterProduitCatalogueDuControleur("produitDeTest2", "20", "30");

    }

    private void ajouterProduitCatalogueDuControleur(String nomProduit, String prixSaisi, String quantiteSaisie) {
        nomSaisie.setText(nomProduit);
        prixSaisie.setText(prixSaisi);
        this.quantiteSaisie.setText(quantiteSaisie);


        controleurPrincipal.creerPdt(nomSaisie,prixSaisie, this.quantiteSaisie);
    }

    @Test
    public void testGetProduits() {

        assertEquals(cat.toString(),controleurPrincipal.getProduits().toString());

    }
    @Test
    public void testGetNomProduits() {

        assertEquals(cat.getNomProduits(),controleurPrincipal.getNomProduits());

    }




}
