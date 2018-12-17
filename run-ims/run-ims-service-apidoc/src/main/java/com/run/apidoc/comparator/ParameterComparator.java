package com.run.apidoc.comparator;

import java.util.Comparator;

import com.run.apidoc.entity.Parameter;

public class ParameterComparator implements Comparator<Parameter> {

	public int compare(Parameter o1, Parameter o2) {
		return o1.getId() < o2.getId() ? 0 : 1;
	}

}
