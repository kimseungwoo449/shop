package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

// 최종

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
		userSave();
	}

	public ArrayList<String[]> allLoad() {
		String userData = userLoad();
		String itemData = itemLoad();

		String userDataArray[] = userData.split("\n");
		String itemDataArray[] = itemData.split(",");
		ArrayList<String[]> data = new ArrayList<String[]>();
		data.add(itemDataArray);
		data.add(userDataArray);

		return data;
	}

	private void itemSave() {
		String data = itemManager.makeData();
		save(itemFile, data);
	}

	private void userSave() {
		String data = userManager.makeData();
		save(userFile, data);
	}

	private String itemLoad() {
		String data = load(itemFile);
		return data;
	}

	private String userLoad() {
		String data = load(userFile);
		return data;
	}

	private String load(File file) {
		String data = "";
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while (br.ready()) {
				data += br.readLine();
				data += "\n";
			}
			if (data.length() != 0)
				data = data.substring(0, data.length() - 1);
		} catch (Exception e) {
			System.err.println("로드 실패");
		}

		return data;
	}

	private void save(File file, String data) {
		try {
			fw = new FileWriter(file);
			fw.write(data);
			fw.close();
		} catch (Exception e) {
			System.err.println("저장 실패");
		}
	}
}
