package uml.e05.monestier.dezette.DAO.produitDAO;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import uml.e05.monestier.dezette.metier.I_Produit;

import java.util.List;

public class ProduitDAONoSQL implements I_produitDAO {

    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;

    public ProduitDAONoSQL(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.mongoDatabase =  mongoClient.getDatabase("db_produits");
    }

    @Override
    public List<I_Produit> findAll(String nomCatalogue) {
        return null;
    }

    @Override
    public void create(I_Produit produit) {

    }

    @Override
    public void deleteProduit(I_Produit produit) {

    }

    @Override
    public void modifierStockProduit(I_Produit produit) {

    }

    @Override
    public void close() {

    }
}
