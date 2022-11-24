package cartes;

public class Baralla {
    private class Node {
        public Carta value;
        public Node next;
        // Preguntar si puede guardar el valor del nodo anterior
    }

    private Node root;

    public Baralla() {
        // Crear una baraja entera desordenada (bueno en este caso ordenada, pero ns si importa)
        this.root = new Node();
        Node curr = this.root;
        for (int i = 0; i < Carta.TYPES.length; i++) {
            for (int j = 0; i < 12; i++) {
                curr.value = new Carta(j + 1, i);
                curr.next = new Node();
                curr = curr.next;
            }
        }
    }
}
