package arbres;

public interface Acb<E extends Comparable<E>> {

    //Cal llençar una excepció si es demana l'arrel d'un arbre buit
    E arrel() throws ArbreException;

    // No llença excepcions. Si no té fill esquerre retorna un arbre buit public
    Acb<E> fillEsquerre();

    // No llença excepcions. Si no té fill dret retorna una arbre buit
    Acb<E> fillDret();

    boolean abBuit();

    void buidar();

    // Llença una excepció si l'element que s'insereix està repetit
    void inserir(E e) throws ArbreException;

    // Llança una excepció si l'element no hi és i en conseqüència no es
    // realitza la eliminació
    void esborrar(E e) throws ArbreException;

    // Retorna true si l'arbre conté un element com el donat com a
    // paràmetre
    boolean membre(E e);
}
