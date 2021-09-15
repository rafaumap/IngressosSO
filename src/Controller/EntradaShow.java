
package Controller;

import java.util.concurrent.Semaphore;

public class EntradaShow extends Thread {
	
	private Semaphore semaphore;
	private int idCompraIng;
	private static int numberIngressos = 100;

	public EntradaShow(int idCompraIng, Semaphore semaphore) {
		this.idCompraIng = idCompraIng;
		this.semaphore = semaphore;
	}
	
	@Override
	public void run() {
		logar();
	}
	
	
	private void logar() {
		int tempoDeLogon = (int) ((Math.random() * 2000) + 50);
		
		try {
			sleep(tempoDeLogon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (tempoDeLogon > 1000) {
			System.err.println("Usuário #" + idCompraIng + " apresentou problemas no login: Tempo Esgotado");
			
		} else {
			escolherIngresso();
		}
		
	}
	
	private void escolherIngresso() {
		int tempoDeEscolha = (int) ((Math.random() * 3000) + 1000);
		
		try {
			sleep(tempoDeEscolha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (tempoDeEscolha > 2500) {
			System.err.println("Usuário #" + idCompraIng + " apresentou problemas durante compra: Sessão Expirada");
			
		} else {
			try {
				semaphore.acquire();
				
				int qtdIngressosSolicitados = (int) ((Math.random() * 4) + 1);
				
				
				if (qtdIngressosSolicitados > numberIngressos) {
					System.err.println("Caro usuário #" + idCompraIng + ", a quantidade solicitada de ingressos  (" + qtdIngressosSolicitados + ") não estão disponível.");
				} else {
					comprarIngresso(qtdIngressosSolicitados);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
	private void comprarIngresso(int qtdIngressosSolicitados) {
		numberIngressos -= qtdIngressosSolicitados;
		
		System.out.println("Usuário #" + idCompraIng + " comprou " + qtdIngressosSolicitados + ". [" + numberIngressos + " ingressos disponíveis].");
		semaphore.release();
	}

}