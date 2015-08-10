package Parse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import Properties.ConfigFile;

public class ParseGenres {

	private Set<String> genres = new HashSet<String>();

	public void parseGenres()
			throws IOException {

		int count = 0;
		BufferedReader br = null;
	//	BufferedWriter bw = null;
		DescriptiveStatistics movieStatistics = new DescriptiveStatistics();
		DescriptiveStatistics genreStatistics = new DescriptiveStatistics();

		try {

			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"lists\\genres.list"), "windows-1252"));
		//	bw = new BufferedWriter(new FileWriter(outputFile));

			try {

				for (;;) {
					String line = br.readLine();
					if (line == null)
						break;

					if (count++ < 380)
						continue;

					int mSep1 = line.indexOf("(1");
					int mSep2 = line.indexOf("(2");
					int mSep3 = line.indexOf("(?");

					if (mSep1 > 0) {
						String movieName = line.substring(0, mSep1);
						// System.out.println(movieName);
						movieStatistics.addValue(movieName.length());
					//	bw.write(movieName + " ");
					} else {
						if (mSep2 > 0) {
							String movieName = line.substring(0, mSep2);
							// System.out.println(movieName);
							movieStatistics.addValue(movieName.length());
						//	bw.write(movieName + " ");
						} else {
							if (mSep3 > 0) {
								String movieName = line.substring(0, mSep3);
								// System.out.println(movieName);
								movieStatistics.addValue(movieName.length());
							//	bw.write(movieName + " ");
							}
						}
					}

					int genreSeparator = line.indexOf("\t");

					if (genreSeparator > 0) {
						String genreName = line.substring(genreSeparator,
								line.length()).trim();
						genres.add(genreName);
						genreStatistics.addValue(genreName.length());
					//	bw.write(genreName + " ");
					}

					//bw.write("\n");
				}

				ConfigFile cf = new ConfigFile();
				cf.writeToPropertiesFileGenres();
				cf.addPropGenres("Max-Length-MovieName",
						movieStatistics.getMax());
				cf.addPropGenres("Min-Length-MovieName",
						movieStatistics.getMin());
				cf.addPropGenres("Avg-Length-MovieName",
						movieStatistics.getMean());
				cf.addPropGenres("Max-Length-GenreName",
						genreStatistics.getMax());
				cf.addPropGenres("Min-Length-GenreName",
						genreStatistics.getMin());
				cf.addPropGenres("Avg-Length-GenreName",
						genreStatistics.getMean());
				cf.saveFileGenres();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File genres.list not found");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot read from genres.list");
		} finally {
			try {
				if (br != null) {
					br.close();
				}

//				if (bw != null) {
//					bw.close();
//				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Set<String> getHash() {
		return genres;
	}
}
