// klasa utworzona tylko dla jednej metody - main
// metoda ta moze byc umieszczona w innej klasie, nie ma koniecznosci tworzenia specjalnej klasy jak w tym przypadku 
// zostalo to zrobione dla zwiekszenia czytelnosci projektu

package figury;

import java.util.*;


public class Program {

	public static void main(String[] args) {
		
	//Kwadrat kwadrat = new Kwadrat(5);
	//System.out.format("Kwadrat : %.2f \n", kwadrat.pole());
	
	//Kolo kolo = new Kolo(5);
	//System.out.format("Ko³o : %.2f \n", kolo.pole());
	
	//Trojkat trojkat = new Trojkat(5);
	//System.out.format("Trojkat : %.2f \n", trojkat.pole());
	
	Random generator = new Random(); //obiekt generatora liczb pseudolosowych
	//losowanie  liczb pseudolosowych dla okreslenia typu figury i wymiaru liniowego 
	// wyniki umiescic w tablicy
	
	//generator.nextInt(3); // <0,2>
	//nextDouble(); // 0.0 .. 1.0
	
	int N = 10;
		
	Figura[] figury = new Figura[N]; //tablica figur
	// wypelnianie tablicy  referencjami do wygenerowanych obiektow figur (typ figury okresla wczesniej wylosowana liczba)
	
	for(int idx = 0; idx < N; ++idx){
		int choice = generator.nextInt(3);
		float val = generator.nextFloat();
		switch(choice) {
			case 0:
				figury[idx] = new Kwadrat(val);
			break;
			case 1:
				figury[idx] = new Kolo(val);
			break;
			case 2:
				figury[idx] = new Trojkat(val);
			break;
		}
	}
	

	//wypisywanie figur
	for(int idx = 0; idx < N; ++idx){
		//System.out.println("Hello");
		// odczyt wlasnosci figury z tablicy figur (tablica zawiera referencje do obiektów którymi s¹ figury)
		//System.out.println();// wyswietlenie sformatowanego wiersza w terminalu
		//System.out.format("%s\to polu %.3f "+"i xxx %.3f\n", figury[idx].getLabel(), figury[idx].pole(), figury[idx].getWymiarLiniowy());
		figury[idx].printLab_1();
		}
		
	
	}

}
