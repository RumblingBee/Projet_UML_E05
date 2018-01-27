package uml.e05.monestier.dezette.DAO.catalogueDAO;

import uml.e05.monestier.dezette.metier.I_Catalogue;
import uml.e05.monestier.dezette.metier.I_Produit;

import java.util.List;

public class MockCatalogueDAO implements I_catalogueDAO {
    @Override
    public List<I_Catalogue> findAll() {
        return null;
    }

    @Override
    public void create(I_Catalogue produit) {

    }

    @Override
    public void deleteCatalogue(I_Produit produit) {

    }

    @Override
    public void close() {

    }

    @Override
    public String[] recupererNomsCatalogues() {
        return new String[0];
    }
}
