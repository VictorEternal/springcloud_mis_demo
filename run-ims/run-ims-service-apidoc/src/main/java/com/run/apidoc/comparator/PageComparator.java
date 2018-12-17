package com.run.apidoc.comparator;

import java.util.Comparator;

import com.run.apidoc.entity.Page;

public class PageComparator implements Comparator<Page> {

	public int compare(Page o1, Page o2) {
		return o1.getId() < o2.getId() ? 0 : 1;
	}

}
