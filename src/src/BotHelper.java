import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.Console;

/**
 * Created by valdemar on 19/08/2016.
 */
public class BotHelper {
    private static String pathToBot = "C:\\Users\\valdemar\\Downloads\\Release\\NecroBot.exe"; //bot path
    private static Process p; //bot process
    private static int giorni; //days of activity (chosen by user)
    private static Console c = System.console(); //system console, pretty explainatory

    public static void main(String args[]){
        System.out.println("Inserire il numero di giorni di funzionamento del bot: ");
        giorni = Integer.parseInt(c.readLine());
        for(int i = 0; i<giorni; i++) {
            //mattina 4-5h
            System.out.println("Mattina");
            cycle(timeslice(4,5));
            //pranzo 1-2h
            System.out.println("Pranzo");
            dormi(timeslice(1,2));
            //pomeriggio 5-6h
            System.out.println("Pomeriggio");
            cycle(timeslice(5,6));
            //cena 1-2h
            System.out.println("Cena");
            dormi(timeslice(1,2));
            //sera 3-4h
            System.out.println("Sera");
            cycle(timeslice(3,4));
            //letto 8-9h
            System.out.println("Letto");
            dormi(timeslice(8,9));
        }
    }

    private static void cycle(int n){ //gotta catch em all!
        //executes one cycle for <n> seconds
        try {
            p = new ProcessBuilder(pathToBot).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MINUTES.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.destroy();
    }

    private static void dormi(int n){ //sleep time, baby
        try {
            TimeUnit.MINUTES.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int timeslice(int min, int max){ //randomize the duration of the activity in minutes
        Random rand = new Random();
        int randomNum = rand.nextInt(((max*60) - (min*60)) + 1) + min;
        return randomNum;
    }
}
