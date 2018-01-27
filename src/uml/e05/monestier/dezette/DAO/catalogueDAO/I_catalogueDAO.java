package uml.e05.monestier.dezette.DAO.catalogueDAO;

import uml.e05.monestier.dezette.metier.I_Catalogue;
import uml.e05.monestier.dezette.metier.I_Produit;

import java.util.List;

public interface I_catalogueDAO
{
    List<I_Catalogue> findAll();

    void create(I_Catalogue produit);
    void deleteCatalogue(I_Produit produit);
    void close();
    String[] recupererNomsCatalogues();
}
