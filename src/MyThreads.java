/**
 * Created by michael on 5/11/17.
 */
public class MyThreads{
    private static class threads implements Runnable{
        int j = 0;
        public void run(){
            String tname = Thread.currentThread().getName();
            // must take String object
            System.out.format("%s %s%n", "running thread: ", tname);
            for(int i = 0; i < 15; i++){
                System.out.format("%s %d%n", "counter at", i++);
            }
        }
    }

    public static void main(String args[]){
        int num_threads = 2;
        if(args.length > 0){
            num_threads = Integer.parseInt(args[0]);
        }
        Thread t1 = new Thread(new threads());
        Thread t2 = new Thread(new threads());
        t1.start(); t2.start();
    }
}
