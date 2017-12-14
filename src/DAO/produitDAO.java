
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import uml.e05.monestier.dezette.metier.Catalogue;
import uml.e05.monestier.dezette.metier.I_Produit;
import uml.e05.monestier.dezette.metier.Produit;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author clement
 */
public class produitDAO {

    private Connection cn = null;
    private ResultSet rs = null;
    private Statement st = null;


    private String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private String username = "monestierc";
    private String password = "1105018199N";

    public produitDAO() {


     try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         try {
             cn = DriverManager.getConnection(url,username,password);
         }catch (SQLException e){
             e.printStackTrace();
         }

     }catch (Exception e){
         e.printStackTrace();
     }

    }



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
    
    public void close(){
        try{
            cn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }




}
