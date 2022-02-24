package wordmap;

public class main {
    public static void main(String[] args){
        wordmap.Occurrences occ = new wordmap.Occurrences( );

        occ. put( "hello", "file", new wordmap.Position(1,2));
        occ. put( "hello", "file", new wordmap.Position(3,4));
        occ. put( "goodbye", "f2", new wordmap.Position(5,6));
        occ. put( "goodbye", "file", new wordmap.Position(1,2));

        System. out. println( occ );
        System. out. println( "countWords      " + occ. countWords( ));
        System. out. println( "count           " + occ. count( ));
        System. out. println( "goodbye in f2   " + occ. count( "goodbye", "f2" ));
        System. out. println( "goodbye in f4   " + occ. count( "goodbye", "f4" ));
        System. out. println( "farewell        " + occ. count( "farewell" ));
    }
}
