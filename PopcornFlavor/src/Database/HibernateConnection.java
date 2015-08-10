package Database;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Entities.Actor;
import Entities.Genre;
import Entities.Movie;
import Parse.ParseActors;
import Parse.ParseGenres;
import Parse.ParseMovies;

public class HibernateConnection {

	private static SessionFactory factory;
	public static Set<String> set = new HashSet<String>();
	public static Set<Movie> setMovies = new HashSet<Movie>();
	public static Set<Actor> setActors = new HashSet<Actor>();

	public static void main(String args[]) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();

//		Adding genres into database
//		getGenre();
//		System.out.println("Adding genres into database");
//		for (String st : set) {
//			addGenre(st);
//		}
//		System.out.println("Done adding genres into database");

//		Adding movies into database
		getMovies();
		System.out.println("Adding movies into database");
		for (Movie sm : setMovies) {
			addMovie(sm.getTitle(), sm.getType(), sm.getBeginYear(),
					sm.getFinishYear(), null, 0, 0, null);
		}
		System.out.println("Done adding movies into database");

//		Adding actors into database
//		getActors();
//		System.out.println("Adding actors into database");
//		for (Actor a : setActors) {
//			addActor(a.getFirstName(), a.getLastName(), null, null, null, null,
//					null, null);
//		}
//		System.out.println("Done adding actors into database");
		
//		Update actors with nickname, height, biography, dateBirth, dateDeath
		

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

	public static Integer addGenre(String name) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer genreID = null;
		try {
			tx = session.beginTransaction();
			Genre g = new Genre(name);
			genreID = (Integer) session.save(g);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return genreID;
	}

	public static void getMovies() {
		ParseMovies pm = new ParseMovies();
		try {
			pm.ParseMovies();
			setMovies = pm.getMovies();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Integer addMovie(String name, String type, String beginYear,
			String finishYear, String plot, int tv, int tr, String img) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer movieID = null;
		try {
			tx = session.beginTransaction();
			Movie m = new Movie(name, type, beginYear, finishYear, plot, tv,
					tr, img);
			movieID = (Integer) session.save(m);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return movieID;
	}

	public static void getActors() {
		ParseActors pa = new ParseActors();
		try {
			pa.parseActors();
			setActors = pa.getActors();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Integer addActor(String firstName, String lastName,
			String nickname, String height, String bio, String db, String dd,
			String img) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer actorID = null;
		try {
			tx = session.beginTransaction();
			Actor a = new Actor(firstName, lastName, nickname, height, bio, db,
					dd, img);
			actorID = (Integer) session.save(a);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return actorID;
	}

}
