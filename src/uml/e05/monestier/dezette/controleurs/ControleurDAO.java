package uml.e05.monestier.dezette.controleurs;

import uml.e05.monestier.dezette.DAO.catalogueDAO.I_catalogueDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.I_produitDAO;
import uml.e05.monestier.dezette.factory.DAOFactoryAbstract;
import uml.e05.monestier.dezette.metier.I_Catalogue;
import uml.e05.monestier.dezette.metier.I_Produit;

import java.util.List;

public class ControleurDAO {

    private I_catalogueDAO catalogueDAO;
    private I_produitDAO produitDAO;



    public ControleurDAO() {


        catalogueDAO = DAOFactoryAbstract.getInstance().createCatalogueDAO();
        produitDAO=DAOFactoryAbstract.getInstance().createProduitDAO();

    }

    public List<I_Catalogue> recupererListeCatalogues(){
        return catalogueDAO.findAll();
    }
    public List<I_Produit> recupererListeProduits(String  nomCatalogue){ return produitDAO.findAll(nomCatalogue);
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

    public String[] getInfosCatalogues(){
        return catalogueDAO.toStringAllCatalogue();
    }

    public int getNbCatalogue(){
        return catalogueDAO.getCountCatalogue();
    }

    public void addProduit(I_Produit produit){
        produitDAO.create(produit);
    }


    public void modifierStockProduit(I_Produit produit) {
        produitDAO.modifierStockProduit(produit);
    }

    public List<I_Produit> findAll(String nomCatalogue) {
        return produitDAO.findAll(nomCatalogue);
    }

    public void close() {
        produitDAO.close();
        catalogueDAO.close();
    }
}
