package Parse;

import Properties.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.math3.stat.Frequency;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class BiographyFields {

	public static Set<String> fields = new TreeSet<String>();
	public static int numberOfFields;
	public static Multiset<String> m = HashMultiset.create();
	Frequency f = new Frequency();
	Frequency fc = new Frequency();
	public void getFields(String inputFile, String outputFile)
			throws IOException {

		BufferedWriter bw = null;
		BufferedReader br = null;
		String line = null;
		int count = 0;

		try {
			ConfigFile cf = new ConfigFile();
			cf.writeToPropertiesFileBiographies();
			cf.saveFileBiographies();
			
			bw = new BufferedWriter(new FileWriter(outputFile));
			br = new BufferedReader(new FileReader(inputFile));
			line = br.readLine();

			try {
				String field = null;
				String content = null;
				String field1 = null;
				while (field1 != null) {
				if ("".equals(line) || line.contains("------------")) line = br.readLine();
				int	fieldNameSep1 = line.indexOf(':');
				if (fieldNameSep1 > 0)
					field1 = line.substring(0, fieldNameSep1);
				if (field1.length() == 2) {
					content = line.substring(fieldNameSep1 + 2, line.length()); 
					fields.add(field);
					m.add(field);
					bw.write(field1 + " " + content + "\n");
				}
				line = br.readLine();
			}
				
				for(;;) {
					
					if (line == null) break;
					if("".equals(line) || line.contains("------------")) line = br.readLine();
					int	fieldNameSep = line.indexOf(':');
					if (fieldNameSep > 0)
						field = line.substring(0, fieldNameSep);
					if (field.length() == 2) {
						content = line.substring(fieldNameSep + 2, line.length()); 
						if(field1.equals(field)) {
							bw.write( content + "\n");
							fields.add(field);
							f.addValue(field);
							m.add(field);
						}
						else {
							bw.write(field + ":	" + content + "\n");
							fields.add(field);
							m.add(field);
							f.addValue(field);
						}
					}
					line = br.readLine();
					field1 = field;
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (String s : fields) {
				System.out.println("Field code: " + s);
				System.out.println("Frequency:  " + m.count(s) + " / " + m.size() + "("+( f.getPct(s) *100 )+ ")");
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File biographies.list not found");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot read from biographies.list");
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
		System.out.println("Done!!");
	}

}