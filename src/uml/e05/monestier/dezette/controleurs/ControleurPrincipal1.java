/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.e05.monestier.dezette.controleurs;


import javax.swing.*;



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
    
    private static I_Catalogue Produits;
    private static ControleurPrincipal1 instance;
    
    private ControleurPrincipal1(){
        Produits=new Catalogue();
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
