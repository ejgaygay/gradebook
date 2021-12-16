import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.io.FileNotFoundException;
//imports the data from the txt file in our ignore directory to our gradebook gui
//work on this part of project - finding out how to put the data written to the file back into the program for the gui
public class ImportData {
    public ImportData(String filename) throws Exception{
        String path = ("..\\ignore\\" + filename);
        Scanner scanner = new Scanner(new File(path));

        //general file format
        //category name weight
        //Assignment name: pointsEarned/pointsPossible
        ArrayList<String> strings = new ArrayList<>();
        while(scanner.hasNext()) {
            strings.add(scanner.nextLine());
        }

        for(int i = 0; i < strings.size(); i++) {
            if(!strings.get(i).equals("\n"))
                System.out.println(strings.get(i));
            else
                System.out.println("There is a new line added to the list of strings");
        }
    }   

    public static void main(String [] args) throws Exception{
        ImportData data = new ImportData("student-data.txt");
    }
}