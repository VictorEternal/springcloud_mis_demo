package com.run.apidoc.entity;

import java.util.Comparator;

public class CheckInComparator implements Comparator<CheckIn> {

	public int compare(CheckIn o1, CheckIn o2) {
		return o1.getId() > o2.getId() ? -1 : 1;
	}

}
