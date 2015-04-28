package models;

import java.util.Comparator;

public class ItemSetComparator implements Comparator<ItemSet> {
	@Override
	public int compare(ItemSet i1, ItemSet i2) {
		Integer supportCount1 = i1.getSupportCount();
		Integer supportCount2 = i2.getSupportCount();

		return supportCount1.compareTo(supportCount2);
	}
	
}