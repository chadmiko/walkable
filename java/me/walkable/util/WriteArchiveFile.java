/**
 * 
 */
package me.walkable.util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Christopher Butera
 *
 */
public class WriteArchiveFile {

	public final static String JSON_DIR = "./jsonFiles";

	
	public static void writeFile(String vendor, String fileContents, String fileExtension){
		FileWriter gWriter; // I orginally used BufferedWriter - but it couldn't write everything to the file - kept creating incomplete files.
		try {
			String fileName = JSON_DIR + "/" + vendor + "-" + UtcTime.getFileTime() + fileExtension;
			gWriter = new FileWriter(fileName) ;
			gWriter.write(fileContents);
			gWriter.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

}
