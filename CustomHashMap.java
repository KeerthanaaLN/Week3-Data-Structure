import java.util.Scanner;

public class CustomHashMap {

    static class Entry {
        int key, value;
        Entry next;

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    static class HashMap {
        private final int SIZE = 10;
        private Entry[] table;

        public HashMap() {
            table = new Entry[SIZE];
        }

        private int hash(int key) {
            return key % SIZE;
        }

        public void put(int key, int value) {
            int index = hash(key);
            Entry head = table[index];

            while (head != null) {
                if (head.key == key) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }

            Entry newEntry = new Entry(key, value);
            newEntry.next = table[index];
            table[index] = newEntry;
        }

        public Integer get(int key) {
            int index = hash(key);
            Entry head = table[index];

            while (head != null) {
                if (head.key == key) {
                    return head.value;
                }
                head = head.next;
            }
            return null;
        }

        public void remove(int key) {
            int index = hash(key);
            Entry head = table[index];
            Entry prev = null;

            while (head != null) {
                if (head.key == key) {
                    if (prev == null) {
                        table[index] = head.next;
                    } else {
                        prev.next = head.next;
                    }
                    return;
                }
                prev = head;
                head = head.next;
            }
        }

        public void display() {
            for (int i = 0; i < SIZE; i++) {
                Entry head = table[i];
                System.out.print("Bucket " + i + ": ");
                while (head != null) {
                    System.out.print("[" + head.key + "=" + head.value + "] -> ");
                    head = head.next;
                }
                System.out.println("null");
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap map = new HashMap();

        while (true) {
            System.out.println("\n1. Put\n2. Get\n3. Remove\n4. Display\n5. Exit");
            System.out.print("Enter choice: ");
            int choice = in.nextInt();

            if (choice == 1) {
                System.out.print("Enter key and value: ");
                int key = in.nextInt();
                int value = in.nextInt();
                map.put(key, value);
            } else if (choice == 2) {
                System.out.print("Enter key to retrieve: ");
                int key = in.nextInt();
                Integer val = map.get(key);
                if (val != null) {
                    System.out.println("Value: " + val);
                } else {
                    System.out.println("Key not found");
                }
            } else if (choice == 3) {
                System.out.print("Enter key to remove: ");
                int key = in.nextInt();
                map.remove(key);
                System.out.println("Key removed if it existed.");
            } else if (choice == 4) {
                map.display();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }

        in.close();
    }
}
