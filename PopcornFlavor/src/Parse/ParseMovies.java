package Parse;

import Entities.Movie;
import Properties.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.math3.stat.Frequency;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.log4j.Logger;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;

public class ParseMovies {

	Set<Movie> movies = new HashSet<Movie>();
	static Logger log = Logger.getLogger(ParseMovies.class.getName());
	Multiset<String> m = HashMultiset.create();
	
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

	public void ParseMovies() {
		ConfigFile cf = new ConfigFile();
	//	String line;
		int count = -1;
		int yearSep1 = -1;
		int yearSep2 = -1;
		int yearSep3 = -1;
		int yearSep = -1;
		String put = null;
		String plot = null;
		String img = null;
		int tv = 0;
		int tr = 0;
		String line = "";
		Frequency freq = new Frequency();
		Frequency freqTitle = new Frequency();
		DescriptiveStatistics statistic = new DescriptiveStatistics();

		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream("lists\\movies.list"), "windows-1252"));
			
			for(;;) {
				line = br.readLine();
				if (line == null) break;
				if("".equals(line)) 
					line = br.readLine();
				else {
					int verifySuspended = line.indexOf("{{SUSPENDED}}");
					if (verifySuspended < 0) {
						int tabSep = line.indexOf("\t");
						
						if (tabSep > 0) {
							String left = line.substring(0, tabSep);
							String right = line.substring(tabSep + 1, line.length());
							String title = "";
							String year = "";
							String type = "";
							String digit0 = "(?";
							String digit1 = "(1";
							String digit2 = "(2";
							yearSep1 = left.indexOf(digit1);
							yearSep2 = left.indexOf(digit2);
							yearSep3 = left.indexOf(digit0);

							if (yearSep1 > 0)
								yearSep = yearSep1;
							else if (yearSep2 > 0)
								yearSep = yearSep2;
							else if (yearSep3 > 0)
								yearSep = yearSep3;
							String wholeYear = line.substring(yearSep + 1, yearSep+ 5);
							if (yearSep > 0 && isYear(wholeYear) == true || wholeYear.equals("????")) {
								title = left.substring(0, yearSep);
								if(left.contains("/I")) {
									String partYear = wholeYear;
									int k = line.indexOf(partYear);
									if ((line.substring(k + 4, k + 6)).equals("/I") || (line.substring(k + 4, k + 6)).equals("/V")) {
										if (left.indexOf("I)") > 0) 
											year = left.substring(yearSep + 1,  left.indexOf("I)") + 1 );
										else
										if (left.indexOf("IV)") > 0)
											year = left.substring(yearSep + 1,  left.indexOf("IV)") + 2 );
										System.out.println(title + "\t" + year);
									}
									else
										if (!((line.substring(k + 4, k + 6)).equals("/I")) && !( (line.substring(k + 4, k + 6)).equals("/V")))
											year = partYear;
								} else {
									year = wholeYear;
								}
							}
							if (right.length() > 0) {
								String[] years = line.substring(tabSep + 5,
										line.length()).split("-");
								if (years.length > 1) {
									type = "SERIES";
									movies.add(new Movie(title, "SERIES", year, years[1], null, 0, 0, null));
								}
								else {
									if (line.indexOf("(VG)") > 0)
										type = "VG";
									else
										type = "MOVIE";
									movies.add(new Movie(title, type, year, null, null, 0, 0, null));
								}
									
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(line + "\n" +  e.getMessage());
		}
		int k = 0;		
		System.out.println(freq);

	}
	
	public Set getMovies() {
		return movies;
	}

	public void displayHashSet() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("lists\\MOVIES.txt"));
		System.out.println("Start writing in file");
		for (Movie m : movies) {
			String line = m.getTitle() + " " + m.getType() + " " + m.getBeginYear()
					+ " " + m.getFinishYear() + "\n";
			bw.write(line);
		}
		bw.close();
		System.out.println("Ended writing in file");
	}
}
