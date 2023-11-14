// Follower.java
package fr.isep;

public class Follower extends Node {
    public Follower(int nodeId) {
        super(nodeId);
    }

    @Override
    public void processModification(String logEntry) {
        this.log.add(new LogEntry(logEntry));

        // Send acknowledgment back to the leader
        sendAcknowledgment(logEntry);
    }

    private void sendAcknowledgment(String logEntry) {
        // Simulate acknowledgment
        System.out.println("Acknowledgment sent for: " + logEntry);
    }
}
