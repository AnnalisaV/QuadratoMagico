package it.polito.tdp.quadrato.model;

import java.util.ArrayList;
import java.util.List;

public class RisolviQuadrato {
	private int N ; // lato del quadrato
	private int N2 ; // numero di caselle (N^2)
	private int magica ; // costante magica
	
	private List<List<Integer>> soluzioni; //una lista di liste
	
	public RisolviQuadrato(int lato) {
		this.N=lato; 
		this.N2=lato*lato; 
		this.magica= lato*(N2+1)/2; //formula per quanto devono fare le somme
		
	}
	
	//Calcola tutti i quadrati magici
	public List<List<Integer>> quadrati() {
		// si occupa dle livello zero
		List<Integer> parziale= new ArrayList<>(); 
		int livello= 0; 
		
		
		this.soluzioni= new ArrayList<List<Integer>>(); 
		ricorsiva(parziale, livello); 
		
		return soluzioni; 
	}
	
	//procedura ricorsiva (privata)
	private void ricorsiva(List<Integer> parziale, int livello) {
		
		//caso terminale
		if (livello==N2) {
			// ho un quadrato completo ma non e' detto che sia magico
			if (controlla(parziale)) {
				//ok e' magico
				System.out.println(parziale); 
				this.soluzioni.add(new ArrayList<>(parziale)); // nuovo perche' voglio il valore non il riferimento all'oggetto
			}
			// magico o no devo tornare perche' e' completo
			return; 
		}
		// controllo intermedio per livello multiplo di N ovvero alcune righe sono complete
		// perche' se la somma di quella riga e' sbagliata e' inutile andare avanti
		if(livello%N==0 && livello!=0) {
			if (!controllaRiga(parziale, livello/N-1))
				return; //potatura dell'albero di ricerca (pruning)
		}
		
		
		
		//caso generale 
		for (int valore =1; valore<=N2; valore++) {
			if (!parziale.contains(valore)) {
				// non e' ancora stato usato quindi lo posso provare 
				parziale.add(valore); 
				ricorsiva(parziale, livello+1); 
				
				// backtracking
				parziale.remove(parziale.size()-1); //tolgo l'ultimo elemento aggiunto
				
			}
		}
		
		
		
	}
	/**
	 * Verifica se una soluzione rispetta tutte le somme
	 * @param parziale
	 * @return
	 */
	private boolean controlla(List<Integer> parziale) {
		//controllo che sia completo altriimenti tanto vale 
		if(parziale.size()!=this.N*this.N)
			throw new IllegalArgumentException("Numero di elementi insufficiente") ;
		
		// Fai la somma delle righe
		for(int riga=0; riga<this.N; riga++) {
			int somma = 0 ;
			for(int col=0; col<this.N; col++) {
				somma += parziale.get(riga*this.N+col) ;
			}
			if(somma!=this.magica)
				return false ;
		}
		
		// Fai la somma delle colonne
		for(int col=0; col<this.N; col++) {
			int somma = 0 ;
			for(int riga=0; riga<this.N; riga++) {
				somma += parziale.get(riga*this.N+col) ;
			}
			if(somma!=this.magica)
				return false ;
		}
		
		// diagonale principale
		int somma = 0;
		for(int riga=0; riga<this.N; riga++) {
			somma += parziale.get(riga*this.N+riga) ;
		}
		if(somma!=this.magica)
			return false ;
		
		// diagonale inversa
		somma = 0;
		for(int riga=0; riga<this.N; riga++) {
			somma += parziale.get(riga*this.N+(this.N-1-riga)) ;
		}
		if(somma!=this.magica)
			return false ;

		return true ;
	}
	
	//controlla la somma della sola riga 
	private boolean controllaRiga(List<Integer> parziale, int riga) {
		int somma=0;
		for(int col=0; col<N; col++)
			somma+=parziale.get(riga*N+col);
		return somma==magica ;
			
	}
}
