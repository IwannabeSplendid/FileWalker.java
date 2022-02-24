
package wordmap;

import java.util.*;

public class Occurrences  
{
    private Map< String, Map< String, Set< Position >>> occ; 
        // Maps words -> filename -> sets of their Positions in the file.
 
    public Occurrences( ) 
    {
        occ = new TreeMap<> ( ); 
    }

    public void put( String word, String filename, Position pos ){
        word = word.toLowerCase();
        if(!occ.containsKey(word)){
           Map< String, Set< Position >> newMap1 = new TreeMap<>();
           Set<Position> newSet1 = new TreeSet<>();
           newSet1.add(pos);
           newMap1.put(filename, newSet1);
           occ.put(word,newMap1);
        }else{
            Map< String, Set< Position >> valueMap = occ.get(word);
            if(!valueMap.containsKey(filename)){
                Set<Position> newSet2 = new TreeSet<>();
                newSet2.add(pos);
                valueMap.put(filename, newSet2);
            }else{
                Set<Position> newSet3 = valueMap.get(filename);
                newSet3.add(pos);
            }
        }
    }


    public int countWords( ){
        return occ.size();
    }

    public int count( ){
        int sum=0;

        for(String word : occ.keySet()){
            sum+=count(word);
        }
        return sum;
    }

    public int count( String word ){
        int sum=0;

        if(occ.containsKey(word)){
            Map< String, Set< Position >> valueMap = occ.get(word);

            for( String file : valueMap.keySet()) {
                sum += count(word, file);
            }
        }
        return sum;
    }

    public int count( String word, String file ){
        if(occ.containsKey(word)){
            Map< String, Set< Position >> valueMap = occ.get(word);

            if(valueMap.containsKey(file)){
                return valueMap.get(file).size();
            }
        }
        return 0;
    }
 
    public String toString( ){
        StringBuilder ans = new StringBuilder();

        for(String word : occ.keySet()){
            Map< String, Set< Position >> valueMap = occ.get(word);
            ans.append("word \"").append(word).append("\"").append(" occurs ").append(count(word)).append(" times:\n");

            for(String file : valueMap.keySet()){
                ans.append("\t in file ").append("\"").append(file).append("\":\n");

                for( Position p : valueMap.get(file)){
                    ans.append("\t\tat ").append(p).append("\n");
                }
            }
        }

        ans.append("\noccurrences    \t").append(count()).append("\ndistinct words \t").append(countWords());

        return ans.toString();
    }
}


