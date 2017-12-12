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
     System.out.println("Produit créé avec le nom" +Produits.get(0).getNom());
      System.out.println("Produit créé avec le num " +Produits.size());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeProduit(String nom) {
        boolean hasBeenRemoved = false;
        int i = 0;
        while(i <= Produits.size() && hasBeenRemoved == false){
            if(Produits.get(i).getNom() == nom){
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

        System.out.print("Test" +Produits.size() );
     for (int i = 0; i< Produits.size(); i++){
             if(Produits.size() == 0){
           System.out.println("Vide!");
       } 
             else{
          nomProduits[i] = Produits.get(i).getNom();
             }
        }
       
       /*    
            Produit p = (Produit)Produits.get(0);

       }
       else{
           
          
       }
        */
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
