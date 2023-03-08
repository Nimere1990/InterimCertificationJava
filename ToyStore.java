import java.util.PriorityQueue;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;


public class ToyStore {
    private PriorityQueue<Toy> queue;
    private Random rand;
    private static final int NUM_TOYS = 20;
    private static final String FILENAME = "result.txt";

    public static void main(String[] args) {
        ToyStore store = new ToyStore();
        store.addToy("1", "ToyOne", 5);
        store.addToy("2", "ToyTwo", 10);
        store.addToy("3", "ToyThree", 3);
        store.addToy("4", "ToyFour", 6);
        store.addToy("5", "ToyFive", 9);
        store.addToy("6", "ToySix", 4);
        store.addToy("7", "ToySeven", 1);
        store.addToy("8", "ToyEight", 7);
        store.addToy("9", "ToyNine", 8);
        store.addToy("10", "ToyTen", 2);
        store.play();
    }
    
    public ToyStore() {
        queue = new PriorityQueue<Toy>((t1, t2) -> t1.getWeight() - t2.getWeight());
        rand = new Random();
    }
    
    public void addToy(String id, String name, int weight) {
        Toy toy = new Toy(id, name, weight);
        queue.add(toy);
    }
    
    public void play() {
        try (FileWriter fw = new FileWriter(FILENAME)) {
            for (int i = 0; i < NUM_TOYS; i++) {
                Toy toy = queue.poll();
                String result = String.format("Игрушка %s с номером %s выбрана.", toy.getName(), toy.getId());
                System.out.println(result);
                System.out.println("______________________________________");
                fw.write(result + "\n");
                toy = new Toy(toy.getId(), toy.getName(), toy.getWeight() + rand.nextInt(10));
                queue.add(toy);
            }
        } catch (IOException e) {
            System.err.println("Невозможно записать в файл!");
            e.printStackTrace();
        }
    }
}