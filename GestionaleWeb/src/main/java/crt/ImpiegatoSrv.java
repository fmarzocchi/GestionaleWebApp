package crt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ImpiegatoDaoImpl;
import model.Impiegato;


@WebServlet("/ImpiegatoSrv")
public class ImpiegatoSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public ImpiegatoSrv() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Impiegato impiegato = new Impiegato();
		impiegato.setNome(request.getParameter("nome"));
		impiegato.setCodiceFiscale(request.getParameter("codiceFiscale"));
		impiegato.setCognome(request.getParameter("cognome"));
		if (request.getParameter("matricola") != null && request.getParameter("matricola") != "")
			impiegato.setMatricola(Integer.parseInt(request.getParameter("matricola")));
		
		ImpiegatoDaoImpl impDaoImpl = new ImpiegatoDaoImpl();
		String op = request.getParameter("tipoOperazione");
		String jspBack = request.getParameter("tornaAllaJsp");
		String tornaAllaJsp = jspBack != null ? jspBack : "";
		String tipoOperazione = op != null ? op : "";
		
		doOperation(response,request,impiegato,impDaoImpl,tornaAllaJsp,tipoOperazione);
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void scriviMessaggio(String message, HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("text/html"); // Imposta il tipo di contenuto come HTML
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>" + message + "</h1>"); // Stampa il messaggio a schermo
		out.println("</body></html>");
	}
	
	private void doOperation(HttpServletResponse response, HttpServletRequest request , Impiegato impiegato,
			 ImpiegatoDaoImpl impDaoImpl, String tornaAllaJsp, String tipoOperazione) throws IOException, ServletException {
		
		switch (tipoOperazione) {
		    case "inserisci":
		        impDaoImpl.inserisci(impiegato);
		        String message1 = "Il tuo Impiegato " + request.getParameter("nome") + " è stato inserito!";
		        scriviMessaggio(message1, response, request);
		        break;
		        
		    case "aggiorna":
		        impDaoImpl.aggiorna(impiegato);
		        if (tornaAllaJsp.equals("AggiornaImpiegato")) {
		        	request.getRequestDispatcher("/AggiornaImpiegato.jsp").forward(request, response);
				}
		        String message2 = "Il tuo Impiegato " + request.getParameter("nome") + " è stato aggiornato!";
		        scriviMessaggio(message2, response, request);
		        break;
		        
		    case "elimina":
		        impDaoImpl.elimina(impiegato.getCodiceFiscale());
		        if (tornaAllaJsp.equals("RisultatiRicercaImpiegatoPerCognome")) {
					doOperation(response, request, impiegato, impDaoImpl, tornaAllaJsp, "ricercaPerCognome");
					System.out.println("sto a cerca per cognome");
				}
		        String message3 = "Il tuo Impiegato " + request.getParameter("nome") + " è stato eliminato!";
		        scriviMessaggio(message3, response, request);
		        break;
		        
		    case "cerca":
		        Impiegato impTrovato =impDaoImpl.ricercaPerCodiceFiscale(impiegato.getCodiceFiscale());
		        if (impTrovato != null) {
		        	request.getSession().setAttribute("impTrovato", impTrovato);
					request.getRequestDispatcher("/AggiornaImpiegato.jsp").forward(request, response);
				}else {
					String message4 = "Il tuo Impiegato non è stato trovato!";
			        scriviMessaggio(message4, response, request);
				}
		        break;
		        
		    case "ricercaPerCognome":
		    	System.out.println("confermo che sto a cerca");
		    	List<Impiegato> impTrovatiCognome =impDaoImpl.ricercaPerCognome(impiegato.getCognome());
		        if (impTrovatiCognome != null) {
		        	System.out.println("ho trovato qualcosa");
		        	request.getSession().setAttribute("impTrovati", impTrovatiCognome);
					request.getRequestDispatcher("/RisultatiRicercaImpiegatoPerCognome.jsp")
					.forward(request, response);
				}else {
					String message4 = "Il tuo Impiegato non è stato trovato!";
			        scriviMessaggio(message4, response, request);
				}
		    	break;
		        
		    default:
		       
		        break;
		}
	}

}
