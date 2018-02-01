package uml.e05.monestier.dezette.DAO.catalogueDAO;

import uml.e05.monestier.dezette.metier.Catalogue;
import uml.e05.monestier.dezette.metier.I_Catalogue;
import uml.e05.monestier.dezette.metier.I_Produit;
import uml.e05.monestier.dezette.metier.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAO implements I_catalogueDAO {

    private Connection cn = null;
    private ResultSet rs = null;
    private Statement st = null;


    public CatalogueDAO(Connection cn) {
        this.cn = cn;
    }

    @Override
    public List<I_Catalogue> findAll() {
        List<I_Catalogue> catalogues = new ArrayList<>();

        try {
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT * FROM CATALOGUES");
            peuplerListeCatalogues(catalogues);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return catalogues;
    }
    private void peuplerListeCatalogues(List<I_Catalogue> catalogues) throws SQLException {
        while(rs.next()){
           I_Catalogue catalogue = new Catalogue(rs.getString("NOMCATALOGUE"));
            catalogues.add(catalogue);
        }
    }



    public String[] recupererNomsCatalogues(){
        String[] nomsCatalogues = new String[findAll().size()];

        List<I_Catalogue> catalogues =  findAll();

        for (int i = 0; i < findAll().size(); i++) {
           nomsCatalogues[i] = catalogues.get(i).getNomCatalogue();
        }
        return nomsCatalogues;
    }

    @Override
    public String[] toStringAllCatalogue() {

        String[] infosCatalogues=new String[findAll().size()];
        List<String> catalogues=new ArrayList<>();
        getInfosCatalogues(catalogues);
        formatageInfosCatalogue(infosCatalogues, catalogues);
        return infosCatalogues;
    }

    @Override
    public int getCountCatalogue() {
        int nbCatalogue=-1;
        try {
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT count(*) FROM CATALOGUES");
            rs.next();
            nbCatalogue=rs.getInt(1);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return nbCatalogue;
    }

    private void formatageInfosCatalogue(String[] infosCatalogues, List<String> catalogues) {
        for (int i = 0; i < findAll().size(); i++) {
            infosCatalogues[i] = catalogues.get(i);
        }
    }

    private void getInfosCatalogues(List<String> catalogues) {
        try {
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT NOMCATALOGUE,COUNT(IDPRODUIT) FROM CATALOGUES JOIN PRODUITS P ON " +
                    "CATALOGUES.CODECATALOGUE = P.CATALOGUE GROUP BY NOMCATALOGUE ORDER BY NOMCATALOGUE");
            while(rs.next()){
                String catalogueInfo = rs.getString(1)+" : "+rs.getInt(2)+ " produits";
                catalogues.add(catalogueInfo);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void create(String nomCatalogue) {
        PreparedStatement insertCataloguePreparedStatement = null;

        try{
            String insertCatalogueString = "CALL INSERTCATALOGUE(?)";
            insertCataloguePreparedStatement = cn.prepareStatement(insertCatalogueString);

            insertCataloguePreparedStatement.setString(1,nomCatalogue);

            insertCataloguePreparedStatement.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void supprimerCatalogue(String nomCatalogue) {
        PreparedStatement deleteCataloguePreparedStatement = null;
        try {
            String deleteCatalogueString = "DELETE  FROM CATALOGUES WHERE NOMCATALOGUE = ?";
            deleteCataloguePreparedStatement = cn.prepareStatement(deleteCatalogueString);
            deleteCataloguePreparedStatement.setString(1,nomCatalogue);
            deleteCataloguePreparedStatement.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }
}
