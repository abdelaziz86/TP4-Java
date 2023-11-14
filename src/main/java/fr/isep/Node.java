// Node.java
package fr.isep;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
    protected int nodeId;
    protected List<LogEntry> log;
    protected List<Node> followers;

    public Node(int nodeId) {
        this.nodeId = nodeId;
        this.log = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public abstract void processModification(String data);

    public void addFollower(Node follower) {
        followers.add(follower);
    }
}
