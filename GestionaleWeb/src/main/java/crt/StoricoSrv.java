package crt;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSource;

import dao.ImpiegatoDaoImpl;
import dao.RuoloDaoImpl;
import dao.StoricoDaoImpl;
import model.Impiegato;
import model.Ruolo;
import model.Storico;

/**
 * Servlet implementation class StoricoSrv
 */
@WebServlet("/StoricoSrv")
public class StoricoSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoricoSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Storico storico = new Storico();
		StoricoDaoImpl storicoDaoImpl = new StoricoDaoImpl();
		Ruolo ruolo = new Ruolo();
		RuoloDaoImpl ruoloDaoImpl = new RuoloDaoImpl();
		Impiegato impiegato = new Impiegato();
		ImpiegatoDaoImpl impiegatoDaoImpl = new ImpiegatoDaoImpl();
		
		if (request.getParameter("idRuolo") != null)
			storico.setIdRuolo(Integer.parseInt(request.getParameter("idRuolo")));
			
		if (request.getParameter("matricola") != null)
			storico.setMatricola(Integer.parseInt(request.getParameter("matricola")));
		
		if (request.getParameter("idStorico") != null)
			storico.setIdStorico(Integer.parseInt(request.getParameter("idStorico")));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			
			java.sql.Date dataInizio = new java.sql.Date(format.parse
					(request.getParameter("dataInizio")).getTime());
			storico.setDataInizio(dataInizio);
			
			java.sql.Date dataFine = new java.sql.Date(format.parse
					(request.getParameter("dataFine")).getTime());
			storico.setDataFine(dataFine);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String op = request.getParameter("tipoOperazione");
		String tipoOperazione = op != null ? op : "";
		
		switch (tipoOperazione) {
		case "ricerca":
			storicoDaoImpl.ricercaPerIdStorico(storico.getIdStorico());
			String message = "Il tuo Storico"+ request.getParameter("idStorico")+" è stato trovato !";
			scriviMessaggio(message, response, request);
			break;
			
		case "inserisci":
			storicoDaoImpl.inserisci(storico);
			String message2 = "Il tuo Storico"+ request.getParameter("idStorico")+" è stato inserito !";
			scriviMessaggio(message2, response, request);
			break;
		case "elimina":
			storicoDaoImpl.elimina(storico.getIdRuolo());
			String message3 = "Il tuo Storico"+ request.getParameter("idStorico")+" è stato elimininato !";
			scriviMessaggio(message3, response, request);
			break;
		case "aggiorna":
			storicoDaoImpl.aggiorna(storico);
			String message4 = "Il tuo Storico"+ request.getParameter("idStorico")+" è stato aggiornato !";
			scriviMessaggio(message4, response, request);
			break;
			
		default:
			break;
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
