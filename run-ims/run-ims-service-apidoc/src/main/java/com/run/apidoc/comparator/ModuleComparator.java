package com.run.apidoc.comparator;

import java.util.Comparator;

import com.run.apidoc.entity.Module;

public class ModuleComparator implements Comparator<Module> {

	public int compare(Module o1, Module o2) {
		return o1.getId() < o2.getId() ? 0 : 1;
	}

}
