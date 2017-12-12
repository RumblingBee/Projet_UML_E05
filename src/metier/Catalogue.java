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

    private static List<I_Produit> Produits = new ArrayList<>();
   

  
    @Override
    public boolean addProduit(I_Produit produit) {
       System.out.println("Creation d'un nouveau produit ...");
     Produits.add(produit);
        return true;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        Produit pdt=new Produit(qte, nom, prix);
        Produits.add(pdt);
        return Produits.add(pdt);
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        int nbAjout = 0;
        for(int i = l.size();i>=0;i--){
          if(this.addProduit( l.get(i))){
           nbAjout++;
          }
        }
        
        return nbAjout;
    }

    @Override
    public boolean removeProduit(String nom) {
        boolean hasBeenRemoved = false;
        int i = 0;
        while(i < Produits.size() && hasBeenRemoved == false){
            if(Produits.get(i).getNom().equals(nom)){
                Produits.remove(i);
                hasBeenRemoved = true;
            
            }
            else{
                i++;
            }
            
         }
        
        return hasBeenRemoved;
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
             if(Produits.size() == 0){
           System.out.println("Vide!");
       } 
             else{
          nomProduits[i] = Produits.get(i).getNom();
             }
        }
        return nomProduits;
    }

    @Override
    public double getMontantTotalTTC() {
       double prixTTC = 0;
       for(int i = Produits.size(); i>= 0; i--){
           prixTTC = prixTTC + (Produits.get(i).getPrixUnitaireTTC()*Produits.get(i).getQuantite());
       }
       return prixTTC;
    }

    @Override
    public void clear() {
        Produits.clear();
    }

}
