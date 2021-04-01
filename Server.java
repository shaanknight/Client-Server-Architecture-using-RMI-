import java.rmi.Naming; 

public class Server
{ 
	public Server(String port) 
	{ 
		try
		{ 
			//Create a object reference for the interface 
			Mst obj = new Mst_Implementation(); 

			// to avoid writing rmiregistry command on terminal
			java.rmi.registry.LocateRegistry.createRegistry(Integer.parseInt(port));
			
			//Bind the localhost with the service 
			Naming.rebind("rmi://localhost:"+port+"/MstService",obj); 
		} 
		catch(Exception e)
		{ 
			// If any error occur
			System.out.println("ERR: "+e); 
		} 
	} 

	public static void main(String[] args) 
	{ 
		// there must be one arguement passed as port
		new Server(args[0]); 
	} 
} 
