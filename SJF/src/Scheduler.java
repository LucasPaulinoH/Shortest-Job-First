public class Scheduler {
    private static int TIME_OF_EXECUTION_CPU = 3;
    private Heap heap;

    public Scheduler(Heap heap) {
        this.heap = heap;
    }

    public void runScheduler() {
        while (!heap.processes.isEmpty()) {
            Process currentProcess = heap.poll();
            runProcess(currentProcess);
            if (currentProcess.getExecutionTime() > 0) {
                heap.processes.add(currentProcess);
            }
        }
    }

    public void runProcess(Process process) {
        int timeOfExecutionLeft = process.getExecutionTime();
        int executeFor;

        if (timeOfExecutionLeft < TIME_OF_EXECUTION_CPU)
            executeFor = timeOfExecutionLeft;
        else
            executeFor = TIME_OF_EXECUTION_CPU;

        try {
            for (int i = 0; i < executeFor; i++) {
                process.showMessage();
                Thread.sleep(1000);
                process.setExecutionTime(process.getExecutionTime() - 1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
