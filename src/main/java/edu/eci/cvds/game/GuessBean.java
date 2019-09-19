package edu.eci.cvds.game;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.ArrayList;
import java.util.Random;
@ManagedBean(name="guessBean")
@SessionScoped
public class GuessBean {
	public static final int PREMIO_MAXIMO=100;
	private int numberToGuess;
	private int numeroIntentos;
	private int acumulado;
	private String estado;
	private ArrayList<Integer> intentos;
	
	
	


	public GuessBean() {
		 intentos=new ArrayList<Integer>();
		 restart();
	}
	public ArrayList<Integer> getIntentos() {
		return intentos;
	}


	public int getNumberToGuess() {
		return numberToGuess;
	}

	public int getNumeroIntentos() {
		return numeroIntentos;
	}

	public int getAcumulado() {
		return acumulado;
	}


	public String getEstado() {
		return estado;
	}
	public void restart() {
		acumulado=PREMIO_MAXIMO;
		numeroIntentos=0;
		Random r=new Random();
		numberToGuess=r.nextInt(100);
		estado="Bienvenido";
		intentos.clear();
	} 
	public void guess(int intento) {
		if(intento!=numberToGuess) {
			numeroIntentos+=1;
			acumulado-=10;
			estado="Sigue intentando";
			intentos.add(intento);
		}
		else {
			estado="Has ganado !";
		}
	} 


}
