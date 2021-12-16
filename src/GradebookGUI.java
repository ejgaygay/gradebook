import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

//creating a jar file - jar vfe jarname.jar mainclass *.class
/**
 * A gradebook that the user interacts with. 
 */
//utilize jlist
//buttons under lists
// - add
// - remove
public class GradebookGUI {
    JFrame mainMenu;
    private ArrayList<Category> categories;
    public GradebookGUI (){

        //init frame
        //for now we want to test the other methods in assignment, category, and the lists
        /*
        mainMenu = new JFrame();
        mainMenu.setTitle("Gradebook and Calculator");
        mainMenu.setLayout(new GridLayout());
        mainMenu.pack();
        mainMenu.setVisible(true);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */
        //init categories
        categories = new ArrayList<>(0);
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



    public static void main(String [] args) throws Exception{
        GradebookGUI gradebook = new GradebookGUI();
        Category tests = new Category("Test", 50);
        Category homework = new Category("Homework", 50);
        gradebook.addCategory(tests);
        gradebook.addCategory(homework);
        gradebook.addAssignment(tests, new Assignment("Test 1", 25, 50));
        gradebook.addAssignment(tests, new Assignment("Test 2",50, 50));
        gradebook.addAssignment(homework, new Assignment("HW 1", 10, 10));
        gradebook.addAssignment(homework, new Assignment("HW 2", 10,10));

        System.out.println(gradebook.getGrade());

        gradebook.getCategory("Test").setAssignment(new Assignment("Test 1", 40, 50));

        System.out.println(gradebook.getGrade());

        WriteToFile saveData = new WriteToFile("student-data.txt", gradebook);

    }
}