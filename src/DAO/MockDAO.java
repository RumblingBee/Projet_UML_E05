package DAO;

import uml.e05.monestier.dezette.metier.I_Produit;

import java.util.ArrayList;

public class MockDAO implements I_DAO {
    @Override
    public ArrayList<I_Produit> findAll() {
        return null;
    }

    @Override
    public void create(I_Produit produit) {

    }

    @Override
    public void deleteProduit(I_Produit produit) {

    }

    @Override
    public void modifierStockProduit(I_Produit produit) {

    }

}
