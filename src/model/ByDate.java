package model;
import java.util.Comparator;

public class ByDate implements Comparator<Game> {

	@Override
	public int compare(Game g1, Game g2) {
		// TODO Auto-generated method stub
		return g1.getDate().compareTo(g2.getDate());
	}

}
