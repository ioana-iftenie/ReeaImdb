package Entities;

public class Genre {

   private int idGenre;
   private String name;
   
   public Genre() {
      
   }
   
   public Genre(String name) {
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
   
}
