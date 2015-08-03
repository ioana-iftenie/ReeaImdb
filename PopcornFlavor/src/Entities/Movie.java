package Entities;

public class Movie {
	private int idMovie;
	private String title;
	private String type;
	private String beginYear;
	private String finishYear;
	private String plot;
	private int totalVoters;
	private int totalRating;
	private String img;

	public Movie(String name, String type, String beginYear, String finishYear, String plot, int totalVoters, int totalRat9ing, String img) {
		this.title = name;
		this.type = type;
		this.beginYear = beginYear;
		this.finishYear = finishYear;
		plot = null;
		totalVoters = 0;
		setTotalRating(0);
		img = null;
	}

	
	public int getIdMovie() {
		return idMovie;
	}


	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getBeginYear() {
		return beginYear;
	}


	public void setBeginYear(String beginYear) {
		this.beginYear = beginYear;
	}


	public String getFinishYear() {
		return finishYear;
	}


	public void setFinishYear(String finishYear) {
		this.finishYear = finishYear;
	}


	public String getPlot() {
		return plot;
	}


	public void setPlot(String plot) {
		this.plot = plot;
	}


	public int getTotalVoters() {
		return totalVoters;
	}


	public void setTotalVoters(int totalVoters) {
		this.totalVoters = totalVoters;
	}


	public int getTotalRating() {
		return totalRating;
	}


	public void setTotalRating(int totalRating) {
		this.totalRating = totalRating;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	@Override
	public int hashCode() {
		int result = 1;
		result = result * 37 + title.hashCode();
//		result = 37 * result + type.hashCode();
		result = 37 * result + beginYear.hashCode();
//		result = 37 * result + finishYear.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Movie)) return false;
		Movie aMovie = (Movie) o;
		
		if ((title == null) && (aMovie.title != null)) return false;
		if ((aMovie.title == null) && (title != null)) return false;
		if ((title != null) && !title.equals(aMovie.title)) return false;
//		
//		if ((type == null) && (aMovie.type != null)) return false;
//		if ((aMovie.type == null) && (type != null)) return false;
//		if ((type != null) && !type.equals(type)) return false;
		
		if ((beginYear == null) && (aMovie.beginYear != null)) return false;
		if ((aMovie.beginYear == null) && (beginYear != null)) return false;
		if ((beginYear != null) && !beginYear.equals(aMovie.beginYear)) return false;
//		
//		if ((finishYear == null) && (aMovie.finishYear != null)) return false;
//		if ((aMovie.finishYear == null) && (finishYear != null)) return false;
//		if ((finishYear != null) && !finishYear.equals(aMovie.finishYear)) return false;
		
			
		return (o instanceof Movie 
				&& (this.getTitle().equals(((Movie) o).getTitle())
//				&& this.getType().equals(((Movie) o).getType())
				&& this.getBeginYear().equals(((Movie) o).getBeginYear())
//				&& this.getFinishYear().equals(((Movie) o).getFinishYear())
				)
				);
	}



}
