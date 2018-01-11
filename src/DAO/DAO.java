
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;




import uml.e05.monestier.dezette.metier.I_Produit;
import uml.e05.monestier.dezette.metier.Produit;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author clement
 */
public class DAO implements I_DAO {

    private Connection cn = null;
    private ResultSet rs = null;
    private Statement st = null;


    private String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private String username = "monestierc";
    private String password = "1105018199N";

    public DAO() {


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
    public ArrayList<I_Produit> findAll(){

        ArrayList<I_Produit> arrayProduit = new ArrayList<>();

        try {
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT * FROM PRODUITS");

            //On rajoute tous les produits
            while(rs.next()){
                Produit p = new Produit(rs.getString("nomProduit"),rs.getInt("prixProduit"),rs.getInt("qteProduit"));
                arrayProduit.add(p);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }


        return arrayProduit;

    }

    @Override
    public void create(I_Produit produit){
        PreparedStatement insertProduitPreparedStatement = null;

        try{
            String insertProduitString = "CALL INSERTPRODUIT(?,?,?)";
            insertProduitPreparedStatement = cn.prepareStatement(insertProduitString);

            insertProduitPreparedStatement.setString(1,produit.getNom());
            insertProduitPreparedStatement.setDouble(2,produit.getPrixUnitaireHT());
            insertProduitPreparedStatement.setInt(3,produit.getQuantite());

            insertProduitPreparedStatement.executeQuery();


        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    @Override
    public void deleteProduit(String nomProduit){
       
        PreparedStatement deleteProduitPreparedStatement = null;
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
    public void modifierStockProduit(String nomProduit, int stock) {
        PreparedStatement acheterProduitPreparedStatement = null;
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



    @Override
    public void close(){
        try{
            cn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }




}
