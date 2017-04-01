import java.util.Scanner;

public class Application {
	private ArraySet _starCollector;

	public void showNumOfStars() {
		System.out.println("집합 내에 있는 별의 개수는 " + this._starCollector.size()
				+ "입니다");
	}

	public void showExistence(Star givenStar) {
		// 검색 시 사용하는 함수
		if (this._starCollector.doesContain(givenStar)) {
			if (givenStar.starName() != null)
				System.out.println(givenStar.starName() + "별이 존재합니다");
			else
				System.out.println(givenStar.xCoordinate() + ", "
						+ givenStar.yCoordinate() + " 위치에 별이 존재합니다");
		} else
			System.out.println("별이 존재하지 않습니다");
	}

	public void run() {
		Scanner scan = new Scanner(System.in);
		this._starCollector = new ArraySet();
		int xCoordinate = 0;
		int yCoordinate = 0;
		String starName = null;
		int command = 0;
		while (command != 9) {
			try {
				System.out.print("[1:입력 2:주어진 별 삭제 3:임의의 별 삭제 4:출력 5:이름으로 검색 6:좌표로 검색 9:종료]:");
				command = Integer.parseInt(scan.nextLine());
				if (command == 1) {
					System.out.println("- [입력] - ");
					System.out.print("- x좌표를 입력하시오 : ");
					xCoordinate = Integer.parseInt(scan.nextLine());
					System.out.print("- y좌표를 입력하시오 : ");
					yCoordinate = Integer.parseInt(scan.nextLine()); 
//nextInt로 하면 여기서 정수만 끊어버리고 엔터값이 다음으로 넘어가서 입력하지 않고 넘어가버려서 이렇게 쓰면안됨
//숫자만 입력하는 경우는 괜찮지만 숫자입력도중 글자를 입력하는 형태라면 nextInt사용불가
					System.out.print("- 별의 이름을 입력하시오 : ");
					starName = scan.nextLine();
					Star aStar = new Star(xCoordinate, yCoordinate, starName);
					this._starCollector.add(aStar);
				} else if (command == 2) {
					System.out.print("-[삭제] 별의 이름을 입력하시오 : ");
					starName = scan.nextLine();
					Star aStar = new Star(starName);
					this._starCollector.remove(aStar);
				} else if (command == 3) {
					System.out.println("-[삭제] 임의의 별 삭제");
					this._starCollector.removeAny();
				} else if (command == 4) {
					System.out.println(this._starCollector.size()
							+ "개의 별이 존재합니다");
				} else if (command == 5) {
					System.out.println("-[검색] 별의 이름을 입력하세요");
					starName = scan.nextLine();
					Star aStar = new Star(starName);
					this.showExistence(aStar);
				} else if (command == 6) {
					System.out.println("-[검색]-");
					System.out.print("- x좌표를 입력하시오 : ");
					xCoordinate = Integer.parseInt(scan.nextLine());
					System.out.print("- y좌표를 입력하시오 : ");
					yCoordinate = Integer.parseInt(scan.nextLine());
					Star aStar = new Star(xCoordinate, yCoordinate);
					this.showExistence(aStar);
				} else if (command == 9) {
					System.out.println("9가 입력되어 종료합니다.");
					break;
				} else {
					System.out.println("[오류]잘못된 입력입니다.");
				}
			} catch (Exception ex) {
				System.out.println("Error Message : " + ex.getMessage());
				continue;
			}
		}
	}
}
