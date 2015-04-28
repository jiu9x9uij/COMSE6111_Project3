package models;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ItemSet {
	List<Integer> items;
	int supportCount;
	
	public ItemSet(List<Integer> itemSet, int supportCount) {
		this.items = itemSet;
		this.supportCount = supportCount;
	}
	
	public List<Integer> getItems() {
		return items;
	}
	
	public int getSupportCount() {
		return supportCount;
	}
	
	public void setSupportCount(int supportCount) {
		this.supportCount = supportCount;
	}
	
	public void increaseSupportCount() {
		this.supportCount++;
	}
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(items).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
    	if (!(obj instanceof ItemSet)) {
    		return false;
    	}
            
        if (obj == this) {
        	return true;
        }
            
        ItemSet rhs = (ItemSet) obj;
        return new EqualsBuilder().append(items, rhs.items).isEquals();
    }
    
    @Override
    public String toString() {
    	StringBuffer s = new StringBuffer();
    	s.append("{");
    	for (int i = 0; i < items.size(); i++) {
    		if (i == items.size()-1) {
    			s.append(items.get(i));
    		} else {
    			s.append(items.get(i) + ", ");
    		}
    	}
    	s.append("}:" + supportCount);
    	
		return s.toString();
    }
}