package cartes;

import java.util.LinkedList;
import java.util.Random;

public class Baralla {
    private Random random = new Random();

    private class Node {
        public Carta card;
        public Node next;

        public Node(Carta card) {
            this.card = card;
        }
    }

    private Node root;

    public Baralla() {
        this.root = null;

        // Es crea una llista enllaçada amb les cartes ordenades
        LinkedList<Carta> base = new LinkedList<Carta>();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 12; j++) {
                base.add(new Carta(j+1, i));
            }
        }

        // S'agafa una carta aleatoria, s'afegeix a la nostre llista enllaçada
        // i s'elimina de la llista enllaçada base. Aixo fins que la llista base
        // es quedi buida
        int size = base.size();
        while(size > 0) {
            Carta c = base.get(random.nextInt(size));
            this.add(c);
            base.remove(c);
            size = base.size();
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
