import java.util.Scanner;

public class CircularTourProblem {

    static class PetrolPump {
        int petrol;
        int distance;

        PetrolPump(int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }

    public static int findStartingPoint(PetrolPump[] pumps, int n) {
        int start = 0;
        int surplus = 0;
        int deficit = 0;

        for (int i = 0; i < n; i++) {
            surplus += pumps[i].petrol - pumps[i].distance;

            if (surplus < 0) {
                start = i + 1;
                deficit += surplus;
                surplus = 0;
            }
        }

        return (surplus + deficit >= 0) ? start : -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of petrol pumps: ");
        int n = in.nextInt();

        PetrolPump[] pumps = new PetrolPump[n];

        System.out.println("Enter petrol and distance to next pump for each pump:");
        for (int i = 0; i < n; i++) {
            int petrol = in.nextInt();
            int distance = in.nextInt();
            pumps[i] = new PetrolPump(petrol, distance);
        }

        int startIndex = findStartingPoint(pumps, n);

        if (startIndex == -1) {
            System.out.println("No feasible starting point for circular tour.");
        } else {
            System.out.println("Start the tour at petrol pump index: " + startIndex);
        }

        in.close();
    }
}
