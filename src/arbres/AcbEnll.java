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
            // Si la comparaci√≥ es 0 vol dir que el que intentem
            // inserir ja existeix per tant no l'inserim
        }
        public NodeA esborrar(E val) throws ArbreException{
            if (inf.compareTo(val)<0){
                if (l != null){
                    l = l.esborrar(val);
                    return this;
                }else throw new ArbreException("no hi es");

            }else if (inf.compareTo(val)>0){
                if (r != null){
                    r = r.esborrar(val);
                    return this;
                }else throw new ArbreException("no hi es");
            }else {
                if (l != null && r != null){
                    inf = r.searchMinim();
                    r = r.esborrar(inf);
                    return this;
                }else if (l == null && r == null) return null;
                else if (l == null) return r;
                else return l;
            }
        }

        public E searchMinim(){
            if (this.l == null) return this.inf;
            NodeA aux = this.l;
            while (aux.l != null){
                aux = aux.l;
            }
            return aux.inf;
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
        prepara l‚Äôarbre per a ser recorregut en inordre. Despr√©s d‚Äôinvocar
        aquest m√®tode, la invocaci√≥ del m√®tode segRecorregut retornar√† el
        primer element en inordre de l‚Äôarbre. Aquest m√®tode ha d‚Äôemplenar l‚Äôatribut
        cua amb els elements de l‚Äôarbre aplicant un recorregut en inordre. Cal tenir
        present el par√†metre alhora d‚Äôemplenar la cua
    */
    public void iniRecorregut(boolean sentit) {
        this.queue = new LinkedList<E>();
        this.addToQueue(this.root, sentit);
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
        retorna true si ja s‚Äôha arribat al final del recorregut en inordre de
        l‚Äôarbre. Aix√≤ √©s si:
        ‚Äź l‚Äôarbre √©s buit
        ‚Äź la darrera vegada que es va invocar segRecorregut aquest m√®tode
        ja va retornar el darrer element en inordre de l‚Äôarbre.
        Tot aix√≤ √©s el mateix que dir que retorna true quan no t√© sentit invocar el
        m√®tode segRecorregut
    */
    public boolean finalRecorregut() {
        if(this.queue == null) return false;
        return this.queue.peek() == null;
    }

    /*
        retorna el seg√ľent element en inordre, si n‚Äôhi ha.
        Llen√ßa una excepci√≥ si:
        ‚Äź abans d‚Äôinvocar‚Äźlo no s‚Äôha invocat el m√®tode iniRecorregut
        ‚Äź la darrera vegada que es va invocar ja va retornar
        el darrer element del recorregut (finalRecorregut retornaria true)
        ‚Äź s‚Äôinvoca quan entre la invocaci√≥ de iniRecorregut i la del m√®tode
        s‚Äôha produ√Įt una modificaci√≥ de l‚Äôarbre, aix√≤ √©s, s‚Äôha fet √ļs del
        m√®tode inserir, esborrar, buidar
    */

    public E segRecorregut() throws ArbreException {
        if(this.queue == null) throw new ArbreException("No s'ha cridat el metode iniRecorregut o s'ha produit una modificaci√≥ de l'arbre sense cridar al metode iniRecorregut");
        E e = this.queue.poll();
        if(e == null) throw new ArbreException("La darrera vegada que es va invocar aquest metode ja es va retornar l'ultim element");
        return e;
    }

    // Retorna el numero de nodes q te l'arbre
    public int cardinalitat() {
        int c = this.root.count();
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
        if (this.root == null) throw new ArbreException("l'arbre √©s buit");
        this.root = this.root.esborrar(e);
        this.queue = null;
    }


    @Override
    public boolean membre(E e) {
        if (this.root == null) return false;
        return this.root.hiEs(e);
    }

}
