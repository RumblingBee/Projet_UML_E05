/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.e05.monestier.dezette.metier;

import java.text.DecimalFormat;

/**
 *
 * @author prax
 */
public class Produit implements I_Produit {
    
    private int quantiteStock;
    private String nom;
    private double prixUnitaire;
    private static double tauxTVA = 0.2;

    public Produit(int quantiteStock, String nom, double prixUnitaire) {
        this.quantiteStock = quantiteStock;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
    }
       public Produit(String nom, double prixUnitaire, int quantiteStock) {
        this.quantiteStock = quantiteStock;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
    }

    @Override
    public boolean ajouter(int qteAchetee) {
        if(qteAchetee>0){
            quantiteStock+=qteAchetee;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean enlever(int qteVendue) {
        if(qteVendue<quantiteStock){
            quantiteStock-=qteVendue;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public int getQuantite() {
        return quantiteStock;
    }

    @Override
    public double getPrixUnitaireHT() {
        return prixUnitaire;
    }

    @Override
    public double getPrixUnitaireTTC() {
        return prixUnitaire+(tauxTVA*prixUnitaire);
    }

    @Override
    public double getPrixStockTTC() {
        return getPrixUnitaireTTC()*quantiteStock;
    }
    
    @Override
    public String toString(){
        String descriptionDuProduit = "";
        DecimalFormat prixFormate = formatageDuPrix();
        descriptionDuProduit =this.nom +" - prix HT : " + prixFormate.format(this.getPrixUnitaireHT()) + " € - prix TTC : " + prixFormate.format(this.getPrixUnitaireTTC()) +" € - quantité en stock : " + this.getQuantite();
        return descriptionDuProduit;
    }

    private DecimalFormat formatageDuPrix() {
        return new DecimalFormat("0.00");
    }

}
