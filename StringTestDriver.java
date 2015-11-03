public class StringTestDriver
{
    public static void main(String[] args)
    {
        Graph graph = new Graph(4, 4);

        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);

        graph.addEdge(graph.nodes[1], graph.nodes[0], 10);
        graph.addEdge(graph.nodes[2], graph.nodes[1], 5);
        graph.addEdge(graph.nodes[1], graph.nodes[3], 8);
        graph.addEdge(graph.nodes[3], graph.nodes[0], 2);

        System.out.println(graph.toString());
    }
}
