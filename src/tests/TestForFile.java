package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.rules.ExternalResource;

public class TestForFile extends ExternalResource {

	private String fileName;
	private File fileForRead = null;
	
	public TestForFile( String fileName) {
		this.fileName = fileName;
		this.fileForRead = new File(fileName);
	}
	
	public String getContent( ) throws FileNotFoundException, IOException
    {
		String fileContent = "";
			BufferedReader br = new BufferedReader(new FileReader(fileForRead));
			String fileLine = br.readLine();

			while (fileLine != null) {
				if (!fileLine.isEmpty()) {
					fileContent += fileLine;
				}
				fileLine = br.readLine();
			}
			br.close();
		return fileContent;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFileForRead() {
		return fileForRead;
	}

	public void setFileForRead(File fileForRead) {
		this.fileForRead = fileForRead;
	}
	
	
	
}
