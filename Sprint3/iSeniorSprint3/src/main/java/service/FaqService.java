package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.FaqDAO;
import model.Faq;
import spark.Request;
import spark.Response;


public class FaqService {

	private FaqDAO faqDAO = new FaqDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_PERGUNTA = 2;
	private final int FORM_ORDERBY_RESPOSTA = 3;
	
	
	public FaqService() {
		makeForm();
	}

	
	
	public String getHome() {
		makeForm();
		return form;
	}
	
	public void makeForm() {
		makeForm(FORM_INSERT, new Faq(), FORM_ORDERBY_PERGUNTA);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Faq(), orderBy);
	}

	
	public void makeForm(int tipo, Faq faq, int orderBy) {
		String nomeArquivo = "form2.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umFaq = "";
		if(tipo != FORM_INSERT) {
			umFaq += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umFaq += "\t\t<tr>";
			umFaq += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/faq/list/1\">Nova Pergunta</a></b></font></td>";
			umFaq += "\t\t</tr>";
			umFaq += "\t</table>";
			umFaq += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/faq/";
			String name, pergunta, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Pergunta";
				pergunta = "Adicione a pergunta";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + faq.getId();
				name = "Atualizar Pergunta (ID " + faq.getId() + ")";
				pergunta = faq.getPergunta();
				buttonLabel = "Atualizar";
			}
			umFaq += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umFaq += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umFaq += "\t\t<tr>";
			umFaq += "\t\t\t<td colspan=\"3\" align=\"center\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umFaq += "\t\t</tr>";
			umFaq += "\t\t<tr>";
			umFaq += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umFaq += "\t\t</tr>";
			umFaq += "\t\t<tr>";
			umFaq += "\t\t\t<td>&nbsp;Pergunta: <input class=\"input--register\" type=\"text\" name=\"pergunta\" value=\""+ pergunta +"\"></td>";
			umFaq += "\t\t\t<td>Resposta: <input class=\"input--register\" type=\"text\" name=\"resposta\" value=\""+ faq.getResposta() +"\"></td>";
			umFaq += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umFaq += "\t\t</tr>";	
			umFaq += "\t</table>";
			umFaq += "\t</form>";
		} else if (tipo == FORM_DETAIL){
			umFaq += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umFaq += "\t\t<tr>";
			umFaq += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Pergunta (ID " + faq.getId() + ")</b></font></td>";
			umFaq += "\t\t</tr>";
			umFaq += "\t\t<tr>";
			umFaq += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umFaq += "\t\t</tr>";
			umFaq += "\t\t<tr>";
			umFaq += "\t\t\t<td>&nbsp;Pergunta: "+ faq.getPergunta() +"</td>";
			umFaq += "\t\t\t<td>Resposta: "+ faq.getResposta() +"</td>";
			umFaq += "\t\t</tr>";
			umFaq += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo nÃ£o identificado " + tipo);
		}
		form = form.replaceFirst("<UM-FAQ>", umFaq);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação FAQ</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/faq/list/" + FORM_ORDERBY_ID + "\"><b>Id</b></a></td>\n" +
        		"\t<td><a href=\"/faq/list/" + FORM_ORDERBY_PERGUNTA + "\"><b>Pergunta</b></a></td>\n" +
        		"\t<td><a href=\"/faq/list/" + FORM_ORDERBY_RESPOSTA + "\"><b>Resposta</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Faq> faqs;
		if (orderBy == FORM_ORDERBY_ID) {                 	faqs = faqDAO.getOrderById();
		} else if (orderBy == FORM_ORDERBY_PERGUNTA) {		faqs = faqDAO.getOrderByPergunta();
		} else if (orderBy == FORM_ORDERBY_RESPOSTA) {			faqs = faqDAO.getOrderByResposta();
		} else {											faqs = faqDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Faq f : faqs) {
			bgcolor = (i++ % 2 == 0) ? "#dcebff" : "#858fbb";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + f.getId() + "</td>\n" +
            		  "\t<td>" + f.getPergunta() + "</td>\n" +
            		  "\t<td>" + f.getResposta() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/faq/" + f.getId() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/faq/update/" + f.getId() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteFaq('" + f.getId() + "', '" + f.getPergunta() + "', '" + f.getResposta() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-FAQ>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String pergunta = request.queryParams("pergunta");
		String resposta = request.queryParams("resposta");
		
		String resp = "";
		
		Faq faq = new Faq(-1, pergunta, resposta);
		
		if(faqDAO.insert(faq) == true) {
            resp = "Pergunta (" + pergunta + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Pergunta (" + pergunta + ") nÃ£o inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Faq faq = (Faq) faqDAO.get(id);
		
		if (faq != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, faq, FORM_ORDERBY_PERGUNTA);
        } else {
            response.status(404); // 404 Not found
            String resp = "Pergunta " + id + " nÃ£o encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Faq faq = (Faq) faqDAO.get(id);
		
		if (faq != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, faq, FORM_ORDERBY_PERGUNTA);
        } else {
            response.status(404); // 404 Not found
            String resp = "Pergunta " + id+ " nÃ£o encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Faq faq = faqDAO.get(id);
        String resp = "";       

        if (faq != null) {
        	faq.setPergunta(request.queryParams("pergunta"));
        	faq.setResposta(request.queryParams("resposta"));
        	faqDAO.update(faq);
        	response.status(200); // success
            resp = "Pergunta (ID " + faq.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Pergunta (ID \" + faq.getId() + \") nÃ£o encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params("id"));
        Faq faq = faqDAO.get(id);
        String resp = "";       

        if (faq != null) {
            faqDAO.delete(id);
            response.status(200); // success
            resp = "Pergunta (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Pergunta (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}