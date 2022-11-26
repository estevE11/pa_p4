package arbres;

import cartes.Carta;

import java.util.Queue;

public class
AcbEnll<E extends Comparable<E>> implements Acb{

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

        public int count(){
            int count = 0;
            if (this == null) return 0;
            if (this.l == null && this.r == null) count++;
            this.l.count();this.r.count();
            return count;
        }

        public boolean hiEs(E val){
            if (val.compareTo(this.inf) < 0){
                if (this.l==null) return false;
                return this.l.hiEs(val);
            } else if (val.compareTo(this.inf) > 0) {
                if (this.r==null) return false;
                return this.r.hiEs(val);
            }else{
                return true;
            }
        }
        public void insert(E val) throws ArbreException{
            int comp = this.inf.compareTo(val);
            if(comp > 0) {
                if(this.r == null) this.r = new NodeA(val);
                else this.r.insert(val);
            } else if(comp < 0) {
                if(this.l == null) this.l = new NodeA(val);
                else this.l.insert(val);
            }else {
                throw new ArbreException("No es pot inserir perque ja existeix.");
            }
            // Si la comparació es 0 vol dir que el que intentem
            // inserir ja existeix per tant no l'inserim
        }
        public void esborrar(E val) throws ArbreException{

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
    public AcbEnll() {
        this.root = null;
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
        return this.root.count();
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
        return (Comparable) this.root;
    }

    @Override
    public Acb fillEsquerre() {
        NodeA l = this.root.l;
        return new AcbEnll(l.inf, l.l, l.r);
    }

    @Override
    public Acb fillDret() {

        NodeA r = this.root.r;
        return new AcbEnll(r.inf, r.l, r.r);
    }

    @Override
    public boolean abBuit() {
        return this.root == null;
    }

    @Override
    public void buidar() {
        this.root = null;
    }

    @Override
    public void inserir(Comparable c) throws ArbreException {
        E val = (E)c;
        if(this.root == null) this.root = new NodeA(val, null, null);
        else this.root.insert(val);
    }

    @Override
    public void esborrar(Comparable c) throws ArbreException {
        this.root = this.esborrar(this.root, (E)c);
    }

    private NodeA esborrar(NodeA n, E e) {
        if(n == null) throw new RuntimeException("no existeix " + e.toString());
        int comp = n.inf.compareTo(e);
        if(comp < 0) {
            n.l = esborrar(n.l, e);
        } else {
            if(comp > 0) {
                n.r = esborrar(n.r, e);
            } else {
                if(n.l == null && n.r == null) n = null;
                else {
                    if(n.l != null && n.r != null) {
                        n = this.searchMin(n.r);
                        n.r = deleteMin(n.r);
                    } else {
                        if(n.l == null) n = n.r;
                        else n = n.l;
                    }
                }
            }
        }
        return n;
    }

    private NodeA searchMin(NodeA n) {
        while(n.l != null) n = n.l;
        return n;
    }

    private NodeA deleteMin(NodeA n) {
        if(n.l == null) return n.r; // de vez en cuando falla aqui, no entiendo ni cuando ni como
        else {
            n.l = deleteMin(n.l);
            return n;
        }
    }

    @Override
    public boolean membre(Comparable c) {
        if (this.root == null) return false;
        return this.root.hiEs((E)c);
        //return this.membre(this.root, (E)c);
    }

    private boolean membre(NodeA a, E e) {
        if(a == null) return false;
        if(e.compareTo(a.inf) == 0) return true;
        if(e.compareTo(a.inf)<0) return membre(a.l,e);
        else return membre(a.r,e);
    }

}
