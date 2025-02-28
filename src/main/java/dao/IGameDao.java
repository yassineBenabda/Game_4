package dao;

import java.util.List;
import store.entities.Game;

public interface IGameDao {
	public Game save(Game g);

	public List<Game> gamesParMC(String mc);

	public Game getGame(Long id);

	public Game updateGame(Game g);

	public void deleteGame(Long id);
}
