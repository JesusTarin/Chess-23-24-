package es.ieslavereda.tools;

import es.ieslavereda.board.Coordinate;

import java.util.Scanner;

public class Input {

    public static Coordinate askCoordinate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce the coordinate you want to select: ");
        String coord = sc.nextLine();
        while (coord.length()!=2) {
            System.err.println("Coordinate does not have the correct length, try again: ");
            coord = sc.nextLine();
        }
        while (((coord.charAt(0)+"").toUpperCase().charAt(0)<'A' && (coord.charAt(0)+"").toUpperCase().charAt(0)>'H') && (Integer.parseInt(coord.charAt(1)+"")<1 && Integer.parseInt(coord.charAt(1)+"")>8)) {
            System.err.println("Coordinate is not in the board, try again: ");
            coord = sc.nextLine();
        }
        try {
                return new Coordinate((coord.charAt(0)+"").toUpperCase().charAt(0), Integer.parseInt(coord.charAt(1)+""));
        } catch (NumberFormatException e) {
                System.err.println("That is not a coordinate");
        }
        return new Coordinate('a',0);
    }

    public static String askOS() {
        Scanner sc = new Scanner(System.in);
        String OS;
        do {
            System.out.println("Do you use linux (l) or windows (w)");
            OS = sc.nextLine();
            if (!(OS.equalsIgnoreCase("w") || (OS.equalsIgnoreCase("l")))) {
                System.err.println("Wrong OS");
            }
        } while (!(OS.equalsIgnoreCase("w") || (OS.equalsIgnoreCase("l"))));
        return OS;
    }

}
