package model;
import java.util.Comparator;


public class ByTime implements Comparator<Game> {

	@Override
	public int compare(Game g1, Game g2) {
		// TODO Auto-generated method stub
		return g1.getTime().compareTo(g2.getTime());
	}

}
