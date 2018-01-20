package DAO;

import uml.e05.monestier.dezette.metier.I_Produit;

import java.util.ArrayList;
import java.util.List;

public interface I_DAO {
    List<I_Produit> findAll();

    void create(I_Produit produit);

    void deleteProduit(I_Produit produit);
    void modifierStockProduit(I_Produit produit);
}
