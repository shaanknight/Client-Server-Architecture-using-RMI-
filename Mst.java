import java.rmi.RemoteException;

public interface Mst extends java.rmi.Remote
{ 
	public void add_graph(String graph_id,int n)
			throws java.rmi.RemoteException; 

	public void add_edge(String graph_id,int u,int v,int wgt) 
			throws java.rmi.RemoteException; 

	public long get_mst(String graph_id)
			throws java.rmi.RemoteException; 
}