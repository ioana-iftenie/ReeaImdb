package Parse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.common.primitives.Ints;

public class ParsePlot {
	
	public boolean isYear(String year) {
		int i = -1;
		Integer s = Ints.tryParse(year);
		if (s != null)
			i = s.intValue();
		
		if (i ==-1)
			return false;
		else
			return true;
	}
	

	public void getFields(String inputFile, String outputFile) {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		int index;
		String[] delimitator = {"MV", "PL"};
		
		try {
			br = new BufferedReader(new FileReader (inputFile));
			bw = new BufferedWriter(new FileWriter (outputFile));
			String line = br.readLine();
			String del = "";
			String content = "";
			String finalContent = "";
			String firstMovie = "";
			int ok = 0;
			for(;;) {
				if(line == null) break;
				if("".equals(line)) line = br.readLine(); // de pus si in while
				else {
					if(line.equals("-------------------------------------------------------------------------------")) {
						bw.write("\n");
					}
				int fieldSep = line.indexOf(':');
				if (fieldSep > 0) {
					del = line.substring(0, fieldSep);
					if (del.length() == 2 && del.equals(delimitator[0]) ) { // MV
						content = (String) line.subSequence(fieldSep + 1, line.length());
						index  = content.indexOf(" (");
						if ( index > 0) {
							String year = content.substring(index + 2 , index + 6);
							if(isYear(year) == true) {
								finalContent = line.substring(fieldSep + 2, index + 10);
								//System.out.println(finalContent);
								if (finalContent.equals(firstMovie)) {
									ok = 0;
									do {
										line = br.readLine();
										if(line == null) break;
										if("".equals(line)) 
											line = br.readLine();
										else {
										    fieldSep = line.indexOf(':');
											if (fieldSep > 0) {
												del = line.substring(0, fieldSep);
												if (del.length() == 2 && del.equals(delimitator[0]) ) { 
													content = (String) line.subSequence(fieldSep + 1, line.length());
													index  = content.indexOf(" (");
													if ( index > 0) {
														 year = content.substring(index + 2 , index + 6);
														 if(isYear(year) == true) {
																finalContent = line.substring(fieldSep + 2, index + 10);
																if (!(finalContent.equals(firstMovie))) {
																	ok = 1;
																	firstMovie = finalContent;
																	bw.write("MV: "+ finalContent + "\t");
																}
														 }
													}
												}
											}
										}
									} while(ok == 0);
								}
								else {
									bw.write("MV: "+ finalContent + "\t");
									firstMovie = finalContent;
								}
								
							}
						}
					}
					if (del.length() == 2 && del.equals(delimitator[1])) { //PL
						content = line.substring(fieldSep + 1, line.length());
						bw.write(content);
					}
					
				}
				line = br.readLine();
				
			}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File " + inputFile + " was not fount \nExeception:" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot read from file \nException " + e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
					System.out.println("Input File - closed");
				}

				if (bw != null) {
					bw.close();
					System.out.println("Output File - closed");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
