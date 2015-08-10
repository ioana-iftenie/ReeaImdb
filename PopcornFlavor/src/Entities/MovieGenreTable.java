package Entities;

public class MovieGenreTable {
	private int idMovieGenre;
	private int idMovie;
	private int idGenre;

	public MovieGenreTable(int idMovie, int idGenre) {
		this.idMovie = idMovie;
		this.idGenre = idGenre;
	}

	public MovieGenreTable() {

	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

	public int getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}

	public int getIdMovieGenre() {
		return idMovieGenre;
	}

	public void setIdMovieGenre(int idMovieGenre) {
		this.idMovieGenre = idMovieGenre;
	}

}
