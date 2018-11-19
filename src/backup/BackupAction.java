package backup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.AdminsVO;
import vo.UpdateVO;
import vo.UpdateVO2;
import vo.VersesVO;

public class BackupAction {
	private String rootDirectory = "C:\\backup_reciting";
	private String todayDirectory = "";
	private BackupDAO backupDAO;

	public BackupAction() {
		backupDAO = new BackupDAO();
	}
	
	public void createBackupDirectory() {
		File file = new File(rootDirectory);
		if (!file.isDirectory()) {
			file.mkdir();
		};
	}
	
	public void createTodayDirectory() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String dateStr = dateFormat.format(date);
		
		todayDirectory = rootDirectory +"\\"+ dateStr;
		File file = new File(todayDirectory);
		if (!file.isDirectory()) {
			file.mkdir();
		};
	}
	
	public void backupDB() {
		backupAdmins();
		backupYears();
		backupMonths();
		backupVerses();
		backupUpdates();
		
	}
	
	public void backupAdmins() {
		ArrayList<AdminsVO> listAdmins = (ArrayList<AdminsVO>) backupDAO.getAdmins();
		String txt = "";
		String fileName = "1.admins.txt";
		for(int i = 0; i < listAdmins.size(); i++) {
			txt = txt.concat("insert into admins values(" + listAdmins.get(i).getAdmins_sq() + ", "
													+ "'" + listAdmins.get(i).getId() + "', "
													+ "'" + listAdmins.get(i).getPw() + "', "
													+ "'" + listAdmins.get(i).getName() + "');" + 
															System.lineSeparator());
		}
		createFile(txt, fileName);
	}
	
	public void backupYears() {
		ArrayList<Integer> listYears = (ArrayList<Integer>)backupDAO.getYears();
		String txt = "";
		String fileName = "2.years.txt";
		for(int i = 0; i < listYears.size(); i++) {
			txt += "insert into years values(" + listYears.get(i) + ");" +System.lineSeparator();
		}
		createFile(txt, fileName);
	}
	
	public void backupMonths() {
		ArrayList<Integer> listMonths = (ArrayList<Integer>)backupDAO.getMonths();
		String txt = "";
		String fileName = "3.months.txt";
		for(int i = 0; i < listMonths.size(); i++) {
			txt += "insert into months values(" + listMonths.get(i) + ");" +System.lineSeparator();
		}
		
		createFile(txt, fileName);
	}
	
	public void backupVerses() {
		ArrayList<VersesVO> listVerses = (ArrayList<VersesVO>) backupDAO.getVerses();
		String txt = "";
		String fileName = "4.verses.txt";
		String verse_kr = "";
		String verse_en = "";
		for(int i = 0; i < listVerses.size(); i++) {
			verse_kr = "";
			verse_en = "";
			verse_kr = listVerses.get(i).getVerse_kr();
			verse_kr = verse_kr.replace("'", "''");
			verse_kr = verse_kr.replace("\n", "");
			verse_en = listVerses.get(i).getVerse_en();
			verse_en = verse_en.replace("'", "''");
			verse_en = verse_en.replace("\n", "");
			txt = txt.concat("insert into verses values(" + listVerses.get(i).getVerses_sq() + ", "
													+ "'" + listVerses.get(i).getVerse_title_kr() + "', "
													+ "'" + listVerses.get(i).getVerse_title_en() + "', "
													+ ""  + listVerses.get(i).getVerse_section() + ", "
											  		+ "'" + verse_kr + "', "
									  				+ "'" + verse_en + "', " 
									  				+ ""  + listVerses.get(i).getVersion() + ", " 
									  				+ ""  + listVerses.get(i).getMon() + ", " 
									  				+ ""  + listVerses.get(i).getYr() + ");" + 
															System.lineSeparator());
		}
		createFile(txt, fileName);
	}
	public void backupUpdates() {
		ArrayList<UpdateVO2> listUpdates = (ArrayList<UpdateVO2>) backupDAO.getUpdates();
		String txt = "";
		String fileName = "5.updates.txt";
		String verse_kr = "";
		String verse_en = "";
		for(int i = 0; i < listUpdates.size(); i++) {
			verse_kr = "";
			verse_en = "";
			verse_kr = listUpdates.get(i).getVerse_kr();
			verse_kr = verse_kr.replace("'", "''");
			verse_kr = verse_kr.replace("\n", "");
			verse_en = listUpdates.get(i).getVerse_en();
			verse_en = verse_en.replace("'", "''");
			verse_en = verse_en.replace("\n", "");
			txt = txt.concat("insert into updatedates values(" + listUpdates.get(i).getUpdatedate_sq() + ", "
													+ "'" + listUpdates.get(i).getLastdate() + "', "
													+ ""  + listUpdates.get(i).getUpdate_type() + ", "
													+ ""  + listUpdates.get(i).getVerses_sq() + ", "
											  		+ "'" + listUpdates.get(i).getVerse_title_kr() + "', "
									  				+ "'" + listUpdates.get(i).getVerse_title_en() + "', "
							  						+ ""  + listUpdates.get(i).getVerse_section() + ", "
					  								+ "'" + verse_kr + "', "
									  				+ "'" + verse_en + "', "
									  				+ ""  + listUpdates.get(i).getYr() + ", " 
									  				+ ""  + listUpdates.get(i).getMon() + ", "
									  				+ ""  + listUpdates.get(i).getIspushed() + ");" +
															System.lineSeparator());
		}
		createFile(txt, fileName);
	}
	
	public void createFile(String txt, String fileName) {
		File file = new File(todayDirectory + "\\" + fileName);
		FileWriter writer = null;
        try {
        	writer = new FileWriter(file, true);
        	writer.write(txt);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
