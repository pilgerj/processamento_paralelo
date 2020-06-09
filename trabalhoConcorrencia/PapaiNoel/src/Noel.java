import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Noel extends Thread {
	private Controller controller;
	private ArrayList<Elfo> elfosSendoAjudados = new ArrayList<Elfo>();
	private ArrayList<Rena> renasEntregando = new ArrayList<Rena>();
	private int status = 0;
	// 0 = Nova
	// 1 = Pronta
	// 2 = Executando
	// 3 = Esperando

	public Noel(Controller controller, String nomeNoel) {
		super(nomeNoel);
		this.controller = controller;

	}

	@Override
	public void run() {
		while (true) {
			try {
				setStatus(3); // dormir
				System.out.println(" Noel dormindo zZzZzZ \n");
				sleep((int) (Math.random() * 10000));

				atendeRenas();

				atendeElfos();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void atendeElfos() throws InterruptedException {
		synchronized (controller) {
			if (controller.getElfosAguardando().size() >= 3) {
				setStatus(2);
				System.out.println("Papai noel: Ola Elfos, vou ajudar voces ! \n");
				sleep(5000);
				controller.pegaTresElfos(elfosSendoAjudados);
				System.out.println(" * Papai-Noel esta ajudando o grupo de elfos * \n");
				System.out.println("Grupo de elfos: \n");
				for (Elfo elfo : elfosSendoAjudados) {
					System.out.println(elfo.getNomeElfo() + "\n");
				}
				sleep(5000);
				System.out.println(" Papai-Noel: Pronto elfos, podem voltar ao trabalho. \n");
				controller.voltarElfosTrabalho(getElfosSendoAjudados());
			}
		}
	}

	private void atendeRenas() throws InterruptedException {
		synchronized (controller) {
			if (controller.getRenasAguardando().size() == 9) {
				setStatus(2);
				controller.pegaRenasParaEntrega(renasEntregando);
				System.out.println(" Papai-Noel: Opa, as Renas estão prontas! \n");
				sleep(2000);
				System.out.println(" *Amarrando Renas no trenó* \n");
				sleep(5000);
				System.out.println(" *Distribuindo brinquedos* \n");
				sleep(5000);
				System.out.println(" *Desamarrando renas e voltando a dormir* \n");
				
				controller.voltarRenasFerias(renasEntregando);
			}
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<Elfo> getElfosSendoAjudados() {
		return elfosSendoAjudados;
	}

	public ArrayList<Rena> getRenasEntregando() {
		return renasEntregando;
	}
}
