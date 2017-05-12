/**
 * Created by michael on 5/12/17.
 */
public class SynchronizedPrinter{
    public static void main(String args[]){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                System.out.println("interrupted, shutdown hook called");
            }
        });
        myPrinter2 p = new myPrinter2();
        Thread t1 = new Thread(() -> {
            while(p.getmine() > -500) {
                //try{ Thread.sleep(100); } catch (InterruptedException e){}
                myPrinter2.print();
                p.decrement();
                Integer mine = p.getmine();
                System.out.println("my value " + mine.toString());
            }
        });
        Thread t2 = new Thread(() -> {
            while(p.getmine() < 500) {
                //try{ Thread.sleep(100); }catch(InterruptedException e){}
                myPrinter2.print();
                p.increment();
                Integer mine = p.getmine();
                System.out.println("my value " + mine.toString());
            }
        });
        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }
        // does not catch sigint interrupt?
        catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println(p.getmine());
    }
}
class myPrinter2 {
    public int mine=0;
    // adding synchronized to static makes all instances
    public static synchronized void print() {
        System.out.print("-");
        try { Thread.sleep(50); } catch (InterruptedException e) {}
        // can't reference a non-static field in a static method
        // mine++;
        System.out.print("|");
    }
    public void increment(){ mine++; }
    public void decrement(){ mine--; }
    public int getmine(){return mine;}
}
