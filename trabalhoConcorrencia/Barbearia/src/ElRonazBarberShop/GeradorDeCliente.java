package ElRonazBarberShop;

public class GeradorDeCliente extends Thread {

	private Barber barber;
	private int cont = 0;

	public GeradorDeCliente(Barber b, String nome) {
		super(nome);
		this.barber = b;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep((int) (Math.random() * 10000));
				cont ++;
				Cliente c = new Cliente(barber, "Cliente" + cont);
				c.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
