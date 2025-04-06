import java.util.Scanner;

class Item {
    String name;
    int id, quantity;
    double price;
    Item next;

    Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }
}

class Inventory {
    Item head = null;

    public void addAtBeginning(String name, int id, int qty, double price) {
        Item newItem = new Item(name, id, qty, price);
        newItem.next = head;
        head = newItem;
    }

    public void addAtEnd(String name, int id, int qty, double price) {
        Item newItem = new Item(name, id, qty, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newItem;
    }

    public void addAtPosition(int pos, String name, int id, int qty, double price) {
        if (pos <= 1 || head == null) {
            addAtBeginning(name, id, qty, price);
            return;
        }
        Item newItem = new Item(name, id, qty, price);
        Item temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++)
            temp = temp.next;
        newItem.next = temp.next;
        temp.next = newItem;
    }

    public void removeById(int id) {
        if (head == null) return;
        if (head.id == id) {
            head = head.next;
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.id != id)
            temp = temp.next;
        if (temp.next != null)
            temp.next = temp.next.next;
    }

    public void updateQuantity(int id, int qty) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.quantity = qty;
                return;
            }
            temp = temp.next;
        }
    }

    public void searchById(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                printItem(temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    public void searchByName(String name) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                printItem(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Item not found.");
    }

    public void calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.printf("Total Inventory Value: ₹%.2f\n", total);
    }

    public void sortByName(boolean ascending) {
        head = mergeSort(head, "name", ascending);
    }

    public void sortByPrice(boolean ascending) {
        head = mergeSort(head, "price", ascending);
    }

    private Item mergeSort(Item head, String key, boolean asc) {
        if (head == null || head.next == null) return head;
        Item middle = getMiddle(head);
        Item nextToMiddle = middle.next;
        middle.next = null;

        Item left = mergeSort(head, key, asc);
        Item right = mergeSort(nextToMiddle, key, asc);

        return merge(left, right, key, asc);
    }

    private Item merge(Item left, Item right, String key, boolean asc) {
        Item result;
        if (left == null) return right;
        if (right == null) return left;

        boolean condition = false;
        if (key.equals("name"))
            condition = asc ? left.name.compareTo(right.name) <= 0 : left.name.compareTo(right.name) > 0;
        else if (key.equals("price"))
            condition = asc ? left.price <= right.price : left.price > right.price;

        if (condition) {
            result = left;
            result.next = merge(left.next, right, key, asc);
        } else {
            result = right;
            result.next = merge(left, right.next, key, asc);
        }
        return result;
    }

    private Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        Item temp = head;
        while (temp != null) {
            printItem(temp);
            temp = temp.next;
        }
    }

    private void printItem(Item item) {
        System.out.println("ID: " + item.id + ", Name: " + item.name + ", Qty: " + item.quantity + ", Price: ₹" + item.price);
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (true) {
            System.out.println("\n1. Add Item\n2. Remove Item\n3. Update Quantity\n4. Search by ID\n5. Search by Name");
            System.out.println("6. Display All\n7. Total Inventory Value\n8. Sort by Name\n9. Sort by Price\n10. Exit");
            System.out.print("Enter choice: ");
            int ch = in.nextInt();
            in.nextLine();

            switch (ch) {
                case 1: {
                    System.out.print("Name: ");
                    String name = in.nextLine();
                    System.out.print("ID: ");
                    int id = in.nextInt();
                    System.out.print("Quantity: ");
                    int qty = in.nextInt();
                    System.out.print("Price: ");
                    double price = in.nextDouble();
                    System.out.print("Position (1 - Beginning, 2 - End, 3 - Specific): ");
                    int posType = in.nextInt();
                    if (posType == 1)
                        inventory.addAtBeginning(name, id, qty, price);
                    else if (posType == 2)
                        inventory.addAtEnd(name, id, qty, price);
                    else {
                        System.out.print("Enter position: ");
                        int pos = in.nextInt();
                        inventory.addAtPosition(pos, name, id, qty, price);
                    }
                    break;
                }
                case 2:
                    System.out.print("Enter Item ID to remove: ");
                    inventory.removeById(in.nextInt());
                    break;
                case 3:
                    System.out.print("Enter Item ID: ");
                    int id = in.nextInt();
                    System.out.print("Enter new Quantity: ");
                    int qty = in.nextInt();
                    inventory.updateQuantity(id, qty);
                    break;
                case 4:
                    System.out.print("Enter Item ID: ");
                    inventory.searchById(in.nextInt());
                    break;
                case 5:
                    System.out.print("Enter Item Name: ");
                    inventory.searchByName(in.nextLine());
                    break;
                case 6:
                    inventory.displayAll();
                    break;
                case 7:
                    inventory.calculateTotalValue();
                    break;
                case 8:
                    System.out.print("Sort Ascending? (true/false): ");
                    inventory.sortByName(in.nextBoolean());
                    break;
                case 9:
                    System.out.print("Sort Ascending? (true/false): ");
                    inventory.sortByPrice(in.nextBoolean());
                    break;
                case 10:
                    in.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
