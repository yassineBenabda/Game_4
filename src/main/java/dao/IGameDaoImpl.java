package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import store.entities.Game;

public class IGameDaoImpl implements IGameDao {

	@Override
	public Game save(Game g) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO GAMES(NOM_GAME,PRIX) VALUES(?,?)");
			ps.setString(1, g.getNomGame());
			ps.setDouble(2, g.getPrix());
			ps.executeUpdate();
			PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(ID_GAME) as MAX_ID FROM GAMES");
			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				g.setIdGame(rs.getLong("MAX_ID"));
			}
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}

	@Override
	public List<Game> gamesParMC(String mc) {
		List<Game> gas = new ArrayList<Game>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from GAMES where NOM_GAME LIKE ?");
			ps.setString(1, "%" + mc + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Game g = new Game();
				g.setIdGame(rs.getLong("ID_GAME"));
				g.setNomGame(rs.getString("NOM_GAME"));
				g.setPrix(rs.getDouble("PRIX"));
				gas.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gas;
	}

	@Override
	public Game getGame(Long id) {
		Connection conn = SingletonConnection.getConnection();
		Game g = new Game();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from GAMES where ID_GAME = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				g.setIdGame(rs.getLong("ID_GAME"));
				g.setNomGame(rs.getString("NOM_GAME"));
				g.setPrix(rs.getDouble("PRIX"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}

	@Override
	public Game updateGame(Game g) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE GAMES SET NOM_GAME=?,PRIX=? WHERE ID_GAME=?");
			ps.setString(1, g.getNomGame());
			ps.setDouble(2, g.getPrix());
			ps.setLong(3, g.getIdGame());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}

	@Override
	public void deleteGame(Long id) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM GAMES WHERE ID_GAME = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
