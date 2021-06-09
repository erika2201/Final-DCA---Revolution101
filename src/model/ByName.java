package model;
import java.util.Comparator;
public class ByName implements Comparator<Game> {

	@Override
	public int compare(Game g1, Game g2) {
		// TODO Auto-generated method stub
		return g1.getPlayName().compareTo(g2.getPlayName());
	}

}
