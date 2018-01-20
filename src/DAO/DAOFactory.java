package DAO;

public class DAOFactory {
    public DAOFactory() {
    }
    public I_DAO createDAO(){

        return new DAO();
    }
    public I_DAO createXMLDAO(){
        return new DAO.AdapteurXML();
    }
    public I_DAO createMockDAO(){
        return new MockDAO();

    }
}
