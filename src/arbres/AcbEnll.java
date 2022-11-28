package arbres;

import java.util.LinkedList;
import java.util.Queue;

public class
AcbEnll<E extends Comparable<E>> implements Acb<E>{

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
            System.out.println("contando: " + this.inf.toString());
            if (this.l == null && this.r == null) {
                return 1;
            } else {
                int c = 0;
                if(this.l != null) c += this.l.count();
                if(this.r != null) c += this.r.count();
                return c+1;
            }
        }

        public boolean hiEs(E val){ // NO FUNCIONA CRACK
            if (this.inf.compareTo(val) < 0){
                if (this.l==null) return false;
                return this.l.hiEs(val);
            } else if (this.inf.compareTo(val) > 0) {
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
            } else {
                //throw new ArbreException("No es pot inserir perque ja existeix.");
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
        this.queue = new LinkedList<E>();
        this.addToQueue(this.root, sentit);
        System.out.println("afegit");
    }

    private void addToQueue(NodeA a, boolean sentit) {
        if(a.l == null && a.r == null) {
            this.queue.add(a.inf);
            return;
        }

        if(sentit) {
            if (a.l != null) this.addToQueue(a.l, sentit);
        } else
            if(a.r != null) this.addToQueue(a.r, sentit);


        this.queue.add(a.inf);

        if(sentit) {
            if (a.r != null) this.addToQueue(a.r, sentit);
        } else
            if(a.l != null) this.addToQueue(a.l, sentit);
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
        if(this.queue == null) return false;
        return this.queue.peek() == null;
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
        if(this.queue == null) throw new ArbreException("No s'ha cridat el metode iniRecorregut o s'ha produit una modificació de l'arbre sense cridar al metode iniRecorregut");
        E e = this.queue.poll();
        if(e == null) throw new ArbreException("La darrera vegada que es va invocar aquest metode ja es va retornar l'ultim element");
        return e;
    }

    // Retorna el numero de nodes q te l'arbre
    public int cardinalitat() {
        int c = this.root.count();
        System.out.println(c);
        return c;
    }

    public Object clone() {
        AcbEnll<E> cloned = new AcbEnll<E>();
        try {
            this.addToClone(this.root, cloned);
        } catch(ArbreException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cloned;
    }
    
    private AcbEnll<E> addToClone(NodeA n, AcbEnll<E> cloned) throws ArbreException{
        cloned.inserir(n.inf);
        if(n.r != null) this.addToClone(n.r, cloned);
        if(n.l != null) this.addToClone(n.l, cloned); 
        return cloned;
    }

    @Override
    public E arrel() throws ArbreException {
        return this.root.inf;
    }

    @Override
    public Acb<E> fillEsquerre() {
        NodeA l = this.root.l;
        return new AcbEnll<E>(l.inf, l.l, l.r);
    }

    @Override
    public Acb<E> fillDret() {
        NodeA r = this.root.r;
        return new AcbEnll<E>(r.inf, r.l, r.r);
    }

    @Override
    public boolean abBuit() {
        return this.root == null;
    }

    @Override
    public void buidar() {
        this.root = null;
        this.queue = null;
    }

    @Override
    public void inserir(E e) throws ArbreException {
        if(this.root == null) this.root = new NodeA(e, null, null);
        else this.root.insert(e);
        this.queue = null;
    }

    @Override
    public void esborrar(E e) throws ArbreException {
        this.root = this.esborrar(this.root, e);
        this.queue = null;
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
        if(n == null) return null;
        if(n.l == null) return n.r; // de vez en cuando falla aqui, no entiendo ni cuando ni como
        else {
            n.l = deleteMin(n.l);
            return n;
        }
    }

    @Override
    public boolean membre(E e) {
        if (this.root == null) return false;
        return this.root.hiEs(e);
    }

    private boolean membre(NodeA a, E e) {
        if(a == null) return false;
        if(e.compareTo(a.inf) == 0) return true;
        if(e.compareTo(a.inf)<0) return membre(a.l,e);
        else return membre(a.r,e);
    }

}
