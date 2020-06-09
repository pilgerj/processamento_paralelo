import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Controller c1 = new Controller();
		
		ArrayList<Rena> renas = new ArrayList<Rena>();
		criaRenas(c1, renas);
		
		ArrayList<Elfo> elfos = new ArrayList<Elfo>();
		criaElfos(c1, elfos);
		
		Noel noel = new Noel(c1, "PapaiNoel");
		c1.setNoel(noel);

		c1.setRenas(renas);
		c1.setElfos(elfos);
		
		c1.startaRotina();
		try {
			c1.joinRotina();
			
		} catch (Exception e) {
		}
	}
	
	private static ArrayList<Rena> criaRenas(Controller c1, ArrayList<Rena> renas) {
		for (int i=1; i<10; i++) {
			renas.add(new Rena(c1, "Rena"+i));
		}
		return renas;
	}
	
	private static ArrayList<Elfo> criaElfos(Controller c1, ArrayList<Elfo> elfos) {
		for (int i=1; i<11; i++) {
			elfos.add(new Elfo(c1, "Elfo"+i));
		}
		return elfos;
	}
	

}
