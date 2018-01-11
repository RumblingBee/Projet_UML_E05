package DAO;

import uml.e05.monestier.dezette.metier.I_Produit;

import java.util.ArrayList;

public interface I_DAO {
    ArrayList<I_Produit> findAll();

    void create(I_Produit produit);

    void deleteProduit(String nomProduit);
    void modifierStockProduit(String nomProduit, int stock);
    void close();
}
