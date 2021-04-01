import java.util.*;
import java.net.MalformedURLException; 
import java.rmi.Naming; 
import java.rmi.NotBoundException; 
import java.rmi.RemoteException; 

public class Client { 
	public static void main(String[] args) 
	{ 

		try { 

			String server_ip = args[0];
			String server_port = args[1];

			// Create an remote object with the same name 
			// Cast the lookup result to the interface 
			Mst obj = (Mst) Naming.lookup("rmi://"+server_ip+":"+server_port+"/MstService"); 

			Scanner sc = new Scanner(System.in);
			while (sc.hasNext()) {
			    
			    String query = sc.nextLine();
			    if(query == null) break;
			    if(query.isEmpty()) continue;

			    String[] tokens = query.split(" ",5);
			    String request = tokens[0];
			    String graph_id = tokens[1];
			    char req = request.charAt(4);
			    
			    if(req == 'g')
			    {
			    	int n = Integer.parseInt(tokens[2]);
			    	obj.add_graph(graph_id,n);
			    }
			    else if(req == 'e')
			    {
			    	int u = Integer.parseInt(tokens[2]);
			    	int v = Integer.parseInt(tokens[3]);
			    	int wgt = Integer.parseInt(tokens[4]);
			    	obj.add_edge( graph_id, u, v, wgt);
			    }
			    else
			    {
			    	System.out.println(obj.get_mst(graph_id));
			    }
			}
		} 

		// If any error occur 
		catch (MalformedURLException murle) { 
			System.out.println("\nMalformedURLException: "
							+ murle); 
		} 

		catch (RemoteException re) { 
			System.out.println("\nRemoteException: "
							+ re); 
		} 

		catch (NotBoundException nbe) { 
			System.out.println("\nNotBoundException: "
							+ nbe); 
		} 

		catch (java.lang.ArithmeticException ae) { 
			System.out.println("\nArithmeticException: " + ae); 
		} 
	} 
} 

