
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class UnionFind {
    
    private Map<Integer, Integer> leaders = new HashMap<>();
    private Map<Integer, List<Integer>> followers = new HashMap<>();
    private int groupCount = 0;
    
    public UnionFind(Graph graph) {
        // Add every vertex to the leaders and followers hashmaps. Initially, every vertex's leader is itself.
        for (Vertex vertex : graph.getVertices().values()) {
            leaders.put(vertex.getName(), vertex.getName());
            
            List<Integer> followersList = new ArrayList<>();
            followersList.add(vertex.getName());
            followers.put(vertex.getName(), followersList);
            
            this.groupCount++;
        }
    }
    
    public Map<Integer, Integer> getLeaders() {
        return this.leaders;
    }
    
    public Map<Integer, List<Integer>> getFollowers() {
        return this.followers;
    }
    
    // Return the name of the group (leader name) that the vertex with specified name belongs to.
    public int find(Vertex vertex) {
        return leaders.get(vertex.getName());
    }
    
    // Fuse the connected components (give them all the same leader) of vertexOne and vertexTwo.
    public void union(Vertex vertexOne, Vertex vertexTwo) {
        // Determine which vertex's group is the smallest. We will update the leader of each vertex in the smaller group.
        int leaderOfGroupOne = leaders.get(vertexOne.getName());
        int leaderOfGroupTwo = leaders.get(vertexTwo.getName());
        
        int sizeOfGroupOne = followers.get(leaderOfGroupOne).size();
        int sizeOfGroupTwo = followers.get(leaderOfGroupTwo).size();
        
        int leaderOfSmallerGroup;
        int leaderOfLargerGroup;
        
        if (sizeOfGroupOne < sizeOfGroupTwo) {
            leaderOfSmallerGroup = leaderOfGroupOne;
            leaderOfLargerGroup = leaderOfGroupTwo;
        } else {
            leaderOfSmallerGroup = leaderOfGroupTwo;
            leaderOfLargerGroup = leaderOfGroupOne;
        }
        
        // Update the leader of all vertices in the smaller group, and add the new followers to the leader of the larger group.
        for (int follower : followers.get(leaderOfSmallerGroup)) {
            leaders.put(follower, leaderOfLargerGroup);
            followers.get(leaderOfLargerGroup).add(follower);
        }
        
        // Remove all followers from the leader of the former smaller group, since it no longer has any followers.
        followers.get(leaderOfSmallerGroup).clear();
        
        // Decrement group count, since there is now one less group.
        this.groupCount--;
    }
    
    public int getGroupCount() {
        return this.groupCount;
    }
}
