package Entities;

public class Role {
	private String title;
	private String role;

	public Role(String title, String role) {
		this.title = title;
		this.role = role;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = result * 37 + title.hashCode();
		result = 37 * result + role.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Role))
			return false;
		Role aRole = (Role) o;

		if ((title == null) && (aRole.title != null))
			return false;
		if ((aRole.title == null) && (title != null))
			return false;
		if ((title != null) && !title.equals(aRole.title))
			return false;

		if ((role == null) && (aRole.role != null))
			return false;
		if ((aRole.role == null) && (role != null))
			return false;
		if ((role != null) && !role.equals(aRole.role))
			return false;

		return (o instanceof Role && (this.getTitle().equals(
				((Role) o).getTitle()) && this.getRole().equals(
				((Role) o).getRole())));
	}
}
