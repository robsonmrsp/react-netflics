/* generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06 */
package br.com.netflics.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;		

import java.time.LocalDate;
import java.time.LocalDateTime;
	
import java.util.ArrayList;
import java.util.List;

import br.com.netflics.core.model.AbstractEntity;

@Entity
@Table(name = "ROLE", uniqueConstraints = {
		@UniqueConstraint(name = "ROLE_AUTHORITY", columnNames = { "AUTHORITY" }), 
		@UniqueConstraint(name = "ROLE_DESCRIPTION", columnNames = { "DESCRIPTION" }), 
})
public class Role extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer id;
	
	@Column(name = "AUTHORITY" )
	private String authority;		
	
	@Column(name = "DESCRIPTION" )
	private String description;		
	
    @ManyToMany
    @JoinTable(name="ROLE_USER", joinColumns = @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_USER", referencedColumnName = "ID") )
    private List<User> users;
    
    @ManyToMany
    @JoinTable(name="ROLE_PERMISSION", joinColumns = @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_PERMISSION", referencedColumnName = "ID") )
    private List<Permission> permissions;
    
    @ManyToMany
    @JoinTable(name="ROLE_GROUP", joinColumns = @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_GROUP", referencedColumnName = "ID") )
    private List<Group> groups;
    
		
	public  Role() {
		
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
	public void setUsers(List<User> users){
		this.users = users;
	}
	
	public List<User>  getUsers() {
		if(this.users == null){
			setUsers(new ArrayList<User>());
		}
		return this.users; 
	}
		
	public boolean addUsers(User user){	
		return getUsers().add(user);
	}
	
	public boolean removeUsers(User user){	
		return getUsers().remove(user);
	}
	public void setPermissions(List<Permission> permissions){
		this.permissions = permissions;
	}
	
	public List<Permission>  getPermissions() {
		if(this.permissions == null){
			setPermissions(new ArrayList<Permission>());
		}
		return this.permissions; 
	}
		
	public boolean addPermissions(Permission permission){	
		return getPermissions().add(permission);
	}
	
	public boolean removePermissions(Permission permission){	
		return getPermissions().remove(permission);
	}
	public void setGroups(List<Group> groups){
		this.groups = groups;
	}
	
	public List<Group>  getGroups() {
		if(this.groups == null){
			setGroups(new ArrayList<Group>());
		}
		return this.groups; 
	}
		
	public boolean addGroups(Group group){	
		return getGroups().add(group);
	}
	
	public boolean removeGroups(Group group){	
		return getGroups().remove(group);
	}
	
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06