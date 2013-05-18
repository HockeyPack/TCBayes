package cn.edu.gdut.bayestc.tfidf.entity;

import java.util.Map;

public class TrainingStorage {
	private Map<String,String[]> structure;

	public Map<String, String[]> getStructure() {
		return structure;
	}

	public void setStructure(Map<String, String[]> structure) {
		this.structure = structure;
	}
	
}
