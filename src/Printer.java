/**
 * Created by michael on 5/12/17.
 */
public class Printer{
    public static void main(String args[]){
        myPrinter p = new myPrinter();
        Thread t1 = new Thread(() -> {
            while(true)
                p.print();
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            while(true)
                p.print();
        });
        t2.start();
    }
}
class myPrinter {
    public void print() {
        System.out.print("-");
        try { Thread.sleep(50); } catch (InterruptedException e) {}
        System.out.print("|");
    }
}
