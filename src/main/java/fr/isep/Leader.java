// Leader.java
package fr.isep;

import java.util.ArrayList;
import java.util.List;

public class Leader extends Node {
    private List<Client> clients;
    private int currentValue;

    public Leader(int nodeId) {
        super(nodeId);
        this.clients = new ArrayList<>();
        this.currentValue = 50; // Initial value (midpoint of 0 and 100)
    }

    @Override
    public void processModification(String data) {
        String logEntry = createLogEntry(data);
        this.log.add(new LogEntry(logEntry));

        // Notify followers to update their logs
        notifyFollowers(logEntry);

        // Verify guesses and determine the closest guess
        verifyGuesses();
    }

    void verifyGuesses() {
        int closestGuess = Integer.MAX_VALUE;
        for (LogEntry entry : log) {
            String[] parts = entry.getData().split(" ");
            if (parts.length >= 4 && parts[3].equals("Guess:")) {
                int guessValue = Integer.parseInt(parts[4]);
                int distance = Math.abs(currentValue - guessValue);
                if (distance < Math.abs(currentValue - closestGuess)) {
                    closestGuess = guessValue;
                }
            }
        }

        currentValue = closestGuess;
        System.out.println("Current value: " + currentValue);

        // Notify followers about the closest guess
        notifyFollowers("Value:" + currentValue);
    }

    private void notifyFollowers(String message) {
        for (Client client : clients) {
            client.receiveMessage(message);
        }
    }

    public void suggestNumber() {
        // Suggest a number for clients to guess
        int suggestedNumber = currentValue; // Example suggestion
        for (Client client : clients) {
            client.guessNumber(suggestedNumber);
        }
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    private String createLogEntry(String data) {
        // Create log entry with timestamp and ID
        long timestamp = System.currentTimeMillis();
        return "ID " + this.nodeId + " \"" + data + "\" " + timestamp;
    }
}
