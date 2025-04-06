import java.util.Scanner;

class Student {
    int rollNumber;
    String name;
    int age;
    char grade;
    Student next;

    Student(int rollNumber, String name, int age, char grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentLinkedList {
    private Student head;

    public void addAtBeginning(int roll, String name, int age, char grade) {
        Student newStudent = new Student(roll, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    public void addAtEnd(int roll, String name, int age, char grade) {
        Student newStudent = new Student(roll, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newStudent;
    }

    public void addAtPosition(int position, int roll, String name, int age, char grade) {
        if (position == 1) {
            addAtBeginning(roll, name, age, grade);
            return;
        }
        Student newStudent = new Student(roll, name, age, grade);
        Student temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) return;
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    public void deleteByRollNumber(int roll) {
        if (head == null) return;
        if (head.rollNumber == roll) {
            head = head.next;
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNumber != roll) {
            temp = temp.next;
        }
        if (temp.next == null) return;
        temp.next = temp.next.next;
    }

    public void searchByRollNumber(int roll) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                System.out.println("Found: " + temp.rollNumber + ", " + temp.name + ", " + temp.age + ", " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }

    public void updateGrade(int roll, char newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                temp.grade = newGrade;
                return;
            }
            temp = temp.next;
        }
    }

    public void displayAll() {
        Student temp = head;
        while (temp != null) {
            System.out.println(temp.rollNumber + " " + temp.name + " " + temp.age + " " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentRecordManager {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StudentLinkedList list = new StudentLinkedList();

        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Delete by Roll Number");
            System.out.println("5. Search\n6. Update Grade\n7. Display All\n8. Exit");
            System.out.print("Enter choice: ");
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3: {
                    System.out.print("Roll Number: ");
                    int roll = in.nextInt();
                    System.out.print("Name: ");
                    String name = in.next();
                    System.out.print("Age: ");
                    int age = in.nextInt();
                    System.out.print("Grade: ");
                    char grade = in.next().charAt(0);
                    if (choice == 1) list.addAtBeginning(roll, name, age, grade);
                    else if (choice == 2) list.addAtEnd(roll, name, age, grade);
                    else {
                        System.out.print("Position: ");
                        int pos = in.nextInt();
                        list.addAtPosition(pos, roll, name, age, grade);
                    }
                    break;
                }
                case 4:
                    System.out.print("Roll Number to delete: ");
                    list.deleteByRollNumber(in.nextInt());
                    break;
                case 5:
                    System.out.print("Roll Number to search: ");
                    list.searchByRollNumber(in.nextInt());
                    break;
                case 6:
                    System.out.print("Roll Number to update grade: ");
                    int roll = in.nextInt();
                    System.out.print("New Grade: ");
                    char grade = in.next().charAt(0);
                    list.updateGrade(roll, grade);
                    break;
                case 7:
                    list.displayAll();
                    break;
                case 8:
                    in.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
