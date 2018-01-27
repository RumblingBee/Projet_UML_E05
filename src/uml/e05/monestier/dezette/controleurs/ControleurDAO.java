package uml.e05.monestier.dezette.controleurs;

import uml.e05.monestier.dezette.DAO.catalogueDAO.CatalogueDAO;
import uml.e05.monestier.dezette.DAO.catalogueDAO.I_catalogueDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.I_produitDAO;
import uml.e05.monestier.dezette.metier.Catalogue;
import uml.e05.monestier.dezette.metier.I_Catalogue;

import java.util.List;

public class ControleurDAO {

    private I_catalogueDAO catalogueDAO;
    private I_produitDAO produitDAO;
    private ControleurDAO instance;


    protected ControleurDAO() {

        //TODO: FACTORY

        catalogueDAO = new CatalogueDAO();

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

        boolean catalogueExistePas = true;

        for(String s : recupererNomCatalogues()) {
            if(s == nomCatalogue){
               catalogueExistePas = false;
            }
        }
        if (catalogueExistePas) {
            catalogueDAO.create(nomCatalogue);
        }
            return catalogueExistePas;
    }




}
