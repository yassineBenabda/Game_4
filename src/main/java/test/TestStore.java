package test;

import java.util.List;

import dao.IGameDaoImpl;
import store.entities.Game;

public class TestStore {

	public static void main(String[] args) {
		IGameDaoImpl gdao = new IGameDaoImpl();
		Game game = gdao.save(new Game("nfs", 40));
		System.out.println(game);
		List<Game> gas = gdao.gamesParMC("n");
		for (Game p : gas)
			System.out.println(p);

	}
}
