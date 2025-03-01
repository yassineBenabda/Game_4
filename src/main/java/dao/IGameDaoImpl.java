package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import store.entities.Game;
import util.JPAutil;

public class IGameDaoImpl implements IGameDao {

	private EntityManager entityManager = JPAutil.getEntityManager("GAME5");

	@Override
	public Game save(Game g) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(g);
		tx.commit();
		return g;
	}

	@Override
	public List<Game> gamesParMC(String mc) {
		List<Game> gas = entityManager.createQuery("select g from Game g where g.nomGame like :mc").setParameter("mc", "%" + mc + "%").getResultList();
		return gas;
	}

	@Override
	public Game getGame(Long id) {
		return entityManager.find(Game.class, id);

	}

	@Override
	public Game updateGame(Game g) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(g);
		tx.commit();
		return g;

	}

	@Override
	public void deleteGame(Long id) {
		Game game = entityManager.find(Game.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(game);
		entityManager.getTransaction().commit();
	}
}
