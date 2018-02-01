package uml.e05.monestier.dezette.factory;

import uml.e05.monestier.dezette.DAO.catalogueDAO.I_catalogueDAO;
import uml.e05.monestier.dezette.DAO.catalogueDAO.MockCatalogueDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.I_produitDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.MockProduitDAO;

public class FactoryDAOMock extends DAOFactoryAbstract {
    @Override
    public I_catalogueDAO createCatalogueDAO() {
        return new MockCatalogueDAO();
    }

    @Override
    public I_produitDAO createProduitDAO() {
        return new MockProduitDAO();
    }
}
