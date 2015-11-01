public class Graph
{
    private Node[] nodes;
    private Edge[] edges;

    public Graph()
    {
        nodes = new Node[0];
        edges = new Edge[0];
    }

    public void addNode(int val)
    {
        Node newNode = new Node(val);
        Node[] newNodes = new Node[]
    }

    public void addEdge(Node start, Node end, int val)
    {
        Edge newEdge = new Edge(start, end, val);
    }

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
