package tp1;


import java.util.List;

import tp1.beans.Livre;

public class Main2 {
	

	public static void main(String[] args) {
		
		Base base = new Base();
		base.ouvrir();
		base.enregistrer2(new Livre("bobo","leGoinfre",2050));
		List<Object> result = base.lister();
		for(Object obj : result) {
			System.out.println(obj.toString());
		}
		
		base.fermer();
		
	}

}
