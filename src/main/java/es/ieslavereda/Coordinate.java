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
        return new Coordinate(letter,number-1);
    }
    public Coordinate down(){
        return new Coordinate(letter,number+1);
    }
    public Coordinate left(){
        return new Coordinate((char)(letter-1),number);
    }
    public Coordinate right(){
        return new Coordinate((char)(letter+1),number);
    }
    public Coordinate diagonalUpLeft(){ return new Coordinate((char)(letter-1),number-1);}
    public Coordinate diagonalUpRight(){ return new Coordinate((char)(letter+1),number-1);}
    public Coordinate diagonalDownLeft(){ return new Coordinate((char)(letter-1),number+1);}
    public Coordinate diagonalDownRight(){ return new Coordinate((char)(letter+1),number+1);}


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