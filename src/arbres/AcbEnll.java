package arbres;

import java.util.Queue;

public class AcbEnll<E extends Comparable<E>> implements Acb{

    private class NodeA {
        NodeA l, r;
        E inf;

        NodeA(E inf, NodeA l, NodeA r) {
            this.inf = inf;
            this.l = l;
            this.r = r;
        }

        NodeA(E inf) {
            this.inf = inf;
            this.l = null;
            this.r = null;
        }

        public void insert(E val) {

        }
    }

    private NodeA root;
    private Queue<E> queue;

    public AcbEnll(E val, NodeA l, NodeA r) {
        this.root = new NodeA(val, l, r);
    }

    public AcbEnll(E val) {
        this.root = new NodeA(val);
    }

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

    @Override
    public Comparable arrel() throws ArbreException {
        return null;
    }

    @Override
    public Acb fillEsquerre() {
        /* No entiendo
        NodeA l = this.root.l;
        return new AcbEnll(l.inf, l.l, l.r);
        */
    }

    @Override
    public Acb fillDret() {
        /* No entiendo
        NodeA r = this.root.r;
        return new AcbEnll(r.inf, r.l, r.r);
        */
    }

    @Override
    public boolean abBuit() {
        return this.root == null;
    }

    @Override
    public void buidar() {

    }

    @Override
    public void inserir(Comparable c) throws ArbreException {
        E val = (E)c;
        if(this.root == null) this.root = new NodeA((E)c, null, null);
        else this.root.insert(val);
    }

    @Override
    public void esborrar(Comparable c) throws ArbreException {

    }

    @Override
    public boolean membre(Comparable c) {
        return false;
    }

}
