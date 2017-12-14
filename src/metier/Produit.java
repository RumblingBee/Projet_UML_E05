/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

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
        String sProduit = "";
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        sProduit =this.nom +" - prix HT : " + df.format(this.getPrixUnitaireHT()) + " € - prix TTC : " + df.format(this.getPrixUnitaireTTC()) +" € - quantité en stock : " + this.getQuantite();
        return sProduit;
    }
    
}
