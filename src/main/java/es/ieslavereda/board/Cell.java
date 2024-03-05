package es.ieslavereda.board;

import com.diogonunes.jcolor.Attribute;
import es.ieslavereda.piece.Piece;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Cell {

    private Piece piece;
    private Board board;
    private Coordinate coordinate;
    private Color originalColor;
    private Color color;
    private int[] colors;

    public Cell(Board board, Coordinate coordinate) {
        this.board = board;
        this.coordinate = coordinate;
        this.piece = null;
        colors = new int[3];

        if ((coordinate.getNumber() + coordinate.getLetter()) % 2 == 1) {
            this.originalColor = Color.BLACK;
            colors[0] = 180;
            colors[1] = 180;
            colors[2] = 180;
        } else {
            this.originalColor = Color.WHITE;
        }
        this.color = originalColor;

    }

    public void setColors(int[] colors) {
        this.colors = colors;
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

    public void highlight(){
        if(originalColor== Color.WHITE)
            this.color = (piece!=null)? Color.HIGHLIGHT_KILL_WHITE : Color.HIGHLIGHT_WHITE;
        else
            this.color = (piece!=null)? Color.HIGHLIGHT_KILL_BLACK : Color.HIGHLIGHT_BLACK;
    }

    public void removeHighLight(){
        this.color = originalColor;
    }

    @Override
    public String toString(){
        if(piece==null){
            return colorize("   ",color.getAttribute());
        }else{
            return colorize(" ",color.getAttribute())+piece+colorize(" ",color.getAttribute());
        }
    }

    public Color getColor() {
        return color;
    }

    public boolean isEmpty() {
        return piece==null;
    }

    public enum Color {
        WHITE(Attribute.BACK_COLOR(180,180,180)),
        BLACK(Attribute.BACK_COLOR(100,100,100)),
        HIGHLIGHT_KILL_WHITE(Attribute.BACK_COLOR(180,0,0)),
        HIGHLIGHT_KILL_BLACK(Attribute.BACK_COLOR(130,0,0)),
        HIGHLIGHT_WHITE(Attribute.BACK_COLOR(180,180,0)),
        HIGHLIGHT_BLACK(Attribute.BACK_COLOR(130,130,0));

        private Attribute color;

        Color(Attribute color) {
            this.color = color;
        }

        public Attribute getAttribute() {
            return color;
        }
    }
}