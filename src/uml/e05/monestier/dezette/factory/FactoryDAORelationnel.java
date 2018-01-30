package uml.e05.monestier.dezette.factory;

import uml.e05.monestier.dezette.DAO.catalogueDAO.CatalogueDAO;
import uml.e05.monestier.dezette.DAO.catalogueDAO.I_catalogueDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.I_produitDAO;
import uml.e05.monestier.dezette.DAO.produitDAO.ProduitDAO;

import java.sql.*;

public class FactoryDAORelationnel extends DAOFactoryAbstract {

    private Connection cn = null;

    private String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private String username = "monestierc";
    private String password = "1105018199N";


    public FactoryDAORelationnel() {
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

    public I_produitDAO createProduitDAO(){
        return new ProduitDAO(cn);
    }

    public I_catalogueDAO createCatalogueDAO(){
        return new CatalogueDAO(cn);
    }
}
