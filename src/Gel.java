import java.util.ArrayList;

public class Gel {

	static ArrayList<Gel> gels = new ArrayList<>();
	
	public Manufacturer manufacturer;
	public int number;
	public String name;
	public float red;
	public float green;
	public float blue;
	
	public Gel(Manufacturer m, int num, String name, float red, float green, float blue) {
		manufacturer = m;
		number = num;
		this.name = name;
		this.red = red;
		this.green = green;
		this.blue = blue;
		gels.add(this);
	}
	
	public static Gel[] getAllGels() {
		ArrayList<Gel> gs = new ArrayList<>();
		for(int i = 0; i < gels.size(); i++) {
			if(gels.get(i).manufacturer != Manufacturer.Blackbody) {
				gs.add(gels.get(i));
			}
		}
		Gel[] g = new Gel[gs.size()];
		g = gs.toArray(g);
		return g;
	}
	
	public static Gel[] getAllBlackBody() {
		ArrayList<Gel> gs = new ArrayList<>();
		for(int i = 0; i < gels.size(); i++) {
			if(gels.get(i).manufacturer == Manufacturer.Blackbody) {
				gs.add(gels.get(i));
			}
		}
		Gel[] g = new Gel[gs.size()];
		g = gs.toArray(g);
		return g;
	}
	
	public static Gel getGel(Manufacturer m, int num) {
		for(Gel g : gels) {
			if(g.manufacturer.equals(m) && g.number == num) {
				return g;
			}
		}
		return null;
	}
	
	public static void loadGels(String s) {
		String[] line = s.split("\\n");
		for(String gLine : line) {
			String[] data = gLine.split(",");
			Gel g = new Gel(Manufacturer.valueOf(data[0]), new Integer(data[1].trim()), data[2], new Integer(data[3].trim()), new Integer(data[4].trim()), new Integer(data[5].trim()));
			System.out.println("Loaded "+g.toString());
		}
	}
	
	public String toString() {
		return manufacturer.toString()+" "+number;
	}
	
}