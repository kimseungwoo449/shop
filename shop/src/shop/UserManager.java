package shop;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
	private ArrayList<User> users;
	private static UserManager instance = new UserManager();
	private Scanner sc = new Scanner(System.in);
	
	private UserManager() {
		users = new ArrayList<User>();
		User admin = new User("admin","1111");
		users.add(admin);
	}

	public static UserManager getInstance() {
		return instance;
	}
	
	private String inputString(String message) {
		System.out.print(message + " : ");
		return sc.next();
	}
	
	private int inputNumber(String message) {
		int number = -1;
		try {
			String input = sc.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자로 입력가능합니다.");
		}
		return number;
	}
	
	// Create User
	public void createUser() {
		String id = inputString("ID");
		String password = inputString("PASSWORD");
		
		if(findUserById(id)!=null) {
			System.err.println("이미 존재하는 ID입니다.");
			return;
		}
		
		User user = new User(id,password);
		users.add(user);;
		System.out.printf("회원가입 완료. %s님 안녕하세요\n",id);
	}
	
	private User findUserById(String id) {
		for(User user:users) {
			if(user.getId().equals(id))
				return user;
		}
		return null;
	}
}
