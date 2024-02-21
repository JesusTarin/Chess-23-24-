package es.ieslavereda;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Board tablero = new Board();
        Rook rook= new Rook(tablero,new Coordinate('D',5), Rook.Type.BLACK);
        Queen queen=new Queen(tablero,new Coordinate('B',3), Queen.Type.WHITE);
        Bishop b1 = new Bishop(tablero, new Coordinate('C',3),Bishop.Type.BLACK);
        Bishop b2 = new Bishop(tablero, new Coordinate('A',4),Bishop.Type.WHITE);
        Pawn peon1 = new Pawn(tablero,new Coordinate('A',2),Pawn.Type.WHITE);
        Pawn peon2 = new Pawn(tablero,new Coordinate('A',7),Pawn.Type.BLACK);
        System.out.println("Se puede mover? " + peon2.canMoveTo(new Coordinate('A',8)));

        tablero.highligh(peon1.getNextMovements());
        System.out.println(tablero);
        tablero.resetColor();
        peon1.moveTo(new Coordinate('A',1));
        System.out.println(tablero);
        tablero.highligh(tablero.getCellAt(new Coordinate('A',1)).getPiece().getNextMovements());
        System.out.println(tablero);

    }
}
