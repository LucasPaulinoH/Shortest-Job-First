import java.util.Random;

public class Process {
  private int id, executionTime;
  private String name;
  private Random random = new Random();

  public Process(String name) {
    this.name = name;
    this.executionTime = random.nextInt(20) + 1; // gera aleatoriamente o tempo de execução
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getExecutionTime() {
    return executionTime;
  }

  public void setExecutionTime(int executionTime) {
    this.executionTime = executionTime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void showMessage() {
    if (getExecutionTime() == 0) {
      System.out.println("Processo " + getId() + " finalizado.\n");
    }
    System.out
        .println("Oi, sou o processo " + getName() + ".\ntempo de execução restante: " + getExecutionTime() + ".\n");
  }
}
