// Client.java
package fr.isep;

public class Client {
    private int clientId;
    private Node connectedNode;

    public Client(int clientId, Node connectedNode) {
        this.clientId = clientId;
        this.connectedNode = connectedNode;
    }

    public void guessNumber(int number) {
        // Send the guessed number to the connected server
        connectedNode.processModification("Guess:" + number);
    }
}
