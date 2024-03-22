package shop;

import java.util.Scanner;

public class Shop {
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOG_IN = 3;
	private final int LOG_OUT = 4;
	private final int SHOPPING = 5;
	private final int MY_PAGE = 6;

	private final int ENROLL_ITEM = 1;
	private final int DELETE_ITEM = 2;
	private final int MODIFY_ITEM = 3;
	private final int SHOW_TOTAL = 4;
	private final int EXIT_ADMIN = 0;

	private UserManager userManager;
	private ItemManager itemManager;
	public static Scanner sc = new Scanner(System.in);

	private String shopName;
	public static int log;

	public Shop(String shopName) {
		this.shopName = shopName;
		this.log = -1;
		this.userManager = UserManager.getInstance();
		this.itemManager = ItemManager.getInstance();
	}

	public static String inputString(String message) {
		System.out.print(message + " : ");
		return sc.next();
	}

	public static int inputNumber(String message) {
		int number = -1;
		try {
			System.out.print(message + " : ");
			String input = sc.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자로 입력가능합니다.");
		}
		return number;
	}

	private void printMenu() {
		if (this.log != -1)
			System.out.printf("=== %s님 로그인 중... ===\n", userManager.showUserId());
		System.out.printf("=== %s ===\n", this.shopName);
		if (this.log != 0) {
			System.out.println("[1] 회원 가입");
			System.out.println("[2] 회원 탈퇴");
			System.out.println("[3] 로그 인");
			System.out.println("[4] 로그 아웃");
			System.out.println("[5] 쇼핑 하기");
			System.out.println("[6] 마이 페이지");
		}
		if (this.log == 0) {
			System.out.println("[1] 아이템 등록");
			System.out.println("[2] 아이템 삭제");
			System.out.println("[3] 아이템 수정");
			System.out.println("[4] 총 매출 조회");
			System.out.println("[0] 로그 아웃");
		}
	}

	private void runMenu(int choice) {
		if (this.log != 0) {
			if (choice == JOIN)
				join();
			else if (choice == LEAVE)
				leave();
			else if (choice == LOG_IN)
				login();
			else if (choice == LOG_OUT)
				logout();
			else if (choice == SHOPPING)
				shopping();
			else if (choice == MY_PAGE)
				myPage();
		} else {
			if (choice == ENROLL_ITEM)
				enrollItem();
			else if (choice == DELETE_ITEM)
				deleteItem();
			else if (choice == MODIFY_ITEM)
				modifyItem();
//			else if(choice==SHOW_TOTAL)
//				showTotal();
			else if (choice == EXIT_ADMIN)
				logout();
		}
	}

	private void join() {
		Cart cart = userManager.createUser();
		itemManager.setAllCarts(cart);
	}

	private void leave() {
		userManager.deleteUser();
	}

	private void login() {
		userManager.login();
	}

	private void logout() {
		userManager.logout();
	}

	private void myPage() {
		userManager.printMyPageSubMenu();;
	}
	
	private void enrollItem() {
		itemManager.enrollItem();
	}

	private void deleteItem() {
		itemManager.deleteItem();
	}

	private void modifyItem() {
		itemManager.updateItem();
	}

	private void shopping() {
		Item item = itemManager.getItem();
		userManager.setMyCart(item);
	}

	public void run() {
		while (true) {
			printMenu();
			runMenu(inputNumber("Menu"));
		}
	}
}