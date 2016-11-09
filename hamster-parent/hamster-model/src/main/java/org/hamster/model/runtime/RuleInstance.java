package org.hamster.model.runtime;

import java.util.HashMap;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

public class RuleInstance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Lob
	private HashMap<String, Object> parameters;

	private String ruleClass;

	@ManyToOne
	private ContainerInstance containerInstance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HashMap<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String getRuleClass() {
		return ruleClass;
	}

	public void setRuleClass(String ruleClass) {
		this.ruleClass = ruleClass;
	}

	public ContainerInstance getContainerInstance() {
		return containerInstance;
	}

	public void setContainerInstance(ContainerInstance containerInstance) {
		this.containerInstance = containerInstance;
	}

}
