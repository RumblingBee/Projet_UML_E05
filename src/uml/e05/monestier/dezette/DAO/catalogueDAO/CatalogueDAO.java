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


    private String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private String username = "monestierc";
    private String password = "1105018199N";


    public CatalogueDAO() {
        try{

            Class.forName("oracle.jdbc.OracleDriver");
            try {
                cn = DriverManager.getConnection(url,username,password);
            }catch (SQLException e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
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
    public void create(I_Catalogue produit) {

    }

    @Override
    public void deleteCatalogue(I_Produit produit) {

    }

    @Override
    public void close() {

    }
}
