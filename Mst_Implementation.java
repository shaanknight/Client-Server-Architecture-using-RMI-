import java.util.*; 

public class Mst_Implementation
    extends java.rmi.server.UnicastRemoteObject 
    implements Mst
{
    class edge
    {
        int u;
        int v;
        int wgt;
    }

    class Graph 
    { 
    	int N; 
    	LinkedList<edge> adj;
        int[] pr;
        int[] sz; 
    
    	Graph(int N) 
    	{ 
    		this.N = N;
    		adj = new LinkedList<>(); 
            pr = new int[N+1];
            sz = new int[N+1];
            for(int i = 1; i <= N; ++i)
            {
                pr[i] = i;
                sz[i] = 0;
            }
    	} 
    } 

    void print_graph(Graph graph) 
    {    
         for(edge ed:graph.adj)
            System.out.println(ed.u + " " + ed.v + " " + ed.wgt);
    }

	void add_edge_graph(Graph graph, int u, int v, int wgt) 
	{ 
        edge ed = new edge();
        ed.u = u;
        ed.v = v;
        ed.wgt = wgt;
        graph.adj.add(ed);
	}  

    class sort_by_weight implements Comparator<edge> 
    { 
        public int compare(edge a, edge b) 
        { 
            return a.wgt - b.wgt; 
        } 
    } 

    long find_mst(Graph graph)
    {
        Collections.sort(graph.adj , new sort_by_weight()); 
        
        int edges = graph.adj.size();
        if(edges < graph.N - 1)
            return -1;
        
        for(int i = 1; i <= graph.N; ++i)
        {
            graph.pr[i] = i;
            graph.sz[i] = 0;
        }

        long cost = 0;
        int m = 0;
        for(edge ed:graph.adj)
        {
            int u = ed.u;
            int v = ed.v;
            int w = ed.wgt;

            int tmp = u;

            while(graph.pr[u] != u) 
                u = graph.pr[u];

            while(graph.pr[tmp] != tmp)
            {
                int rd = graph.pr[tmp];
                graph.pr[tmp] = u;
                tmp = rd;
            }

            tmp = v;
            
            while(graph.pr[v] != v) 
                v = graph.pr[v];

            while(graph.pr[tmp] != tmp)
            {
                int rd = graph.pr[tmp];
                graph.pr[tmp] = v;
                tmp = rd;
            }

            if(u != v)
            {
                m++;
                cost += w;
                if( graph.sz[u] < graph.sz[v])
                {
                    graph.pr[u] = v;
                    graph.sz[v] += graph.sz[u];
                }
                else
                {
                    graph.pr[v] = u;
                    graph.sz[u] += graph.sz[v];
                }
            }
            if(m == graph.N-1)
                break;
        }

        if(m == graph.N - 1)
            return cost;
        else
            return -1;

        // int dsu_root = graph.pr[1];
        // while(graph.pr[dsu_root] != dsu_root) 
        //     dsu_root = graph.pr[dsu_root];

        // for(int i = 2; i <= graph.N; ++i)
        // {
        //     int loc_root = graph.pr[i];
        //     while(graph.pr[loc_root] != loc_root) 
        //         loc_root = graph.pr[loc_root];
            
        //     if(dsu_root != loc_root)
        //     {
        //         // System.out.println(-1);
        //         return -1;
        //     }
        // }
        // System.out.println(cost);
        // return cost;
    }

    // public static void main(String[] args)
    // {
    //     Scanner sc = new Scanner(System.in); 
    //     int n = sc.nextInt();
    //     Graph graph = new Graph(n);
    //     get_mst(graph);
        
    //     int m = sc.nextInt();
    //     for(int i = 1; i <= m; ++i)
    //     {
    //         int u = sc.nextInt();
    //         int v = sc.nextInt();
    //         int wgt = sc.nextInt();
    //         add_edge(graph, u, v, wgt); 
    //     }

    //     get_mst(graph);
    // }
 
    public Mst_Implementation() 
        throws java.rmi.RemoteException 
    { 
        super(); 
    }

    List<Graph> graphs = new ArrayList<Graph>();
    Map<String, Integer> graph_ids = new HashMap<String, Integer>();

    public void add_graph(String graph_id,int n)
        throws java.rmi.RemoteException
    {
        Graph graph = new Graph(n);
        graphs.add(graph);
        int sz = graphs.size();
        graph_ids.put(graph_id,sz-1);
    }

    public void add_edge(String graph_id,int u,int v,int wgt) 
        throws java.rmi.RemoteException
    {
        int id = graph_ids.get(graph_id);
        Graph graph = graphs.get(id);
        add_edge_graph( graph, u, v, wgt); 
    }

    public long get_mst(String graph_id) 
        throws java.rmi.RemoteException
    {
        int id = graph_ids.get(graph_id);
        Graph graph = graphs.get(id);
        return find_mst(graph);
    }

}
