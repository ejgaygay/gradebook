import java.util.*;
import java.io.Serializable;
public class Class implements Serializable{
    
    private ArrayList<Category> categories;

    private String className;

    public Class(String className) {
        this.className = className;
        categories = new ArrayList<>(0);
    }

    public String getClassName() {
        return this.className;
    }

    //add a category to our class
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
        double pointsPossible = 0.0;
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).checkToggle()) {
                points += categories.get(i).pointsForThisCategory();
                pointsPossible += categories.get(i).getWeight();
            }
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

    public boolean isEmpty() {
        return categories.isEmpty();
    }

    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append(getClassName() + "\n");
        for (int i = 0; i < categories.size(); i++) {
            string.append(categories.get(i));
        }
        return string.toString();
    }

    public boolean equals(Object o) {
        return (o instanceof Class) && ((Class)o).getClassName().equals(this.getClassName());
    }
}