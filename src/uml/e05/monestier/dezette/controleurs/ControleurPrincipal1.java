/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.e05.monestier.dezette.controleurs;


import javax.swing.*;


import uml.e05.monestier.dezette.DAO.produitDAO.ProduitDAO;
import uml.e05.monestier.dezette.metier.Catalogue;
import uml.e05.monestier.dezette.metier.I_Catalogue;

/**
 *
 * @author clement
 */
public class ControleurPrincipal1 {
    
    private ControleurCreation creationControleur;
    private ControleurAchatVente controleurAchatVente;
    private ControleurSuppression suppressionControleur;
    private ControleurDAO controleurDAO;
    private String catalogueSelectionne;
    private static I_Catalogue Produits;
    private static ControleurPrincipal1 instance;

    public String getCatalogueSelectionne() {
        return catalogueSelectionne;
    }

    public void setCatalogueSelectionne(String catalogueSelectionne) {
        this.catalogueSelectionne = catalogueSelectionne;
    }

    private ControleurPrincipal1(){
        Produits=new Catalogue();
        controleurDAO = new ControleurDAO();
    }

    public void buildCatalogue(){
        Produits.initialisationCatalogue(this.catalogueSelectionne);
    }

    public static ControleurPrincipal1 getInstance(){
        if(instance==null){
            instance=new ControleurPrincipal1();
        }
        return instance;
    }
    
    public I_Catalogue getProduits() {
        return Produits;
    }
    
    public String[] getNomProduits(){
        return Produits.getNomProduits();
    }

    public String[] getNomCatalogues(){return controleurDAO.recupererNomCatalogues();}

    public void creerCatalogue(JTextField nomCatalogue){
        controleurDAO.creerCatalogue(nomCatalogue.getText());
    }
    public void supprimerCatalogue(JComboBox nomCatalogue){ controleurDAO.supprimerCatalogue( (String)nomCatalogue.getSelectedItem()); }

    
    public boolean creerPdt(JTextField nomSaisi, JTextField prixUnitaireSaisi, JTextField quantiteSaisie){
        if(creationControleur==null){
            creationControleur=new ControleurCreation();
        }
        return creationControleur.creerPdt(nomSaisi, prixUnitaireSaisi, quantiteSaisie, Produits);
    }



    public boolean achatProduit(JTextField saisie,String nom){
        if(controleurAchatVente==null){
            controleurAchatVente=new ControleurAchatVente();
        }
        return controleurAchatVente.enregistrerAchat(saisie,nom,Produits);
    }

    public boolean supprimerProduit(JComboBox<String> nomProduit){
        if(suppressionControleur==null){
            suppressionControleur=new ControleurSuppression();
        }
        return suppressionControleur.supprimerProduit(nomProduit, Produits);
    }
    
    public String[] getInfoCatalogue(){
        return controleurDAO.getInfosCatalogues();
    }
    public int getNbCatalogue(){
        return controleurDAO.getNbCatalogue();
    }



    public boolean venteProduit(JTextField txtQuantite, String nomProduit) {
        if(controleurAchatVente==null){
            controleurAchatVente=new ControleurAchatVente();
        }
        return controleurAchatVente.enregistrerVente(txtQuantite,nomProduit,Produits);
    }
    public void close(){
        Produits.close();
    }

}
