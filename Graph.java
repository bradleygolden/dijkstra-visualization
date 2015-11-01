public class Graph
{
    public static void main(String[] args)
    {
        Node node1 = new Node(Integer.MAX_VALUE);
        Node node2 = new Node(Integer.MAX_VALUE);
        Node node3 = new Node(Integer.MAX_VALUE);
        Edge edge1_2 = new Edge(node1, node2, 20);
        Edge edge1_3 = new Edge(node1, node3, 10);
        Edge edge2_3 = new Edge(node2, node3, 6);
    }
}
