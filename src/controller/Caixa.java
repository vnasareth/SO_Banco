package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class Caixa extends Thread {

	protected int idConta;
	protected Semaphore semaforoSaque;
	protected Semaphore semaforoDeposito;
	protected double sConta;
	protected double valorTrans;

	public Caixa(int idConta, Semaphore semaforoSaque, Semaphore semaforoDeposito) {
		this.idConta = idConta;
		this.semaforoSaque = semaforoSaque;
		this.semaforoDeposito = semaforoDeposito;
	}

	public void run() {
		recebe();
		transacao();
	}
	
// faz o tempo de processamento da conta
	public void recebe() {
		sConta = ((Math.random() * 4000) + 1000);
		valorTrans = ((Math.random() * 2000) + 500);
	}

//	faz o procedimento de transação de saque e deposito
	public void transacao() {
		int tipoTransacao = (int) ((Math.random() * 2) + 1);// faz o gerenciamento da quantidade de saque e deposito 
		if (tipoTransacao == 1) {//faz a verificação se esta sendo feito um deposito e um saque
			try {
				semaforoSaque.acquire();
				saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoSaque.release();
			}
		} else {
			try {
				semaforoDeposito.acquire();
				deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoDeposito.release();
			}
		}

	}

	public void saque() {
		sConta -= valorTrans;
		String format = new DecimalFormat("###.##").format(valorTrans);
		String format1 = new DecimalFormat("###.##").format(sConta);
		System.out.println(
				"Conta: " + idConta + "\n"+"Saque R$: " + format + "\n"+"Saldo: " + format1);
		System.out.println("======================");
	}

	public void deposito() {
		sConta += valorTrans;
		String format = new DecimalFormat("###.##").format(valorTrans);
		String format1 = new DecimalFormat("###.##").format(sConta);
		System.out.println(
				"Conta: " + idConta +"\n"+ " Deposito R$: " + format + "\n"+"Saldo: " + format1);
		System.out.println("======================");
	}
}