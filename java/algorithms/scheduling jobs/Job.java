
public class Job implements Comparable<Job> {

    private int weight;
    private int length;
    private int score;
    private int completionTime;

    public Job(int weight, int length, String scoreType) {
        this.weight = weight;
        this.length = length;
        
        // Allow caller to specify how they would like the score to be calculated - either using the difference or the quotient of the weight and length.
        if (scoreType.equals("difference")) {
            this.score = computeDifferenceScore();
        } else if (scoreType.equals("quotient")) {
            this.score = computeQuotientScore();
        }
    }
    
    public int getWeight() {
        return this.weight;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }
    
    // Computes score based on the difference of the job's weight and length.
    private int computeDifferenceScore() {
        return this.weight - this.length;
    }
    
    // Computes score based on the quotient of the job's weight and length.
    private int computeQuotientScore() {
        return this.weight / this.length;
    }
    
    @Override
    public int compareTo(Job job) {
        // If the scores are equal, then the job with the higher weight comes before. If they have the same weight, then the second job arbitrarily comes before.
        if (this.score == job.getScore()) {
            
            if (this.weight > job.getWeight()) {
                return -1;
            } else {
                return 1;
            }
            
        } else if (this.score > job.getScore()) { // If this score is higher, then this comes before.
            return -1;
        } else { // If this score is lower, then this comes after.
            return 1;
        }
    }
}
