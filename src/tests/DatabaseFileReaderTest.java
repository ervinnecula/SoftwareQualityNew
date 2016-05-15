package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import database.DatabaseDetails;

public class DatabaseFileReaderTest {

	@Rule
	public TestForFile testFile = new TestForFile(DatabaseDetails.PATH_TO_STUDENTS_FILE);
	
	@Test
	public void testFileInputStudents() {
		boolean fileExists = testFile.getFileForRead().exists();
		assertTrue(fileExists);
		if(fileExists) {		
			try {
				assertTrue(testFile.getContent().length() > 0);
			} catch (FileNotFoundException e) {
				fail("File Not Found exception!");
			} catch (IOException e) {
				fail("IO Exception!");
			}
		}
	}

}
