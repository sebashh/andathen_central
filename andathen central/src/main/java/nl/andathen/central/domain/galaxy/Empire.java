package nl.andathen.central.domain.galaxy;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Empire extends Body implements List<Star> {
	private String description;
	private List<Star> stars;

	@Override
	public int size() {
		return stars.size();
	}

	@Override
	public boolean isEmpty() {
		return stars.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return stars.contains(o);
	}

	@Override
	public Iterator<Star> iterator() {
		return stars.iterator();
	}

	@Override
	public Object[] toArray() {
		return stars.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return stars.toArray(a);
	}

	// To Do: check to prevent a star system being member of more than 1 empire
	@Override
	public boolean add(Star e) {
		return stars.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return stars.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return stars.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Star> c) {
		return stars.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return stars.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return stars.removeAll(c);
	}

	@Override
	public void clear() {
		stars.clear();
		
	}
	
	public Star find(Star planet) {
		Iterator<Star> i = stars.iterator();
		while (i.hasNext()) {
			Star result = i.next();
			if (result.equals(planet)) {
				return result;
			}
		}
		return null;
	}
	
	@Override
	public Coordinate getCoordinate() {
		float distance = 0;
		float latitude = 0;
		float longitude = 0;
		for (Star p: stars) {
			distance += p.getCoordinate().getDistance();
			latitude += p.getCoordinate().getLatitude();
			longitude += p.getCoordinate().getLongitude();
		}
		return new Coordinate(distance / stars.size(), longitude / stars.size(), latitude / stars.size());
	}

	@Override
	public boolean addAll(int index, Collection<? extends Star> c) {
		return stars.addAll(c);
	}

	@Override
	public Star get(int index) {
		return stars.get(index);
	}

	@Override
	public Star set(int index, Star element) {
		return stars.set(index, element);
	}

	@Override
	public void add(int index, Star element) {
		stars.add(index,element);
		
	}

	@Override
	public Star remove(int index) {
		return stars.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return stars.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return stars.lastIndexOf(o);
	}

	@Override
	public ListIterator<Star> listIterator() {
		return stars.listIterator();
	}

	@Override
	public ListIterator<Star> listIterator(int index) {
		return stars.listIterator(index);
	}

	@Override
	public List<Star> subList(int fromIndex, int toIndex) {
		return stars.subList(fromIndex, toIndex);
	}

	public String getDescription() {
		return description;
		
	}

	public void setDescription(String description) {
		this.description = description;
		
	}
}
