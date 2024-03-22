package shop;

import java.util.ArrayList;

public class UserManager {
	private final int SHOW_MY_CART = 1;
	private final int DELETE_ITEM_IN_MY_CART = 2;
	private final int MODIFY_ITEM_AMOUNT = 3;
	private final int PAYMENT = 4;

	private ArrayList<User> users;
	private static UserManager instance = new UserManager();

	private UserManager() {
		users = new ArrayList<User>();
		User admin = new User("admin", "1111");
		users.add(admin);
	}

	public static UserManager getInstance() {
		return instance;
	}

	private boolean isPossible() {
		if (Shop.log == -1)
			return false;
		else
			return true;
	}

	// Create User
	public Cart createUser() {
		if (isPossible()) {
			System.err.println("로그아웃 후 이용가능합니다.");
			return null;
		}

		String id = Shop.inputString("ID");
		String password = Shop.inputString("PASSWORD");

		if (findUserIndexById(id) != -1) {
			System.err.println("이미 존재하는 ID입니다.");
			return null;
		}

		User user = new User(id, password);
		users.add(user);

		System.out.printf("회원가입 완료. %s님 안녕하세요\n", id);
		return user.getMyCart();
	}

	private int findUserIndexById(String id) {
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (user.getId().equals(id))
				return i;
		}
		return -1;
	}

	private boolean isMyId(User user) {
		String id = Shop.inputString("ID");
		String password = Shop.inputString("PASSWORD");

		if (!user.getId().equals(id) || !user.getPassword().equals(password)) {
			System.err.println("회원 정보가 일치 하지 않습니다.");
			return false;
		}
		return true;
	}

	public void printMyPageSubMenu() {
		if (!isPossible()) {
			System.err.println("로그인 후 이용가능합니다.");
			return;
		}

		User user = users.get(Shop.log);

		if (!isMyId(user))
			return;

		System.out.println("[1] 내 장바구니");
		System.out.println("[2] 품목 삭제");
		System.out.println("[3] 수량 수정");
		System.out.println("[4] 결제");
		runMyPageSubMenu(Shop.inputNumber("SubMenu"));
	}
	
	private void runMyPageSubMenu(int choice) {
		if (choice == SHOW_MY_CART)
			readMyInformation();

	}

	private void readMyInformation() {
		System.out.println(users.get(Shop.log));
	}

	// Delete user
	public String deleteUser() {
		if (!isPossible()) {
			System.err.println("로그인 후 이용가능합니다.");
			return null;
		}

		User user = users.get(Shop.log);

		if (!isMyId(user))
			return null;

		users.remove(user);
		System.out.println("탈퇴 완료.");
		Shop.log = -1;
		return user.getId();
	}

	// login
	public void login() {
		if (isPossible()) {
			System.err.println("로그아웃 후 이용가능합니다.");
			return;
		}

		String id = Shop.inputString("ID");
		String password = Shop.inputString("PASSWORD");

		int index = findUserIndexById(id);
		if (index == -1 || !users.get(index).getPassword().equals(password)) {
			System.err.println("ID 혹은 PASSWORD 재확인");
			return;
		}

		Shop.log = index;
		System.out.println("로그인 성공.");
	}

	// logout
	public void logout() {
		if (!isPossible()) {
			System.err.println("로그인 후 이용가능합니다.");
			return;
		}
		Shop.log = -1;
		System.out.println("로그아웃 성공.");
	}

	public String showUserId() {
		return users.get(Shop.log).getId();
	}

	// shopping
	public void setMyCart(Item item) {
		if (!isPossible() || item == null) {
			System.err.println("로그인 후 이용가능합니다.");
			return;
		}

		User user = users.get(Shop.log);
		user.setMyCart(item);
	}
}