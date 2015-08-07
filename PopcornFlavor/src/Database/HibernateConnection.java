package Database;

import java.io.IOException;
import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Entities.*;
import Parse.*;

public class HibernateConnection {

	private static SessionFactory factory;
	public static Set<String> set = new HashSet<String>();
	
	public static Set<Movie> setMovies = new HashSet<Movie>();
	public static List<Genre> listGenre = new ArrayList<Genre>();
	public static List<MovieGenre> movieGenreList = new ArrayList<MovieGenre>();
	
	public static List<Plot> plotList = new ArrayList<Plot>();

	public static void main(String args[]) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
		//Session s = factory.openSession();
		//Transaction tx = s.beginTransaction();	
		
//		getGenre();
//		 for(String st: set) {
//			 addGenre(st);
//			 System.out.println("Added " + st);
//		 }
//		 
//		 System.out.println("Finished adding genres to database");
//		
//		getMovies();
//		System.out.println("Adding movies to database");
//		for(Movie sm: setMovies) {
//			addMovie(sm.getTitle(), sm.getType(), sm.getBeginYear(), sm.getFinishYear(), null, 0, 0,null);
//		}
//		System.out.println("Done");
//		System.out.println("Start");
//		 System.out.println("Start getting plot");
//		 getPlot();
//		 System.out.println("done getting plot");
//		for(int i=0;i<plotList.size(); i++) {
//		 UpdatePlot(plotList.get(i).getPlot(), plotList.get(i).getTitle(), plotList.get(i).getYear(), i);
//		}
		 

		ParseGenres pg = new ParseGenres();
		try {
			pg.parseGenres();
			movieGenreList = pg.getMovieGenre();
			for (int i = 1; i<=32; i++) {
				getGenreToList(i);
			}
//			int i=0;
			for (MovieGenre mg: movieGenreList) {
//				if( i>10)
//					break;
				//System.out.println(mg.getTitle() + "\t" + mg.getYear() + "\t" + mg.getGenre());
				addMovieGenre(mg.getTitle(), mg.getYear(), mg.getGenre());
				//i++;
			}
			System.out.println("Done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public static void getPlot() {
		 ParsePlot pp = new ParsePlot();
        try {
			pp.populateList();
			plotList = pp.getPlot();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}

	
	public static void getMovies() {
		ParseMovies pm = new ParseMovies();
        pm.ParseMovies();
		setMovies = pm.getMovies();
        
	}
	
	 public static Integer addMovieGenre(String titleMovie, String year, String genreName) {
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer movieGenreTableID = null;
	      int idGenre = 0;
	      try{
		         Query query = session.createQuery("Select idMovie from Movie where title = :Title and beginYear = :BeginYear");
		         query.setParameter("Title",titleMovie);
		         query.setParameter("BeginYear", year);
		        // Movie m = (Movie)query.list().get(0);
		         List m = query.list();
		         if (m.size() > 0) {
		        // System.out.print(m.get(0) + "\t");
		         for (int i = 0; i< listGenre.size(); i++)
		        	 if (listGenre.get(i).getName().equals(genreName))
		        		 idGenre = listGenre.get(i).getIdGenre();
		        // System.out.print(idGenre + "\n");
		         tx = session.beginTransaction();
		         MovieGenreTable mgt = new MovieGenreTable((int) m.get(0), idGenre);
		         movieGenreTableID = (Integer) session.save(mgt); 
		         //System.out.println(mgt.getIdGenre() + "\t" + mgt.getIdMovie() + m.getTitle() );
		         
		         tx.commit();
		         }
	      }catch (Exception  e) {
	    	  System.out.println(titleMovie + "\t" + year + "\t" + genreName);
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return movieGenreTableID;
	   }
	
	public static Integer getMovieDetails(String title, String year){
		Session session = factory.openSession();
		
			Query query = session.createQuery("from Movie where title = :Title and beginYear = :BeginYear");
	         query.setParameter("Title",title);
	         query.setParameter("BeginYear", year);
	         Movie m = (Movie)query.list().get(0);
	         System.out.println(m.getTitle()+ "\t" + m.getBeginYear() + "\t" + m.getPlot());
	         
	     
		return 0;
		
		
	}
	
	
	public static Integer getGenreToList(int i){
		Session session = factory.openSession();
		
			Query query = session.createQuery("from Genre where idGenre = :id");
			query.setParameter("id", i);
	       
	         Genre g = (Genre)query.list().get(0);
	         //System.out.println(m.getTitle()+ "\t" + m.getBeginYear() + "\t" + m.getPlot());
	         listGenre.add(new Genre(g.getIdGenre(), g.getName()));
	         //listGenre.get(i).setIdGenre(g.getIdGenre());
	     
		return 0;
		
		
	}
	public static Integer UpdatePlot(String plot, String title, String year, int i) {
		Session session = factory.openSession();
		Transaction tx = null;
		 Integer movieID = null;
	      try{
	    	   tx = session.beginTransaction();
	    	   Query query = session.createQuery("update Movie set plot = :Plot where title = :Title and beginYear = :BeginYear");
		         query.setParameter("Plot",plot);
		         query.setParameter("Title",title);
		         query.setParameter("BeginYear",year);
	  		        int result = query.executeUpdate();
	  		         System.out.println(i + "\t Updated " + title );
	  		         tx.commit();
	    	  
	      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		         System.out.println("Error " + e.getMessage());
		      }finally {
		         session.close(); 
		      }
		      return movieID;
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
