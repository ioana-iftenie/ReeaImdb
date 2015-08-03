package Database;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Entities.Genre;
import Entities.Movie;
import Parse.ParseGenres;
import Parse.ParseMovies;

public class HibernateConnection {

	private static SessionFactory factory;
	public static Set<String> set = new HashSet<String>();
	
	public static Set<Movie> setMovies = new HashSet<Movie>();

	public static void main(String args[]) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();	
		
		getGenre();
		 for(String st: set) {
			 addGenre(st);
			 System.out.println("Added " + st);
		 }
		 
		 System.out.println("Finished adding genres to database");
		
//		getMovies();
//		System.out.println("Adding movies to database");
//		for(Movie sm: setMovies) {
//			addMovie(sm.getTitle(), sm.getType(), sm.getBeginYear(), sm.getFinishYear(), null, 0, 0,null);
//		}
//		System.out.println("Done");
		 s.close();
	}

	public static void getGenre() {
		 ParseGenres pg = new ParseGenres();
         try {
			pg.parseGenres();
			set = pg.getHash();
		} catch (IOException e) {
			e.printStackTrace();
		}
         
	}

	
	public static void getMovies() {
		ParseMovies pm = new ParseMovies();
        pm.ParseMovies();
		setMovies = pm.getMovies();
        
	}
	
	 public static Integer addMovie(String name, String type, String beginYear, String finishYear, String plot, int tv, int tr, String img) {
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer movieID = null;
	      try{
		         tx = session.beginTransaction();
		         Movie m = new Movie(name, type, beginYear, finishYear, plot, tv,tr, img);
		         movieID = (Integer) session.save(m); 
		         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return movieID;
	   }
	
	 public static Integer addGenre(String name) {
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer genreID = null;
	      try{
		         tx = session.beginTransaction();
		         Genre g = new Genre(name);
		         genreID = (Integer) session.save(g); 
		         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return genreID;
	   }
}
