package org.hamster.model.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hamster.model.def.Definition;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@Entity
@Table(name = "DefinitionAvailabilities")
@GeneratePojoBuilder(withSetterNamePattern = "*")
public class DefinitionAvailability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private User user;

	private Definition def;

	private Date validUntil;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Definition getDef() {
		return def;
	}

	public void setDef(Definition def) {
		this.def = def;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

}
