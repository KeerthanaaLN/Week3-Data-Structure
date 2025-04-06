import java.util.Scanner;

class Process {
    int processId, burstTime, priority, remainingTime, waitingTime = 0, turnaroundTime = 0;
    Process next;

    Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }
}

class RoundRobinScheduler {
    Process head = null;

    public void addProcess(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = newProcess;
            head.next = head;
        } else {
            Process temp = head;
            while (temp.next != head)
                temp = temp.next;
            temp.next = newProcess;
            newProcess.next = head;
        }
    }

    public void simulateRoundRobin(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        int time = 0;
        Process current = head;
        boolean allDone;
        System.out.println("\n--- Round Robin Scheduling ---");
        do {
            allDone = true;
            Process start = current;

            do {
                if (current.remainingTime > 0) {
                    allDone = false;
                    int execTime = Math.min(timeQuantum, current.remainingTime);
                    time += execTime;
                    current.remainingTime -= execTime;

                    if (current.remainingTime == 0) {
                        current.turnaroundTime = time;
                        current.waitingTime = current.turnaroundTime - current.burstTime;
                        removeProcess(current.processId);
                    }
                }
                displayProcesses();
                current = current.next;
            } while (current != start);
        } while (!allDone);

        calculateAverageTimes();
    }

    public void removeProcess(int processId) {
        if (head == null) return;

        if (head.processId == processId && head.next == head) {
            head = null;
            return;
        }

        Process curr = head, prev = null;
        do {
            if (curr.processId == processId) {
                if (prev != null) {
                    prev.next = curr.next;
                } else {
                    Process temp = head;
                    while (temp.next != head)
                        temp = temp.next;
                    temp.next = head.next;
                    head = head.next;
                }
                break;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("Queue is empty.");
            return;
        }
        Process temp = head;
        System.out.print("Processes: ");
        do {
            System.out.print("[P" + temp.processId + "|BT:" + temp.burstTime +
                    "|RT:" + temp.remainingTime + "] ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    public void calculateAverageTimes() {
        int count = 0, totalWaiting = 0, totalTurnaround = 0;
        Process temp = head;

        if (temp == null) return;

        do {
            count++;
            totalWaiting += temp.waitingTime;
            totalTurnaround += temp.turnaroundTime;
            temp = temp.next;
        } while (temp != head);

        double avgWaiting = (double) totalWaiting / count;
        double avgTurnaround = (double) totalTurnaround / count;
        System.out.printf("Average Waiting Time: %.2f\n", avgWaiting);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaround);
    }
}

public class RoundRobinSimulation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        RoundRobinScheduler scheduler = new RoundRobinScheduler();

        while (true) {
            System.out.println("\n1. Add Process\n2. Display Processes\n3. Run Scheduler\n4. Exit");
            System.out.print("Enter choice: ");
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Process ID: ");
                    int id = in.nextInt();
                    System.out.print("Burst Time: ");
                    int bt = in.nextInt();
                    System.out.print("Priority: ");
                    int p = in.nextInt();
                    scheduler.addProcess(id, bt, p);
                    break;
                case 2:
                    scheduler.displayProcesses();
                    break;
                case 3:
                    System.out.print("Enter Time Quantum: ");
                    int tq = in.nextInt();
                    scheduler.simulateRoundRobin(tq);
                    break;
                case 4:
                    in.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
