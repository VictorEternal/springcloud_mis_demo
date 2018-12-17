package com.run.apidoc.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Workspace implements java.io.Serializable {

	private static final long	serialVersionUID	= -861260674013547691L;
	private int					id;
	private Project				project;
	private Date				createDate;
	private Date				updateDate;
	private String				projectData;
	private String				projectDataOriginal;



	public boolean isAccessable() {
		return accessable;
	}



	public void setAccessable(boolean accessable) {
		this.accessable = accessable;
	}

	private boolean accessable;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getModeInt() {
		return project.getWorkspaceModeInt();
	}



	public Project getProject() {
		return project;
	}



	public void setProject(Project project) {
		this.project = project;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public Date getUpdateDate() {
		return updateDate;
	}



	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}



	public String getProjectData() {
		return projectData;
	}



	public void setProjectData(String projectData) {
		this.projectData = projectData;
	}



	public String getProjectDataOriginal() {
		return projectDataOriginal;
	}



	public void setProjectDataOriginal(String projectDataOriginal) {
		this.projectDataOriginal = projectDataOriginal;
	}



	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{\"projectData\":" + getProject().getProjectData() + ",");
		stringBuilder.append("\"relatedIds\":\"" + getProject().getRelatedIds() + "\",");
		stringBuilder.append("\"checkList\":[");
		List<CheckIn> checkList = getProject().getCheckInListOrdered();
		if (checkList != null) {
			Iterator<CheckIn> iterator = checkList.iterator();
			while (iterator.hasNext()) {
				stringBuilder.append(iterator.next().toString());
				if (iterator.hasNext()) {
					stringBuilder.append(", ");
				}
			}
		}
		stringBuilder.append("]}");
		return stringBuilder.toString();
	}

	public enum ModeType {
		VSS, SVN
	}
}
