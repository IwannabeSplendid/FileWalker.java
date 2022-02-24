package wordmap;


public class Position implements Comparable<Position> 
{
    final private int line;
    final private int column;

    public Position( int line, int column ) {
        this.line = line;
        this.column = column;
    }

    public Position clone( ) 
    {
        return new Position( line, column );
    }

    public String toString() {
        return "line " + line + ", column " + column;  
    }

    public int compareTo( Position pos ){
        if(this.line == pos.line){
            return Integer.compare(this.column, pos.column);
        }
        return Integer.compare(this.line, pos.line);
    }

}


