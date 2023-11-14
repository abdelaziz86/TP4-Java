// DistributedSystem.java
package fr.isep;

public class DistributedSystem {
    public static void main(String[] args) {
        // Create nodes
        Leader leader = new Leader(1);

        Follower follower1 = new Follower(2);
        Follower follower2 = new Follower(3);
        Follower follower3 = new Follower(4);
        Follower follower4 = new Follower(5);

        // Set followers for the leader
        leader.addFollower(follower1);
        leader.addFollower(follower2);
        leader.addFollower(follower3);
        leader.addFollower(follower4);

        // Create clients
        Client client1 = new Client(1, leader);
        Client client2 = new Client(2, leader);

        // Add clients to the leader
        leader.addClient(client1);
        leader.addClient(client2);

        // Simulate client interaction
        client1.guessNumber(42);
        client2.guessNumber(55);

        // Simulate leader suggesting a number
        leader.suggestNumber();

        // Simulate verifying guesses
        leader.verifyGuesses();
    }
}
