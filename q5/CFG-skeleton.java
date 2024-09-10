import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Stack;
import org.objectweb.asm.commons.*;
import org.objectweb.asm.tree.*;

public class CFG {
    Set<Node> nodes = new HashSet<Node>();
    Map<Node, Set<Node>> edges = new HashMap<Node, Set<Node>>();

    static class Node {

      int position;
      MethodNode method;
      ClassNode clazz;

  Node(int p, MethodNode m, ClassNode c) {
    position = p; method = m; clazz = c;
  }

  public boolean equals(Object o) {
    if (!(o instanceof Node)) return false;
      Node n = (Node)o;
      return (position == n.position) &&
    method.equals(n.method) && clazz.equals(n.clazz);
  }

  public int hashCode() {
      return position + method.hashCode() + clazz.hashCode();
  }

  public String toString() {
      return clazz.name + "." +
    method.name + method.signature + ": " + position;
  }
    }

    public void addNode(int p, MethodNode m, ClassNode c) {
      Node newNode = new Node(p, m, c);
      if (!nodes.contains(newNode)) {
        nodes.add(newNode);
        edges.put(newNode, new HashSet<Node>());
      }
    }

    public void addEdge(int p1, MethodNode m1, ClassNode c1,
      int p2, MethodNode m2, ClassNode c2) {
      Node node1 = new Node(p1, m1, c1);
      Node node2 = new Node(p2, m2, c2);

      // Add nodes if they don't exist
      if (!nodes.contains(node1)) addNode(p1, m1, c1);
      if (!nodes.contains(node2)) addNode(p2, m2, c2);

      // Add the edge from node1 to node2
      edges.get(node1).add(node2);
    }

  public void deleteNode(int p, MethodNode m, ClassNode c) {
    Node nodeToDelete = new Node(p, m, c);
    if (nodes.contains(nodeToDelete)) {
      // Remove the node from set of nodes
      nodes.remove(nodeToDelete);
      // Remove all edges that reference this node
      edges.remove(nodeToDelete);
      for (Set<Node> neighbours : edges.values()) {
        neighbours.remove(nodeToDelete);
      }
    }
  }

    public void deleteEdge(int p1, MethodNode m1, ClassNode c1,
      int p2, MethodNode m2, ClassNode c2) {
      Node node1 = new Node(p1, m1, c1);
      Node node2 = new Node(p2, m2, c2);

      if (edges.containsKey(node1)) {
        edges.get(node1).remove(node2);
      }
    }


    public boolean isReachable(int p1, MethodNode m1, ClassNode c1,
      int p2, MethodNode m2, ClassNode c2) {
      Node startNode = new Node(p1, m1, c1);
      Node endNode = new Node(p2, m2, c2);

      if (!nodes.contains(startNode) || !nodes.contains(endNode)) return false;

      // Use a stack to implement BFS
      Stack<Node> stack = new Stack<>();
      Set<Node> visited = new HashSet<>();
      stack.push(startNode);
      visited.add(startNode);

      while (!stack.isEmpty()) {
        Node current = stack.pop();
        if (current.equals(endNode)) return true;
        for (Node neighbor : edges.get(current)) {
          if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            stack.push(neighbor);
          }
        }
      }
      return false;
    }

}

