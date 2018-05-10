package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReplayPool implements Iterable< ReplayData > {
	
	List< ReplayData > pool;
			
	public ReplayPool() {
		pool = new ArrayList< ReplayData >();
	}

	@Override
	public Iterator< ReplayData > iterator() {
		return pool.iterator();
	}
	
	public void addDatas(ReplayData ls ) {
		pool.add(ls);
	}
	
}
