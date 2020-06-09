package ElRonazBarberShop;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Barber {

	private LinkedBlockingQueue<Cliente> clientesNaFila = new LinkedBlockingQueue<>();
	private ArrayList<Barbeiro> barbeiros = new ArrayList<Barbeiro>();

	public Barber() {
	}

	public synchronized void atenderCliente(Barbeiro barbeiro) {
		if (getClientesNaFila().size() > 0) {
			barbeiro.setCortando();
			System.out.println("|Barbeiro| " + barbeiro.getName() + " ira atender o primeiro da fila ! \n");

			Cliente c = getClientesNaFila().peek();
			barbeiro.setClienteEmAtendimento(c);
			c.setEmAtendimento();
			getClientesNaFila().poll();
		}
	}

	public synchronized void addClienteFila(Cliente cliente) {
		if (getClientesNaFila().size() < 8) {
			getClientesNaFila().add(cliente);
			cliente.setEsperando(true);
			System.out.println("|Cliente| " + cliente.getNome() + " esta na fila aguardando atendimento. \n");

		} else {
			System.out.println("|SYSTEM| Fila lotada, cliente " + cliente.getNome() + " indo embora. \n");
			cliente.interrupt();
		}

	}

	public synchronized void terminarCorte(Barbeiro barbeiro, Cliente cliente) {
		barbeiro.getClienteEmAtendimento().setAtendido();
		barbeiro.setDormindo();
		cliente.setAtendido();

		trocarBarbeiro(barbeiro);
	}

	private synchronized void trocarBarbeiro(Barbeiro barbeiro) {
		String nmTrocou = "";
		for (Barbeiro b : barbeiros) {
			if (b.isOrganizando()) {
				nmTrocou = b.getNome();
				b.setDormindo();
			}
		}
		barbeiro.setOrganizando();
		System.out.println("|SYSTEM| Barbeiro " + nmTrocou + " trocou de lugar com o Barbeiro " + barbeiro.getNome() + "\n");
	}

	public synchronized LinkedBlockingQueue<Cliente> getClientesNaFila() {
		return clientesNaFila;
	}

	public void addBarbeiros(Barbeiro b1, Barbeiro b2, Barbeiro b3) {
		barbeiros.add(b1);
		barbeiros.add(b2);
		barbeiros.add(b3);
		barbeiros.stream().forEach(b -> b.start());
	}

	public void joinRotina() {
		for (Barbeiro b : barbeiros) {
			try {
				b.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
}
