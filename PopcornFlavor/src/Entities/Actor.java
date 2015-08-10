package Entities;

import java.util.Set;

public class Actor {
	private String actor_id;
	private String actor_name;
	private String nickname;
	private String birthDay; // DB
	private String deathDay; // DD
	private String bio; // TR
	private Set<Role> rolesList;

	// HT height

	public Actor(String actor_id, String actor_name, Set<Role> rolesList) {
		this.actor_name = actor_name;
		this.rolesList = rolesList;
	}

	public String getActor_name() {
		return actor_name;
	}

	public void setActor_name(String actor_name) {
		this.actor_name = actor_name;
	}

	public Set<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(Set<Role> rolesList) {
		this.rolesList = rolesList;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = result * 37 + actor_name.hashCode();
		result = 37 * result + rolesList.hashCode();
		return result;
	}

	public void makeActorId() {

		this.actor_id = this.actor_name + "/" + this.birthDay;

	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Actor))
			return false;
		Actor anActor = (Actor) o;

		if ((actor_name == null) && (anActor.actor_name != null))
			return false;
		if ((anActor.actor_name == null) && (actor_name != null))
			return false;
		if ((actor_name != null) && !actor_name.equals(anActor.actor_name))
			return false;

		if ((rolesList == null) && (anActor.rolesList != null))
			return false;
		if ((anActor.rolesList == null) && (rolesList != null))
			return false;
		if ((rolesList != null) && !rolesList.equals(anActor.rolesList))
			return false;

		return (o instanceof Actor && (this.getActor_name().equals(
				((Actor) o).getActor_name()) && this.getRolesList().equals(
				((Actor) o).getRolesList())));
	}
}
