package cartes;

import java.util.Random;

public class Baralla {
    private Random random = new Random();

    private class Node {
        public Carta card;
        public Node next;
        // Preguntar si puede guardar el valor del nodo anterior

        public Node(Carta card) {
            this.card = card;
        }
    }

    private Node root;

    public Baralla() {
        // Crear una baraja entera desordenada (no esta desordenada)
        this.root = null;
        for (int i = 0; i < Carta.TYPES.length; i++) {
            for (int j = 0; j < 12; j++) {
                this.add(new Carta(random.nextInt(11)+1, i));
            }
        }
    }

    public boolean add(Carta c) {
        if(this.root == null) {
            this.root = new Node(c);
            return true;
        }
        Node curr_node = this.root;
        while(curr_node.next != null) {
            if(curr_node.next.card.compareTo(c) == 0) return false;
            curr_node = curr_node.next;
        }
        if(curr_node.card.compareTo(c) != 0) {
            curr_node.next = new Node(c);
            return true;
        }
        return false;
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
        return size;
    }
}
