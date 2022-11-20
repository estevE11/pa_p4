package arbres;

import java.util.Queue;

public class AcbEnll<E extends  Comparable<E>> {
    private class NodeA {
        NodeA l, r;
        E infM;
    }

    private NodeA root;
    private Queue<E> queue;

    /*
        prepara l’arbre per a ser recorregut en inordre. Després d’invocar
        aquest mètode, la invocació del mètode segRecorregut retornarà el
        primer element en inordre de l’arbre. Aquest mètode ha d’emplenar l’atribut
        cua amb els elements de l’arbre aplicant un recorregut en inordre. Cal tenir
        present el paràmetre alhora d’emplenar la cua
    */
    public void iniRecorregut(boolean sentit) {

    }

    /*
        retorna true si ja s’ha arribat al final del recorregut en inordre de
        l’arbre. Això és si:
        ‐ l’arbre és buit
        ‐ la darrera vegada que es va invocar segRecorregut aquest mètode
        ja va retornar el darrer element en inordre de l’arbre.
        Tot això és el mateix que dir que retorna true quan no té sentit invocar el
        mètode segRecorregut
    */
    public boolean finalRecorregut() {
        return false;
    }

    /*
        retorna el següent element en inordre, si n’hi ha.
        Llença una excepció si:
        ‐ abans d’invocar‐lo no s’ha invocat el mètode iniRecorregut
        ‐ la darrera vegada que es va invocar ja va retornar
        el darrer element del recorregut (finalRecorregut retornaria true)
        ‐ s’invoca quan entre la invocació de iniRecorregut i la del mètode
        s’ha produït una modificació de l’arbre, això és, s’ha fet ús del
        mètode inserir, esborrar, buidar
    */
    public E segRecorregut() throws ArbreException {
        return null;
    }

    // Retorna el numero de nodes q te l'arbre
    public int cardinalitat() {
        return 0;
    }

    public Object clone() {
        return null;
    }

    // Se pueden añadir metodos privados nunca publicos


    // Implementar el metodo "compareTO"
    public boolean compareTo(AcbEnll<E> other) {
        return false;
    }
}
