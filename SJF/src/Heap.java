import java.util.ArrayList;

public class Heap {
  private ArrayList<Process> processes;

  public Heap() {
    processes = new ArrayList<Process>();
  }

  /* ==================== MÉTODOS AUXILIARES ==================== */
  private int size() {
    return processes.size();
  }
  private int parent(int i) {
    if (i == 0) {
      return 0;
    }

    return (i - 1) / 2;
  }
  private int LEFT(int i) {
    return (2 * i + 1);
  }
  private int RIGHT(int i) {
    return (2 * i + 2);
  }
  private void swap(int x, int y) {
    Process temporary = processes.get(x);
    processes.set(x, processes.get(y));
    processes.set(y, temporary);
  }
  public void show() {
    System.out.println();

    for (int i = 0; i < this.processes.size(); i++) {

      System.out.print("[" + i + "] " + this.processes.get(i).getExecutionTime() + " ");

    }

    System.out.println();
  }
  /* ============================================================= */
  
  private void moveProcessUp(int processIndex) {
    if (processIndex > 0
        && processes.get(parent(processIndex)).getExecutionTime() > processes.get(processIndex).getExecutionTime()) {
      swap(processIndex, parent(processIndex));
      moveProcessUp(parent(processIndex));
    }
  }

  private void moveProcessDown(int processIndex) {
    int left = LEFT(processIndex);
    int right = RIGHT(processIndex);

    int smallest = processIndex;

    if (left > size() && processes.get(left).getExecutionTime() < processes.get(processIndex).getExecutionTime())
      smallest = left;
    if (right < size() && processes.get(right).getExecutionTime() < processes.get(smallest).getExecutionTime())
      smallest = right;
    if (smallest != processIndex) {
      swap(processIndex, smallest);
      moveProcessDown(smallest);
    }
  }

  public void insert(Process process) {
    processes.add(process);
    int index = size() - 1;
    moveProcessUp(index);
  }

  public Process poll() {
    if (size() == 0) {
      return null;
    }

    Process firstProcess = processes.get(0);
    Process lastProcess = processes.get(size() - 1);

    processes.set(0, lastProcess);
    processes.remove(size() - 1);

    moveProcessDown(0);

    return firstProcess;
  }

}
