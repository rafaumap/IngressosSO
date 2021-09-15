
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
			System.err.println("Usu�rio #" + idCompraIng + " apresentou problemas no login: Tempo Esgotado");
			
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
			System.err.println("Usu�rio #" + idCompraIng + " apresentou problemas durante compra: Sess�o Expirada");
			
		} else {
			try {
				semaphore.acquire();
				
				int qtdIngressosSolicitados = (int) ((Math.random() * 4) + 1);
				
				
				if (qtdIngressosSolicitados > numberIngressos) {
					System.err.println("Caro usu�rio #" + idCompraIng + ", a quantidade solicitada de ingressos  (" + qtdIngressosSolicitados + ") n�o est�o dispon�vel.");
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
		
		System.out.println("Usu�rio #" + idCompraIng + " comprou " + qtdIngressosSolicitados + ". [" + numberIngressos + " ingressos dispon�veis].");
		semaphore.release();
	}

}