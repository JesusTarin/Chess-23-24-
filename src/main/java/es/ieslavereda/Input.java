package es.ieslavereda;

import java.util.Scanner;

public class Input {

    public static Coordinate askCoordinate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce the coordinate you want to select: ");
        String coord = sc.nextLine();
        while (coord.length()!=2) {
            System.err.println("Coordinate is not long enough, try again: ");
            coord = sc.nextLine();
        }
        while (((coord.charAt(0)+"").toUpperCase().charAt(0)<'A' && (coord.charAt(0)+"").toUpperCase().charAt(0)>'H') && (Integer.parseInt(coord.charAt(1)+"")<1 && Integer.parseInt(coord.charAt(1)+"")>8)) {
            System.err.println("Coordinate is not in the board, try again: ");
            coord = sc.nextLine();
        }
        return new Coordinate((coord.charAt(0)+"").toUpperCase().charAt(0), Integer.parseInt(coord.charAt(1)+""));
    }

    public static String askOS() {
        Scanner sc = new Scanner(System.in);
        String SO;
        do {
            System.out.println("Do you use linux (l) or windows (w)");
            SO = sc.nextLine();
            if (SO != "w" || SO != "l") {
                System.err.println("Wrong OS");
            }
        } while (SO != "w" || SO != "l");
    }

}
