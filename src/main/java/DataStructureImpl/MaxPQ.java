package DataStructureImpl;


import java.util.Iterator;

/**
 * 基于堆的优先队列：数组实现
 * Created by alan on 16/12/5.
 */
public class MaxPQ<Key extends  Comparable<Key>>{

    private Key[] pq;
    private int N=0;

    public MaxPQ(int n) {
        pq= (Key[]) new Comparable[n+1];
    }

    /**
     * 上浮操作
     * @param k
     */
    public void swim(int k){
        while (less(k/2,k) && k>1){
            exch(pq,k/2,k);
            k=k/2;
        }
    }

    /**
     * 下沉操作
     * @param k
     */
    public void sink(int k){
        while (2*k<N){
            int j=2*k;
            if(j<N&&less(j,j+1))
                j++;
            if(!less(k,j)) break;
            exch(pq,k,j);
            k=j;
        }
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    /**
     * 插入元素
     * @param v
     */
    public void insert(Key v){
        pq[++N]=v;
        swim(N);
    }

    /**
     * 删除最大元素，以为index=0位置弃用，index=1 为根元素最大.
     * @return
     */
    public Key delMax(){
        Key max=pq[1];
        exch(pq,1,N--);
        pq[N+1]=null;//防止对象游离
        sink(1);
        return max;
    }

    public boolean less(int a, int b){
        if(pq[a]==null||pq[b]==null) return false;
        return pq[a].compareTo(pq[b]) < 0;
    }

    public void exch(Comparable a[],int i,int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }


    public void foreach(){
        StringBuilder sb=new StringBuilder("[");
        for (int i=1;i<=N;i++){
            sb.append(pq[i]+",");
        }
        int end=sb.lastIndexOf(",");
        sb.replace(end,end+1,"]");
        System.out.println(sb.toString());
    }

}
