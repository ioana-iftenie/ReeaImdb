package Parse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import Properties.ConfigFile;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class ParseActors {

	public void parseActors(String inputFile, String outputFile)
			throws IOException {

		int count = 0;
		BufferedReader br = null;
		BufferedWriter bw = null;
		DescriptiveStatistics actorStatistics = new DescriptiveStatistics();
		DescriptiveStatistics roleStatistics = new DescriptiveStatistics();
		DescriptiveStatistics movieStatistics = new DescriptiveStatistics();

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					inputFile), "windows-1252"));
			bw = new BufferedWriter(new FileWriter(outputFile));

			try {

				for (;;) {
					String line = br.readLine();
					if (line == null)
						break;

					if (count++ < 239)
						continue;

					int actorNameSeparator = line.indexOf("\t");

					if (actorNameSeparator > 0) {
						String actorName = line
								.substring(0, actorNameSeparator).trim();
						actorStatistics.addValue(actorName.length());
					}

					int movieNameSeparator1 = line.indexOf("(1");
					int movieNameSeparator2 = line.indexOf("(2");
					int movieNameSeparator3 = line.indexOf("(?");

					if (movieNameSeparator1 > 0) {
						String movieName = line.substring(
								actorNameSeparator + 1, movieNameSeparator1)
								.trim();
						movieStatistics.addValue(movieName.length());
					} else {
						if (movieNameSeparator2 > 0) {
							String movieName = line
									.substring(actorNameSeparator + 1,
											movieNameSeparator2).trim();
							movieStatistics.addValue(movieName.length());
						} else {
							if (movieNameSeparator3 > 0) {
								String movieName = line.substring(
										actorNameSeparator + 1,
										movieNameSeparator3).trim();
								movieStatistics.addValue(movieName.length());
							}
						}
					}

					int roleStartSeparator = line.indexOf('[');
					int roleEndSeparator = line.indexOf(']');

					if (roleStartSeparator > 0) {
						String roleName = line.substring(
								roleStartSeparator + 1, roleEndSeparator);
						roleStatistics.addValue(roleName.length());
					}

					int actorSeparator = line.indexOf("\n");
					int movieSeparator = line.indexOf("\t\t\t");

					if (actorSeparator > 0) {

						while (movieSeparator > 0) {

							int newMovieNameSeparator1 = line.indexOf("(1");
							int newMovieNameSeparator2 = line.indexOf("(2");
							int newMovieNameSeparator3 = line.indexOf("(?");

							if (newMovieNameSeparator1 > 0) {
								String newMovieName = line.substring(0,
										newMovieNameSeparator1).trim();
								movieStatistics.addValue(newMovieName.length());
							} else {
								if (newMovieNameSeparator2 > 0) {
									String newMovieName = line.substring(0,
											newMovieNameSeparator2).trim();
									movieStatistics.addValue(newMovieName
											.length());
								} else {
									if (newMovieNameSeparator3 > 0) {
										String newMovieName = line.substring(0,
												newMovieNameSeparator3).trim();
										movieStatistics.addValue(newMovieName
												.length());
									}
								}
							}

							int newRoleStartSeparator = line.indexOf('[');
							int newRoleEndSeparator = line.indexOf(']');

							if (newRoleStartSeparator > 0) {
								String newRoleName = line.substring(
										newRoleStartSeparator + 1,
										newRoleEndSeparator);
								roleStatistics.addValue(newRoleName.length());
							}
						}
					}
				}
				
				ConfigFile cf = new ConfigFile();
				cf.writeToPropertiesFileActor();
				cf.addPropActor("No-Of-Actors", actorStatistics.getN());
				cf.addPropActor("Max-Length-Actor", actorStatistics.getMax());
				cf.addPropActor("Min-Length-Actor", actorStatistics.getMin());
				cf.addPropActor("Avg-Length-Actor", actorStatistics.getMean());
				cf.addPropActor("No-Of-Movies", movieStatistics.getN());
				cf.addPropActor("Max-Length-Actor", movieStatistics.getMax());
				cf.addPropActor("Min-Length-Actor", movieStatistics.getMin());
				cf.addPropActor("Avg-Length-Actor", movieStatistics.getMean());
				cf.addPropActor("No-Of-Roles", roleStatistics.getN());
				cf.addPropActor("Max-Length-Actor", roleStatistics.getMax());
				cf.addPropActor("Min-Length-Actor", roleStatistics.getMin());
				cf.addPropActor("Avg-Length-Actor", roleStatistics.getMean());
				cf.saveFileActor();			

				bw.write("File name: actors.list" + "\n");
				bw.write("Fields:" + "\n\n");

				bw.write("Field #1" + "\n");
				bw.write("Field name: actor name" + "\n");
				bw.write("Frequency: " + actorStatistics.getN() + " values"
						+ "\n");
				bw.write("Maximum length of the values: "
						+ actorStatistics.getMax() + "\n");
				bw.write("Minimum length of the values: "
						+ actorStatistics.getMin() + "\n");
				bw.write("Mean length of the values: "
						+ actorStatistics.getMean() + "\n\n");

				bw.write("Field #2" + "\n");
				bw.write("Field name: movie name" + "\n");
				bw.write("Frequency: " + movieStatistics.getN() + " values"
						+ "\n");
				bw.write("Maximum length of the values: "
						+ movieStatistics.getMax() + "\n");
				bw.write("Minimum length of the values: "
						+ movieStatistics.getMin() + "\n");
				bw.write("Mean length of the values: "
						+ movieStatistics.getMean() + "\n\n");

				bw.write("Field #3" + "\n");
				bw.write("Field name: role name" + "\n");
				bw.write("Frequency: " + roleStatistics.getN() + " values"
						+ "\n");
				bw.write("Maximum length of the values: "
						+ roleStatistics.getMax() + "\n");
				bw.write("Minimum length of the values: "
						+ roleStatistics.getMin() + "\n");
				bw.write("Mean length of the values: "
						+ roleStatistics.getMean());

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File actors.list not found");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot read from actors.list");
		} finally {
			try {
				if (br != null) {
					br.close();
				}

				if (bw != null) {
					bw.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
