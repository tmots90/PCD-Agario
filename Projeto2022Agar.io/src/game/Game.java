package game;
import environment.Direction;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import environment.Cell;
import environment.Coordinate;

public class Game extends Observable {

	public static final int DIMY = 30;
	public static final int DIMX = 30;
	private static final int NUM_PLAYERS = 90;
	private static final int NUM_FINISHED_PLAYERS_TO_END_GAME=3;

	public static final long REFRESH_INTERVAL = 400;
	public static final double MAX_INITIAL_STRENGTH = 3;
	public static final long MAX_WAITING_TIME_FOR_MOVE = 2000;
	public static final long INITIAL_WAITING_TIME = 10000;

	protected Cell[][] board;

	public Game() {
		board = new Cell[Game.DIMX][Game.DIMY];
		for (int x = 0; x < Game.DIMX; x++) 
			for (int y = 0; y < Game.DIMY; y++) 
				board[x][y] = new Cell(new Coordinate(x, y),this);
	}
	
	/** 
	 * @param player 
	 */
	public void addPlayerToGame(Player player) {
		Cell initialPos=getRandomCell();
		System.out.println("posicão original do player "+player.getIdentification()+":"+initialPos.getPosition().toString());
		initialPos.setPlayer(player);
		player.setPosition(initialPos);
		// To update GUI 
		notifyChange();
		player.run();
		System.out.println(player.getIdentification()+" lançado");
	}

	public Cell getCell(Coordinate at) {
		return board[at.x][at.y];
	}

	/**	
	 * Updates GUI. Should be called anytime the game state changes
	 */
	public void notifyChange() {
		setChanged();
		notifyObservers();
	}

	public Cell getRandomCell() {
		Cell newCell=getCell(new Coordinate((int)(Math.random()*Game.DIMX),(int)(Math.random()*Game.DIMY)));
		return newCell; 
	}
	
	public synchronized void move(Player p) throws InterruptedException {
// gerar a direcao pa mover
		p.th.sleep(REFRESH_INTERVAL);
		System.out.print(p.getIdentification()+" a mexer de "+ p.getPosition().toString()+"\n");
		Direction[] hipoteses = { Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT };
		int d = (int) (Math.random() * hipoteses.length);
		p.next=hipoteses[d];
// mexer o ghost
		moveTo(p, p.next);
	}
	public synchronized void moveTo(Player entity, Direction direction) throws InterruptedException { 
//		int y = (int) entity.getLocation().getY();
//		int x = (int) entity.getLocation().getX();
		Coordinate future = null; 
		Coordinate pre= entity.getPosition();	
		System.out.println(entity.getIdentification() + " - origem: "+pre.toString());
		switch (direction) {
				case UP: {//
					future = pre.translate(Direction.UP.getVector());break;
				}
				case DOWN: {
					future = pre.translate(Direction.DOWN.getVector());break;
				}
				case LEFT: {
					future = pre.translate(Direction.LEFT.getVector());break;
				}
				case RIGHT: {
					future = pre.translate(Direction.RIGHT.getVector());break;
				}
			}
			
		System.out.println(entity.getIdentification() + " - destino: "+future.toString());

		entity.setPosition(getCell(future));
		notifyChange();
		System.out.println("origem:"+ getCell(pre).isOcupied()+ "| destino:" + getCell(future).isOcupied() );
	}
}
