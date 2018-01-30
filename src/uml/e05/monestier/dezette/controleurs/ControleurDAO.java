package uml.e05.monestier.dezette.controleurs;

import uml.e05.monestier.dezette.DAO.catalogueDAO.I_catalogueDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.I_produitDAO;
import uml.e05.monestier.dezette.factory.DAOFactoryAbstract;
import uml.e05.monestier.dezette.metier.I_Catalogue;

import java.util.List;

public class ControleurDAO {

    private I_catalogueDAO catalogueDAO;
    private I_produitDAO produitDAO;
    private ControleurDAO instance;


    protected ControleurDAO() {

        //TODO: FACTORY

        catalogueDAO = DAOFactoryAbstract.getInstance().createCatalogueDAO();

    }
    public ControleurDAO getInstance(){
        if(instance == null){
            instance = new ControleurDAO();
        }
        return instance;

    }
    public List<I_Catalogue> recupererListeCatalogues(){
        return catalogueDAO.findAll();
    }

    public String[] recupererNomCatalogues(){
        return catalogueDAO.recupererNomsCatalogues();
    }
    public boolean creerCatalogue(String nomCatalogue){

        if (catalogueExiste(nomCatalogue) == false) {
            catalogueDAO.create(nomCatalogue);
        }
            return !catalogueExiste(nomCatalogue);
    }
    public boolean supprimerCatalogue(String nomCatalogue){

        if (catalogueExiste(nomCatalogue) == true) {
            catalogueDAO.supprimerCatalogue(nomCatalogue);
        }
        return catalogueExiste(nomCatalogue);
    }

    private boolean catalogueExiste(String nomCatalogue) {
        boolean catalogueExiste = false;

        for(String s : recupererNomCatalogues()) {
            if(s.equals(nomCatalogue)){
               catalogueExiste = true;
            }
        }
        return catalogueExiste;
    }


}
