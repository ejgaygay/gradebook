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
    ArrayList<Class> classes;
    //for os
    String slash = System.getProperty("file.separator");
    //System.setProperty("java.awt.headless", "false");
    public GradebookGUI (){
        classes = new ArrayList<>(0);
        retrieveData();

        //init frame
        //for now we want to test the other methods in assignment, category, and the lists
        mainMenu = new JFrame();
        mainMenu.setTitle("Gradebook and Calculator");
        //mainMenu.setLayout(new GridLayout());
        //just set it to this for now.
        mainMenu.setLayout(new FlowLayout());

    //  --==setting up the menu==-- \\

        JMenuBar menuBar = new JMenuBar();
        JMenu modes = new JMenu("Modes");
        JMenuItem predictionMode = new JMenuItem("Prediction Mode");
        JMenuItem viewGrades = new JMenuItem("View Grades");

        JMenu options = new JMenu("Options");
        JMenuItem exit = new JMenuItem("Exit");
        //shows us a message when we try to click on the menu item mode
        predictionMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object s = new String("Hey. Feature is a work in progress right now. :(");
                JOptionPane.showMessageDialog(null,s);
            }
        });

        viewGrades.addActionListener(new ActionListener() {
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
        modes.add(viewGrades);
        options.add(exit);
        menuBar.add(modes);
        menuBar.add(options);
        mainMenu.setJMenuBar(menuBar);

    //  --==set up gui==-- \\
        //set up jtable
        //columns = number of attributes for an assignment - ass. name, ass. category, points earned, points possible - 4 columns
        //rows = number of assignments for a class - variable
        //for now we test / see how it looks so im doing that here
        //learning mode activated:

        
        mainMenu.add(new Label("test"));
        JTable table = new JTable(100,4);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //make the table scrollable.
        JScrollPane scroll = new JScrollPane(table);
        mainMenu.add(table);

        mainMenu.add(new Label("class2"));
        JTable table2 = new JTable(100,4);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //make the table scrollable.
        JScrollPane scroll2 = new JScrollPane(table);
        mainMenu.add(table2);

        mainMenu.add(new Label("class3"));
        JTable table3 = new JTable(100,4);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //make the table scrollable.
        JScrollPane scroll3 = new JScrollPane(table);
        mainMenu.add(table3);

        //set up visibility and what happens when we close the program
        mainMenu.pack();
        mainMenu.setVisible(true);
        mainMenu.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                writeData();
            }
        });
    }

    public void addClass(Class course) {
        classes.add(course);
    }

    public void removeClass(Class course) {
        classes.remove(course);
    }

    public Class getClass(String course) {
        for (int i = 0 ; i < classes.size(); i++) {
            if (course.equals(classes.get(i).getClassName())){
                return classes.get(i);
            }
        }
        return null;
    }

    public boolean isEmpty(){
        return classes.isEmpty();
    }


    //write categories and assignments to data
    private void writeData(){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream((".." + slash + "ignore-student-data" + slash + "grades.bin")));
            System.out.println(classes.size());
            objectOutputStream.writeInt(classes.size());
            for(int i = 0; i < classes.size(); i++) {
                objectOutputStream.writeObject(classes.get(i));
            }
            objectOutputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    private void retrieveData() {
        File gradesBin = new File(".." + slash + "ignore-student-data" + slash + "grades.bin");
        if (gradesBin.exists()) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(".." + slash + "ignore-student-data" + slash + "grades.bin"));
                int size = objectInputStream.readInt();
                System.out.println(size);
                for(int i = 0; i < size; i++) {
                    Class course = (Class) objectInputStream.readObject();
                    System.out.println(course);
                    classes.add(course);
                }
                objectInputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(gradesBin.delete());
            printClasses();
        }
    }

    public void printClasses(){
        for (int i = 0; i<classes.size(); i++) {
            System.out.println(classes.get(i));
            System.out.println(classes.get(i).getGrade());
        }
    }


    //ignore, this is all testing.
    public static void main(String [] args) throws Exception{
        GradebookGUI gradebook = new GradebookGUI();
        Class math = new Class("Math");
        Class computers = new Class("Computers");
        Category tests = new Category("Test", 50);
        Category homework = new Category("Homework", 50);
        Category tests2 = new Category("Test", 50);
        Category homework2 = new Category("Homework", 50);
        math.addCategory(tests);
        math.addCategory(homework);
        computers.addCategory(tests2);
        computers.addCategory(homework2);
        math.getCategory("Test").add(new Assignment("Test 1", 25, 50));
        math.getCategory("Test").add(new Assignment("Test 2",50, 50));
        computers.getCategory("Test").add(new Assignment("Test 1",50, 50));
        computers.getCategory("Test").add(new Assignment("Test 2", 50, 50));
        
        if (gradebook.isEmpty()){
            gradebook.addClass(math);
            gradebook.addClass(computers);
        }

        //System.out.println(gradebook.getGrade());

        //gradebook.getCategory("Test").setAssignment(new Assignment("Test 1", 40, 50));

        //System.out.println(gradebook.getGrade());



    }
}