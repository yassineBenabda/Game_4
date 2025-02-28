package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.IGameDao;
import dao.IGameDaoImpl;
import store.entities.Game;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {

	IGameDao store;

	@Override
	public void init() throws ServletException {
		store = new IGameDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();
		if (path.equals("/index.do")) {
			request.getRequestDispatcher("games.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			GameModele model = new GameModele();
			model.setMotCle(motCle);
			List<Game> gas = store.gamesParMC(motCle);
			model.setGames(gas);
			request.setAttribute("model", model);
			request.getRequestDispatcher("games.jsp").forward(request, response);
		} else if (path.equals("/saisie.do")) {
			request.getRequestDispatcher("saisieGame.jsp").forward(request, response);
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Game g = store.save(new Game(nom, prix));
			request.setAttribute("game", g);
			// request.getRequestDispatcher("confirmation.jsp").forward(request,response);
			response.sendRedirect("chercher.do?motCle=");
		} else if (path.equals("/supprimer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			store.deleteGame(id);
			response.sendRedirect("chercher.do?motCle=");

		} else if (path.equals("/editer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Game g = store.getGame(id);
			request.setAttribute("game", g);
			request.getRequestDispatcher("editerGame.jsp").forward(request, response);
		} else if (path.equals("/update.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Game g = new Game();
			g.setIdGame(id);
			g.setNomGame(nom);
			g.setPrix(prix);
			store.updateGame(g);
			request.setAttribute("game", g);
			// request.getRequestDispatcher("confirmation.jsp").forward(request,response);
			response.sendRedirect("chercher.do?motCle=");
		}

		else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}