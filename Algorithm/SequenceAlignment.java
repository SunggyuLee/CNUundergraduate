import java.util.Scanner;

public class SequenceAlignment {

    static String[][] table;
    static final int GAP = 1;
    static final int MISMATCH = 2;

    public static void main(String[] args) {
        String s1;
        String s2;

        Scanner scan = new Scanner(System.in);
        System.out.println("두 개의 문자열을 차례대로 입력하세요.");

        s1 = scan.next();
        s2 = scan.next();

        table = new String[2 * s1.length() + 1][2 * s2.length() + 1];
        for (int i = 0; i < 2 * s1.length() + 1; i++)
            for (int j = 0; j < 2 * s2.length() + 1; j++)
                table[i][j] = "";

        sequenceAlignment(s1, s2);

        makeArrow(s1, s2);

        System.out.println("MIN COST : " + table[2 * s1.length()][2 * s2.length()]);

        System.out.print("\t\t");
        for (int k = 2; k < 2 * s2.length() + 1; k += 2) {
            System.out.print("\t\t" + s2.toCharArray()[k / 2 - 1]);
        }
        System.out.println("\n");

        for (int i = 0; i < 2 * s1.length() + 1; i++) {
            if (i % 2 == 0 && i != 0) {
                System.out.print("\t" + s1.toCharArray()[i / 2 - 1]);
            } else {
                System.out.print("\t");
            }
            for (int j = 0; j < 2 * s2.length() + 1; j++) {
                System.out.print("\t" + table[i][j]);
            }
            System.out.println();
        }
    }

    private static void makeArrow(String s1, String s2) {
        int i = 2 * s1.length();
        int j = 2 * s2.length();

        while (!(i == 0 && j == 0)) {
            if (j == 0) {
                table[i - 1][j] = "↑";
                i -= 2;
            } else if (i == 0) {
                table[i][j - 1] = "←";
                j -= 2;
            } else {

                if (s1.toCharArray()[i / 2 - 1] == s2.toCharArray()[j / 2 - 1]) {
                    if (Integer.parseInt(table[i][j]) - GAP == Integer.parseInt(table[i - 2][j])) {
                        table[i - 1][j] = "↑";
                        i -= 2;
                    } else if (Integer.parseInt(table[i][j]) - GAP == Integer.parseInt(table[i][j - 2])) {
                        table[i][j - 1] = "←";
                        j -= 2;
                    } else {
                        table[i - 1][j - 1] = "↖";
                        i -= 2;
                        j -= 2;
                    }
                } else {
                    if (Integer.parseInt(table[i][j]) - GAP == Integer.parseInt(table[i - 2][j])) {
                        table[i - 1][j] = "↑";
                        i -= 2;
                    } else if (Integer.parseInt(table[i][j]) - GAP == Integer.parseInt(table[i][j - 2])) {
                        table[i][j - 1] = "←";
                        j -= 2;
                    } else {
                        table[i - 1][j - 1] = "↖";
                        i -= 2;
                        j -= 2;
                    }
                }
            }
        }
    }


    private static void sequenceAlignment(String s1, String s2) {
        for (int i = 0; i < 2 * s1.length() + 1; i += 2)
            table[i][0] = Integer.toString(i / 2 * GAP);

        for (int j = 0; j < 2 * s2.length() + 1; j += 2)
            table[0][j] = Integer.toString(j / 2 * GAP);

        for (int i = 2; i < 2 * s1.length() + 1; i += 2) {
            for (int j = 2; j < 2 * s2.length() + 1; j += 2) {
                if (s1.toCharArray()[i / 2 - 1] == s2.toCharArray()[j / 2 - 1])
                    table[i][j] = Integer.toString(Math.min(Integer.parseInt(table[i - 2][j - 2]), Math.min(GAP + Integer.parseInt(table[i - 2][j]), GAP + Integer.parseInt(table[i][j - 2]))));
                else
                    table[i][j] = Integer.toString(Math.min(MISMATCH + Integer.parseInt(table[i - 2][j - 2]), Math.min(GAP + Integer.parseInt(table[i - 2][j]), GAP + Integer.parseInt(table[i][j - 2]))));
            }
        }
    }

}
