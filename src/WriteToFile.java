import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.io.FileNotFoundException;
//class will write the data from our gui and other classes to a READ-ONLY file to store things like
//assignments, scores, etc.
//essentially called upon closing of gui inside the ignore folder.
public class WriteToFile {
    //assume filename is a txt file
    //filename will typically be named gradebook_data.txt
    public WriteToFile(String filename, GradebookGUI gradebook) throws Exception{
        String slash = System.getProperty("file.separator");
        String path = ".." + slash + "ignore-student-data" + slash + filename;
        File file = new File(path);
        file.setWritable(true);
        file.setReadable(true);
        file.setExecutable(true);
        if (file.createNewFile()) {
            FileWriter writing = new FileWriter(path, true);
            for(int i = 0; i < gradebook.getSize(); i++) {
                writing.write(gradebook.getCategory(i).getCategory() + " " + gradebook.getCategory(i).getWeight() + "\n" + gradebook.getCategory(i).toString());
            }
            writing.close();
        }
        else {
            FileWriter writing = new FileWriter(path, true);
            for(int i = 0; i < gradebook.getSize(); i++) {
                writing.write(gradebook.getCategory(i).getCategory() + "\n" + gradebook.getCategory(i).toString());
            }
            writing.close();
        }
        //no one can edit the grades other than the app.
        file.setReadable(false);
        file.setWritable(false);
        file.setExecutable(false);
    }
}