
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.e05.monestier.dezette.DAO.produitDAO;




import uml.e05.monestier.dezette.controleurs.ControleurPrincipal1;
import uml.e05.monestier.dezette.metier.I_Produit;
import uml.e05.monestier.dezette.metier.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author clement
 */
public class ProduitDAO implements I_produitDAO {

    private Connection cn = null;
    private ResultSet rs = null;
    private Statement st = null;

    public ProduitDAO(Connection factoryCn) {
        cn = factoryCn;
    }

    @Override
    public List<I_Produit> findAll(String nomCatalogue){

        List<I_Produit> produits = new ArrayList<>();
        PreparedStatement selectProduitPreparedStatement = null;

        try {
            String selectProductString = "SELECT * from PRODUITS JOIN CATALOGUES C2 ON PRODUITS.CATALOGUE = C2.CODECATALOGUE WHERE NOMCATALOGUE=?";
            selectProduitPreparedStatement= cn.prepareStatement(selectProductString);
            selectProduitPreparedStatement.setString(1,nomCatalogue);
            rs=selectProduitPreparedStatement.executeQuery();
            peuplerCatalogue(produits);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return produits;
        }

    private void peuplerCatalogue(List<I_Produit> produits) throws SQLException {
        while(rs.next()){
            Produit produit = new Produit(rs.getString("nomProduit"),rs.getInt("prixProduit"),rs.getInt("qteProduit"));
            produits.add(produit);
        }
    }

    @Override
    public void create(I_Produit produit){
        PreparedStatement insertProduitPreparedStatement = null;
        String nomCatalogue = ControleurPrincipal1.getInstance().getCatalogueSelectionne();
        int idCatalogue = getIdCatalogue(nomCatalogue);

        try{
            String insertProduitString = "CALL INSERTPRODUIT(?,?,?,?)";
            insertProduitPreparedStatement = cn.prepareStatement(insertProduitString);

            insertProduitPreparedStatement.setString(1,produit.getNom());
            insertProduitPreparedStatement.setDouble(2,produit.getPrixUnitaireHT());
            insertProduitPreparedStatement.setInt(3,produit.getQuantite());
            insertProduitPreparedStatement.setInt(4,idCatalogue);


            insertProduitPreparedStatement.executeQuery();



        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void deleteProduit(I_Produit produit){
       
        PreparedStatement deleteProduitPreparedStatement = null;
        String nomProduit=produit.getNom();
        try {
           String deleteProduitString = "DELETE FROM PRODUITS WHERE NOMPRODUIT = ?";
           deleteProduitPreparedStatement = cn.prepareStatement(deleteProduitString);

           deleteProduitPreparedStatement.setString(1,nomProduit);

           deleteProduitPreparedStatement.executeQuery();



        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void modifierStockProduit(I_Produit produit) {
        PreparedStatement acheterProduitPreparedStatement = null;

        int stock=produit.getQuantite();
        String nomProduit=produit.getNom();
        try {
            String acheterProduitString = "UPDATE PRODUITS SET QTEPRODUIT = ? WHERE NOMPRODUIT = ? ";
            acheterProduitPreparedStatement = cn.prepareStatement(acheterProduitString);
            acheterProduitPreparedStatement.setInt(1,stock);
            acheterProduitPreparedStatement.setString(2,nomProduit);
            acheterProduitPreparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    private int getIdCatalogue(String nomCatalogue){

        PreparedStatement selectProduitPreparedStatement = null;

        int id=0;
        try {
            String selectIdCatalogueString = "SELECT CODECATALOGUE from CATALOGUES WHERE NOMCATALOGUE = ? ";
            selectProduitPreparedStatement= cn.prepareStatement(selectIdCatalogueString);
            selectProduitPreparedStatement.setString(1,nomCatalogue);
            rs=selectProduitPreparedStatement.executeQuery();
            rs.next();
            id= rs.getInt(1);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return id;

    }



    public void close(){
        try{
            cn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }




}
