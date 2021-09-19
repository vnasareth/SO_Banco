package view;

import java.util.concurrent.Semaphore;
import controller.Caixa;

public class Principal {

	public static void main(String[] args) {

		int permissaoSaque = 1;
		int permissaoDeposito = 1;

		Semaphore semaforoSaque = new Semaphore(permissaoSaque);
		Semaphore semaforoDeposito = new Semaphore(permissaoDeposito);

		for (int idConta = 1; idConta < 21; idConta++) {
			Thread tTrans = new Caixa(idConta, semaforoSaque, semaforoDeposito);
			tTrans.start();
		}
	}
}