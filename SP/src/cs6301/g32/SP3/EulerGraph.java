package cs6301.g32.SP3;

import java.util.Scanner;

/**
 * EulerGraph is meant to check if a directed graph is Eulerian 
 * @author Vinaya, Pradeep, Mohan, Nikita
 */
public class EulerGraph {

    Graph g;
    
    /**
     * initialize directed graph for the class
     * @param g 
     */
    public EulerGraph(Graph g) {
        this.g = g;
    }
    
    /**
     * function check if graph is Eulearian
     * @return true if Eulerian graph
     */
    public boolean testEulerian(){
        
        StronglyConnected scc = new StronglyConnected(g);
        
        //if graph is strongly connected then there will be a maximum of one component
        if(scc.stronglyConnectedComponents(g)!=1)
            return false;
        
        //checking if inDegree of graph is equal to outDegree
        for(Graph.Vertex v:g){
            if(v.adj.size()!=v.revAdj.size()){
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        EulerGraph eg = new EulerGraph(Graph.readDirectedGraph(in));
        System.out.println("The graph is Eulerian?:"+eg.testEulerian());
    }
}
