package ElRonazBarberShop;

public class Barbeiro extends Thread {

	private boolean dormindo = true;
	private boolean cortando = false;
	private boolean organizando = false;

	private Barber barber;
	private Cliente clienteEmAtendimento = null;

	public Barbeiro(Barber b, String nome) {
		super(nome);
		this.barber = b;
	}

	public Barbeiro(Barber b, String nome, boolean organizando) {
		super(nome);
		this.barber = b;
		this.setOrganizando(organizando);
	}

	@Override
	public void run() {
		while (true) {
			try {
				sleep(100);
				if (isOrganizando()) {
					System.out.println("|Barbeiro| " + getNome() + " esta organizando. \n");
					while (isOrganizando()) {
						sleep(2000);
					}
				}

				while (isDormindo()) {
					if (barber.getClientesNaFila().size() == 0) {
						System.out.println("|Barbeiro| " + getNome() + " esta dormindo. \n");
						sleep(10000);
					}
					barber.atenderCliente(this);
				}

				System.out.println("|Barbeiro| " + getNome() + " esta atendendo o cliente "
						+ getClienteEmAtendimento().getName() + "! \n");

				if (isCortando()) {
					sleep((int) (Math.random() * 30000));

					System.out.println("|Barbeiro| " + getNome() + ": Corte feito, dispensando "+getClienteEmAtendimento().getNome()+"! \n");
					barber.terminarCorte(this, getClienteEmAtendimento());
					sleep(3000);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public synchronized void setOrganizando() {
		setCortando(false);
		setDormindo(false);
		setOrganizando(true);
	}

	public synchronized void setCortando() {
		setDormindo(false);
		setOrganizando(false);
		setCortando(true);
	}

	public synchronized void setDormindo() {
		setOrganizando(false);
		setCortando(false);
		setDormindo(true);
	}

	public String getNome() {
		return getName();
	}

	public Barber getBarber() {
		return barber;
	}

	public  boolean isDormindo() {
		return dormindo;
	}

	public void setDormindo(boolean dormindo) {
		this.dormindo = dormindo;
	}

	public boolean isCortando() {
		return cortando;
	}

	public void setCortando(boolean cortando) {
		this.cortando = cortando;
	}

	public Cliente getClienteEmAtendimento() {
		return clienteEmAtendimento;
	}

	public void setClienteEmAtendimento(Cliente clienteEmAtendimento) {
		this.clienteEmAtendimento = clienteEmAtendimento;
	}

	public boolean isOrganizando() {
		return organizando;
	}

	public void setOrganizando(boolean organizando) {
		this.organizando = organizando;
	}
}
