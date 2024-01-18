package es.ieslavereda;

public class Board {

    private Cell[][] cells;

    public Board(){
        cells = new Cell[8][8];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell(this,new Coordinate((char)(j+'A'),i+1));
            }
        }
    }


    public boolean contains(Coordinate c) {

        return !(c.getNumber()<1 || c.getNumber()>8
                || c.getLetter()<'A' || c.getLetter()>'H');
    }

    public Cell getCellAt(Coordinate c) {
        if(!contains(c)) return null;
        return cells[c.getNumber()-1][c.getLetter()-'A'];
    }

    @Override
    public String toString() {
        String aux = "    A  B  C  D  E  F  G  H\n";
        int row = 1;
        for(Cell[] fila:cells){
            aux += " " + row + " ";
            for(Cell celda:fila){
                aux += celda;
            }
            aux += " " + row++ + "\n";
        }
        aux += "    A  B  C  D  E  F  G  H";
        return aux;
    }
}
