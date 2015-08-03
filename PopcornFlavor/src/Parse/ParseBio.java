package Parse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Entities.Plot;

import com.google.common.primitives.Ints;

public class ParseBio {
	public List<Plot> plot  = new ArrayList<Plot>();
	
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
	
	public void populateList() throws IOException {
		BufferedReader br = new BufferedReader (new FileReader("lists\\plot.txt"));
		BufferedWriter bw = new BufferedWriter (new FileWriter("lists\\plot2.txt"));

		
		for(;;) {
			String line = br.readLine();
			if (line == null) break;
			int tabSep = line.indexOf("\t");
			if (tabSep > 0) {
				String year = line.substring(tabSep - 5, tabSep - 1);
				if (isYear(year)) {
					String plot = line.substring(tabSep + 1, line.length());
					String title = line.substring(4, tabSep -6);
					bw.write(title + "\t" + year + "\t" + plot + "\n");
				}
			}
		} 
	}

}
