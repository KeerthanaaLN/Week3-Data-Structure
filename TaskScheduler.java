import java.util.Scanner;

class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;

    Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
    }
}

class CircularTaskList {
    Task head = null, tail = null, current = null;

    public void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }

    public void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }
    }

    public void addAtPosition(int pos, int id, String name, int priority, String dueDate) {
        if (pos <= 1 || head == null) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }
        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        for (int i = 1; i < pos - 1 && temp.next != head; i++) {
            temp = temp.next;
        }
        newTask.next = temp.next;
        temp.next = newTask;
        if (temp == tail) {
            tail = newTask;
            tail.next = head;
        }
    }

    public void removeById(int id) {
        if (head == null) return;

        Task temp = head, prev = tail;
        do {
            if (temp.id == id) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                    if (temp == tail) tail = prev;
                }
                if (temp == current) current = current.next;
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    public void viewCurrentAndMoveNext() {
        if (current == null) current = head;
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Current Task -> ID: " + current.id + ", Name: " + current.name + ", Priority: " + current.priority + ", Due: " + current.dueDate);
        current = current.next;
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }
        Task temp = head;
        do {
            System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks to search.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Due: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No tasks found with priority " + priority);
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CircularTaskList taskList = new CircularTaskList();

        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by ID");
            System.out.println("5. View Current and Move Next\n6. Display All\n7. Search by Priority\n8. Exit");
            System.out.print("Enter choice: ");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1: case 2: case 3: {
                    System.out.print("Task ID: ");
                    int id = in.nextInt(); in.nextLine();
                    System.out.print("Task Name: ");
                    String name = in.nextLine();
                    System.out.print("Priority: ");
                    int priority = in.nextInt(); in.nextLine();
                    System.out.print("Due Date: ");
                    String dueDate = in.nextLine();

                    if (choice == 1) taskList.addAtBeginning(id, name, priority, dueDate);
                    else if (choice == 2) taskList.addAtEnd(id, name, priority, dueDate);
                    else {
                        System.out.print("Position: ");
                        int pos = in.nextInt();
                        taskList.addAtPosition(pos, id, name, priority, dueDate);
                    }
                    break;
                }
                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    int id = in.nextInt();
                    taskList.removeById(id);
                    break;
                case 5:
                    taskList.viewCurrentAndMoveNext();
                    break;
                case 6:
                    taskList.displayAllTasks();
                    break;
                case 7:
                    System.out.print("Enter priority to search: ");
                    int priority = in.nextInt();
                    taskList.searchByPriority(priority);
                    break;
                case 8:
                    in.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
