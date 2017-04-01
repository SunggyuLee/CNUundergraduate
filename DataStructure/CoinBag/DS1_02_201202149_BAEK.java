import java.util.Scanner;

public class DS1_02_201202149_백승진 {
	static ArrayBag coinCollector;

	public void showNumOfCoins() {
		System.out.println("총 코인은 "+coinCollector.size()+"개 입니다");
	}

	public void showCoinsCounts(Coin givenCoin) {
		System.out.println(givenCoin.value()+"코인은 "+coinCollector.frequencyOf(givenCoin)+"개 존재합니다");
	}

	public void showMaxCoinValues() {
		System.out.println("가장 큰 코인은 "+coinCollector.maxCoinValues().value()+"입니다");
	}

	public void showSumCoinValues() {
		System.out.println("전체 총 코인의 합은 "+coinCollector.sumCoinValues()+"입니다");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		DS1_02_201202149_백승진 ds1_01_app = new DS1_02_201202149_백승진();
		int input = 0;
		System.out.print("가방에 들어갈 총 코인 개수를 입력하시오 : ");
		input = scan.nextInt();
		coinCollector = new ArrayBag(input);
		System.out.print("메뉴를 선택하시오 [1:입력 2:삭제 3:출력 4:검색 9:종료]:");
		while (input != 9) {
			input = scan.nextInt();
			if (input == 1) {
				System.out.print("- [입력] 코인의 액수를 입력하시오 : ");
				input = scan.nextInt();
				Coin anCoin = new Coin(input);
				coinCollector.add(anCoin);
				System.out.print("메뉴를 선택하시오 [1:입력 2:삭제 3:출력 4:검색 9:종료]:");
			} else if (input == 2) {
				System.out.print("- [삭제] 코인의 액수를 입력하세오 : ");
				input = scan.nextInt();
				Coin anCoin = new Coin(input);
				coinCollector.remove(anCoin);
				System.out.print("메뉴를 선택하시오 [1:입력 2:삭제 3:출력 4:검색 9:종료]:");
			} else if (input == 3) {
				System.out.println("- [출력]");
				System.out.println("총 코인은 "+coinCollector.size()+"개 입니다");
				System.out.println("가장 큰 코인은 "+coinCollector.maxCoinValues().value()+"입니다");
				System.out.println("전체 총 코인의 합은 "+coinCollector.sumCoinValues()+"입니다");
				System.out.print("메뉴를 선택하시오 [1:입력 2:삭제 3:출력 4:검색 9:종료]:");
			} else if (input == 4) {
				System.out.print("- [검색] 코인의 액수를 입력하세오 : ");
				input = scan.nextInt();
				Coin anCoin = new Coin(input);
				ds1_01_app.showCoinsCounts(anCoin);
				System.out.print("메뉴를 선택하시오 [1:입력 2:삭제 3:출력 4:검색 9:종료]:");
			} else if (input == 9) {
				ds1_01_app.showNumOfCoins();
				ds1_01_app.showMaxCoinValues();
				ds1_01_app.showSumCoinValues();
			} else {
				System.out.println("[오류]잘못된 입력입니다.");
				System.out.print("메뉴를 선택하시오 [1:입력 2:삭제 3:출력 4:검색 9:종료]:");
			}
		}
	}
}