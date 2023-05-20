package bnk.enums;

public enum ObjectType {
	Route_("p2.logging.p2.logging.Route"), Segment_("p2.logging.p2.logging.Segment"), Station_("p2.logging.p2.logging.Station"), Train_("p2.logging.p2.logging.Train");

	private String description;

	ObjectType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
