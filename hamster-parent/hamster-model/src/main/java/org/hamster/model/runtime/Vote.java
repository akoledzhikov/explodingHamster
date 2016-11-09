package org.hamster.model.runtime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hamster.model.user.User;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@Entity
@Table(name = "ChallengeVotes")
@GeneratePojoBuilder(withSetterNamePattern = "*")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private User user;
	@ManyToOne
	private Instance challenge;

	private boolean positive;

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

	public Instance getChallenge() {
		return challenge;
	}

	public void setChallenge(Instance challenge) {
		this.challenge = challenge;
	}

	public boolean isPositive() {
		return positive;
	}

	public void setPositive(boolean positive) {
		this.positive = positive;
	}

}