package uml.e05.monestier.dezette.metier;

import java.util.List; 

public interface I_Catalogue {

	boolean addProduit(I_Produit produit);
	boolean addProduit(String nom, double prix, int qte);
	int addProduits(List<I_Produit> l);
	boolean removeProduit(String nom);
	boolean acheterStock(String nomProduit, int qteAchetee);
	boolean vendreStock(String nomProduit, int qteVendue);
	String[] getNomProduits();
	double getMontantTotalTTC();
	String toString();
	String getNomCatalogue();
	void close();
	void clear();
	void initialisationCatalogue(String nomCatalogue);


}