import java.io.*;
import java.util.*;

public class ClosestPair {


    public static void main(String[] args) throws IOException {
        AnswerPoint minPoints;

//        BufferedReader reader = new BufferedReader(new FileReader("input.in"));
        BufferedReader reader = new BufferedReader(new FileReader("closestData/closest_1000.in"));

        List<Point> array = new ArrayList<>();

        while (reader.ready()) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine(), " ");

            while (tokens.hasMoreTokens()) {
                array.add(new Point(Double.parseDouble(tokens.nextToken()), Double.parseDouble(tokens.nextToken())));
            }
        }

        Comparator<Point> cpX = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.getX() - o2.getX() > 0)
                    return 1;
                else if(o1.getX() - o2.getX() < 0)
                    return -1;
                else
                    return 0;
            }
        };

        array.sort(cpX);

        minPoints = closestPair(array, 0, array.size() - 1);

        System.out.println("( " + minPoints.getP1().getX() + " , " + minPoints.getP1().getY() + ") ( " + minPoints.getP2().getX() + " , " + minPoints.getP2().getY() + ") \n" + "최소거리 : " + minPoints.calDistance());
    }

    private static AnswerPoint closestPair(List<Point> array, int p, int r) {

        int mid = (p + r) / 2;

        AnswerPoint leftPoints;
        AnswerPoint rightPoints;
        AnswerPoint minPoints;

        if(r - p + 1 <= 2){
            return new AnswerPoint(array.get(p), array.get(p+1));
        }
        else if(r - p + 1 <= 3){
            AnswerPoint temp1 = new AnswerPoint(array.get(p), array.get(p+1));
            AnswerPoint temp2 = new AnswerPoint(array.get(p+1), array.get(p+2));
            AnswerPoint temp3 = new AnswerPoint(array.get(p), array.get(p+2));

            if(temp1.calDistance() <= temp2.calDistance())
                minPoints = temp1;
            else
                minPoints = temp2;

            if(minPoints.calDistance() > temp3.calDistance())
                minPoints = temp3;
        }
        else {
            leftPoints = closestPair(array, p, mid);
            rightPoints = closestPair(array, mid + 1, r);
            if(leftPoints.calDistance() >= rightPoints.calDistance())
                minPoints = rightPoints;
            else
                minPoints = leftPoints;
        }

        double centerX = (array.get(mid).getX() + array.get(mid).getX()) / 2;
        List<Point> windowList = new ArrayList<>();

        for(int i = mid; i >= p && array.get(i).getX() >= centerX - minPoints.calDistance(); i--){
            windowList.add(array.get(i));
        }

        for(int i = mid+1; i <= r && array.get(i).getX() <= centerX + minPoints.calDistance(); i++){
            windowList.add(array.get(i));
        }

        Comparator<Point> cpY = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.getY() - o2.getY() > 0)
                    return 1;
                else if(o1.getY() - o2.getY() < 0)
                    return -1;
                else
                    return 0;
            }
        };

        windowList.sort(cpY);

        int temp = 0;

        while(temp < windowList.size()){

            int j = 1;

            for(int i = temp; i + j < windowList.size() && Math.abs(windowList.get(i).getY() - windowList.get(i + j).getY()) < minPoints.calDistance(); i++){
                AnswerPoint tempPoints = new AnswerPoint(windowList.get(i), windowList.get(i+j));

                if(tempPoints.calDistance() < minPoints.calDistance())
                    minPoints = tempPoints;
                j++;
            }
            temp++;
        }

        return minPoints;

    }

    public static class Point {

        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

    }


    public static class AnswerPoint {

        Point p1;
        Point p2;

        public AnswerPoint(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public Point getP1() {
            return p1;
        }

        public Point getP2() {
            return p2;
        }


        public double calDistance(){
            double tempX = Math.pow(p1.getX()-p2.getX(), 2);
            double tempY = Math.pow(p1.getY()-p2.getY(), 2);

            return Math.sqrt(tempX + tempY);
        }
    }
}





