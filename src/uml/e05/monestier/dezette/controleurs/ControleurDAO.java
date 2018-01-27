package uml.e05.monestier.dezette.controleurs;

import uml.e05.monestier.dezette.DAO.catalogueDAO.I_catalogueDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.I_produitDAO;
import uml.e05.monestier.dezette.factory.DAOFactoryAbstract;
import uml.e05.monestier.dezette.metier.I_Catalogue;

import java.util.List;

public class ControleurDAO {

    private I_catalogueDAO catalogueDAO;
    private I_produitDAO produitDAO;


    public ControleurDAO() {

        //TODO: FACTORY

        catalogueDAO = DAOFactoryAbstract.getInstance().createCatalogueDAO();

    }
    public List<I_Catalogue> recupererListeCatalogues(){
        return catalogueDAO.findAll();
    }

    public String[] recupererNomCatalogues(){
        return catalogueDAO.recupererNomsCatalogues();
    }




}
