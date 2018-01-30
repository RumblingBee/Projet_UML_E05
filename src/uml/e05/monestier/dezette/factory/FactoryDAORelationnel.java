package uml.e05.monestier.dezette.factory;

import uml.e05.monestier.dezette.DAO.catalogueDAO.CatalogueDAO;
import uml.e05.monestier.dezette.DAO.catalogueDAO.I_catalogueDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.I_produitDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.ProduitDAO;

public class FactoryDAORelationnel extends DAOFactoryAbstract {

    public I_produitDAO createProduitDAO(){
        return new ProduitDAO();
    }

    public I_catalogueDAO createCatalogueDAO(){
        return new CatalogueDAO();
    }
}
