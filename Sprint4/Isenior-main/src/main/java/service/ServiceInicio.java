package service;

import java.io.File;
import java.util.Scanner;

public class ServiceInicio {
	public ServiceInicio(){
		getHome();
	}
	public String getHome() {
		String nomeArquivo = "index.html";
		String form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }

	return form;
}
}
