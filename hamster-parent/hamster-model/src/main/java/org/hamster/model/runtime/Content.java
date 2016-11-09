package org.hamster.model.runtime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hamster.api.StorageService;
import org.hamster.util.ApplicationContextUtil;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@Entity
@Table(name = "ChallengeContents")
@GeneratePojoBuilder(withSetterNamePattern = "*")
public class Content {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private ContentType type;

	@OneToOne
	private Instance challenge;

	@Transient
	private byte[] content;

	@Transient
	private boolean setContentCalled;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public Instance getChallenge() {
		return challenge;
	}

	public void setChallenge(Instance challenge) {
		this.challenge = challenge;
	}

	@Transient
	public byte[] getContent() throws Exception {
		if (content == null) {
			content = ApplicationContextUtil.getInstance().getBean(StorageService.class).getContentAsBytes(this);
		}

		return content;
	}

	@Transient
	public void setContent(byte[] content) {
		setContentCalled = true;
		this.content = content;
	}

	@PostPersist
	@PostUpdate
	private void storeContentOnFileSystem() throws Exception {
		if (setContentCalled) {
			ApplicationContextUtil.getInstance().getBean(StorageService.class).storeContent(this);
		}
	}

}
