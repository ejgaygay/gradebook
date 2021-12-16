import java.io.Serializable;

public class Assignment implements Serializable{
    private int pointsEarned; // pointsPossible/pointsEarned
    private int pointsPossible;
    String name; //assignment name

    public Assignment(String name){
        this.name = name;
        pointsEarned = 0;
        pointsPossible = 0;
    }

    //an easier way to construct an assignment
    public Assignment(String name, int pointsEarned, int pointsPossible) {
        this.name = name;
        this.pointsEarned = pointsEarned;
        this.pointsPossible = pointsPossible;
    }

    public void setGrade(int pointsPossible){
        this.pointsPossible = pointsPossible;
    }

    public void setWeight(int pointsEarned){
        this.pointsEarned = pointsEarned;
    }

    public void setAssignment(String name){
        this.name = name;
    }

    public int getPointsEarned(){
        return pointsEarned;
    }

    public int getPointsPossible(){
        return pointsPossible;
    }

    public String getAssignment(){
        return this.name;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(name + ": " + pointsEarned + "/" + pointsPossible +"\n");
        return string.toString();
    }

    //assignment equality based on name.
    public boolean equals(Object o){
        return (o instanceof Assignment) && ((Assignment)o).getAssignment().equals(this.getAssignment());
    }
}