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
@Table(name = "ITEM", uniqueConstraints = {
		@UniqueConstraint(name = "ITEM_NAME", columnNames = { "NAME" }), 
		@UniqueConstraint(name = "ITEM_IDENTIFIER", columnNames = { "IDENTIFIER" }), 
})
public class Item extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer id;
	
	@Column(name = "NAME" )
	private String name;		
	
	@Column(name = "TYPE" )
	private String itemType;		
	
	@Column(name = "IDENTIFIER" )
	private String identifier;		
	
	@Column(name = "DESCRIPTION" )
	private String description;		
	
	@OneToMany()
	private List<Permission> permissions;	
		
		
	public  Item() {
		
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		permission.setItem(this);
		return getPermissions().add(permission);
	}
	
	public boolean removePermissions(Permission permission){
		permission.setItem(null);
		return getPermissions().remove(permission);
	}
	
	
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06