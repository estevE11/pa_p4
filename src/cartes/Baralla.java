package cartes;

public class Baralla {
    private class Node {
        public Carta card;
        public Node next;
        // Preguntar si puede guardar el valor del nodo anterior
    }

    private Node root;

    public Baralla() {
        // Crear una baraja entera desordenada (bueno en este caso ordenada, pero ns si importa)
        this.root = new Node();
        Node curr = this.root;
        for (int i = 0; i < Carta.TYPES.length; i++) {
            for (int j = 0; j < 12; j++) {
                curr.card = new Carta(j + 1, i);
                curr.next = new Node();
                curr = curr.next;
            }
        }
    }

    // Testing: esto se tiene que mirar si cambiarlo ns si es lo que pide
    public Carta get(int i) {
        int curr_i = 0;
        Node curr_node = this.root;
        while(curr_node != null) {
            if(curr_i == i) return curr_node.card;
            curr_node = curr_node.next;
            curr_i++;
        }
        return null;
    }

    public int size() {
        int size = 0;
        Node curr_node = this.root;
        while(curr_node != null) {
            curr_node = curr_node.next;
            size++;
        }
        return size-1;
    }
}
