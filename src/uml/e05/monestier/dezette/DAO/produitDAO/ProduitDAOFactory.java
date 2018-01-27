package uml.e05.monestier.dezette.DAO.produitDAO;

public class ProduitDAOFactory {
    public ProduitDAOFactory() {
    }
    public I_produitDAO createDAO(){

        return new ProduitDAO();
    }
    public I_produitDAO createXMLDAO(){
        return new AdapteurXML();
    }
    public I_produitDAO createMockDAO(){
        return new ProduitDAO();

    }
}
