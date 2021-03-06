import DataStructureImpl.Edge;
import DataStructureImpl.EdgeWeightGraph;
import DataStructureImpl.IndexMinPQ;

/**
 *
 * 基于延时实现上去掉了优先队列的所有的失效边，而是只保存最短的失效边
 * Created by alan on 16/12/25.
 */
public class PrimMST {

    private Edge[] edgeTo;      //距离树最近的边
    private double[] distTo;    //distTo[w]=edgeTo[w].weight()
    private boolean[] marked;   //如果v在树中则为true
    private IndexMinPQ<Double> pq;  //有效的横切边


    public PrimMST(EdgeWeightGraph G){
        edgeTo=new Edge[G.V()];
        distTo=new double[G.V()];
        marked=new boolean[G.V()];

        for (int v=0;v<G.V();v++)
            distTo[v]=Double.POSITIVE_INFINITY;
        pq=new IndexMinPQ<Double>(G.V());

        distTo[0]=0.0;
        pq.insert(0,0.0);
        while (!pq.isEmpty()){
            visit(G,pq.delMin());
        }
    }

    private void visit(EdgeWeightGraph G, int v) {
        marked[v]=true;
        for (Edge e:G.adj(v)){
            int w=e.other(v);

            if(marked[w]) continue;
            if(e.weight()<distTo[w]){
                edgeTo[w]=e;
                distTo[w]=e.weight();
                if(pq.contains(w))
                    pq.change(w,distTo[w]);
                else
                    pq.insert(w,distTo[w]);
            }

        }
    }

}
