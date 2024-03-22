package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private FileReader fr;
	private FileWriter fw;
	private BufferedReader br;

	private File itemFile;
	private String itemFileName;

	private File userFile;
	private String userFileName;

	private static FileManager instance = new FileManager();

	private UserManager userManager = UserManager.getInstance();
	private ItemManager itemManager = ItemManager.getInstance();

	private FileManager() {
		itemFileName = "item.txt";
		itemFile = new File(itemFileName);

		userFileName = "user.txt";
		userFile = new File(userFileName);
	}

	public static FileManager getInstance() {
		return instance;
	}

	public void allSave() {
		itemSave();
	}

	private void itemSave() {
		String data = itemManager.makeData();
		save(itemFile, data);
	}

	private void save(File file, String data) {
		try {
			fw = new FileWriter(file);
			fw.write(data);
			fw.close();
			System.out.println("자동 저장 완료");
		} catch (Exception e) {
			System.err.println("저장 실패");
		}
	}
}
