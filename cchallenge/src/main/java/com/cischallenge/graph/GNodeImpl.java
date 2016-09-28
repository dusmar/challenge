package com.cischallenge.graph;

public class GNodeImpl implements GNode {

	private String name;
	private GNode[] children;

	public GNodeImpl(String name, GNode[] children) {
		super();
		this.name = name;
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public GNode[] getChildren() {
		return children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GNodeImpl other = (GNodeImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GNodeImpl [name=" + name + "]";
	}
	
	
	
	
}
