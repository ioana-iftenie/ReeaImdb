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

public class ParsePlot {
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
				String title = line.substring(3,tabSep +1).trim();
				String left = line.substring(title.length() + 5, line.length());
				int tabSepYear = left.indexOf("\t");
				String year = left.substring(0, tabSepYear);
				String content = left.substring(year.length() + 1, left.length());
				plot.add(new Plot(title, year, content));
				//bw.write(title + "\t" + year + "\t" + content + "\n");
			}
		} 
	}
	
	public List getPlot() {
		return plot;
	}

}
