import java.util.ArrayList;
public class Heap {
  private ArrayList<Process> processes;

  public Heap() {
    processes = new ArrayList<Process>();
  }

  private int parent(int i) {
    if (i == 0) {
      return 0;
    }

    return (i - 1) / 2;
  }

  public void show(){
    System.out.println();
		
		for(int i = 0; i < this.processes.size(); i++) {
			
			System.out.print("[" + i + "] " + this.processes.get(i).getExecutionTime() + " ");
			
		}
		
		System.out.println();
  }


  private void swap(int x, int y){
    Process temporary = processes.get(x);
    processes.set(x, processes.get(y));
    processes.set(y, temporary);
  }

  private void moveProcessUp(int processIndex) {
    if (processIndex > 0
        && processes.get(parent(processIndex)).getExecutionTime() > processes.get(processIndex).getExecutionTime()) {
          swap(processIndex, parent(processIndex));
          moveProcessUp(parent(processIndex));
    }
  }

  public void insert(Process process) {
    processes.add(process);
    int index = processes.size() - 1;
    moveProcessUp(index);
  }

}
