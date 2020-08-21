public class Game {
    private String homeTeam;
    private String visitingTeam;
    private int homeTeamPoints;
    private int visitingTeamPoints;
    
    public Game(String homeTeam, String visitingTeam, int homeTeamPoints, int visitingTeamPoints) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.homeTeamPoints = homeTeamPoints;
        this.visitingTeamPoints = visitingTeamPoints;
    }
    
    public String getWinner() {
        // The specs of this assignment don't require handling of ties, so we'll assume there is always only one winner.
        if (homeTeamPoints > visitingTeamPoints) {
            return homeTeam;
        } else {
            return visitingTeam;
        }
    }
    
    public String getLoser() {
        // The specs of this assignment don't require handling of ties, so we'll assume there is always only one loser.
        if (homeTeamPoints < visitingTeamPoints) {
            return homeTeam;
        } else {
            return visitingTeam;
        }
    }
    
    public boolean teamPlayed(String team) {
        return (this.homeTeam.equals(team) || this.visitingTeam.equals(team));
    }
}
