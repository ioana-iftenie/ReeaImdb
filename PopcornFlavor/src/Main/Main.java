package Main;

import java.io.IOException;

import Parse.*;
import Properties.ConfigFile;

public class Main {
	public static void main(String argss[]) {
//		ConfigFile app = new ConfigFile();
//		ParseActors p = new ParseActors();
//		System.out.println("Parsing actors");
//		
//		try {
//			p.parseActors("lists\\actors.list", "lists\\ACTORS.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		ParseMovies pm = new ParseMovies();
//		System.out.println("Parsing movies");
//		pm.ParseMovies();
//		try {
//			pm.displayHashSet();
//		} catch (IOException e) {
//			System.out.println("Errors at writing in file");
//		}
//		System.out.println("Job done!!");
//		
//		BiographyFields bf = new BiographyFields();
//		System.out.println("Parsing bio");
//		try {
//			bf.getFields("lists\\biographies.list", "lists\\biographies.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Parsing plot");
//		ParsePlot pp = new ParsePlot();
//		pp.getFields("lists\\plot.list", "lists\\plot.txt");
//		System.out.println("Job done");
		
		ParseBio pb = new ParseBio();
		try {
			pb.populateList();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error " + e.getMessage());
		}
	}
}
