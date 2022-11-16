package gui;

import java.util.Observable;
import java.util.Observer;
import game.Game;
import game.PhoneyHumanPlayer;
import game.Player;
import game.HumanPlayer;

import javax.swing.JFrame;

public class GameGuiMain implements Observer {
	private JFrame frame = new JFrame("pcd.io");
	private BoardJComponent boardGui;
	private Game game;
	private static final int NUMBER_PLAYERS =7;

	public GameGuiMain() {
		super();
		game = new Game();
		game.addObserver(this);

		buildGui();

	}

	private void buildGui() {
		boardGui = new BoardJComponent(game);
		frame.add(boardGui);


		frame.setSize(800,800);
		frame.setLocation(0, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init()  {
		frame.setVisible(true);

		// Demo players, should be deleted
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Player p= new HumanPlayer(10,game);
		game.human=(HumanPlayer) p;
		game.addPlayerToGame(p);
		p.th.start();
		System.out.println("\n\nJogador "+p.getIdentification()+" lançado c sucesso\nHumano\n");
		for (int i = 0; i<NUMBER_PLAYERS; i++) { 
			p=new PhoneyHumanPlayer((i+1), game);
			game.addPlayerToGame(p);
			p.th.start();
			System.out.println("\n\nJogador "+i+" lançado c sucesso\n\n");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		boardGui.repaint();
	}

	public static void main(String[] args) {
		GameGuiMain game = new GameGuiMain();
		game.init();
	}

}
