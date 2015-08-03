package Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigFile {

	private static final String PROPERTIES_MOVIE = "configMovies.properties";
	private static final String PROPERTIES_ACTOR = "configActor.properties";
	private static final String PROPERTIES_BIOGRAPHIES = "configBiographies.properties";
	private static final String PROPERTIES_GENRES = "configGenres.properties";
	PropertiesConfiguration configMovie = new PropertiesConfiguration();
	PropertiesConfiguration configActor = new PropertiesConfiguration();
	PropertiesConfiguration configBiographies = new PropertiesConfiguration();
	PropertiesConfiguration configGenres = new PropertiesConfiguration();

	public void writeToPropertiesFileMovie() {

		configMovie.setProperty("Project", "PopcornFlavor");
		configMovie.setProperty("Encoding", "windows-1252");

		configMovie.setProperty("1-Attribute&Type", new String[] { "title",
				"String" });
		configMovie.setProperty("2-Attribute&Type", new String[] { "type",
				"String" });
		configMovie.setProperty("3-Attribute&Type", new String[] { "beginYear",
				"String" });
		configMovie.setProperty("4-Attribute&Type", new String[] {
				"finishYear", "String" });

		configMovie.setProperty("File-separator-between-title-and-year",
				"One or more tabs");
		configMovie.setProperty("1-Special-Case-For-Movies", new String[] {
				"FinishYear", "'no-year'", "Always" });
		configMovie.setProperty("2-Special-Case-For-Movies",
				new String[] { "FinishYear ", "'{{SUSPENDED}}'",
						" When movies are suspended" });
		configMovie.setProperty("3-Special-Case-For-Movies", new String[] {
				"BeginYear ", "' ????'", "When begining year is not known" });
		configMovie
				.setProperty("1-Special-Case-Series", new String[] {
						"FinishYear ", "' ????'",
						" When finishing year is not known" });
		configMovie.setProperty("2-Special-Case-Series",
				new String[] { "FinishYear ", "'{{SUSPENDED}}'",
						" When series are suspended" });
		configMovie.setFooter("PopcornFlavor Project Reea");
		configMovie.setHeader("Delimitator for multi value key is ',' !!!!");

	}

	public void writeToPropertiesFileActor() {
		configActor.setProperty("Project", "PopcornFlavor");
		configActor.setProperty("Encoding", "windows-1252");
		configActor.setProperty("1-Attribute&Type", new String[] {
				"actor name", "String" });
		configActor.setProperty("2-Attribute&Type", new String[] {
				"movie name", "String" });
		configActor.setProperty("3-Attribute&Type", new String[] { "role name",
				"String" });
		configActor.setProperty("Separator-between-actors", "new line");
	}

	public void writeToPropertiesFileBiographies() {
		configGenres.setProperty("Project", "PopcornFlavor");
		configGenres.setProperty("Encoding", "windows-1252");
		configGenres.setProperty("1-Attribute&Type", new String[] {
				"movie name", "String" });
		configGenres.setProperty("2-Attribute&Type", new String[] {
				"movie genre", "String" });
		configGenres.setProperty("Separator-between-movie", "new line");
	}
	
	public void writeToPropertiesFileGenres() {
		
	}

	public void addPropMovie(String key, double value) {
		Configuration properties = new PropertiesConfiguration();
		properties.setProperty(key, value);
		configMovie.append(properties);
	}

	public void addPropActor(String key, double value) {
		Configuration properties = new PropertiesConfiguration();
		properties.setProperty(key, value);
		configActor.append(properties);
	}
	
	public void addPropGenres(String key, double value) {
		Configuration properties = new PropertiesConfiguration();
		properties.setProperty(key, value);
		configGenres.append(properties);
	}

	public void saveFileMovie() {
		try {
			configMovie.save(PROPERTIES_MOVIE);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void saveFileActor() {
		try {
			configActor.save(PROPERTIES_ACTOR);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void saveFileBiographies() {
		try {
			configBiographies.save(PROPERTIES_BIOGRAPHIES);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void saveFileGenres() {
		try {
			configGenres.save(PROPERTIES_GENRES);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

}
