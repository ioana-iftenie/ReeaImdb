package Entities;

public class MovieGenre {
	private String title;
	private String year;
	private String genre;

	public MovieGenre(String title, String year, String genre) {
		this.title = title;
		this.year = year;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int hashCode() {
		int result = 1;
		result = result * 37 + title.hashCode();
		result = 37 * result + year.hashCode();
		result = 37 * result + genre.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MovieGenre))
			return false;
		MovieGenre aMovie = (MovieGenre) o;

		if ((title == null) && (aMovie.title != null))
			return false;
		if ((aMovie.title == null) && (title != null))
			return false;
		if ((title != null) && !title.equals(aMovie.title))
			return false;

		if ((year == null) && (aMovie.year != null))
			return false;
		if ((aMovie.year == null) && (year != null))
			return false;
		if ((year != null) && !year.equals(aMovie.year))
			return false;

		if ((genre == null) && (aMovie.genre != null))
			return false;
		if ((aMovie.genre == null) && (genre != null))
			return false;
		if ((genre != null) && !genre.equals(aMovie.genre))
			return false;

		return (o instanceof MovieGenre && (this.getTitle().equals(
				((MovieGenre) o).getTitle())
				&& this.getYear().equals(((MovieGenre) o).getYear()) && this
				.getGenre().equals(((MovieGenre) o).getGenre())));
	}

}