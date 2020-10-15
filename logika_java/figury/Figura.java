package figury;

public abstract class Figura {

	//(1)
	// POLA charakteryzujace wspolne wlasnosci wszystkich figur
	private float wymiarLiniowy; // bok lub promien
	public String label;
	public String fieldname;

	// 
	public Figura(float wymiarLiniowy) {
		this.wymiarLiniowy = wymiarLiniowy;
		
	}

	//(2)
	// METODY definiujace dostep do pol klasy Figura tzw. getters i setters
	//np. 
	public float getWymiarLiniowy() {
		return this.wymiarLiniowy;
	}
	public void setWymiarLiniowy(float wymiarLiniowy) {
		this.wymiarLiniowy = wymiarLiniowy;
	}
	
	/*
	public String getLabel() {
		return this.label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	*/
	
	public void printLab_1() {
		System.out.format(
			"%s\to polu %.3f "+"i %s %.3f\n",
			this.label,
			this.pole(),
			this.fieldname,
			this.wymiarLiniowy
		);
	}

	// itd.
	//
	
	//(3)
	// deklaracje METOD ABSTRAKCYJNYCH (tylko nazwa i argumenty)
	// sa to metody, ktore sa wykorzystywane dla wszystkich typow figur, ale zaleznie od rodzaju figury nalezy zastosowac inna definicje metody
	// metody abstrakcyjne sa definiowane w klasach pochodnych (klasach dziedziczacych z klasy Figura)
	//np.
	 // public abstract double pole(float wymiarLiniowy);
	 public abstract double pole();
	
	 // itd.
	 //
	
	
}
