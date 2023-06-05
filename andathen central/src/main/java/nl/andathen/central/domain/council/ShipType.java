package nl.andathen.central.domain.council;

public enum ShipType { 	PASSENGER("A spaceship with as its primary purpose transporting people. It will have decent to luxury accomodations and some space for luggage, but no real cargo hold or defensive systems."),
						CARGO("A spaceship with a large cargo hold. It might have some defensive systems but is not really fit for combat.");

	private final String description;

	private ShipType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static ShipType parse(String description) {
		if (description.equals("PASSENGER")) {
			return ShipType.PASSENGER;
		}
		if (description.equals("CARGO")) {
			return ShipType.CARGO;
		}
		return null;
	}
}
