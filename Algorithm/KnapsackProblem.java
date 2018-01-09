import java.util.Scanner;

public class KnapsackProblem {
    static Item[] items;
    static int[][] table;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("아이템의 개수와 가방의 무게 제한을 입력하세요");
        int numOfItems = scan.nextInt();
        int limitWeight = scan.nextInt();

        items = new Item[numOfItems + 1];
        table = new int[numOfItems+1][limitWeight+1];

        System.out.println("아이템의 가치와 무게를 차례로 입력하세요");
        items[0] = new Item(0,0);
        for(int i = 1; i < numOfItems + 1; i++){
            System.out.println(i + "번째 아이템을 입력하세요");
            int tempV = scan.nextInt();
            int tempW = scan.nextInt();

            items[i] = new Item(tempV, tempW);
        }

        OPT(numOfItems, limitWeight);
        int maxValue = 0;
        int selectedN = 0, selectedW = 0;

        for(int n = 0; n < numOfItems + 1; n++) {
            for (int w = 0; w < limitWeight + 1; w++) {
                System.out.format(" %3d", table[n][w]);
                if(table[n][w] >= maxValue) {
                    maxValue = table[n][w];
                    selectedN = n;
                    selectedW = w;
                }
            }
            System.out.println();
        }

        System.out.println("Max : " + maxValue);
        System.out.print("Item : ");
        while(maxValue != 0 ){
            if(selectedW >= items[selectedN].getWeight() && table[selectedN][selectedW] == items[selectedN].getValue() + table[selectedN - 1][selectedW - items[selectedN].getWeight()]){
                System.out.print(selectedN + " ");
                selectedW -= items[selectedN].getWeight();
                maxValue -= items[selectedN].getValue();
                selectedN--;
            }
            else{
                selectedN--;
            }
        }
    }

    private static void OPT(int numOfItems, int limitWeight) {

        for(int w = 0; w < limitWeight + 1; w++)
            table[0][w] = 0;

        for(int n = 1; n < numOfItems + 1; n++){
            for(int w = 1; w < limitWeight + 1; w++) {
                if(items[n].getWeight() > w)
                    table[n][w] = table[n-1][w];
                else
                    table[n][w] = Math.max(table[n - 1][w], items[n].getValue() + table[n - 1][w - items[n].getWeight()]);
            }
        }
    }


    public static class Item {
        int value = 0;
        int weight = 0;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public int getWeight() {
            return weight;
        }

    }
}