import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Process> processesList = new ArrayList<>();
    private static int DEFAULT_PROCESS_EXECUTION_TIME = 3; 

    public Scheduler() {
        super();
    }

    public void executeSimulation() {
        createProcesses(4);

        int iterationIndex = 0;
        while(!processesList.isEmpty()) {
            Process higherPriorityProcess = removeFirst();
            if(iterationIndex == 3) waitOneSecondToCreateMoreProcesses(8);
            if(iterationIndex == 6) waitOneSecondToCreateMoreProcesses(16);

            executeProcess(higherPriorityProcess, iterationIndex == 3 || iterationIndex == 6);
            if(!higherPriorityProcess.hasFullyExecuted()) add(higherPriorityProcess);

            iterationIndex++;
        }
    }

    private void createProcesses(int quantity) {
        for(int ind=0 ; ind<quantity ; ind++) {
            add(new Process());
        }
    }

    private void waitOneSecondToCreateMoreProcesses(int processesQuantity) {
        try {
            Thread.sleep(1000);
            createProcesses(processesQuantity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeProcess(Process process, boolean sleepOneLessSecond) {
        int processRemainingTime = process.getRemainingTime();
        int executionTime = 
            processRemainingTime < DEFAULT_PROCESS_EXECUTION_TIME ? 
            processRemainingTime : DEFAULT_PROCESS_EXECUTION_TIME;

        try {
            process.showHelloMessage();
            Thread.sleep((executionTime - (sleepOneLessSecond ? 1 : 0)) * 1000);
            process.run(executionTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void add(Process newProcess) {
        processesList.add(newProcess);
        riseProcess(processesList.size() - 1);
    }

    private Process getProcessAtPosition(int index) {
        if(index >= processesList.size() || index < 0) return null;
        return processesList.get(index);
    }

    private Process removeFirst() {
        if(processesList.size() == 0) return null;

        int lowerPriorityProcessIndex = processesList.size() - 1;
        Process higherPriorityProcess = getProcessAtPosition(0);
        Process lowerPriorityProcess = getProcessAtPosition(
            lowerPriorityProcessIndex
        );

        processesList.set(0, lowerPriorityProcess);
        processesList.remove(lowerPriorityProcessIndex);
        descendProcess(0);

        return higherPriorityProcess;
    }

    private void exchangePositions(int firstIndex, int secondIndex) {
        Process firstProcess = getProcessAtPosition(firstIndex);
        Process secondProcess = getProcessAtPosition(secondIndex);

        processesList.set(firstIndex, secondProcess);
        processesList.set(secondIndex, firstProcess);
    }

    private void riseProcess(int processIndex) {
        if(processIndex == 0) return;

        Process process = getProcessAtPosition(processIndex);
        int parentIndex = (processIndex + 1)/2 - 1;
        Process parentProcess = getProcessAtPosition(parentIndex);

        if(process.hasBiggerPriorityThan(parentProcess)) {
            exchangePositions(processIndex, parentIndex);
            riseProcess(parentIndex);
        }
    }

    private void descendProcess(int processIndex) {
        int leftChildIndex = (processIndex + 1) * 2 - 1;
        if(leftChildIndex > processesList.size() - 1) return;

        Process leftChild = getProcessAtPosition(leftChildIndex);
        int rightChildIndex = leftChildIndex + 1;
        Process rightChild = getProcessAtPosition(rightChildIndex);

        boolean leftChildHasBiggerPriority = leftChild.hasBiggerPriorityThan(rightChild);
        int higherPriorityChildIndex = 
            leftChildHasBiggerPriority ? leftChildIndex : rightChildIndex;
        Process higherPriorityChildProcess = 
            leftChildHasBiggerPriority ? leftChild : rightChild;

        Process process = getProcessAtPosition(processIndex);
        boolean needsExchange = higherPriorityChildProcess.hasBiggerPriorityThan(process);
        if(needsExchange) {
            exchangePositions(processIndex, higherPriorityChildIndex);
            descendProcess(higherPriorityChildIndex);
        }
    }
}
