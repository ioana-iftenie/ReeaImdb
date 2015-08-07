package Entities;

public class Genre {

   private int idGenre;
   private String name;
   
   public Genre() {
      
   }
   
   public Genre(String name) {
      this.name = name;
   }
   
   
   
   public Genre(int idGenre, String name) {
	   this.idGenre = idGenre;
      this.name = name;
   }
   
   public int getIdGenre() {
      return idGenre;
   }
   
   public void setIdGenre(int idGenre) {
      this.idGenre = idGenre;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   public int hashCode() {
		int result = 1;
		result = result * 37 + name.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Genre)) return false;
		Genre aGenre = (Genre) o;
		
		if ((name == null) && (aGenre.name != null)) return false;
		if ((aGenre.name == null) && (name != null)) return false;
		if ((name != null) && !name.equals(aGenre.name)) return false;

		return (o instanceof Genre 
				&& (this.getName().equals(((Genre) o).getName())));
	}
   
   
}
