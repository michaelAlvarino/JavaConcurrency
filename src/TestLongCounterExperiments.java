// For week 1
// sestoft@itu.dk * 2014-08-21

public class TestLongCounterExperiments {
  public static void main(String[] args) {
    final thisLongCounter lc = new thisLongCounter();
    final int counts = 10_000_000;
    Thread t1 = new Thread(() -> {
      for (int i=0; i<counts; i++) 
	lc.count --;
    });
    Thread t2 = new Thread(() -> {
      for (int i=0; i<counts; i++) 
	lc.count++;
    });
    t1.start(); t2.start();
    try { t1.join(); t2.join(); }
    catch (InterruptedException exn) { 
      System.out.println("Some thread was interrupted");
    }
    System.out.println("Count is " + lc.get() + " and should be " + 0);
  }
}

class thisLongCounter {
  public long count = 0;
  public synchronized void increment() {
    count = count + 1;
  }
  public synchronized long get() {
    return count; 
  }
  public synchronized void decrement() { count = count - 1; }
}
