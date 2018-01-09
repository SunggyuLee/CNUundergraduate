import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MinimizingLateness {

    public static void main(String[] args) throws IOException{

        //        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("data06_lateness.txt"));

        ArrayList<Integer> array = new ArrayList<>();

        while (reader.ready()) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());

            while (tokens.hasMoreTokens()) {
                array.add(Integer.parseInt(tokens.nextToken()));
            }
        }
        int[] transformedArray = array.stream().mapToInt(i -> i).toArray();
        // ArrayList에서 일반배열로 변환

        System.out.println("Input data : ");
        System.out.println("Number of Jobs : " + transformedArray[0] + " ");
        Job[] jobs = new Job[transformedArray[0]];
        int j = 0;
        for(int i = 1; i < transformedArray.length; i+=2) {
            System.out.println(transformedArray[i] + " " + transformedArray[i + 1]);
            jobs[j] = new Job(transformedArray[i],transformedArray[i + 1]);
            j++;
        }
        System.out.println();

        int answer = minimizingLateness(jobs);

        System.out.println("Lateness : " + answer);

    }

    private static int minimizingLateness(Job[] jobs) {
        int t = 0;
        int f;
        int lateness = 0;

        for(int i = 0; i < jobs.length; i++){
            f = t + jobs[i].getRunningTime();
            t += jobs[i].getRunningTime();

            lateness = Math.max(lateness, Math.max(0, f - jobs[i].deadline));
        }

        return lateness;
    }


    private static class Job {
        int runningTime;
        int deadline;


        public Job(int runningTime, int deadline) {
            this.runningTime = runningTime;
            this.deadline = deadline;
        }

        public int getRunningTime() {
            return runningTime;
        }


        public int getDeadline() {
            return deadline;
        }


    }
}

