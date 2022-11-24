package cartes;

public class Carta implements Comparable<Carta> {
    public static int COPA = 0, ESPASA = 1, OROS = 2, BASTONS = 3;
    public static String[] TYPES = {"COPAS", "ESPASES", "OROS", "BASTONS"};
    private int num, type_id;

    public Carta(int num, int type_id) {
        this.num = num;
        this.type_id = type_id;
    }

    public String toString() {
        return this.num + " de " + TYPES[this.type_id];
    }

    public int getNum() {
        return this.num;
    }

    public int getTypeID() {
        return this.type_id;
    }

    @Override
    public int compareTo(Carta o) {
        int comp_type = o.getTypeID() - this.type_id;
        if(comp_type == 0) return o.getNum() - this.getNum();
        return comp_type;
    }
}
