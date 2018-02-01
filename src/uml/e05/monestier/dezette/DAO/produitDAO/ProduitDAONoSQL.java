package uml.e05.monestier.dezette.DAO.produitDAO;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import uml.e05.monestier.dezette.controleurs.ControleurPrincipal1;
import uml.e05.monestier.dezette.metier.I_Produit;
import uml.e05.monestier.dezette.metier.Produit;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAONoSQL implements I_produitDAO {

    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;
    private DB db;

    public ProduitDAONoSQL(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.mongoDatabase =  mongoClient.getDatabase("local");
        this.db  = mongoClient.getDB("local");
    }

    @Override
    public List<I_Produit> findAll(String nomCatalogue) {
        String nomProduit;
        int quantiteProduit;
        double prixProduit;


        List<I_Produit> produits = new ArrayList<>();
        MongoCollection<org.bson.Document> collectionProduits = mongoDatabase.getCollection("db_produits");

        for(org.bson.Document doc : collectionProduits.find()){

            nomProduit = (String)doc.get("nom");
            quantiteProduit = (int)doc.get("quantite");
            prixProduit = (double)doc.get("prix");

            I_Produit produit = new Produit(nomProduit,prixProduit,quantiteProduit);
            produits.add(produit);

        }


        return produits;
    }

    @Override
    public void create(I_Produit produit) {
        String nomCatalogue = ControleurPrincipal1.getInstance().getCatalogueSelectionne();
 org.bson.Document nouveauProduit = new org.bson.Document()
         .append("nom",produit.getNom())
         .append("prix",produit.getPrixUnitaireHT())
         .append("quantite",produit.getQuantite())
         .append("codeCatalogue",this.getIdCatalogue(nomCatalogue));

        mongoDatabase.getCollection("db_produits").insertOne(nouveauProduit);

    }

    @Override
    public void deleteProduit(I_Produit produit) {
mongoDatabase.getCollection("db_produits").deleteOne(new org.bson.Document("nom",produit.getNom()));
    }

    @Override
    public void modifierStockProduit(I_Produit produit) {
        mongoDatabase.getCollection("db_produits").updateOne(new org.bson.Document("nom",produit.getNom()), new org.bson.Document("$set",new org.bson.Document("quantite",produit.getQuantite() )));

    }

    @Override
    public void close() {

    }
    private ObjectId getIdCatalogue(String nomCatalogue){
        ObjectId idCatalogue = null;

        BasicDBObject query = new BasicDBObject();
        query.put("nomCatalogue",nomCatalogue);

        BasicDBObject field = new BasicDBObject();
        field.put("_id", 1);

        DBCollection collectionCat = db.getCollection("db_catalogues");

        DBCursor cursor = collectionCat.find(query,field);

while (cursor.hasNext()) {
     idCatalogue = (ObjectId) cursor.next().get("_id");
}


        return idCatalogue;
    }
}
