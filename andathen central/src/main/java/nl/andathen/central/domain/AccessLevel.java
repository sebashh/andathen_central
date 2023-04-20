package nl.andathen.central.domain;

public enum AccessLevel { L0("General knowledge"), 
							L1("Advanced Computer Use"), 
							L2("Schooled in database use"), 
							L3("Academic computer access"), 
							L4("Granted access to all semi-public databases"), 
							L5("Top secret, authorized personnel only"), 
							L6("Reserved for larp staff"), 
							L7("Access for LARP Designers only"),
							L8("Special restrictions or results."),
							NaN("Infinite access level required. This level should not even exist!");
	private final String description;

	private AccessLevel(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static AccessLevel parse(String description) {
		switch (description) {
		case "L0": return AccessLevel.L0;
		case "L1": return AccessLevel.L1;
		case "L2": return AccessLevel.L2;
		case "L3": return AccessLevel.L3;
		case "L4": return AccessLevel.L4;
		case "L5": return AccessLevel.L5;
		case "L6": return AccessLevel.L6;
		case "L8": return AccessLevel.L8;
		default: return AccessLevel.NaN;
		}
	}
	
	public static AccessLevel parse(int level) {
		switch (level) {
			case 0: return AccessLevel.L0;
			case 1: return AccessLevel.L1;
			case 2: return AccessLevel.L2;
			case 3: return AccessLevel.L3;
			case 4: return AccessLevel.L4;
			case 5: return AccessLevel.L5;
			case 6: return AccessLevel.L6;
			case 8: return AccessLevel.L8;
			default: return AccessLevel.NaN;
		}
	}
	
	public int asInt() {
		switch (this) {
			case L0: return 0;
			case L1: return 1;
			case L2: return 2;
			case L3: return 3;
			case L4: return 4;
			case L5: return 5;
			case L6: return 6;
			case L7: return 7;
			case L8: return 8;
			default: return 9;
		}
	}

	public AccessLevel increase() {
		switch (this) {
			case L0: return L1;
			case L1: return L2;
			case L2: return L3;
			case L3: return L4;
			case L4: return L5;
			case L5: return L6;
			case L6: return L7;
			case L7: return L8;
			default: return NaN;
		}
	}
	
	public AccessLevel decrease() {
		switch (this) {
			case L0: return L0;
			case L1: return L0;
			case L2: return L1;
			case L3: return L2;
			case L4: return L3;
			case L5: return L4;
			case L6: return L5;
			case L7: return L6;
			case L8: return L7;
			default: return NaN;
		}
	}
}
