package es.ieslavereda;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Cell {

    private Piece piece;
    private Board board;
    private Coordinate coordinate;
    private Color originalColor;
    private Color color;

    public Cell(Board board, Coordinate coordinate) {
        this.board = board;
        this.coordinate = coordinate;
        this.piece = null;
        this.originalColor=(((coordinate.getLetter()-'A')+(coordinate.getNumber()-1))%2==0)?Color.WHITE:Color.BLACK;
        this.color=originalColor;
    }
    public boolean isEmpty(){
        return piece==null;
    }

    public Piece getPiece() {
        return piece;
    }

    public Board getBoard() {
        return board;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Color getColor() {
        return color;
    }

    public void highlight(){
        if(isEmpty()){
            if(originalColor==Color.BLACK)
                color = Color.HIGHLIGHT_BLACK;
            else
                color = Color.HIGHLIGHT_WHITE;
        } else {
            color = (originalColor==Color.BLACK)?Color.HIGHLIGHT_KILL_BLACK:Color.HIGHLIGHT_KILL_WHITE;
        }
    }

    public void resetColor(){ color = originalColor;}

    @Override
    public String toString() {
        if(piece==null)
            return colorize("   ",color.getAttribute());
        return colorize(" ",color.getAttribute())+piece+colorize(" ",color.getAttribute());
    }

    public enum Color {
        //put your task here
        WHITE(Attribute.BACK_COLOR(180,180,180)),
        BLACK(Attribute.BACK_COLOR(100,100,100)),
        HIGHLIGHT_KILL_WHITE(Attribute.BACK_COLOR(180,0,0)),
        HIGHLIGHT_KILL_BLACK(Attribute.BACK_COLOR(130,0,0)),
        HIGHLIGHT_WHITE(Attribute.BACK_COLOR(180,180,0)),
        HIGHLIGHT_BLACK(Attribute.BACK_COLOR(130,130,0));

        private Attribute color;
        Color(Attribute color){
            this.color=color;
        }
        public Attribute getAttribute(){return color;}

    }
}