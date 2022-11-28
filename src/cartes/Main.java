package cartes;

import arbres.Acb;
import arbres.AcbEnll;
import arbres.ArbreException;

public class Main {
    public static void main(String[] args) throws ArbreException {
        Baralla b = new Baralla();

        AcbEnll<Carta> arbre = new AcbEnll<Carta>();
        for(int i = 0; i < b.size(); i++) {
            arbre.inserir(b.get(i));
        }

        arbre.iniRecorregut(true);
        while(!arbre.finalRecorregut()) {
            Carta c = arbre.segRecorregut();
            System.out.print(c.toString() + "   ");
        }

        AcbEnll<Carta> cloned = (AcbEnll<Carta>) arbre.clone();

        
    }
}
