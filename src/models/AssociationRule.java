package models;

import java.util.List;

public class AssociationRule{
	private List<Integer> X;
	private List<Integer> Y;
	private double support;
	private double confidence;
	
	public AssociationRule(List<Integer> X, List<Integer> Y, double support, double confidence) {
		this.X = X;
		this.Y = Y;
		this.support = support;
		this.confidence = confidence;
	}
	
	public List<Integer> getX() {
		return X;
	}
	
	public List<Integer> getY() {
		return Y;
	}
	
	public double getSupport() {
		return support;
	}
	
	public double getConfidence() {
		return confidence;
	}
	
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		
		s.append("{");
		s.append(X.toString());
		s.append(" => ");
		s.append(Y.toString());
		s.append("}");
		s.append(":" + confidence + "," + support);
		
		return s.toString();
	}
}
