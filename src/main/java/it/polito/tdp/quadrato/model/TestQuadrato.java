package it.polito.tdp.quadrato.model;


import java.util.List;

public class TestQuadrato {

	public static void main(String[] args) {

		RisolviQuadrato r= new RisolviQuadrato(5);
		
		List<List<Integer>> soluzioni= r.quadrati();
		
		for (List<Integer> sol : soluzioni) {
			System.out.println(sol); 
		}
	}

}
