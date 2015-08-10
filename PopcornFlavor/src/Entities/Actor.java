package Entities;

import Entities.Actor;

public class Actor {
	private int idActor;
	private String firstName;
	private String lastName;
	private String nickname;
	private String height;
	private String biography;
	private String dateBirth;
	private String dateDeath;
	private String img;

	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Actor() {
	}

	public Actor(String firstName, String lastName, String nickname,
			String height, String bio, String dateB, String dateD, String img) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickname = nickname;
		this.height = height;
		this.biography = bio;
		this.dateBirth = dateB;
		this.dateDeath = dateD;
		this.img = img;
	}

	public int getIdActor() {
		return idActor;
	}

	public void setIdActor(int idActor) {
		this.idActor = idActor;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getDateDeath() {
		return dateDeath;
	}

	public void setDateDeath(String dateDeath) {
		this.dateDeath = dateDeath;
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
		result = result * 37 + firstName.hashCode();
		result = 37 * result + lastName.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Actor))
			return false;
		Actor anActor = (Actor) o;

		if ((firstName == null) && (anActor.firstName != null))
			return false;
		if ((anActor.firstName == null) && (firstName != null))
			return false;
		if ((firstName != null) && !firstName.equals(anActor.firstName))
			return false;

		if ((lastName == null) && (anActor.lastName != null))
			return false;
		if ((anActor.lastName == null) && (lastName != null))
			return false;
		if ((lastName != null) && !lastName.equals(anActor.lastName))
			return false;

		return (o instanceof Actor && (this.getFirstName().equals(
				((Actor) o).getFirstName()) && this.getLastName().equals(
				((Actor) o).getLastName())));
	}

}
