import java.util.ArrayList;

public class Controller {

	private Noel noel;

	private ArrayList<Rena> renasFerias = new ArrayList<Rena>();
	private ArrayList<Elfo> elfosTrabalhando = new ArrayList<Elfo>();

	private ArrayList<Rena> renasAguardando = new ArrayList<Rena>();
	private ArrayList<Elfo> elfosAguardando = new ArrayList<Elfo>();

	public synchronized void attRenasAguardando(Rena renaAguardando) {
		if (renasFerias.remove(renaAguardando)) {
			renaAguardando.setAjudandoNoel(true);
			renasAguardando.add(renaAguardando);
		}
	}

	public synchronized void attElfosAguardando(Elfo elfoAguardando) {
		if (elfosTrabalhando.remove(elfoAguardando)) {
			elfosAguardando.add(elfoAguardando);
			elfoAguardando.setEmConsultoria(true);
		}
	}

	public synchronized void voltarRenasFerias(ArrayList<Rena> renasEmEntrega) {
		if (renasFerias.addAll(renasEmEntrega)) {
			renasEmEntrega.stream().forEach(r -> r.setAjudandoNoel(false));
			renasEmEntrega.clear();
		}
	}

	public synchronized void voltarElfosTrabalho(ArrayList<Elfo> elfosSendoAjudados) {
		if (elfosTrabalhando.addAll(elfosSendoAjudados)) {
			elfosSendoAjudados.stream().forEach(e -> e.setEmConsultoria(false));
			elfosSendoAjudados.clear();
			
		}
	}

	public synchronized void pegaRenasParaEntrega(ArrayList<Rena> renasEntregando) {
		if (renasEntregando.addAll(renasAguardando)) {
			renasAguardando.clear();
		}
	}

	public synchronized void pegaTresElfos(ArrayList<Elfo> elfosSendoAjudados) {
		for (int i = 0; i < 3; i++) {
			elfosSendoAjudados.add(elfosAguardando.get(i));
		}
		for (Elfo elfo : elfosSendoAjudados) {
			elfosAguardando.remove(elfo);
		}
	}

	public void startaRotina() {
		noel.start();
		elfosTrabalhando.stream().forEach(e -> e.start());
		renasFerias.stream().forEach(r -> r.start());
	}

	public void joinRotina() {
		for (Elfo elfo : elfosTrabalhando) {
			try {
				elfo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (Rena rena : renasFerias) {
			try {
				rena.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			noel.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized ArrayList<Rena> getRenasFerias() {
		return renasFerias;
	}

	public synchronized void setRenas(ArrayList<Rena> renas) {
		this.renasFerias = renas;
	}

	public synchronized ArrayList<Elfo> getElfos() {
		return elfosTrabalhando;
	}

	public synchronized void setElfos(ArrayList<Elfo> elfos) {
		this.elfosTrabalhando = elfos;
	}

	public synchronized Noel getNoel() {
		return noel;
	}

	public void setNoel(Noel noel) {
		this.noel = noel;
	}

	public synchronized ArrayList<Elfo> getElfosAguardando() {
		return elfosAguardando;
	}

	public ArrayList<Rena> getRenasAguardando() {
		return renasAguardando;
	}

}
