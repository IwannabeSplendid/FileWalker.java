package wordmap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestWordMap {
    //REMOVE COMMENTS FROM THE RELEVANT TEST AS YOU NEED
    public static void main(String[] args) {
        try {
           testCompareTo();
            testPutAndToString();
           testCount(); //based on test case from profs

            //Two test below check #5 and #6 from assignment instructions respectively
            testAddOccBuff("dir1/f1"); //CHOOSE YOUR OWN FILEPATH
            testAddOcc("dir1"); //CHOOSE YOUR OWN FILEPATH

            //The final test checks difference between your output and expected output of SECOND TEST from profs in "test_dir"
            finalTest("test_dir", "expected_output.txt"); //CHOOSE YOUR OWN FILEPATH
            test("test_dir/Music/Johnny Cash/One Piece at a Time.txt");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }


    public static void testCompareTo() {
        Position testPos = new Position(2, 2);
        System.out.println("TESTING compareTo");

        System.out.println("Equality check1..." + (testPos.compareTo(new Position(2, 2)) == 0 ? "PASSED" : "FAILED"));
        System.out.println("Equality check2..." + (testPos.compareTo(new Position(2, 1)) != 0 ? "PASSED" : "FAILED"));
        System.out.println("Equality check3..." + (testPos.compareTo(new Position(1, 2)) != 0 ? "PASSED" : "FAILED"));
        System.out.println("Line number lesser..." + (testPos.compareTo(new Position(3, 2)) < 0 ? "PASSED" : "FAILED"));
        System.out.println("Line number greater..." + (testPos.compareTo(new Position(1, 2)) > 0 ? "PASSED" : "FAILED"));
        System.out.println("Column number lesser..." + (testPos.compareTo(new Position(2, 3)) < 0 ? "PASSED" : "FAILED"));
        System.out.println("Column number greater..." + (testPos.compareTo(new Position(2, 1)) > 0 ? "PASSED" : "FAILED"));
        System.out.println("----------------------------------------------------");
    }

    public static void testCount() {
        System.out.println("TESTING count METHODS");
        wordmap.Occurrences occ = new wordmap.Occurrences();

        occ.put("hello", "file", new wordmap.Position(1, 2));
        occ.put("hello", "file", new wordmap.Position(3, 4));
        occ.put("goodbye", "f2", new wordmap.Position(5, 6));
        occ.put("goodbye", "file", new wordmap.Position(1, 2));

        boolean testCountWords = occ.countWords() == 2;
        boolean testCountTotal = occ.count() == 4;
        boolean testCountWordFile = occ.count("goodbye", "f2") == 1 && occ.count("goodbye", "f4") == 0;
        boolean testCountWordOnly = occ.count("farewell") == 0;
        // System.out.println(occ);
        boolean testIsPassed = testCountWords && testCountTotal && testCountWordFile && testCountWordOnly;
        if (testIsPassed) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("FAILED, checking for error");
            System.out.println("countWords check..." + (testCountWords ? "PASSED" : "FAILED"));
            System.out.println("count( ) check..." + (testCountTotal ? "PASSED" : "FAILED"));
            System.out.println("count( String word, String file ) check..." + (testCountWordFile ? "PASSED" : "FAILED"));
            System.out.println("count( String word ) check..." + (testCountWordOnly ? "PASSED" : "FAILED"));
        }
        System.out.println("----------------------------------------------------");
    }

    public static void testPutAndToString() {
        wordmap.Occurrences test = new wordmap.Occurrences();
        test.put("a", "dir1/f1", new Position(1, 3));
        test.put("A", "dir1/f1", new Position(1, 3));
        test.put("AA", "dir1/dir2/f2", new Position(3, 3));
        test.put("aa", "dir1/dir2/f3", new Position(3, 3));
        test.put("your_name", "/dir2/misconduct.txt", new Position(1, 1));
        test.put("cC", "/dir2/ll", new Position(1, 2));
        test.put("Cc", "/dir2/ll", new Position(3, 22));
        test.put("rickroll", "never/gonna/give/you/up", new Position(14, 88));

        System.out.println("TESTING put and toString");
        System.out.println("You should get something similar to this: Count may not be implemented");
      /*  System.out.println("""
                word "a" occurs 1 times:
                   in file "dir1/f1":
                      at line 1, column 3
                word "aa" occurs 2 times:
                   in file "dir1/dir2/f2":
                      at line 3, column 3
                   in file "dir1/dir2/f3":
                      at line 3, column 3
                word "cc" occurs 2 times:
                   in file "/dir2/ll":
                      at line 1, column 2
                      at line 3, column 22
                word "rickroll" occurs 1 times:
                   in file "never/gonna/give/you/up":
                      at line 14, column 88
                word "your_name" occurs 1 times:
                   in file "/dir2/misconduct.txt":
                      at line 1, column 1""");
*/
        System.out.println("--------------------Your version below--------------------");
        System.out.println(test);
        System.out.println("----------------------------------------------------");
    }

    public static void testAddOccBuff(String path) throws IOException {
        System.out.println("TESTING addOccurrences( BufferedReader source, String sourcename, Occurrences occ )");
        System.out.println("You should get something similar to this: ");
     /*   System.out.println("""
                word "a" occurs 1 times:
                   in file "dir1/f1":
                      at line 1, column 6
                word "day" occurs 1 times:
                   in file "dir1/f1":
                      at line 2, column 10
                word "great" occurs 1 times:
                   in file "dir1/f1":
                      at line 2, column 4
                word "have" occurs 1 times:
                   in file "dir1/f1":
                      at line 1, column 1
                occurrences      4
                distinct words   4"""); */
        File testFile = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(testFile));

        wordmap.Occurrences occ = new wordmap.Occurrences();
        wordmap.FileWalker.addOccurrences(reader, testFile.getPath(), occ);

        System.out.println("--------------------Your version below--------------------");
        System.out.println(occ);

        reader.close();
        System.out.println("----------------------------------------------------");
    }

    public static void testAddOcc(String path) throws IOException {
        System.out.println("TESTING addOccurrences( File file, Occurrences occ )");
        System.out.println("You should get something similar to this: ");
  /*      System.out.println("""
                word "a" occurs 1 times:
                   in file "dir1/f1":
                      at line 1, column 6
                word "aa" occurs 2 times:
                   in file "dir1/dir2/f2":
                      at line 1, column 1
                   in file "dir1/dir2/f3":
                      at line 1, column 1
                word "bb" occurs 2 times:
                   in file "dir1/dir2/f2":
                      at line 1, column 4
                   in file "dir1/dir2/f3":
                      at line 2, column 5
                word "cc" occurs 1 times:
                   in file "dir1/dir2/f2":
                      at line 2, column 1
                word "day" occurs 1 times:
                   in file "dir1/f1":
                      at line 2, column 10
                word "great" occurs 1 times:
                   in file "dir1/f1":
                      at line 2, column 4
                word "have" occurs 1 times:
                   in file "dir1/f1":
                      at line 1, column 1
                word "zz" occurs 1 times:
                   in file "dir1/dir2/f3":
                      at line 3, column 1
                occurrences      10
                distinct words   8"""); */
        File testFile = new File(path);

        wordmap.Occurrences occ = new wordmap.Occurrences();
        wordmap.FileWalker.addOccurrences(testFile, occ);

        System.out.println("--------------------Your version below--------------------");
        System.out.println(occ);
    }

    public static void makeOutput(Occurrences occ) throws IOException {
        File outfile = new File("output.txt");
        PrintWriter writer = new PrintWriter(
                new FileOutputStream(outfile));
        writer.print('\n');
        writer.print(occ.toString());
        writer.close();
    }

    public static void finalTest(String pathTest, String pathOut) throws IOException {
        System.out.println("FINAL TEST with test_dir");
        File testFile = new File(pathTest);
        wordmap.Occurrences occ = new wordmap.Occurrences();
        wordmap.FileWalker.addOccurrences(testFile, occ);
        //System.out.println(occ);
        makeOutput(occ);
       compareFiles(new File("output.txt"), new File(pathOut));
       System.out.println("If there is no significant difference in content test is passed");
    }

    public static void compareFiles(File file1, File file2) throws IOException {
        BufferedReader br1;
        BufferedReader br2;
        String sCurrentLine;
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        br1 = new BufferedReader(new FileReader(file1));
        br2 = new BufferedReader(new FileReader(file2));

        while(br1.ready() || br2.ready()){
            String l1 =br1.readLine().trim();
            String l2 = br2.readLine().trim();
            if(!Objects.equals(l1,l2)){
                System.out.println("Error: "+l1 + " AND " + l2);
            }
        }

      /*  while ((sCurrentLine = br1.readLine()) != null) {
            list1.add(sCurrentLine);
        }
        while ((sCurrentLine = br2.readLine()) != null) {
            list2.add(sCurrentLine);
        }
        List<String> tmpList = new ArrayList<>(list1);
        tmpList.removeAll(list2);
        System.out.println("content from first file " + file1.getPath() + " which is not there in expected_output.txt");
        for (String value : tmpList) {
            System.out.println(value); //content from first file which is not there in expected_output.txt
        }

        System.out.println("content from second file " + file2.getPath() + " which is not there in output.txt");

        tmpList = list2;
        tmpList.removeAll(list1);
        for (String s : tmpList) {
            System.out.println(s); //content from second file which is not there in output.txt
        } */
    }

    public static void test(String path) throws IOException {
        System.out.println("TESTING");
        File testFile = new File(path);

        wordmap.Occurrences occ = new wordmap.Occurrences();
        wordmap.FileWalker.addOccurrences(testFile, occ);
        System.out.println(occ);
    }
}


