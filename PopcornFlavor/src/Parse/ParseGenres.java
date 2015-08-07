package Parse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import Entities.Genre;
import Entities.MovieGenre;

public class ParseGenres {

	private Set<Genre> genres = new HashSet<Genre>();
	private List<MovieGenre> mg = new ArrayList<MovieGenre>();

	public void parseGenres() throws IOException {

		int count = 0;
		BufferedReader br = null;
		// BufferedWriter bw = null;
		DescriptiveStatistics movieStatistics = new DescriptiveStatistics();
		DescriptiveStatistics genreStatistics = new DescriptiveStatistics();
		String line  = "";
		String movieName = "";
		String year = "";
		int yearSep = -1;
		try {

			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"lists\\genres.list"), "windows-1252"));
			// bw = new BufferedWriter(new FileWriter(outputFile));

			try {

				for (;;) {
					line = br.readLine();
					if (line == null)
						break;

					if (count++ < 380 || line.contains(" {"))
						continue;

					int mSep1 = line.indexOf(" (1");
					int mSep2 = line.indexOf(" (2");
					int mSep3 = line.indexOf(" (?");
					
				

					if (mSep1 > 0)
						yearSep = mSep1;
					else if (mSep2 > 0)
						yearSep = mSep2;
					else if (mSep3 > 0)
						yearSep = mSep3;

					if (yearSep > 0) {
						movieName = line.substring(0, yearSep);
						String right = line.substring(movieName.length() + 2, line.length());
						year = right.substring(0,
								right.indexOf("\t") - 1);
						if (year.length() > 4) {
							if (year.contains("/I") || year.contains("/V"))
								year = right.substring(0, right.indexOf(")"));
							else
								year = right.substring(0, right.indexOf(")"));
						}
				

					int genreSeparator = line.indexOf("\t");

					if (genreSeparator > 0) {
						String genreName = line.substring(genreSeparator,
								line.length()).trim();
						mg.add( new MovieGenre(movieName, year, genreName));
						genres.add(new Genre( genreName));
					}

					}
				}
				//
				// ConfigFile cf = new ConfigFile();
				// cf.writeToPropertiesFileGenres();
				// cf.addPropGenres("Max-Length-MovieName",
				// movieStatistics.getMax());
				// cf.addPropGenres("Min-Length-MovieName",
				// movieStatistics.getMin());
				// cf.addPropGenres("Avg-Length-MovieName",
				// movieStatistics.getMean());
				// cf.addPropGenres("Max-Length-GenreName",
				// genreStatistics.getMax());
				// cf.addPropGenres("Min-Length-GenreName",
				// genreStatistics.getMin());
				// cf.addPropGenres("Avg-Length-GenreName",
				// genreStatistics.getMean());
				// cf.saveFileGenres();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File genres.list not found");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot read from genres.list");
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("ERROR  AT  LINE  " + line);
			e.printStackTrace();
		} finally {
			System.out.println(mg.size() + " Elemente in lista \n" + mg.get(mg.size() -1).getTitle() + "\t" +mg.get(mg.size() -1).getYear() + "\t" + mg.get(mg.size() -1).getGenre());
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Set getHash() {
		return genres;
	}
	
	public List getMovieGenre() {
		return mg;
	}
}