package Parse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.common.primitives.Ints;

public class PlotFields {
	
	public boolean isYear(String year) {
		int i = -1;
		if ((year.indexOf("/I") > 0 ) || (year.indexOf("/V") > 0)) return true;
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
		String line = "";
		try {
			br = new BufferedReader(new FileReader (inputFile));
			bw = new BufferedWriter(new FileWriter (outputFile));
			line = br.readLine();
			String del = "";
			String content = "";
			String finalContent = "";
			String firstMovie = "";
			String firstYear = "";
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
							String year = "";
							if (content.indexOf("/I") > 0) {
								if (content.indexOf("I)") > 0)
									year = content.substring(index + 2 , content.indexOf("I)") + 1);
								if (content.indexOf("V)") > 0)
									year = content.substring(index + 2 , content.indexOf("V)") + 1);
							} else
							if (content.indexOf("/V") > 0) {
								if (content.indexOf("I)") > 0)
									year = content.substring(index + 2 , content.indexOf("I)") + 1);
								if (content.indexOf("V)") > 0)
									year = content.substring(index + 2 , content.indexOf("V)") + 1);
							} else 
							year = content.substring(index + 2 , index + 6);
							if(isYear(year) == true) {
								System.out.println(year);
//								finalContent = line.substring(fieldSep + 2, index + 10);
								finalContent = line.substring(fieldSep + 2,index + 3);
								//System.out.println(finalContent);
								if (finalContent.equals(firstMovie) && year.equals(firstYear)) {
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
														if (content.indexOf("/I") > 0) {
															if (content.indexOf("I)") > 0)
																year = content.substring(index + 2 , content.indexOf("I)") + 1);
															if (content.indexOf("V)") > 0)
																year = content.substring(index + 2 , content.indexOf("V)") + 1);
														} else
														if (content.indexOf("/V") > 0) {
															if (content.indexOf("I)") > 0)
																year = content.substring(index + 2 , content.indexOf("I)") + 1);
															if (content.indexOf("V)") > 0)
																year = content.substring(index + 2 , content.indexOf("V)") + 1);
														} else 
														year = content.substring(index + 2 , index + 6);
														 if(isYear(year) == true) {
															System.out
																	.println(year);
															// System.out
																//	.println(line.length() + "\t" + fieldSep+2 + "\t" + index+ 3 );
															 finalContent = line.substring(fieldSep + 2,index+ 3); 
															// System.out
															//		.println(finalContent);
																if (!(finalContent.equals(firstMovie)) && (!(year.equals(firstYear)))) {
																	ok = 1;
																	firstMovie = finalContent;
																	firstYear = year;
																	bw.write("MV: "+ finalContent + "\t" + year + "\t");
																}
														 }
													}
												}
											}
										}
									} while(ok == 0);
								}
								else {
									bw.write("MV: "+ finalContent + "\t" + year + "\t");
									firstMovie = finalContent;
									firstYear = year;
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
		}  catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(line);
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
