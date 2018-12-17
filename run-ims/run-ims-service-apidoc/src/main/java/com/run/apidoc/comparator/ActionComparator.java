package com.run.apidoc.comparator;

import java.util.Comparator;

import com.run.apidoc.entity.Action;

public class ActionComparator implements Comparator<Action> {

	public int compare(Action o1, Action o2) {
		return o1.getId() < o2.getId() ? 0 : 1;
	}

}
