package es.ieslavereda.controller;

import es.ieslavereda.model.board.Coordinate;

import java.util.Scanner;

public class Input {

    public static Coordinate askCoordinate() throws NumberFormatException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce the coordinate you want to select: ");
        String coord = sc.nextLine();
        if (coord.equalsIgnoreCase("save")) {
            throw new NumberFormatException();
        }
        while (coord.length()!=2) {
            System.err.println("Coordinate does not have the correct length, try again: ");
            coord = sc.nextLine();
            if (coord.equalsIgnoreCase("save")) {
                throw new NumberFormatException();
            }
        }
        while (((coord.charAt(0)+"").toUpperCase().charAt(0)<'A' && (coord.charAt(0)+"").toUpperCase().charAt(0)>'H') && (Integer.parseInt(coord.charAt(1)+"")<1 && Integer.parseInt(coord.charAt(1)+"")>8)) {
            System.err.println("Coordinate is not in the board, try again: ");
            coord = sc.nextLine();
            if (coord.equalsIgnoreCase("save")) {
                throw new NumberFormatException();
            }
        }
        try {
                return new Coordinate((coord.charAt(0)+"").toUpperCase().charAt(0), Integer.parseInt(coord.charAt(1)+""));
        } catch (NumberFormatException e) {
                System.err.println("That is not a coordinate");
        }
        return new Coordinate('a',0);
    }

    public static String newOrLoad() {
        Scanner sc = new Scanner(System.in);
        System.out.println("New game (n) or load game (l)");
        String input = sc.nextLine();
        while (input.isEmpty()) {
            System.out.println("Write at least 1 letter");
            input = sc.nextLine();
        }
        while (!((input.charAt(0)+"").equalsIgnoreCase("l")) && !((input.charAt(0)+"").equalsIgnoreCase("n"))) {
            System.out.println("The first letter must be 'n' for a new game or 'l' to load a saved game.");
            input = sc.nextLine();
            if (input.isEmpty()){
                do {
                    System.out.println("Write at least 1 letter");
                    input = sc.nextLine();
                } while (input.isEmpty());
            }
        }
        return input.toLowerCase();
    }

}