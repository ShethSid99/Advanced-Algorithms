package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static int M = 4000;
    public static int N = 5000;
    private static final int[] D = {10,50,100,200,400,600,800,1000};
    static int npcount =0;
    public static void main(String[] args) {
        for (final int d : D) {
            System.out.printf("M: %d,N: %d,d: %d\n", M, N, d);

            final int[] A = generateRandomNumbers(M);
            /*for (final int d1 : A) {
                System.out.printf("M: %d,N: %d,d: %d\n", M, N, d1);
            }*/
            //System.out.println(Arrays.toString(A));
            final int[] B =  RegenerateRandomNumbers(A, N, d);
            //System.out.println(Arrays.toString(B));

            MyersEditDistance(A,B);
            System.out.println("Edit Distance: " +MillerEditDiatnce(A, B));
            System.out.println("\n");
        }

   }

   public static int[] generateRandomNumbers(final int length) {
        if (length < 0) {
            System.out.println("Negative values is not acceptabe for the Length");
            System.exit(0);
        }
        int[] arr = new int[length];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt();
        }
    return arr;
    }

    private static int[] RegenerateRandomNumbers(int[] sequence, int length, int Deletion) {
        if ((length < 0) || (sequence.length < 0)) {
            System.out.println("Negative values is not acceptabe for the Length");
            System.exit(0);
        }
           int s = (length - sequence.length) + Deletion;
            //System.out.println("For B random numbers generating" +s);

            final List<Integer> list = new ArrayList<Integer>(sequence.length);
            Random rand = new Random();
            for (final int ele : sequence) {
                list.add(ele);
            }
            for (int i = 0; i < Deletion; i++) {
                list.remove(rand.nextInt(list.size()));
            }
            for (int i = 0; i < s; i++) {
                list.add(rand.nextInt(list.size()), rand.nextInt());
            }
            int[] newArr = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                newArr[i] = list.get(i);
            }
        return newArr;
    }

    private static int MyersEditDistance(final int[] A, int[] B) {
        int numComparisons=0;
        final int M = A.length;
        final int N = B.length;

        int sed = M + N;

        final int MAX = M + N;

        final int [] V = new int [(MAX * 2 ) + 1];

        V[MAX + 1] = 0;
        Myersgraph:
        for(int D =0; D<= MAX; D++){
            for(int K = -D; K <= D; K+=2 ){
                int x;
                if(K == -D || K != D && V[K + MAX - 1] < V[K + MAX +1]){
                    x = V[K + MAX +1];
                }
                else {
                    x = V[K + MAX - 1] +1;
                }
                int y = x - K;
                //System.out.printf("x: %d, y: %d, K: %d, M: %d, N: %d\n", x,y,K,M,N);
                while (x < M && y < N && A[x] == B[y]){
                    x++;
                    y++;
                    numComparisons++;
                }

                V[K + MAX] = x;

                if (x >= M && y >= N){
                    sed = D;
                    break Myersgraph;
                }
                numComparisons++;
            }
            numComparisons++;
        }
        System.out.println("O(ND) num comparison: " +numComparisons);
        //System.out.println("O(ND) SED: " +sed);
        return sed;
    }


    public static int MillerEditDiatnce(int[] A, int[] B) {
        //int numComparisons=0;

        int p = -1;
        int size = M + N + 1;
        final int M = A.length;
        final int N = B.length;
        int offset = M + 1;
        int delta = N-M;

        int[] fp = new int [size];
        Arrays.fill(fp,-1);

        do{
            ++p;
            for(int k= -p; k <= delta-1; k++){
                fp[k + offset] = snake(A,B, k, fp[(k - 1) + offset] +1, fp[k+1+offset]);
            }
            for(int k=delta+p; k>= delta+1; --k){
                fp[k + offset] = snake(A, B, k,fp[(k - 1) + offset]+1,fp[k+1+offset]);
            }
            fp[delta + offset] = snake(A, B, delta,fp[delta-1+offset]+1,fp[delta+1+offset]);
        } while(fp[delta+offset] != N);
        System.out.println("O(NP) num comparison: " +npcount);
        npcount=0;
        return delta + 2 * p;
    }

    private static int snake(int[] Q, int [] W, int k, int s, int ss){
        int y= Math.max(s,ss);
        int x = y - k;
        int numComparisons=0;
        while (x < M && y < N && Q[x] == W[y]){
            ++x;
            ++y;
            numComparisons++;
        }
        numComparisons++;
        npcount=npcount+numComparisons;
        return y;
    }


}