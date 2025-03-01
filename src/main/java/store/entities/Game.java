package store.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GAMES")
public class Game implements Serializable {
	
	@Id
	@Column(name = "ID_GAME")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGame;
	
	@Column(name = "NOM_GAME")
	private String nomGame;
	private double prix;

	public Game() {
		super();
	}

	public Game(String nomGame, double prix) {
		super();
		this.nomGame = nomGame;
		this.prix = prix;
	}

	public Long getIdGame() {
		return idGame;
	}

	public void setIdGame(Long idGame) {
		this.idGame = idGame;
	}

	public String getNomGame() {
		return nomGame;
	}

	public void setNomGame(String nomGame) {
		this.nomGame = nomGame;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Game [idGame=" + idGame + ", nomGame=" + nomGame + ", prix=" + prix + "]";
	}

}
