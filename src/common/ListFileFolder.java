package common;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ListFileFolder {
	
	public List<String> listFilesForFolder(File folder) {
		List<String> list = new ArrayList<>();
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            list.add(fileEntry.getName());
	        }
	    }
	    Collections.reverse(list);
	    return list;
	}
}
