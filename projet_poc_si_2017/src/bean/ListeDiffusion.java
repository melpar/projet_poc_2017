package bean;

import java.util.ArrayList;
import java.util.List;

import annotation.Table;

@Table(name = "T_LISTEDIFFUSION_LIS")
public class ListeDiffusion {
	List<String> lis_listeEmail;

	public ListeDiffusion() {
		// TODO Auto-generated constructor stub
		this.lis_listeEmail = new ArrayList<String>();
	}

	public void ajouterEmail(String email) {
		this.lis_listeEmail.add(email);
	}

	public List<String> getLis_listeEmail() {
		return lis_listeEmail;
	}

}
