package uml.e05.monestier.dezette.factory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import uml.e05.monestier.dezette.DAO.catalogueDAO.CatalogueDAO;
import uml.e05.monestier.dezette.DAO.catalogueDAO.CatalogueDAONoSQL;
import uml.e05.monestier.dezette.DAO.catalogueDAO.I_catalogueDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.I_produitDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.ProduitDAONoSQL;

public class FactoryDAONoSQL extends DAOFactoryAbstract{
private MongoClient mongoClient;
    public FactoryDAONoSQL() {
        mongoClient = new MongoClient("localhost",27017);
    }

    @Override
    public I_catalogueDAO createCatalogueDAO() {
        return new CatalogueDAONoSQL(mongoClient);
    }

    @Override
    public I_produitDAO createProduitDAO() {
        return new ProduitDAONoSQL(mongoClient);
    }
}
