package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private FileReader fr;
	private FileWriter fw;
	private BufferedReader br;

	private File ItemFile;
	private String ItemFileName;

	private File UserFile;
	private String UserFileName;

	private static FileManager instance = new FileManager();

	private UserManager userManager = UserManager.getInstance();
	private ItemManager itemManager = ItemManager.getInstance();

	private FileManager() {
		ItemFileName = "item.txt";
		ItemFile = new File(ItemFileName);
		
		UserFileName = "user.txt";
		UserFile = new File(UserFileName);
	}

	public static FileManager getInstance() {
		return instance;
	}

	public void save() {

	}
}
