package bnk.enums;

public enum Light {
	
	Red("p2.enums.p2.enums.Light is Red"),
	Green("p2.enums.p2.enums.Light is Green!");

	private String description;

	Light(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
