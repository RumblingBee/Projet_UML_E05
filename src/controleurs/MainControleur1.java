/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import javax.swing.JTextField;
import sun.security.jca.GetInstance;
import uml.e05.monestier.dezette.metier.Catalogue;
import uml.e05.monestier.dezette.metier.I_Catalogue;

/**
 *
 * @author clement
 */
public class MainControleur1 {
    
    private Creation_controleur creationControleur;
    private ControleurAchatVente controleurAchatVente;
    
    private static I_Catalogue Produits=new Catalogue();
    private static MainControleur1 instance;
    
    private MainControleur1(){
        
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
    
    public boolean creerPdt(JTextField sNom, JTextField sPrixUnit, JTextField sQteStock){
        if(creationControleur==null){
            creationControleur=new Creation_controleur();
        }
        return creationControleur.creerPdt(sNom, sPrixUnit, sQteStock, Produits);
    }
    public boolean achatProduit(JTextField saisie,String n){
        if(controleurAchatVente==null){
            controleurAchatVente=new ControleurAchatVente();
        }
        return controleurAchatVente.enregistrerAchat(saisie,n,Produits);
    }
    
    
}
