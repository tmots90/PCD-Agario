package game;


import environment.Cell;
import environment.Direction;
import environment.Coordinate;
/**
 * Represents a player.
 * @author luismota
 *
 */
public abstract class Player implements Runnable {


	protected  Game game;

	private int id;

	private byte currentStrength;
	protected byte originalStrength;
	private boolean isDead=false;

	public Cell pos;
	public Direction next;

	public Thread th;

	// TODO: get player position from data in game
	public Cell getCurrentCell() {
		return pos;
	}

	public Player(int id, Game game, byte strength) {
		super();
		this.id = id;
		this.game=game;
		currentStrength=strength;
		originalStrength=strength;
		this.pos=pos;
		Thread p=new Thread(this);
		this.th=p;
	}

	public abstract boolean isHumanPlayer();

	@Override
	public String toString() {
		return "Player [id=" + id + ", currentStrength=" + currentStrength + ", getCurrentCell()=" + getCurrentCell()
		+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public byte getCurrentStrength() {
		return currentStrength;
	}


	public int getIdentification() {
		return id;
	}
	
public Coordinate getPosition() {
		return pos.getPosition();
	}
<<<<<<< Updated upstream:Projeto2022Agar.io_/src/game/Player.java

=======
	
	public void setPosition(Cell c) {
		 if(pos!=null)
			 this.game.getCell(pos.getPosition()).removePlayer();
		 c.setPlayer(this);
		 pos=c;
	}
	
	public void absorve(Player s) {
		this.currentStrength+=s.getCurrentStrength();
		s.death();
		if (this.getCurrentStrength()>=(byte) 10) {
			this.currentStrength=10;
			//ganhou
			System.out.println("Player "+this.getIdentification()+" chegou à energia maxima!!\n-_-_-_-_-_-_-_-_-_-_-_-_-_");
			}//upgrade
		System.out.println("O player "+this.getIdentification()+" chegou a "+this.getCurrentStrength()+" de energia");
	}
	
	private void death() {
		this.currentStrength=0;
		this.isDead=true;
		System.out.println("\nO jogador "+ this.getIdentification() + "morreu na ronda "+this.ronda+".\n#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|#|\n");
		this.th.stop();
	}	
	
>>>>>>> Stashed changes:Projeto2022Agar.io/src/game/Player.java
	public void run() {
		System.out.print("bom dia ...\n" + id + "\n");
//		try {
//			th.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.print("pronto a correr"+ id +"\n");
		for(;;){
			try {
				game.move(this);
				th.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}		
}

