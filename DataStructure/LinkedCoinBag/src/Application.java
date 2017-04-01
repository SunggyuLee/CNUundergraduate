import java.util.Scanner;

public class Application {

	static LinkedBag _coinCollector;

	public void showNumOfCoins() {
		System.out.println("총 코인은 " + this._coinCollector.size() + "개 입니다");
	}

	public void showCoinsCounts(Coin givenCoin) {
		System.out.println(givenCoin.value() + "코인은 "
				+ this._coinCollector.frequencyOf(givenCoin) + "개 존재합니다");
	}

	public void showMaxCoinValues() {
		System.out.println("가장 큰 코인은 "
				+ this._coinCollector.maxValueCoin().value() + "입니다");
	}

	public void showSumCoinValues() {
		System.out.println("전체 총 코인의 합은 " + this._coinCollector.sumCoinValues()
				+ "입니다");
	}

	public void run() {
		Scanner scan = new Scanner(System.in);

		int input = 0;
		System.out.print("가방에 들어갈 총 코인 개수를 입력하시오 : ");
		input = scan.nextInt();
		this._coinCollector = new LinkedBag();
		System.out.print("메뉴를 선택하시오 [1:입력 2:삭제 3:출력 4:검색 5:임의의코인 삭제 9:종료]:");
		while (input != 9) {
			input = scan.nextInt();
			if (input == 1) {
				System.out.print("- [입력] 코인의 액수를 입력하시오 : ");
				input = scan.nextInt();
				Coin anCoin = new Coin(input);
				long start = System.nanoTime();
				this._coinCollector.add(anCoin);
				long end = System.nanoTime();
				double time = (double) (end - start);
				System.out.println(" 수행 시간 = " + time + " nano sec");
			} else if (input == 2) {
				System.out.print("- [삭제] 코인의 액수를 입력하세오 : ");
				input = scan.nextInt();
				Coin anCoin = new Coin(input);
				long start = System.nanoTime();
				this._coinCollector.remove(anCoin);
				long end = System.nanoTime();
				double time = (double) (end - start);
				System.out.println(" 수행 시간 = " + time + " nano sec");
			} else if (input == 3) {
				long start = System.nanoTime();
				System.out.println("- [출력]");
				System.out.println("총 코인은 " + this._coinCollector.size()
						+ "개 입니다");
				System.out.println("가장 큰 코인은 "
						+ this._coinCollector.maxValueCoin().value() + "입니다");
				System.out.println("전체 총 코인의 합은 "
						+ this._coinCollector.sumCoinValues() + "입니다");
				long end = System.nanoTime();
				double time = (double) (end - start);
				System.out.println(" 수행 시간 = " + time + " nano sec");
			} else if (input == 4) {
				System.out.print("- [검색] 코인의 액수를 입력하세오 : ");
				input = scan.nextInt();
				Coin anCoin = new Coin(input);
				long start = System.nanoTime();
				this.showCoinsCounts(anCoin);
				long end = System.nanoTime();
				double time = (double) (end - start);
				System.out.println(" 수행 시간 = " + time + " nano sec");
			} else if (input == 5) {
				long start = System.nanoTime();
				this._coinCollector.removeAny();
				long end = System.nanoTime();
				double time = (double) (end - start);
				System.out.println(" 수행 시간 = " + time + " nano sec");
			}else if (input == 9) {
				this.showNumOfCoins();
				this.showMaxCoinValues();
				this.showSumCoinValues();
				break;
			} else {
				System.out.println("[오류]잘못된 입력입니다.");
			}
			System.out.print("메뉴를 선택하시오 [1:입력 2:삭제 3:출력 4:검색 5:임의의코인 삭제 9:종료]:");

		}
	}
}