package br.com.netflics.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;


/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06 */
public class JsonRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String authority;
	private String description;
	private ArrayList<JsonUser> users = new ArrayList<JsonUser>();	
	private ArrayList<JsonPermission> permissions = new ArrayList<JsonPermission>();	
	private ArrayList<JsonGroup> groups = new ArrayList<JsonGroup>();	
	
	public  JsonRole() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<JsonUser> getUsers() {
		return users;
	}
	
	public void setUsers(ArrayList<JsonUser> user) {
		this.users = user;
	}

	public ArrayList<JsonPermission> getPermissions() {
		return permissions;
	}
	
	public void setPermissions(ArrayList<JsonPermission> permission) {
		this.permissions = permission;
	}

	public ArrayList<JsonGroup> getGroups() {
		return groups;
	}
	
	public void setGroups(ArrayList<JsonGroup> group) {
		this.groups = group;
	}


}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06