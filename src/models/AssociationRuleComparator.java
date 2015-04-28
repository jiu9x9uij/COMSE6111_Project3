package models;

import java.util.Comparator;

public class AssociationRuleComparator implements Comparator<AssociationRule> {
	@Override
	public int compare(AssociationRule r1, AssociationRule r2) {
		Double support1 = r1.getSupport();
		Double support2 = r2.getSupport();

		return support1.compareTo(support2);
	}
	
}