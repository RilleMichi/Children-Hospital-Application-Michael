package version7.models;

public enum Insurance {
	//Der Vorteil bei einem enum gibt man explizit was es für Werte hat
	//Und der User kann keine weitere hinzufügen
	KPT("0123456789"), 
	ASSURA("0123456789"), 
	VISANA("0123456789"), 
	CSS("0123456789"),
	SANITAS("0123456789");
	
	private String phoneNumber;
	
	//Dadurch kann man Zusatzinformation mitgeben
	Insurance(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
}
