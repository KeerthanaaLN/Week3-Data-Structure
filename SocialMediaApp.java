import java.util.*;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}

class SocialMedia {
    User head = null;

    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newUser;
        }
        System.out.println("User added successfully.");
    }

    public User findUserById(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId)
                return temp;
            temp = temp.next;
        }
        return null;
    }

    public void addFriendConnection(int userId1, int userId2) {
        User u1 = findUserById(userId1);
        User u2 = findUserById(userId2);
        if (u1 != null && u2 != null && userId1 != userId2) {
            if (!u1.friendIds.contains(userId2)) u1.friendIds.add(userId2);
            if (!u2.friendIds.contains(userId1)) u2.friendIds.add(userId1);
            System.out.println("Friend connection added.");
        } else {
            System.out.println("Invalid user IDs.");
        }
    }

    public void removeFriendConnection(int userId1, int userId2) {
        User u1 = findUserById(userId1);
        User u2 = findUserById(userId2);
        if (u1 != null && u2 != null) {
            u1.friendIds.remove(Integer.valueOf(userId2));
            u2.friendIds.remove(Integer.valueOf(userId1));
            System.out.println("Friend connection removed.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void displayFriends(int userId) {
        User user = findUserById(userId);
        if (user != null) {
            System.out.println("Friends of " + user.name + ":");
            for (int id : user.friendIds) {
                User friend = findUserById(id);
                if (friend != null)
                    System.out.println(" - " + friend.name + " (ID: " + friend.userId + ")");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void findMutualFriends(int userId1, int userId2) {
        User u1 = findUserById(userId1);
        User u2 = findUserById(userId2);
        if (u1 != null && u2 != null) {
            Set<Integer> mutual = new HashSet<>(u1.friendIds);
            mutual.retainAll(u2.friendIds);
            System.out.println("Mutual Friends:");
            for (int id : mutual) {
                User friend = findUserById(id);
                if (friend != null)
                    System.out.println(" - " + friend.name + " (ID: " + friend.userId + ")");
            }
            if (mutual.isEmpty()) System.out.println("No mutual friends.");
        } else {
            System.out.println("Invalid user IDs.");
        }
    }

    public void searchUser(String query) {
        User temp = head;
        boolean found = false;
        while (temp != null) {
            if (String.valueOf(temp.userId).equals(query) || temp.name.equalsIgnoreCase(query)) {
                System.out.println("User Found: " + temp.name + " (ID: " + temp.userId + ")");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("User not found.");
        }
    }

    public void countFriendsForAllUsers() {
        User temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIds.size() + " friend(s).");
            temp = temp.next;
        }
    }
}

public class SocialMediaApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SocialMedia sm = new SocialMedia();

        while (true) {
            System.out.println("\n1. Add User\n2. Add Friend Connection\n3. Remove Friend Connection\n4. Display Friends");
            System.out.println("5. Find Mutual Friends\n6. Search User\n7. Count Friends\n8. Exit");
            System.out.print("Enter choice: ");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("User ID: ");
                    int uid = in.nextInt();
                    in.nextLine();
                    System.out.print("Name: ");
                    String name = in.nextLine();
                    System.out.print("Age: ");
                    int age = in.nextInt();
                    sm.addUser(uid, name, age);
                    break;
                case 2:
                    System.out.print("Enter User ID 1: ");
                    int id1 = in.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int id2 = in.nextInt();
                    sm.addFriendConnection(id1, id2);
                    break;
                case 3:
                    System.out.print("Enter User ID 1: ");
                    int uid1 = in.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int uid2 = in.nextInt();
                    sm.removeFriendConnection(uid1, uid2);
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    int uId = in.nextInt();
                    sm.displayFriends(uId);
                    break;
                case 5:
                    System.out.print("Enter User ID 1: ");
                    int m1 = in.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int m2 = in.nextInt();
                    sm.findMutualFriends(m1, m2);
                    break;
                case 6:
                    System.out.print("Enter User ID or Name to search: ");
                    String query = in.nextLine();
                    sm.searchUser(query);
                    break;
                case 7:
                    sm.countFriendsForAllUsers();
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
