
public class Elfo extends Thread {
	private Controller controller;
	private String nomeElfo = null;
	private int status = 0;
	// 0 = Nova
	// 1 = Pronta
	// 2 = Executando
	// 3 = Esperando

	private boolean emConsultoria = false;

	public Elfo(Controller controller, String nomeElfo) {
		super(nomeElfo);
		this.nomeElfo = nomeElfo;
		this.controller = controller;
		this.setPriority(MIN_PRIORITY);
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				this.setStatus(2);// executando
				System.out.println(this.getNomeElfo() + " | " + this.getStatus() + " fazendo brinquedo \n ");
				sleep((int) (Math.random() * 100000));

				this.setStatus(3);// esperando
				System.out.println(this.getNomeElfo() + " | " + this.getStatus() + " aguardando ajuda \n");
				synchronized (controller) {
					controller.attElfosAguardando(this);
				}
				while (isEmConsultoria()) {
					sleep(1000);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String getNomeElfo() {
		return nomeElfo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isEmConsultoria() {
		return emConsultoria;
	}

	public void setEmConsultoria(boolean emAjuda) {
		this.emConsultoria = emAjuda;
	}
}
