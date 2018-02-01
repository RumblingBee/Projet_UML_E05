package uml.e05.monestier.dezette.DAO.catalogueDAO;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.sf.json.JSONObject;
import org.bson.Document;
import uml.e05.monestier.dezette.metier.Catalogue;
import uml.e05.monestier.dezette.metier.I_Catalogue;


import java.util.ArrayList;
import java.util.List;

public class CatalogueDAONoSQL implements I_catalogueDAO {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private DB db;
    private DBCollection collection;

    public CatalogueDAONoSQL(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.mongoDatabase =  mongoClient.getDatabase("local");
        this.db  = mongoClient.getDB("local");
        this.collection = db.getCollection("db_catalogue") ;
    }

    @Override
    public List<I_Catalogue> findAll() {


        List<I_Catalogue> catalogues = new ArrayList<>();
MongoCollection<Document> collectionCat = mongoDatabase.getCollection("db_catalogues");

for(Document doc : collectionCat.find()){
    I_Catalogue catalogue = new Catalogue((String)doc.get("nomCatalogue"));
    catalogues.add(catalogue);
}





        return catalogues;
    }

    @Override
    public void create(String nomCatalogue) {
Document nouveauCatalogue = new Document()
        .append("nomCatalogue",nomCatalogue);

mongoDatabase.getCollection("db_catalogues").insertOne(nouveauCatalogue);

    }

    @Override
    public void supprimerCatalogue(String nomCatalogue) {
mongoDatabase.getCollection("db_catalogues").deleteOne(new Document("nomCatalogue",nomCatalogue));
    }

    @Override
    public void close() {

    }

    @Override
    public String[] recupererNomsCatalogues() {
        String[] nomsCatalogues = new String[findAll().size()];

        List<I_Catalogue> catalogues =  findAll();

        for (int i = 0; i < findAll().size(); i++) {
            nomsCatalogues[i] = catalogues.get(i).getNomCatalogue();
        }
        return nomsCatalogues;
    }
}
