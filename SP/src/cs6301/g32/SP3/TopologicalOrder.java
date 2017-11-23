package cs6301.g32.SP3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * this class outputs topological orders using 2 algorithms
 * 1) remove vertices with no incoming edge, along with its outgoing edges
 * 2) Order vertices by decreasing finish times of DFS
 * 
 * @author Vinaya, Pradeep, Mohan, Nikita
 */
public class TopologicalOrder {
    Graph g;
    PlusVertex vertex[];

    public TopologicalOrder(Graph g) {
        this.g=g;
        //initializing PlusVertex based on the number of vertices in grpah
        vertex = new PlusVertex[g.size()];
        int i=0;
        for(Graph.Vertex v: g){
            vertex[i++] = new PlusVertex(v);
        }
    }
    
    /**
     * class to store intermediate values of time and cno between recursions
     */
    class DfsObjects{
        int time;
        int cno;

        public DfsObjects(int time, int cno) {
            this.time = time;
            this.cno = cno;
        }
    }
    
    
    /**
     * returns PlusVertex for given Graph.Vertex
     * @param v : Graph.Vertex whose PlusVertex is needed
     * @return PlusVertex
     */
    PlusVertex getPlusVertex(Graph.Vertex v){
        return vertex[v.name];
    }
    
    /**
     * topologicalOrder1 : function to order vertices in topological order by
     * removing vertices with no incoming edge along with its outgoing edges
     * @return list of vertices ordered topologically
     */
    public List<Graph.Vertex> topologicalOrder1(){
        
        int topNum = 0;
        //Queue to store the vertices whose inDegree is 0
        Queue<PlusVertex> q= new ArrayDeque<>();
        
        //list to store topological order
        List<Graph.Vertex> topList = new ArrayList<>();
        
        //looping through all vertices and adding the ones whose inDegree is 0 in the queue
        for(Graph.Vertex v:g){
            PlusVertex u = getPlusVertex(v);
            u.inDegree = u.revAdj.size();
            if(u.inDegree==0){
                q.add(u);
            }
        }
        
        //looping through the queue elements
        while(!q.isEmpty()){
            PlusVertex u = q.remove();
            u.top = ++topNum;
            topList.add(u.vertex);
            for(Graph.Edge e:u.adj){
                //pv is the Vertex on the other end of edge e
                PlusVertex v = getPlusVertex(e.otherEnd(u.vertex));
                //removing the edge e (same as decreasing inDegree count of v)
                v.inDegree--;
                //if no incoming edge left add it to the queue
                if(v.inDegree==0){
                    q.add(v);
                }
            }
        }
        
        /**
         * if the visited number of vertices is not equal to the total number of vertices in graph
         * then the graph is acyclic
         */
       
        if(topNum!=g.size()){
            return null;
        }   
        
        return topList;
        
    }
    
    /**
     * dfsVisit : recursive function for dfs traversal
     * @param u source to start DFS traversal
     * @param decFinList: linked list to add vertices in decreasing order of finish times
     * @param obj: DfsObjects containing: dis, cno between recursions
     * @return true if DAG
     */
    private boolean dfsVisit(PlusVertex u,LinkedList<Graph.Vertex> decFinList, DfsObjects obj){
        //mark u as seen
        u.seen = true;
        //save the time of discovery to the vertex's dis attribute
        u.dis = ++obj.time;
        //component number of the vertex(same as its parent)
        u.cno = obj.cno;
       
        //iterate through all the outgoing edges of u
        for(Graph.Edge e: u.adj){
            //get the other end of edge e, call it v
            PlusVertex v = getPlusVertex(e.otherEnd(u.vertex));
            /**if other end v, was discovered before u , is in the same path as u
             * and they belong to same component e is a back-edge
             */
            if(v.seen && v.fin==0 && v.cno==u.cno){
                return false;
            }
            //if no cylce then recursively call the dfs on v
            else if(!v.seen){
                //if dfsVisit returns false then graph is not a DAG then return false
                if(!dfsVisit(v, decFinList,obj))
                    return false;
            }
        }
        //add finish time for the node
        u.fin=++obj.time;
        //add the vertex at the beginning of linkedlist
        decFinList.addFirst(u.vertex);
        return true;
    }
    
    /**
     * topologicalOrder2 : function to order vertices by decreasing finish times of DFS
     * @return list of vertices in decreasing order of finish times
     */
    public List<Graph.Vertex> topologicalOrder2(){
        int time = 0;
        int cno = 0;
        
        //initializing store intermediate values of time and cno between recursions
        DfsObjects obj = new DfsObjects(time, cno);
        
        //list to store finishing order of DFS traversal
        LinkedList<Graph.Vertex> decFinList = new LinkedList<>();
         
        //call dfs on every vertex which is not seen before
        for(Graph.Vertex v:g){  
            PlusVertex pv= getPlusVertex(v);
            if(!pv.seen){
                //component number is increased to mark the next vertex as different component
                obj.cno++;
                
                //if dfsVisit returns false then graph is not a DAG then return null
                if(!dfsVisit(pv, decFinList, obj))
                    return null;             
            }
        }
        
        return decFinList;
    }
    
    /**
     * printing given list of vertices
     * @param topList : topologically ordered list
     */
    public void printTopologicalOrder(List<Graph.Vertex> topList){
        
        for(Graph.Vertex v:topList){
            //prining the vertex name
            System.out.print((v.name+1));
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph g = Graph.readDirectedGraph(in);
        
        TopologicalOrder t= new TopologicalOrder(g);
        List res1 = t.topologicalOrder1();
        
        System.out.println("Algorithm 1 Output:");
        if(res1==null)
            System.out.println("Not a DAG");
        else{           
            t.printTopologicalOrder(res1);
        }
        
        
        List<Graph.Vertex> res2 = t.topologicalOrder2();
        
        System.out.println("Algorithm 2 Output:");
        if(res2==null)
            System.out.println("Not a DAG");
        else{
            t.printTopologicalOrder(res2);
        }
        
        
    }
}
