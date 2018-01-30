package uml.e05.monestier.dezette.DAO.produitDAO;

import uml.e05.monestier.dezette.metier.I_Produit;

import java.util.List;

public interface I_produitDAO {
    List<I_Produit> findAll();

    void create(I_Produit produit);

    void deleteProduit(I_Produit produit);
    void modifierStockProduit(I_Produit produit);
    void close();
}
