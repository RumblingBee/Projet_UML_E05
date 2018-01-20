package DAO;

import uml.e05.monestier.dezette.metier.I_Produit;

import java.util.List;

public class AdapteurDAO implements I_DAO{

    private ProduitDAO_XML produitDAO_xml;

    public AdapteurDAO() {
        this.produitDAO_xml = new ProduitDAO_XML();
    }

    @Override
    public List<I_Produit> findAll() {
        return produitDAO_xml.lireTous();
    }

    @Override
    public void create(I_Produit produit) {
        produitDAO_xml.creer(produit);
    }

    @Override
    public void deleteProduit(I_Produit produit) {
        produitDAO_xml.supprimer(produit);
    }


    @Override
    public void modifierStockProduit(I_Produit produit) {
        produitDAO_xml.maj(produit);
    }

    @Override
    public void close() {

    }
}
