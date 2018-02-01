package uml.e05.monestier.dezette.factory;

import uml.e05.monestier.dezette.DAO.catalogueDAO.I_catalogueDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.I_produitDAO;

public abstract class DAOFactoryAbstract {
    private static DAOFactoryAbstract ourInstance;

    public static DAOFactoryAbstract getInstance() {
        if (ourInstance==null){

            ourInstance=new FactoryDAONoSQL();

        }
        return ourInstance;
    }

    protected DAOFactoryAbstract() {
    }

    public abstract I_catalogueDAO createCatalogueDAO();
    public abstract I_produitDAO createProduitDAO();
}
