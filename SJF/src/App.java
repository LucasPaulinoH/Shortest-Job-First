public class App {
    public static void main(String[] args) throws Exception {
        Process p1 = new Process();
        Process p2 = new Process();
        Process p3 = new Process();
        Process p4 = new Process();

        Heap heap = new Heap();

        heap.insert(p1);
        heap.insert(p2);
        heap.insert(p3);
        heap.insert(p4);

        heap.show();

        heap.poll();

        heap.show();

    }
}
