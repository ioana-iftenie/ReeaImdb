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

import Entities.Actor;
import Entities.Movie;
import Properties.ConfigFile;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.log4j.Logger;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class ParseActors {

	public static Set<Actor> actors = new HashSet<Actor>();
	static Logger log = Logger.getLogger(ParseActors.class.getName());

	public void parseActors() throws IOException {

		int count = 0;
		BufferedReader br = null;
		BufferedWriter bw = null;
//		DescriptiveStatistics actorStat = new DescriptiveStatistics();
//		DescriptiveStatistics firstNameStat = new DescriptiveStatistics();
//		DescriptiveStatistics lastNameStat = new DescriptiveStatistics();
//		DescriptiveStatistics roleStat = new DescriptiveStatistics();
//		DescriptiveStatistics movieStat = new DescriptiveStatistics();
		String actor = "";
		String lastName = "";
		String firstName = "";
		String movie = "";
		String role = "";
		String newMovie = "";
		String newRole = "";

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"lists\\actors.list"), "windows-1252"));
			bw = new BufferedWriter(new FileWriter("lists\\ACTORS.txt"));

			try {

				for (;;) {
					String line = br.readLine();

					if (line == null)
						break;

					if (count++ < 239)
						continue;

					if (count == 17790410)
						break;

					int actorNameSeparator = line.indexOf("\t");

					if (actorNameSeparator > 0) {
						actor = line.substring(0, actorNameSeparator).trim();
//						actorStat.addValue(actor.length());
						int sep = actor.indexOf(",");
						if (sep > 0) {
							lastName = actor.substring(0, sep);
							firstName = actor
									.substring(sep + 1, actor.length()).trim();
//							lastNameStat.addValue(lastName.length());
//							firstNameStat.addValue(firstName.length());
							Actor A = new Actor(firstName, lastName);
							actors.add(A);
//							if (actors.add(A))
//							System.out.println(A.getFirstName() + "\t" +
//							A.getLastName());
						}
					}

					int movieSep1 = line.indexOf("(1");
					int movieSep2 = line.indexOf("(2");
					int movieSep3 = line.indexOf("(?");

					if (movieSep1 > 0) {
						movie = line.substring(actorNameSeparator + 1,
								movieSep1).trim();
//						movieStat.addValue(movie.length());
					} else {
						if (movieSep2 > 0) {
							movie = line.substring(actorNameSeparator + 1,
									movieSep2).trim();
//							movieStat.addValue(movie.length());
						} else {
							if (movieSep3 > 0) {
								movie = line.substring(actorNameSeparator + 1,
										movieSep3).trim();
//								movieStat.addValue(movie.length());
							}
						}
					}

					int roleStartSep = line.indexOf('[');
					int roleEndSep = line.indexOf(']');

					if (roleStartSep > 0) {
						role = line.substring(roleStartSep + 1, roleEndSep);
//						roleStat.addValue(role.length());
					}

					int actorSep = line.indexOf("\n");
					int movieSep = line.indexOf("\t\t\t");

					if (actorSep > 0) {

						while (movieSep > 0) {

							int newMovieSep1 = line.indexOf("(1");
							int newMovieSep2 = line.indexOf("(2");
							int newMovieSep3 = line.indexOf("(?");

							if (newMovieSep1 > 0) {
								newMovie = line.substring(0, newMovieSep1)
										.trim();
//								movieStat.addValue(newMovie.length());
							} else {
								if (newMovieSep2 > 0) {
									newMovie = line.substring(0, newMovieSep2)
											.trim();
//									movieStat.addValue(newMovie.length());
								} else {
									if (newMovieSep3 > 0) {
										newMovie = line.substring(0,
												newMovieSep3).trim();
//										movieStat.addValue(newMovie.length());
									}
								}
							}

							int newRoleStartSep = line.indexOf('[');
							int newRoleEndSep = line.indexOf(']');

							if (newRoleStartSep > 0) {
								newRole = line.substring(newRoleStartSep + 1,
										newRoleEndSep);
//								roleStat.addValue(newRole.length());
							}
						}
					}
				}

				System.out.println("1. actors size: " + actors.size());
				
//				ConfigFile cf = new ConfigFile();
//				cf.writeToPropertiesFileActor();
//
//				cf.addPropActor("No-Of-Actors", actorStat.getN());
//
//				cf.addPropActor("Max-Length-FirstName", firstNameStat.getMax());
//				cf.addPropActor("Min-Length-FirstName", firstNameStat.getMin());
//				cf.addPropActor("Avg-Length-FirstName", firstNameStat.getMean());
//
//				cf.addPropActor("Max-Length-LastName", lastNameStat.getMax());
//				cf.addPropActor("Min-Length-LastName", lastNameStat.getMin());
//				cf.addPropActor("Avg-Length-LastName", lastNameStat.getMean());
//
//				cf.addPropActor("Max-Length-MovieName", movieStat.getMax());
//				cf.addPropActor("Min-Length-MovieName", movieStat.getMin());
//				cf.addPropActor("Avg-Length-MovieName", movieStat.getMean());
//
//				cf.addPropActor("Max-Length-RoleName", roleStat.getMax());
//				cf.addPropActor("Min-Length-RoleName", roleStat.getMin());
//				cf.addPropActor("Avg-Length-RoleName", roleStat.getMean());
//				cf.saveFileActor();
//
//				bw.write("File name: actors.list" + "\n");
//				bw.write("Fields:" + "\n\n");
//
//				bw.write("Field #1" + "\n");
//				bw.write("Field name: first name" + "\n");
//				bw.write("Frequency: " + firstNameStat.getN() + " values"
//						+ "\n");
//				bw.write("Maximum length of the values: "
//						+ firstNameStat.getMax() + "\n");
//				bw.write("Minimum length of the values: "
//						+ firstNameStat.getMin() + "\n");
//				bw.write("Mean length of the values: "
//						+ firstNameStat.getMean() + "\n\n");
//
//				bw.write("Field #2" + "\n");
//				bw.write("Field name: last name" + "\n");
//				bw.write("Frequency: " + lastNameStat.getN() + " values" + "\n");
//				bw.write("Maximum length of the values: "
//						+ lastNameStat.getMax() + "\n");
//				bw.write("Minimum length of the values: "
//						+ lastNameStat.getMin() + "\n");
//				bw.write("Mean length of the values: " + lastNameStat.getMean()
//						+ "\n\n");
//
//				bw.write("Field #3" + "\n");
//				bw.write("Field name: movie name" + "\n");
//				bw.write("Frequency: " + movieStat.getN() + " values" + "\n");
//				bw.write("Maximum length of the values: " + movieStat.getMax()
//						+ "\n");
//				bw.write("Minimum length of the values: " + movieStat.getMin()
//						+ "\n");
//				bw.write("Mean length of the values: " + movieStat.getMean()
//						+ "\n\n");
//
//				bw.write("Field #4" + "\n");
//				bw.write("Field name: role name" + "\n");
//				bw.write("Frequency: " + roleStat.getN() + " values" + "\n");
//				bw.write("Maximum length of the values: " + roleStat.getMax()
//						+ "\n");
//				bw.write("Minimum length of the values: " + roleStat.getMin()
//						+ "\n");
//				bw.write("Mean length of the values: " + roleStat.getMean());

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

	public Set getActors() {
		return actors;
	}
}
