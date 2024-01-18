package es.ieslavereda;

public class Coordinate {

    private char letter;
    private int number;

    public Coordinate(char letter, int number){
        this.letter = letter;
        this.number = number;
    }

    public char getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
    }
    public Coordinate up(){
        return new Coordinate(letter,--number);
    }
    public Coordinate down(){
        return new Coordinate(letter,++number);
    }
    public Coordinate left(){
        return new Coordinate(--letter,number);
    }
    public Coordinate right(){
        return new Coordinate(++letter,number);
    }
    public Coordinate diagonalUpLeft(){
        return new Coordinate(--letter,--number);
    }
    public Coordinate diagonalUpRight(){
        return new Coordinate(++letter,--number);
    }
    public Coordinate diagonalDownLeft(){
        return new Coordinate(--letter,++number);
    }
    public Coordinate diagonalDownRight(){
        return new Coordinate(++letter,++number);
    }

    @Override
    public String toString(){
        return "("+letter+","+number+")";
    }
    @Override
    public boolean equals(Object obj){
        //es nulo?
        if(obj==null) return false;
        //es una instancia de Coordenada?
        if(!(obj instanceof Coordinate)) return false;
        //casting
        Coordinate coordinate = (Coordinate) obj;
        //comprobación
        return (coordinate.letter==this.letter &&
                coordinate.number==this.number);
    }

}