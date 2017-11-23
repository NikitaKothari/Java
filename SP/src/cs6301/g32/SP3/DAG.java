package cs6301.g32.SP3;

import java.util.Scanner;

/**
 * class to check if given graph is DAG or not using DFS
 * @author Vinaya, Pradeep, Mohan, Nikita
 * @version 1.0: 2017/09/14
 */
public class DAG {
    Graph g;
    
    PlusVertex vertex[]; 
  
    DAG(Graph g){
        this.g=g;
        //initializing PlusVertex based on the number of vertices in grpah
        vertex = new PlusVertex[g.size()];
        int i=0;
        for(Graph.Vertex v: g){
            vertex[i++] = new PlusVertex(v);
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
     * dfsVisit : recursive function for dfs traversal
     * @param u source to start DFS traversal
     * @param time: time of discovery of its parent
     * @param cno: component number of its parent
     * @return true if DAG
     */
    private boolean dfsVisit(PlusVertex u,int time,int cno){
        //mark u as seen
        u.seen = true;
        //save the time of discovery to the vertex's dis attribute
        u.dis = ++time;
        //component number of the vertex(same as its parent)
        u.cno = cno;
       
        //iterate through all the outgoing edges of u
        for(Graph.Edge e: u.adj){
            //get the other end of edge e, call it v
            PlusVertex v = getPlusVertex(e.otherEnd(u.vertex));
            /**if other end v, was discovered before u 
             * and they belong to same component e is a back-edge
             */
            if(v.dis<u.dis && v.cno==u.cno){
                return false;
            }
            //if no cylce then recursively call the dfs on v
            else if(!v.seen){
                //if any of the adjacent vertices discover a cycle, return false
                if(!dfsVisit(v, time,cno))
                    return false;
            }
        }  
        return true;
        
    }
    
    /**
     * helper function for dfs()
     * @return true if DAG
     */
    boolean dfs(){
        //initializing time and component number
        int time = 0;
        int cno =0;
        
        //call dfs on every vertex which is not seen before
        for(Graph.Vertex v:g){  
            PlusVertex dg= getPlusVertex(v);
            if(!dg.seen){
                //component number is increased to mark the next vertex as different component
                cno++;
                
                //if any component returned with cycles then return false
                if(!dfsVisit(dg, time, cno))
                    return false;
            }
        }
        
        return true;
    }
    
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        Graph g = Graph.readDirectedGraph(in);
        
        DAG dag= new DAG(g);
        System.out.println("The graph is a DAG?:"+dag.dfs());
        
    }
}

/********************
 * Sample Output $ java cs6301.g32.DAG 16 16 2 1 1 1 3 1 2 4 1 2 5 1 2 6 1 5 7 1 5 8 1 8 9 1 3 10 1 10 11 1 10 12 1 11 13 1 11 15 1 15 16 1 11 14 1 16 2 1
 * The graph is a DAG?:false
 */