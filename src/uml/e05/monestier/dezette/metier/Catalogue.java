/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.e05.monestier.dezette.metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author prax
 */
public class Catalogue implements I_Catalogue {

    private List<I_Produit> Produits = new ArrayList<>();

    public Catalogue() {
        //produitDAO connexion=new produitDAO();
        //this.addProduits(connexion.findAll());
        
        //TODO a modifier
        
        //connexion.close();
    }
    
    

    @Override
    public boolean addProduit(I_Produit produit) {
      if(pdtOk(produit)){
           Produits.add(produit);
           return true;
      }
      return false;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        nom=nom.replaceAll("\t"," ");
        Produit pdt = new Produit(nom.trim(),prix,qte);
        if (pdtOk(pdt)) {
            return Produits.add(pdt);
        }
        else{
            
        }
        return false;
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        int nbAjout = 0;
        if (l == null) {
            return 0;
        } else {
            for (I_Produit p : l) {
                if(pdtOk(p)){
                    
                    Produits.add(p);
                    nbAjout++;
                }
              
                
            }
            return nbAjout;
        }
    }

    @Override
    public boolean removeProduit(String nom) {
        boolean hasBeenRemoved = false;
        int i = 0;
        while (i < Produits.size() && hasBeenRemoved == false) {
            if (Produits.get(i).getNom().equals(nom)) {
                hasBeenRemoved = Produits.remove(Produits.get(i));
            } else {
                i++;
            }

        }

        return hasBeenRemoved;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        if(produitExiste(nomProduit)){
            I_Produit p = getProduit(nomProduit);
            System.out.println("passage par le catalogue verif existance pdt");
            return p.ajouter(qteAchetee);
        }else{
            return false;
        }
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        I_Produit p = getProduit(nomProduit);
        if(qteVendue>0 && qteVendue<p.getQuantite()){
            return p.enlever(qteVendue);
        }else{
            return false;
        }
    }

    @Override
    public String[] getNomProduits() {

        ArrayList<String> nomProduits = new ArrayList();

        for (int i = 0; i < Produits.size(); i++) {
            if (Produits.size() == 0) {
                System.out.println("Vide!");
            } else {
                nomProduits.add(Produits.get(i).getNom()); 
            }
        }
        Collections.sort(nomProduits);
        String[] noms=new String[nomProduits.size()];
        int i=0;
        for(String s:nomProduits){
            noms[i]=s;
            noms[i]=noms[i].replaceAll("\t"," ");
            noms[i]=noms[i].trim();
            i++;
        }
        
        return noms;
    }

    @Override
    public double getMontantTotalTTC() {
       if(Produits.size() == 0){
            return 0.0;
        }
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        double prixTTC = 0;
        for (I_Produit p : Produits ) {
            System.out.println(""+p.getNom() + " qte: "+p.getQuantite() + "prixTTC" + p.getPrixUnitaireTTC());
            prixTTC += p.getPrixUnitaireTTC() * p.getQuantite();        
             
        }
 
        
        return (double)Math.round(prixTTC*100)/100;
    }

    @Override
    public void clear() {
        Produits.clear();
    }

    @Override
    public String toString() {
        String sCatalogue = "";
        for (I_Produit p:Produits) {
            sCatalogue = sCatalogue + p.toString() + System.lineSeparator();
        }
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        sCatalogue = sCatalogue + System.lineSeparator() + "Montant total TTC du stock : " + df.format(this.getMontantTotalTTC()) + " €";
        sCatalogue =  sCatalogue.replaceAll("\\.",",");
        return sCatalogue;
    }


    public I_Produit getProduit(String nomPdt) {
        int i = 0;
        while (Produits.get(i).getNom().equals(nomPdt) == false && i < Produits.size() - 1) {
            i++;
        }
        I_Produit p = Produits.get(i);
        return p;
    }

    public boolean prixOk(double prixProduit) {

        try {

        } catch (NumberFormatException e) {
            return false;
        }
        return (prixProduit > 0);
    }

    public boolean stockOk(int pStock) {
        return pStock >= 0;
    }

    public boolean produitExiste(String nom) {
        boolean res = false;
        String[] nomPdt = getNomProduits();
        int i = 0;
        while (i < nomPdt.length && res == false) {
            if (nomPdt[i].trim().equals(nom.trim())) {
                res = true;
            }
            i++;
        }
        return res;
    }

    public boolean pdtOk(I_Produit pdt) {
        boolean res = false;
        if (pdt != null) {
            System.out.println("Produit non nul");
            if (produitExiste(pdt.getNom()) == false) {
                 System.out.println("Produit similaire non existant");
                if (prixOk(pdt.getPrixUnitaireHT())) {
                     System.out.println("Prix positif");
                    if (stockOk(pdt.getQuantite())) {
                         System.out.println("Stock Positif");
                        res = true; 
                    }
                    else{
                        res = false;
                    }
                }
            }
        }
        return res;
    }

}
