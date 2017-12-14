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

    private List<I_Produit> Produits = new ArrayList<>();

    @Override
    public boolean addProduit(I_Produit produit) {
      /*  if (produit != null) {
            if (produitExiste(produit.getNom())) {
                return false;
            } else {
                if (stockOk(produit.getQuantite())) {
                    if (prixOk(produit.getPrixUnitaireHT()) == true) {
                        Produits.add(produit);
                        return true;
                    }
                }
                return false;
            }
        } else {
            return false;
        }*/
      
      if(pdtOk(produit)){
           Produits.add(produit);
           return true;
      }
      return false;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        Produit pdt = new Produit(nom,prix,qte);
       
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
        I_Produit p = getProduit(nomProduit);
        return p.ajouter(qteAchetee);
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        I_Produit p = getProduit(nomProduit);
        return p.enlever(qteVendue);
    }

    @Override
    public String[] getNomProduits() {

        String nomProduits[] = new String[Produits.size()];

        for (int i = 0; i < Produits.size(); i++) {
            if (Produits.size() == 0) {
                System.out.println("Vide!");
            } else {
                nomProduits[i] = Produits.get(i).getNom();
            }
        }
        return nomProduits;
    }

    @Override
    public double getMontantTotalTTC() {
        if(Produits.size() == 0){
            return 0.0;
        }
        System.out.println("==== DEBUT MONTANT TTC =====");
        double prixTTC = 0;
        for (I_Produit p : Produits ) {
            System.out.println(""+p.getNom() + " qte: "+p.getQuantite() + "prixTTC" + p.getPrixUnitaireTTC());
            prixTTC += p.getPrixUnitaireTTC() * p.getQuantite();
            
             
        }
        System.out.println("==== FIN MONTANT TTC =====");
       /* for (int i = Produits.size() - 1; i >= 0; i--) {
            prixTTC = prixTTC + (Produits.get(i).getPrixUnitaireTTC() * Produits.get(i).getQuantite());
        }*/
       
        return prixTTC;
    }

    @Override
    public void clear() {
        Produits.clear();
    }

    @Override
    public String toString() {
        String sCatalogue = "";
        for (int i = Produits.size() - 1; i >= 0; i--) {
            sCatalogue = sCatalogue + Produits.get(i).toString() + System.lineSeparator();
        }
        sCatalogue = sCatalogue + System.lineSeparator() + " Montant total TTC du stock " + this.getMontantTotalTTC();
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
        return pStock > 0;
    }

    public boolean produitExiste(String nom) {
        boolean res = false;
        String[] nomPdt = getNomProduits();
        int i = 0;
        while (i < nomPdt.length && res == false) {
            if (nomPdt[i].equals(nom)) {
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
