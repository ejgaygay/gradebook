import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

//creating a jar file - jar vfe jarname.jar mainclass *.class
/**
 * A gradebook that the user interacts with. 
 */
//utilize jlist
//buttons under lists
// - add
// - remove
//currently only supports one class.
//to implement in future
// = multiple class support
// = features associated with it.
public class GradebookGUI implements Serializable{
    JFrame mainMenu;
    private ArrayList<Category> categories;
    //for os
    String slash = System.getProperty("file.separator");
    //System.setProperty("java.awt.headless", "false");
    public GradebookGUI (){

        //init categories
        categories = new ArrayList<>(0);
        retrieveData();

        //init frame
        //for now we want to test the other methods in assignment, category, and the lists
        mainMenu = new JFrame();
        mainMenu.setTitle("Gradebook and Calculator");
        //mainMenu.setLayout(new GridLayout());
        //just set it to this for now.
        mainMenu.setPreferredSize(new Dimension(640,640));
        mainMenu.pack();

        //setting up the menu
        JMenuBar menuBar = new JMenuBar();
        JMenu modes = new JMenu("Modes");
        JMenuItem predictionMode = new JMenuItem("Prediction Mode");

        JMenu options = new JMenu("Options");
        JMenuItem exit = new JMenuItem("Exit");
        //shows us a message when we try to click on the menu item mode
        predictionMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object s = new String("Hey. Feature is a work in progress right now. :(");
                JOptionPane.showMessageDialog(null,s);
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeData();
                System.exit(1);
            }
        });
        
        //set up menus
        modes.add(predictionMode);
        options.add(exit);
        menuBar.add(modes);
        menuBar.add(options);
        mainMenu.setJMenuBar(menuBar);

        //set up visibility and what happens when we close the program
        mainMenu.setVisible(true);
        mainMenu.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                writeData();
            }
        });
    }

    //add a category to our gradebook
    public void addCategory(Category category){
        categories.add(category);
    }

    public void addAssignment(Category category, Assignment assignment) {
        categories.get(categories.indexOf(category)).add(assignment);
    }

    public void removeAssignment(Category category, Assignment assignment) {
        categories.get(categories.indexOf(category)).remove(assignment);
    }

    public double getGrade(){
        double points = 0;
        //assumed that all categories ultimate weights summed will add up to 100
        int pointsPossible = 100;
        for (int i = 0; i < categories.size(); i++) {
            points += categories.get(i).pointsForThisCategory();
        }
        return points / pointsPossible ;
    }

    //get a category based on a category's name
    public Category getCategory(String category) {
        for(int i = 0; i < categories.size(); i++) {
            if(category.equals(categories.get(i).getCategory())){
                return categories.get(i);
            }
        }
        return null;
    }

    //return the category at index i;
    public Category getCategory(int i) {
        return categories.get(i);
    }

    public int getSize() {
        return categories.size();
    }

    //fix this more but for now, it works i guess
    public char getGradeLetter(){
        double grade = getGrade()*100;
        if (grade < 60) {
            return 'F';
        }
        else if(grade < 70) {
            return 'D';
        }
        else if(grade < 80) {
            return 'C';
        }
        else if (grade < 90) {
            return 'B';
        }
        else if(grade < 100) {
            return 'A';
        }
        else
            return 'o';
    }

    //write categories and assignments to data
    private void writeData(){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((".." + slash + "ignore-student-data" + slash + "grades.bin")));
            System.out.println(categories.size());
            objectOutputStream.writeInt(categories.size());
            for(int i = 0; i < categories.size(); i++) {
                objectOutputStream.writeObject(categories.get(i));
            }
            objectOutputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public boolean isEmpty() {
        return categories.isEmpty();
    }

    private void retrieveData() {
        File gradesBin = new File(".." + slash + "ignore-student-data" + slash + "grades.bin");
        if (gradesBin.exists()) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(".." + slash + "ignore-student-data" + slash + "grades.bin"));
                int size = objectInputStream.readInt();
                System.out.println(size);
                for(int i = 0; i < size; i++) {
                    Category category = (Category) objectInputStream.readObject();
                    System.out.println(category + " " +category.pointsForThisCategory());
                    categories.add(category);
                }
                objectInputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(gradesBin.delete());
            System.out.println(getGrade());
        }
    }


    public static void main(String [] args) throws Exception{
        GradebookGUI gradebook = new GradebookGUI();
        Category tests = new Category("Test", 50);
        Category homework = new Category("Homework", 50);
        if (gradebook.isEmpty()){
            gradebook.addCategory(tests);
            gradebook.addCategory(homework);
            gradebook.addAssignment(tests, new Assignment("Test 1", 25, 50));
            gradebook.addAssignment(tests, new Assignment("Test 2",50, 50));
            gradebook.addAssignment(homework, new Assignment("HW 1", 10, 10));
            gradebook.addAssignment(homework, new Assignment("HW 2", 10,10));
        }

        //System.out.println(gradebook.getGrade());

        //gradebook.getCategory("Test").setAssignment(new Assignment("Test 1", 40, 50));

        //System.out.println(gradebook.getGrade());



    }
}