package ElRonazBarberShop;

public class Main {

	public static void main(String[] args) {
		Barber barber = new Barber();
		GeradorDeCliente gera = new GeradorDeCliente(barber, "gerador");
		
		Barbeiro b1 = new Barbeiro(barber, "Moacir");
		Barbeiro b2 = new Barbeiro(barber, "Jair");
		Barbeiro b3 = new Barbeiro(barber, "Aldair", true);
		barber.addBarbeiros(b1, b2, b3);
		gera.start();
		
		try {
			barber.joinRotina();
			gera.join();
		} catch (Exception e) {
			e.getStackTrace();
		}
	
		
	}

}
