/**
 * Created by michael on 5/12/17.
 */
public class SynchronizedCounterRunner {

    public static void main(String args[]){
        int count = 2;

        SynchronizedCounter counter = new SynchronizedCounter();
        System.out.println("Creating new counter");

        if (args.length > 0){
            count = Integer.parseInt(args[1]);
        }

        Thread[] t = new Thread[count];

        for(int i = 0; i < count; i++){
            System.out.println("Creating new threaded() object");
            if(i % 2 == 0)
                t[i] = new Thread(new threaded(counter, "decrementor"));
            else
                t[i] = new Thread(new threaded(counter, "incrementor"));
        }

        for(Thread j : t){
            j.start();
        }

        try {
            for (Thread j : t) {
                j.join();
            }
        } catch(InterruptedException e) {
            System.out.println("Stop interrupting main!");
        }

        System.out.format("%s %d%n", "final counter count is ", counter.value());
    }
}

class SynchronizedCounter {

    public int c;

    public void increment() {
        System.out.format("%s %s%n", "thread is", Thread.currentThread().getName());
        c++;
    }

    public void decrement() {
        System.out.format("%s %s%n", "thread is", Thread.currentThread().getName());
        c--;
    }

    public int value() {
        System.out.format("%s %s%n", "thread is", Thread.currentThread().getName());
        return c;
    }
}

class threaded implements Runnable {

    SynchronizedCounter counter = null;
    String type;

    public threaded(){
        System.out.println("New threaded object created");
    }
    public threaded(SynchronizedCounter counter){
        this.counter = counter;
    }
    public threaded(SynchronizedCounter counter, String type){
        this.counter = counter;
        this.type = type;
    }

    public void run(){
        try {
            for (int j = 0; j < 500; j++) {
                Thread.sleep(0);
                if (counter != null && type == "incrementor") {
                    counter.increment();
                    //System.out.format("%s %d%n", "Counter count is: ", counter.value());
                } else if (counter != null && type == "decrementor") {
                    counter.decrement();
                    //System.out.format("%s %d%n", "Counter count is: ", counter.value());
                }
            }
        } catch (InterruptedException e){
            System.out.println("stop interrupting me");
        }
    }
}
