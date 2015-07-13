/**
 * Created by shade on 7/12/2015.
 */
public class Subset {

    public static void main(String[] args){
        int num_words = Integer.parseInt(args[0]);
        String input;

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        do{
            input = StdIn.readString();
            rq.enqueue(input);
        } while (!StdIn.isEmpty());

        for(int i = 0; i < num_words;i++){
            System.out.println(rq.dequeue());
        }
    }
}
