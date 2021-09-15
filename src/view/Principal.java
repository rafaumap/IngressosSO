
package view;

import java.util.concurrent.Semaphore;

import Controller.EntradaShow;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);
		
		for (int idCompraIng = 1; idCompraIng <= 300; idCompraIng++) {
			EntradaShow entradaShowController = new EntradaShow(idCompraIng, semaphore);
			entradaShowController.start();
		}
		

	}

}