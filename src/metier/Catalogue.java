/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prax
 */
public class Catalogue implements I_Catalogue {

    private ArrayList<I_Produit> Produits;

    @Override
    public boolean addProduit(I_Produit produit) {
        return Produits.add(produit);
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        Produit pdt=new Produit(qte, nom, prix);
        return Produits.add(pdt);
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeProduit(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getNomProduits() {

        String nomProduits[] = new String[Produits.size()];
        for (int i = 0; i< Produits.size(); i++){
            nomProduits[i] = Produits.get(i).getNom();
        }
        return nomProduits;
    }

    @Override
    public double getMontantTotalTTC() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
