
package wordmap;
import java.io.*;
import java.util.Objects;


public abstract class FileWalker {

    public static boolean seemsOK( File f ) {
        return f. exists( ) && f. canRead( ); 
    }

    public static boolean seemsOK( String filename ) {
        return seemsOK( new File( filename ));
    }

    public static Occurrences getOccurrences( String filename )
    throws FileNotFoundException, IOException{
        Occurrences occ = new Occurrences( ); 
        addOccurrences( new File( filename ), occ );
        return occ; 
    } 


    public static void addOccurrences( File file, Occurrences occ )
            throws FileNotFoundException, IOException{

        if(file.isDirectory()){
            for(File files : Objects.requireNonNull(file.listFiles())) {
                if (!Objects.equals(files.getName(), ".DS_Store")){ // in mac we have some issues with this folder
                    addOccurrences(files, occ);}
            }
        }else{
            BufferedReader reader = new BufferedReader( new FileReader(file));
            addOccurrences(reader, file.toString(), occ);
            reader.close();
        }
    }


    public static void addOccurrences( BufferedReader source, String sourcename,
                                       Occurrences occ ) throws IOException{
        char ch;
        StringBuilder word = new StringBuilder();

        int line = 1, column=1;
        int columnIter=1;

        boolean newWord=false;
        boolean notEndFile = source.ready(); // is there ch

        while(notEndFile){
            ch = (char) source.read();
            notEndFile= source.ready(); // is there character after ch

            if(wordmap.Syntax.isInWord(ch)){
                if(!newWord){
                    column = columnIter;
                }
                newWord=true;
                word.append(ch);
            }else if(!word.toString().equals("")) {
                occ.put(word.toString(), sourcename, new Position(line, column));
                word= new StringBuilder();
                newWord=false;
            }

            if(!notEndFile && !word.toString().equals("")){
                occ.put(word.toString(), sourcename, new Position(line, column));
            }

            if(wordmap.Syntax.isNewLine(ch)){
                line++;
                columnIter=1;
            }else {
                columnIter++;
            }
        }

        source.close();
    }

}


