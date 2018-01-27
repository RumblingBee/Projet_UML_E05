package uml.e05.monestier.dezette.DAO.produitDAO;

import uml.e05.monestier.dezette.metier.I_Produit;

import java.util.ArrayList;

public class MockProduitDAO implements I_produitDAO {
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

    @Override
    public void close() {

    }

}
