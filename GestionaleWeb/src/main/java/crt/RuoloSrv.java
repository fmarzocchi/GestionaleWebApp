package crt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RuoloDaoImpl;
import model.Ruolo;

/**
 * Servlet implementation class RuoloSrv
 */
@WebServlet("/RuoloSrv")
public class RuoloSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RuoloSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Ruolo ruolo = new Ruolo();
		if (request.getParameter("idRuolo") != null) {
			
			ruolo.setIdRuolo(Integer.parseInt(request.getParameter("idRuolo")));
			ruolo.setDescrizione(request.getParameter("descrizione"));
			RuoloDaoImpl ruoloDaoImpl = new RuoloDaoImpl();
			
			String op = request.getParameter("tipoOperazione");
			String tipoOperazione = op != null ? op : "";
			
			switch (tipoOperazione) {
			case "ricerca":
				Ruolo ruoloTrovato = ruoloDaoImpl.ricercaPerId(ruolo.getIdRuolo());
				if (ruoloTrovato != null) {
					request.getRequestDispatcher("/AggiornaRuolo.jsp").forward(request, response);
				}else {
					String message = "Il tuo Ruolo non è stato trovato !";
					scriviMessaggio(message, response, request);
				}
				break;
				
			case "inserisci":
				ruoloDaoImpl.inserisci(ruolo);
				String message2 = "Il tuo Ruolo è stato inserito !";
				System.out.println(message2);
				scriviMessaggio(message2, response, request);
				break;
			case "elimina":
				ruoloDaoImpl.elimina(ruolo.getIdRuolo());
				String message3 = "Il tuo Ruolo"+ request.getParameter("descrizione")+" è stato elimininato !";
				scriviMessaggio(message3, response, request);
				break;
			case "aggiorna":
				ruoloDaoImpl.aggiorna(ruolo);
				String message4 = "Il tuo Ruolo"+ request.getParameter("descrizione")+" è stato aggiornato !";
				scriviMessaggio(message4, response, request);
				break;
				
			default:
				break;
			}
			
			
			
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	void scriviMessaggio(String message, HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("text/html"); // Imposta il tipo di contenuto come HTML
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>" + message + "</h1>"); // Stampa il messaggio a schermo
		out.println("</body></html>");
	}

}
