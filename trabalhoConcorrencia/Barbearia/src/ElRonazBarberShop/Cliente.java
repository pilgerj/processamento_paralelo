package ElRonazBarberShop;

public class Cliente extends Thread {
	private Barber barber;
	private boolean esperando = false;
	private boolean emAtendimento = false;
	private boolean atendido = false;

	public Cliente(Barber b, String nome) {
		super(nome);
		this.barber = b;
	}

	@Override
	public void run() {
		try {
			synchronized (barber) {
				barber.addClienteFila(this);
			}

			while (isEsperando()) {
				sleep(10);
			}

			while (emAtendimento()) {
				sleep(10000);
			}

			if (isAtendido()) {
				interrupt();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void setEmAtendimento() {
		setEsperando(false);
		setEmAtendimento(true);
	}

	public synchronized void setAtendido() {
		setEmAtendimento(false);
		setAtendido(true);
	}

	public boolean isEsperando() {
		return esperando;
	}

	public void setEsperando(boolean esperando) {
		this.esperando = esperando;
	}

	public boolean emAtendimento() {
		return emAtendimento;
	}

	public void setEmAtendimento(boolean emAtendimento) {
		this.emAtendimento = emAtendimento;
	}

	public String getNome() {
		return this.getName();
	}

	public boolean isAtendido() {
		return atendido;
	}

	public void setAtendido(boolean atendido) {
		this.atendido = atendido;
	}

}
