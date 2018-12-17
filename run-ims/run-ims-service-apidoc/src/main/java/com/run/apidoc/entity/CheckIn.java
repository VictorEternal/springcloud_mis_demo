package com.run.apidoc.entity;

import java.util.Date;

import com.run.apidoc.utils.DateUtils;
import com.run.apidoc.utils.StringUtils;

public class CheckIn implements java.io.Serializable {

	private static final long	serialVersionUID	= 6339098934792830699L;
	private int					id;
	private Date				createDate;
	private String				tag;
	private Project				project;
	private String				description;
	private String				version;
	private String				projectData;
	private int					workspaceModeInt;
	private String				log;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public String getCreateDateStr() {
		return getCreateDate() == null ? "" : DateUtils.TIME_FORMAT.format(getCreateDate());
	}



	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}



	public Project getProject() {
		return project;
	}



	public void setProject(Project project) {
		this.project = project;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}



	public String getProjectData() {
		return projectData;
	}



	public void setProjectData(String projectData) {
		this.projectData = projectData;
	}



	public int getWorkspaceModeInt() {
		return workspaceModeInt;
	}



	public void setWorkspaceModeInt(int workspaceModeInt) {
		this.workspaceModeInt = workspaceModeInt;
	}



	public String getLog() {
		return log;
	}



	public void setLog(String log) {
		this.log = log;
	}



	public void versionUpgrade(int versionPosition) throws Exception {
		if (versionPosition > 4 || versionPosition < 1) {
			throw new Exception("illegal version position: " + versionPosition);
		}
		String[] versionList = getVersion().split("\\.");
		if (versionList.length != 4) {
			throw new Exception("illegal version format: " + getVersion());
		}
		Integer integer = Integer.parseInt(versionList[versionPosition - 1]) + 1;
		versionList[versionPosition - 1] = integer.toString();
		String newVersion = "";
		for (int i = 0; i < versionList.length; i++) {
			newVersion += versionList[i];
			if (i < versionList.length - 1) {
				newVersion += ".";
			}
		}
		setVersion(newVersion);
	}



	public String toString() {
		return this.toString(ToStringType.NORMAL);
	}



	public String toString(ToStringType type) {
		if (type == ToStringType.NORMAL) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("{\"id\":" + getId() + ",");
			stringBuilder.append("\"version\":\"" + getVersion() + "\",");
			stringBuilder.append("\"createDateStr\":\"" + getCreateDateStr() + "\",");
			stringBuilder.append("\"description\":\"" + StringUtils.escapeInJ(getDescription()) + "\"}");
			return stringBuilder.toString();
		} else if (type == ToStringType.COMPLETED) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("{\"id\":" + getId() + ",");
			stringBuilder.append("\"version\":\"" + getVersion() + "\",");
			stringBuilder.append("\"projectData\":" + getProjectData() + ",");
			stringBuilder.append("\"createDateStr\":\"" + getCreateDateStr() + "\",");
			stringBuilder.append("\"description\":\"" + StringUtils.escapeInJ(getDescription()) + "\"}");
			return stringBuilder.toString();
		} else {
			return this.toString();
		}
	}

	public enum ToStringType {
		COMPLETED, NORMAL
	}
}