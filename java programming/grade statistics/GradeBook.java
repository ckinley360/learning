import java.util.ArrayList;
 
public class GradeBook {
    private ArrayList<Integer> pointsList;
    private int sum;
    private int count;
    
    private ArrayList<Integer> passingPointsList;
    private int passingSum;
    private int passingCount;
    
    private ArrayList<Integer> gradeList;
    
    public GradeBook() {
        this.pointsList = new ArrayList<>();
        this.sum = 0;
        this.count = 0;
        
        this.passingPointsList = new ArrayList<>();
        this.passingSum = 0;
        this.passingCount = 0;
        
        this.gradeList = new ArrayList<>();
    }
    
    // Add a student's points to the points list. Only points between 0 and 100 are valid. If points falls outside of that range, ignore it.
    public void addPoints(int points) {
        // If points is valid, add to the points and grade list.
        if (points >= 0 && points <= 100) {
            pointsList.add(points);
            sum += points;
            count++;
            
            addGrade(points);
        }
        
        // If points is valid and passing (50 or higher), then add it to the passing points list.
        if (points >= 50 && points <= 100) {
            passingPointsList.add(points);
            passingSum += points;
            passingCount++;
        }
    }
    
    // Calculate the student's grade based on the points, and add to the grade list. Only points between 0 and 100 are valid. If points falls outside of that range, ignore it.
    public void addGrade(int points) {
        // If points is invalid, do nothing and return from the method.
        if (points < 0 || points > 100) {
            return;
        }
        
        int grade;
        
        // Calculate grade.
        if (points < 50) {
            grade = 0;
        } else if (points < 60) {
            grade = 1;
        } else if (points < 70) {
            grade = 2;
        } else if (points < 80) {
            grade = 3;
        } else if (points < 90) {
            grade = 4;
        } else {
            grade = 5;
        }
        
        // Add the grade to the grade list.
        gradeList.add(grade);
    }
    
    // Calculate and return the average of the points list.
    public double getAverage() {
        double average = 0;
 
        // Make sure the list is not empty, then calculate the average.
        if (count > 0) {
            average = (double) sum / count;
        }
        
        return average;
    }
    
    // Calculate and return the average of the passing points list.
    public double getPassingAverage() {
        double passingAverage = 0;
        
        // Make sure the list is not empty, then calculate the average.
        if (passingCount > 0) {
            passingAverage = (double) passingSum / passingCount;
        }
        
        return passingAverage;
    }
    
    // Calculate and return the percentage of points that are passing.
    public double getPassingPercentage() {
        double passingPercentage = 0;
 
        // Make sure the points list is not empty, then calculate the passing percentage.
        if (count > 0) {
            passingPercentage = 100 * ((double) passingCount / count);
        }
        
        return passingPercentage;
    }
    
    // Generate and print a string comprised of the specified number of stars on one line.
    public static void printStars(int count) {
        // If count is 0, then just print a newline and return from the method.
        if (count == 0) {
            System.out.println("");
            return;
        }
        
        String stars = "";
        
        for (int i = 1; i <= count; i++) {
            stars = stars + "*";
        }
        
        System.out.println(stars);
    }
    
    // Calculate and print the distribution of grades.
    public void printGradeDistribution() {
        int zeroes = 0;
        int ones = 0;
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        
        // Loop through the grade list and tally up the count for each grade.
        for (int grade:gradeList) {
            if (grade == 0) {
                zeroes++;
            } else if (grade == 1) {
                ones++;
            } else if (grade == 2) {
                twos++;
            } else if (grade == 3) {
                threes++;
            } else if (grade == 4) {
                fours++;
            } else if (grade == 5) {
                fives++;
            }
        }
        
        // Print out the distribution of grades using stars to represent the count.
        System.out.print("5: ");
        printStars(fives);
        System.out.print("4: ");
        printStars(fours);
        System.out.print("3: ");
        printStars(threes);
        System.out.print("2: ");
        printStars(twos);
        System.out.print("1: ");
        printStars(ones);
        System.out.print("0: ");
        printStars(zeroes);
    }
    
    public void printGradeList() {
        String outputString = "[";
 
        // Make sure the grade list is not empty, then concatenate the grades to the outputString.
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                outputString = outputString + gradeList.get(i);
                
                // If this is not the last element in the list, concatenate a comma to separate the elements.
                if ((i + 1) != count) {
                    outputString = outputString + ", ";
                }
            }
        }
        
        outputString = outputString + "]";
        
        System.out.println(outputString);
    }
    
    public String toString() {
        String outputString = "[";
 
        // Make sure the points list is not empty, then concatenate the points to the outputString.
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                outputString = outputString + pointsList.get(i);
                
                // If this is not the last element in the list, concatenate a comma to separate the elements.
                if ((i + 1) != count) {
                    outputString = outputString + ", ";
                }
            }
        }
        
        outputString = outputString + "]";
        
        return outputString;
    }
}
