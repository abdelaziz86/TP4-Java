// Leader.java
package fr.isep;

import java.util.ArrayList;
import java.util.List;

public class Leader extends Node {
    private List<Client> clients;

    public Leader(int nodeId) {
        super(nodeId);
        this.clients = new ArrayList<>();
    }

    @Override
    public void processModification(String data) {
        String logEntry = createLogEntry(data);
        this.log.add(new LogEntry(logEntry));

        // Notify followers to update their logs
        notifyFollowers(logEntry);
    }

    private void notifyFollowers(String logEntry) {
        for (Node follower : followers) {
            follower.processModification(logEntry);
        }
    }

    private String createLogEntry(String data) {
        // Create log entry with timestamp and ID
        long timestamp = System.currentTimeMillis();
        return "ID " + this.nodeId + " \"" + data + "\" " + timestamp;
    }

    public void suggestNumber() {
        // Suggest a number for clients to guess
        int suggestedNumber = 50; // Example suggestion
        for (Client client : clients) {
            client.guessNumber(suggestedNumber);
        }
    }

    public void verifyGuesses() {
        // Verify client guesses and determine the closest guess
        // Implementation logic for verifying guesses goes here
    }

    public void addClient(Client client) {
        clients.add(client);
    }
}
