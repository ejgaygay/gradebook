import java.util.*;
public class Category {
    private int totalScore;
    private int totalPoints; //percent for category = totalScore/totalPoints*weight;
    private int weight;
    private ArrayList<Assignment> assignments;
    private String name;

    public Category(String name, int weight) {
        this.name = name;
        this.weight = weight;
        this.totalScore = 0;
        this.totalPoints = 0;
        assignments = new ArrayList<>(0);
    }

    public double pointsForThisCategory(){
        return ( ((double)totalScore) / ((double)totalPoints) ) * weight ;
    }
    //add an assignment to the list for this category.
    public void add(Assignment assignment) {
        assignments.add(assignment);
        totalScore += assignment.getPointsEarned();
        totalPoints += assignment.getPointsPossible();
    }

    //remove an assignment completely
    public void remove(Assignment assignment) {
        if (assignments.remove(assignment)) {
            totalScore -= assignment.getPointsEarned();
            totalPoints -= assignment.getPointsPossible();
        }
    }

    //get the assignment
    public Assignment getAssigment(int i) {
        return assignments.get(i);
    }

    public int size() {
        return assignments.size();  
    }

    public String getCategory() {
        return this.name;
    }

    public int getWeight(){
        return this.weight;
    }

    //grade change or replacement
    public void setAssignment(Assignment assignment) {
        for(int i = 0; i < assignments.size(); i++) {
            if(assignment.equals(assignments.get(i))){
                this.totalScore -= assignments.get(i).getPointsEarned();
                this.totalPoints -= assignments.get(i).getPointsPossible();
                assignments.set(i, assignment);
                this.totalScore += assignments.get(i).getPointsEarned();
                this.totalPoints += assignments.get(i).getPointsPossible();
                break;
            }
        }
    }

    //equality based on name
    public boolean equals(Object o) {
        return ((o instanceof Category) && ((Category)o).getCategory().equals(this.getCategory()));
    }

    //helps write to a file.
    public String toString() {
        StringBuilder categoryString = new StringBuilder();
        for(int i = 0; i < assignments.size(); i++){
            categoryString.append(assignments.get(i));
        }
        return categoryString.toString();
    }
    //a category holds a list of assignments
}