package cartes;

import arbres.Acb;
import arbres.AcbEnll;
import arbres.ArbreException;

public class Main {
    public static void main(String[] args) throws ArbreException {
        System.out.println("hola buenas");
        AcbEnll<Carta> arbre = new AcbEnll<Carta>();
        Baralla b = new Baralla();
        for(int i = 0; i < b.size(); i++) {
            Carta c = b.get(i);
            System.out.println(c.toString());
            arbre.inserir(c);
        }

        Carta test = new Carta(2, 2); // 2 de OROS
        if(arbre.membre(test)) {
            System.out.println("caca");
            arbre.esborrar(test);
            System.out.println("caca");
        } else
            System.out.println("la carta no esta makina");

        arbre.iniRecorregut(true);
        System.out.println("caca");
    }
}
