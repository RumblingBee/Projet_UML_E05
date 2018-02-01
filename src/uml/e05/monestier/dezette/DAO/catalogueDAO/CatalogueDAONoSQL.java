package uml.e05.monestier.dezette.DAO.catalogueDAO;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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

    @Override
    public String[] toStringAllCatalogue() {

        String[] infosCatalogues=new String[findAll().size()];
        List<String> catalogues=new ArrayList<>();
        getInfosCatalogues(catalogues);
        formatageInfosCatalogue(infosCatalogues, catalogues);
        return infosCatalogues;

    }
    private void getInfosCatalogues(List<String> catalogues) {

        MongoCollection<Document> collectionCat = mongoDatabase.getCollection("db_catalogues");

        MongoCollection<Document> collectionProd = mongoDatabase.getCollection("db_produits");

        for(Document doc : collectionCat.find()){
            Document query = new Document("codeCatalogue",doc.get("_id"));
            int i = (int) collectionProd.count(query);
            catalogues.add((String)doc.get("nomCatalogue")+" : "+collectionProd.count(query)+" produits ");

        }
            }
    private void formatageInfosCatalogue(String[] infosCatalogues, List<String> catalogues) {
        for (int i = 0; i < findAll().size(); i++) {
            infosCatalogues[i] = catalogues.get(i);
        }
    }
    @Override
    public int getCountCatalogue() {

        MongoCollection<Document> collectionCat = mongoDatabase.getCollection("db_catalogues");

int i = (int) collectionCat.count();
        return i;
    }
}
