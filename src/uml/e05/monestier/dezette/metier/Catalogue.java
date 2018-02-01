/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.e05.monestier.dezette.metier;


import uml.e05.monestier.dezette.DAO.produitDAO.ProduitDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.I_produitDAO;
import uml.e05.monestier.dezette.controleurs.ControleurDAO;
import uml.e05.monestier.dezette.factory.DAOFactoryAbstract;
import uml.e05.monestier.dezette.factory.FactoryDAORelationnel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author prax
 */
public class Catalogue implements I_Catalogue {

    private List<I_Produit> produits;
    private ControleurDAO connexion=new ControleurDAO();
    private String nom;

    public Catalogue() {
        this.nom = "Catalogue sans nom";
        produits =  new ArrayList<>();


    }
    public Catalogue(String nom) {
        this.nom = nom;
        produits =  new ArrayList<>();
    }
    

    @Override
    public boolean addProduit(I_Produit produit) {
      if(produitValide(produit)){
           produits.add(produit);
           connexion.addProduit(produit);

           return true;
      }
      return false;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        nom = supprimerTabulation(nom);
        I_Produit produit = new Produit(nom.trim(),prix,qte);
        if (produitValide(produit)) {
            return produits.add(produit);
        }
        else{
            
        }
        return false;
    }

    private String supprimerTabulation(String nom) {
        nom=nom.replaceAll("\t"," ");
        return nom;
    }

    @Override
    public int addProduits(List<I_Produit> produits) {
        int nbAjout = 0;
        if (produits == null) {
            return 0;
        } else {
            for (I_Produit produit : produits) {
                if(produitValide(produit)){
                    
                    this.produits.add(produit);
                    nbAjout++;
                }
            }
            return nbAjout;
        }
    }

    @Override
    public boolean removeProduit(String nom) {
        I_produitDAO pdao = FactoryDAORelationnel.getInstance().createProduitDAO();
        boolean produitSupprime = false;
        int indexProduit = 0;

        while (indexProduit < produits.size() && produitSupprime == false) {
            if (nomProduitTrouveDansCatalogue(nom, indexProduit)) {
                produitSupprime = supprimerProduit(pdao, indexProduit);
            } else {
                indexProduit++;
            }

        }

        return produitSupprime;
    }

    private boolean nomProduitTrouveDansCatalogue(String nom, int indexProduit) {
        return produits.get(indexProduit).getNom().equals(nom);
    }

    private boolean supprimerProduit(I_produitDAO pdao, int indexProduit) {
        boolean produitSupprime;
        pdao.deleteProduit(produits.get(indexProduit));
        produitSupprime = produits.remove(produits.get(indexProduit));
        return produitSupprime;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        if(produitExiste(nomProduit) && qteAchetee >0){
            I_Produit produit = getProduit(nomProduit);
            produit.ajouter(qteAchetee);
            connexion.modifierStockProduit(produit);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        I_Produit produit = getProduit(nomProduit);
        if(qteVendue>0 && qteVendue<produit.getQuantite()){
            produit.enlever(qteVendue);
            connexion.modifierStockProduit(produit);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String[] getNomProduits() {

        List<String> nomProduits = new ArrayList();

        recupererNomsProduits(nomProduits);
        Collections.sort(nomProduits);
        String[] tableauDeNomsProduits = formatageListeEnTableau(nomProduits);
        
        return tableauDeNomsProduits;
    }

    private String[] formatageListeEnTableau(List<String> nomProduits) {
        String[] tableauDeNomsProduits=new String[nomProduits.size()];

        int i=0;
        for(String s:nomProduits){
            tableauDeNomsProduits[i]=s;
            tableauDeNomsProduits[i] = supprimerTabulation(tableauDeNomsProduits[i]);
            tableauDeNomsProduits[i]=tableauDeNomsProduits[i].trim();
            i++;
        }
        return tableauDeNomsProduits;
    }

    private void recupererNomsProduits(List<String> nomProduits) {
        for (int i = 0; i < produits.size(); i++) {
            if (produits.size() == 0) {
                System.out.println("Vide!");
            } else {
                nomProduits.add(produits.get(i).getNom());
            }
        }
    }

    @Override
    public double getMontantTotalTTC() {
       if(produits.size() == 0){
            return 0.0;
        }
        double prixTTC = 0;
        for (I_Produit produit : produits) {
            prixTTC += produit.getPrixUnitaireTTC() * produit.getQuantite();
        }
        return (double)Math.round(prixTTC*100)/100;
    }

    @Override
    public void clear() {
        produits.clear();
    }

    @Override
    public void initialisationCatalogue(String nomCatalogue) {
        this.addProduits(connexion.findAll(nomCatalogue));
    }

    @Override
    public String toString() {
        String descriptionCatalogue = recuperationDescriptionProduits();
        descriptionCatalogue = miseEnFormeDescriptionCatalogue(descriptionCatalogue);
        return descriptionCatalogue;
    }

    @Override
    public String getNomCatalogue() {
        return this.nom;
    }

    private String miseEnFormeDescriptionCatalogue(String descriptionCatalogue) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        descriptionCatalogue = descriptionCatalogue + "\nMontant total TTC du stock : " + df.format(this.getMontantTotalTTC()) + " €";
        descriptionCatalogue =  descriptionCatalogue.replaceAll("\\.",",");
        return descriptionCatalogue;
    }

    private String recuperationDescriptionProduits() {
        String descriptionCatalogue = "";
        for (I_Produit produit: produits) {
            descriptionCatalogue = descriptionCatalogue + produit.toString() + "\n";
        }
        return descriptionCatalogue;
    }


    private I_Produit getProduit(String nomPdt) {
        int i = 0;
        while (nomProduitTrouveDansCatalogue(nomPdt, i) == false && i < produits.size() - 1) {
            i++;
        }
        I_Produit produit = produits.get(i);
        return produit;
    }

    private boolean prixPositif(double prixProduit) {

        try {
        } catch (NumberFormatException e) {
            return false;
        }
        return (prixProduit > 0);
    }

    private boolean stockPositif(int pStock) {
        return pStock >= 0;
    }

    private boolean produitExiste(String nom) {
        boolean produitExiste = false;
        String[] nomsProduits = getNomProduits();
        int i = 0;
        while (i < nomsProduits.length && produitExiste == false) {
            if (nomsProduits[i].trim().equals(nom.trim())) {
                produitExiste = true;
            }
            i++;
        }
        return produitExiste;
    }

    private boolean produitValide(I_Produit produit) {
        boolean produitValide = false;
        if (produit != null) {
            if (produitExiste(produit.getNom()) == false) {
                if (prixPositif(produit.getPrixUnitaireHT())) {
                    if (stockPositif(produit.getQuantite())) {
                        produitValide = true;
                    }
                    else{
                        produitValide = false;
                    }
                }
            }
        }
        return produitValide;
    }

    public void close(){
        connexion.close();
    }

}
