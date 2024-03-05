package es.ieslavereda.board;

import com.diogonunes.jcolor.Attribute;

import java.util.*;

import static com.diogonunes.jcolor.Ansi.colorize;

/*
El codigo comentado es porque he intentado que en windows se vea bien, pero no sirve
 */


public class Board {

    private Map<Coordinate, Cell> cells;
    //private String OS;

    public Board() {
        cells = new HashMap<>();
        //OS = Input.askOS(); //Esto preguntaba si tu sistema operativo era windows

        for (int row = 8; row >= 1; row--)
            for (char col = 'A'; col <= 'H'; col++)
                cells.put(new Coordinate(col, row), new Cell(this, new Coordinate(col, row)));

    }

    public void askColors() {
        Scanner sc = new Scanner(System.in);
        String color = "";
        int red=0,green=0,blue=0;
        boolean loop = true;
        System.out.println("- Write 'c' to use custom colors on the board\n- Write 'd' o use default colors");
        do {
            try {
                color = sc.nextLine();
                if (color.charAt(0)=='c' || color.charAt(0)=='d') {
                    loop = false;
                } else {
                    System.out.println("- Write 'c' to use custom colors on the board (RGB)\n- Write 'd' o use default colors (grey tones)");
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Wrong input");
            }
        } while (loop);
        loop = true;
        if (color.charAt(0)=='c') {
            do {
                System.out.println("Introduce the RGB color numbers: ");
                try {
                    System.out.println("Red:");
                    red = sc.nextInt();
                    System.out.println("Green:");
                    green = sc.nextInt();
                    System.out.println("Blue:");
                    blue = sc.nextInt();
                    if ((red>=0 && red<=255) && (green>=0 && green<=255) && (blue>=0 && blue<=255)) {
                        loop=false;
                        this.getCellAt(new Coordinate('c',0)).setColors(new int[]{red,green,blue});
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Wrong codes");
                }
            } while (loop);
        }
    }

    public boolean contains(Coordinate c) {
        return cells.containsKey(c);
    }

    public Map<Coordinate, Cell> getCells() {
        return cells;
    }

    public Cell getCellAt(Coordinate c) {
        if (!contains(c)) return null;
        return cells.get(c);
    }

    public void highLight(Set<Coordinate> coordinates) {
        coordinates.stream().forEach(coordinate -> getCellAt(coordinate).highlight());
    }

    public void removeHighLight() {
        cells.values().stream().forEach(cell -> cell.removeHighLight());
    }


    @Override
    public String toString() {
        String aux = colorize("    A  B  C  D  E  F  G  H    \n", Attribute.BACK_COLOR(154,100,19));

        for (int row = 1; row <= 8; row++) {
            aux += colorize(" " + row + " ", Attribute.BACK_COLOR(154,100,19));
            for (char col = 'A'; col <= 'H'; col++) {
                aux += cells.get(new Coordinate(col, row));
                /*
                Aquí se añade un espacio en las casillas vacías si tu SO era windows
                para que fueran igual de grandes que las que tienen piezas,
                pero se pasan del tamaño de las casillas ocupadas

                if (cells.get(new Coordinate(col, row)).isEmpty() && OS.equalsIgnoreCase("w")) {
                    aux += colorize(" ",cells.get(new Coordinate(col,row)).getColor().getAttribute());
                }
                 */
            }
            aux += colorize(" " + row + " \n", Attribute.BACK_COLOR(154,100,19));
        }
        aux += colorize("    A  B  C  D  E  F  G  H    ", Attribute.BACK_COLOR(154,100,19));
        return aux;
    }
}