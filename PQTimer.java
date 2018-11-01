package data_structures;

public class PQTimer {
    public static void main(String [] args) {
        int iterations = 10;  // number of timing tests
        int workSize = 10000;   // size of add/remove cycle loop
        int structureSize = 100;  // initial size of PQ, doubles with each iteration
        int loopStructureSize = structureSize; // helper var
        
        long start = 0, stop = 0;
        long [] insertTimes = new long[iterations]; // arrays to hold results
        long [] cycleTimes = new long[iterations];
        long [] removeTimes = new long[iterations];
                        
        PriorityQueue<Integer> pq = 
                new OrderedArrayPriorityQueue<Integer>(512000);
               //new UnorderedArrayPriorityQueue<Integer>(512000);
                // new OrderedLinkedListPriorityQueue<Integer>();
                //new UnorderedLinkedListPriorityQueue<Integer>();                                                
        
        for(int i=0; i < iterations; i++) {
            // build structure first
            pq.clear();
            start = System.nanoTime();   // capture time to build       
            for(int j=0; j < structureSize; j++) {
                int x = (int) (Integer.MAX_VALUE * Math.random());
                pq.insert(x);
                }
            stop = System.nanoTime();
            insertTimes[i] = (stop-start);                
                
            // time for add/remove cycles   
            start = System.nanoTime();  
            for(int j=0; j < workSize; j++) {
                int x = (int) (Integer.MAX_VALUE * Math.random());
                pq.insert(x);            
                pq.remove();
                }
            stop = System.nanoTime();
            cycleTimes[i] = (stop-start);
            
            // time for removal (dequeue) operations
            start = System.nanoTime();
            while(!pq.isEmpty())
                pq.remove();                
            stop = System.nanoTime();
            removeTimes[i] = (stop-start);
            structureSize <<= 1;            
            System.out.println("Loop " + (i+1) + " of " + iterations + " finished.");
            }
         
        // print results   
        int tmp = loopStructureSize;
        System.out.println("\nINSERTION TIMES:");
        for(int i=0; i < iterations; i++) {
            System.out.println("n=" + tmp + "  Time: " + insertTimes[i]);
            tmp <<= 1;
            }
        tmp = loopStructureSize;    
        System.out.println("\nADD/DELETE CYCLE TIMES:");
        for(int i=0; i < iterations; i++) {
            System.out.println("n=" + tmp + "  Time: " + cycleTimes[i]);
            tmp <<= 1;
            }
        tmp = loopStructureSize;   
        System.out.println("\nRemoval TIMES:");
        for(int i=0; i < iterations; i++) {
            System.out.println("n=" + tmp + "  Time: " + removeTimes[i]);  
            tmp <<= 1;
            }                                         
        }
    }