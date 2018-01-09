import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BellmanFord {
    static int s;
    static int t;
    static double[] dist;
    static double[][] w;
    static int numOfEdges;
    static int numOfNodes;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data10.txt"));
        numOfNodes = Integer.parseInt(reader.readLine());

        w = new double[numOfNodes][numOfNodes];
        dist = new double[numOfNodes];

        System.out.println("Node ���� : " + numOfNodes);
        StringTokenizer st1 = new StringTokenizer(reader.readLine(), " ");
        s = Integer.parseInt(st1.nextToken());
        t = Integer.parseInt(st1.nextToken());
        numOfEdges = Integer.parseInt(reader.readLine());

        for (int u = 0; u < numOfNodes; u++) {
            for (int v = 0; v < numOfNodes; v++) {
                if(u == v)
                    w[u][v] = 0;
                else
                    w[u][v] = Double.POSITIVE_INFINITY;
            }
        }
        while (reader.ready()) {
            StringTokenizer st2 = new StringTokenizer(reader.readLine(), " ");
            w[Integer.parseInt(st2.nextToken())][Integer.parseInt(st2.nextToken())] = Integer.parseInt(st2.nextToken());
        }

        if(shortestPath()){
            System.out.println("Negative Cycle �� �����մϴ�.");
            System.out.println("Cycle�� ����� ���� s -> t �ִܰŸ� : " + dist[0] + " "+ dist[1] + " "+ dist[2] + " "+ dist[3] + " "+ dist[4] + " "+ dist[5]);
        }else{
            System.out.println("s -> t �ִܰŸ� : " + dist[0] + " "+ dist[1] + " "+ dist[2] + " "+ dist[3] + " "+ dist[4] + " "+ dist[5]);
        }


    }

    private static boolean shortestPath() {

        for(int v = 0; v < numOfNodes; v++)
            dist[v] = Double.POSITIVE_INFINITY;
        dist[t] = 0;

        for(int i = 1; i < numOfNodes; i++){
            double[] tempDist = new double[numOfNodes];
            System.arraycopy(dist, 0, tempDist, 0, dist.length);
            for(int u = 0; u < numOfNodes; u++){
                if(i == 1){
                    tempDist[u] = w[u][t];
                }
                else {
                    for(int v = 0; v < numOfNodes; v++){
                        if(w[u][v] != Double.POSITIVE_INFINITY && tempDist[u] > dist[v] + w[u][v])
                            tempDist[u] = dist[v] + w[u][v];
                    }
                }
            }
            System.arraycopy(tempDist, 0, dist, 0, tempDist.length);
        }

        for(int u = 0; u < numOfNodes; u++) {
            for (int v = 0; v < numOfNodes; v++){
                if(w[u][v] != Double.POSITIVE_INFINITY && dist[u] > dist[v] + w[u][v])
                    return true;
            }
        }
        return false;
    }
}

