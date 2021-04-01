# Client-Server Architecture using RMI 
## DISTRIBUTED SYSTEMS
### Sayantan Jana


#### Implementation for a Single Server Architecture with support for Multiple Clients
##### Problem : Dynamic addition of edges and graphs in memory of server and querrying for the total of Minimum Spanning Tree for a graph 
ANALYSIS :
1) Architecture : Remote Method Invocation used for building client-server architecture
> Interface Program : A remote interface created, Mst , that holds all the function declarations that are required to perform all the queries . 
> Implementation of Interface : Done in Mst_Implementation that has a public class and holds the entire implementation for performing the queries .
> Server Class : For hosting a service, the server program is created whereby using java.rmi.Naming.rebind() method can be called which takes two arguments i.e., an object reference (service name) and instances reference.
> Client Class : Client program will invokes java.rmi.Naming.lookup() method for RMI URL and returns an instance of object type (Factorial Interface). All the RMI is done on this subject .

2) Algorithm Implementation of Minimum Spanning tree.
> Minimum Spanning Tree is found by the use of Kruskal's ALgorithm.
> The function immediately returns -1 if the total no. of edges is  less than N-1 .
> The function returns a cost function only when there are N-1 edges in the spanning tree found.

3) Results and Observations
> For a single graph with N = 100000 edges and M ~ 100000 edges input and final one query , time taken to run : 3.6 seconds with path compression used and 3.84 seconds without path compression.
> For the following testfile fed to the client, time taken to run :
test  :
input : https://drive.google.com/file/d/1V3QSiDZmKVCYn7dhhhngxLRDDor0yLwu/view?usp=sharing
output : https://drive.google.com/file/d/1FWhq3TgIFnfQ8sQQg6TO0ti3IDZjrZ7C/view?usp=sharing
time taken : ~3 minutes














   
 
