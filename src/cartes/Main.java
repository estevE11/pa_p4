package cartes;

import arbres.Acb;
import arbres.AcbEnll;
import arbres.ArbreException;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Baralla b = new Baralla();

        AcbEnll<Carta> arbre = new AcbEnll<Carta>();
        for(int i = 0; i < b.size(); i++) {
            try {
                arbre.inserir(b.get(i));
            } catch(ArbreException e) {
                System.out.println(e.getMessage());
            }
        }

        // Imprimim el arbre de manera ordenada
        imprimirArbre(arbre, true);

        AcbEnll<Carta> cloned = (AcbEnll<Carta>) arbre.clone();

        boolean loop = true;

        while(loop) {
            if(arbre.cardinalitat() == 0) {
                loop = false;
                continue;
            }

            String response = "";
            do {
                System.out.println();
                System.out.println();
                System.out.println("Menu opcions");
                System.out.println("============");
                System.out.println("1 - Eliminar carta");
                System.out.println("2 - Acabar");
                response = askQuestion("Tria que vols fer (1 o 2)", sc);
            } while (!response.equals("1") && !response.equals("2"));

            if (response.equals("2")) {
                loop = false;
                continue;
            }

            int type = -1;
            do {
                System.out.println("Tria el pal de la carta");
                System.out.println("1 - Copes");
                System.out.println("2 - Espases");
                System.out.println("3 - Oros");
                System.out.println("4 - Bastons");
                response = askQuestion("Tria: ", sc);
                type = Integer.parseInt(response);
            } while (type < 1 || type > 4);
            type -= 1;

            int num = -1;
            do {
                response = askQuestion("Tria el numero de la carta [1, 12]", sc);
                num = Integer.parseInt(response);
            } while (num < 1 || num > 12);

            Carta c_selec = new Carta(num, type);
            try {
                arbre.esborrar(c_selec);
            } catch(ArbreException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("S'ha eliminat la carta " + c_selec.toString());

            // Imprimim el arbre de manera ordenada
            imprimirArbre(arbre, true);
        }

        System.out.println("");
        System.out.println("");

        // Imprimim el arbre clonat de manera ordenada descendentment
        imprimirArbre(cloned, false);
        System.out.println("");

        Random random = new Random();
        int n = random.nextInt(30)+1;

        for(int i = 0; i < n;) {
            Carta c_random = new Carta(random.nextInt(12)+1, random.nextInt(4));
            boolean esborrat = true;
            try {
                cloned.esborrar(c_random);
            } catch(ArbreException e) {
                esborrat = false;
            }
            if(esborrat) {
                i++;
                System.out.println("\nHa sortit la carta " + c_random.toString());
                imprimirArbre(cloned, false);
                System.out.println("");
                System.out.println("");
            }
        }
        System.out.println();

        int card_original = arbre.cardinalitat();
        int card_cloned = cloned.cardinalitat();
        if (card_original>card_cloned){
            System.out.println("Resultat: L'arbre original té més cartes.");
        } else if (card_original<card_cloned) {
            System.out.println("Resultat: L'arbre clonat té més cartes.");
        }else {
            System.out.println("Resultat: Ambdós tenen el mateix nombre de cartes.");
        }
    }

    private static void imprimirArbre(AcbEnll<Carta> arbre, boolean sentit) {
        arbre.iniRecorregut(sentit);
        while(!arbre.finalRecorregut()) {
            try {
                Carta c = arbre.segRecorregut();
                System.out.print(c.toString() + "   ");
            } catch(ArbreException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String askQuestion(String question, Scanner sc) {
        System.out.print(question + " ");
        return sc.nextLine();
    }
}
