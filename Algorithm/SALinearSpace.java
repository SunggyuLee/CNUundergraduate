import java.util.*;


public class SALinearSpace {

    static final int GAP = 1;
    static final int MISMATCH = 2;
    static LinkedList<ArrowPath> arrowPaths = new LinkedList<>();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("두 개의 문자열을 차례대로 입력하세요.");

        String s1 = scan.next();
        String s2 = scan.next();

        char[] X = new char[s1.length() + 1];
        char[] Y = new char[s2.length() + 1];
        X[0] = '\0';
        Y[0] = '\0';
        System.arraycopy(s1.toCharArray(), 0, X, 1, s1.length());
        System.arraycopy(s2.toCharArray(), 0, Y, 1, s2.length());


        hirschberg(X, 0, s1.length(), Y, 0, s2.length());

        arrowPaths.sort(((o1, o2) -> {return o1.x - o2.x;}));
        arrowPaths.addFirst(new ArrowPath(0,0));
        arrowPaths.add(new ArrowPath(s1.length(),s2.length()));
        Iterator<ArrowPath> it = arrowPaths.iterator();
        while (it.hasNext()) {
            ArrowPath temp = it.next();
            System.out.print(temp.toString() + " -> ");
        }
        System.out.print("end");

    }


    private static void hirschberg(char[] X, int Xsrc, int Xdst, char[] Y, int Ysrc, int Ydst) {

        int XLen = Xdst - Xsrc;
        int YLen = Ydst - Ysrc;

        if (XLen <= 1 || YLen <= 1) {
            int[][] matrix = new int[XLen+1][YLen+1];

            if(X[Xsrc] == Y[Ysrc])
                matrix[0][0] = 0;
            else
                matrix[0][0] = MISMATCH;

            for (int i = 1; i < XLen + 1; i++) {
                matrix[i][0] = i * GAP;
            }
            for (int i = 1; i < YLen + 1; i++) {
                matrix[0][i] = i * GAP;
            }

            for (int i = 1; i < XLen + 1; i++) {
                for (int j = 1; j < YLen + 1; j++) {
                    if (X[i + Xsrc] == Y[j + Ysrc]) {
                        matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i][j - 1] + GAP, matrix[i - 1][j] + GAP));
                    } else {
                        matrix[i][j] = Math.min(MISMATCH + matrix[i - 1][j - 1], Math.min(matrix[i][j - 1] + GAP, matrix[i - 1][j] + GAP));
                    }
                }
            }

            int i = XLen;
            int j = YLen;

            while (!(i == 0 && j == 0)) {
                if(Xdst-Xsrc != i || Ydst-Ysrc != j)
                    arrowPaths.add(new ArrowPath(i+Xsrc, j+Ysrc));
                if (j == 0)
                    i--;
                else if (i == 0)
                    j--;
                else {
                    if (matrix[i][j] - GAP == matrix[i - 1][j]) {
                        i--;
                    } else if (matrix[i][j] - GAP == matrix[i][j - 1]) {
                        j--;
                    } else {
                        i--;j--;
                    }
                }
            }
        } else {

            int[] YPrefix = allPrefixCosts(X, Xsrc, Xsrc + (XLen / 2), Y, Ysrc, Ydst);
            int[] YSuffix = allSuffixCosts(X, Xsrc + (XLen / 2), Xdst, Y, Ysrc, Ydst);

            int best = Integer.MAX_VALUE;
            int bestq = 0;

            for (int q = 0; q < YLen + 1; q++) {
                int cost = YPrefix[q] + YSuffix[q];
                if (cost < best) {
                    bestq = q + Ysrc;
                    best = cost;
                }
            }

            arrowPaths.add(new ArrowPath(Xsrc + (XLen / 2), bestq));
            hirschberg(X, Xsrc, Xsrc + (XLen / 2), Y, Ysrc, bestq);
            hirschberg(X, Xsrc + (XLen / 2), Xdst, Y, bestq, Ydst);
        }
    }

    private static int[] allPrefixCosts(char[] X, int Xsrc, int Xdst, char[] Y, int Ysrc, int Ydst) {

        int XLen = Xdst - Xsrc;
        int YLen = Ydst - Ysrc;

        int[] YPrefix = new int[YLen + 1];
        int[] temp = new int[YLen + 1];

        for (int i = 0; i < YPrefix.length; i++)
            YPrefix[i] = i * GAP;

        for (int i = 1; i < XLen + 1; i++) {
            for (int j = 0; j < YLen + 1; j++) {
                if (j == 0)
                    temp[j] = i * GAP;
                else {
                    if (X[i + Xsrc] == Y[j + Ysrc])
                        temp[j] = Math.min(YPrefix[j - 1], Math.min(YPrefix[j] + GAP, temp[j - 1] + GAP));
                    else
                        temp[j] = Math.min(YPrefix[j - 1] + MISMATCH, Math.min(YPrefix[j] + GAP, temp[j - 1] + GAP));
                }
            }
            System.arraycopy(temp, 0, YPrefix, 0, YPrefix.length);
        }
        return YPrefix;
    }

    private static int[] allSuffixCosts(char[] X, int Xsrc, int Xdst, char[] Y, int Ysrc, int Ydst) {

        int XLen = Xdst - Xsrc;  // 문자열 길이
        int YLen = Ydst - Ysrc;

        int[] YSuffix = new int[YLen + 1];
        int[] temp = new int[YLen + 1];

        for (int i = 0; i < temp.length; i++)
            YSuffix[i] = i * GAP;

        for (int i = 1; i < XLen + 1; i++) {
            for (int j = 0; j < YLen + 1; j++) {
                if (j == 0)
                    temp[j] = i * GAP;
                else {
                    if (X[Xdst - i] == Y[Ydst - j])
                        temp[j] = Math.min(YSuffix[j - 1], Math.min(YSuffix[j] + GAP, temp[j - 1] + GAP));
                    else
                        temp[j] = Math.min(YSuffix[j - 1] + MISMATCH, Math.min(YSuffix[j] + GAP, temp[j - 1] + GAP));
                }
            }
            System.arraycopy(temp, 0, YSuffix, 0, YSuffix.length);
        }

        int[] reversedYSuffix = new int[YSuffix.length];
        for (int i = YSuffix.length - 1; i >= 0; i--)
            reversedYSuffix[YSuffix.length - 1 - i] = YSuffix[i];

        return reversedYSuffix;
    }

    public static class ArrowPath {
        int x;
        int y;

        public ArrowPath(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "( " + x + ", " + y + " ) ";
        }

    }
}
