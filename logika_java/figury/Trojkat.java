//Przyklad dla kwadratu. Podobnie dla pozostalych figur.
package figury;

import static java.lang.Math.*;

public class Trojkat extends Figura {

	//(1)
	// dodatkowe POLA potrzebne do okreslenia stanu Kwadratu;
		
	//(2)
	//KONSTRUKTOR - sposob inicjalizowania obiektu
	public Trojkat(float wymiarLiniowy){
		super(wymiarLiniowy);
		this.label = "TROJKAT";
		this.fieldname = "wysokosci";
	}
	
	// dodatkowe METODY  - które nie by³y zdefiniowane w klasie bazowej (Figura), w tym setters i getters
	//.
	//.
	//.
	//

	
	//(3)
	//definicje METOD zadeklarowanych w klasie bazowej jako abstrakcyjne
	@Override // ta adnotacja moze byc pominieta; sluzy tylko do poinformownia ze ponizsza metoda jest metoda nadpisana
	public double pole() {
		return (this.getWymiarLiniowy() * this.getWymiarLiniowy() * sqrt(3)) / 4.0;
	}
	
	// wszystkie metody abstrakcyjne klasy bazowej musza byc zdefiniowane w tej klasie
	//itd.
	
	
		
}
