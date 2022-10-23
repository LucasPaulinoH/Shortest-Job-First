public class Teste {
  public static void main(String[] args) {
    Process p1 = new Process("A");
    Process p2 = new Process("B");
    Process p3 = new Process("C");
    Process p4 = new Process("D");

    Heap heap = new Heap();

    heap.insert(p1);
    heap.insert(p2);
    heap.insert(p3);
    heap.insert(p4);

    heap.show();
    System.out.println("==========================");
    
    Scheduler scheduler = new Scheduler(heap);

    scheduler.runScheduler();

  }

}
