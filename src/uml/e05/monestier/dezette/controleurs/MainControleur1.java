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
public class MainControleur1 {
    
    private Creation_controleur creationControleur;
    private ControleurAchatVente controleurAchatVente;
    private Suppression_controleur suppressionControleur;
    
    private static I_Catalogue Produits;
    private static MainControleur1 instance;
    
    private MainControleur1(){
        Produits=new Catalogue();
    }
    
    public static MainControleur1 getInstance(){
        if(instance==null){
            instance=new MainControleur1();
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
            creationControleur=new Creation_controleur();
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
            suppressionControleur=new Suppression_controleur();
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
