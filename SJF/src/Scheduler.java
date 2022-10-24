public class Scheduler {
    private int cpuTime = 3; // tempo de execução padrão dos processos na CPU
    private Heap heap;
    //private Timer timer;
    //private TimerTask task;

    public Scheduler() {
        this.heap = new Heap();
        //this.timer = new Timer();
        /*this.task = new TimerTask() {
            @Override
            public void run() {
                int numberOfProcesses;
                numberOfProcesses = heap.processes.size();
                try {
                    generateProcesses(numberOfProcesses * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };*/
    }

    public void generateProcesses(int number) throws InterruptedException {
        for (int i = 0; i < number; i++) {
            heap.insert(new Process(heap.processes.size() + 1));
        }
        heap.show();
    }

    /* MÉTODO DE EXECUÇÃO DOS PROCESSOS */
    public void runProcess(Process process) {
        int timeOfExecutionLeft = process.getExecutionTime();
        int executeFor;

        if (timeOfExecutionLeft < cpuTime)
            executeFor = timeOfExecutionLeft;
        else
            executeFor = cpuTime;

        try {
            /* laço usado para executar cada processo por três segundos */
            for (int i = 0; i < executeFor; i++) {
                process.showMessage();
                process.setExecutionTime(process.getExecutionTime() - 1); // decrementa o tempo de execução restante
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /* MÉTODO DE EXECUÇÃO DO ESCALONADOR */
    public void runScheduler() throws InterruptedException {
        generateProcesses(4); // cria os 4 processos iniciais
        // timer.scheduleAtFixedRate(task, 10000, 9000);
        while (heap.processes.isEmpty() != true) {
            Process currentProcess = heap.poll(); // seleção do processo de maior prioridade
            runProcess(currentProcess); // executa o processo
            if (currentProcess.getExecutionTime() > 0) {
                /*
                 * caso o processo não tenha terminado de executar, volta para a o fim da lista.
                 */
                heap.processes.add(currentProcess);
            } else {
                currentProcess.showFinalized();
            }
        }
    }
}
