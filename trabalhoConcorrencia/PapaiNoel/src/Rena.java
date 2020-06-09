
public class Rena extends Thread {
	private Controller controller;
	private String nomeRena = null;
	private int status = 0;
	// 0 = Nova
	// 1 = Pronta
	// 2 = Executando
	// 3 = Esperando
	private boolean ajudandoNoel = false;

	public Rena(Controller controller, String nomeRena) {
		super(nomeRena);
		this.nomeRena = nomeRena;
		this.controller = controller;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				this.setStatus(2);// ferias
				System.out.println(this.getNomeRena() + " | " + this.getStatus() + " de ferias \n");
				sleep((int) (Math.random() * 100000));

				this.setStatus(3);// pronta p entregar
				System.out.println(this.getNomeRena() + " | " + this.getStatus() + " Pronta para entregar \n ");
				synchronized (controller) {
					controller.attRenasAguardando(this);
				}
				while(isAjudandoNoel()) {
					sleep(1000);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNomeRena() {
		return nomeRena;
	}

	public boolean isAjudandoNoel() {
		return ajudandoNoel;
	}

	public void setAjudandoNoel(boolean ajudandoNoel) {
		this.ajudandoNoel = ajudandoNoel;
	}

}
