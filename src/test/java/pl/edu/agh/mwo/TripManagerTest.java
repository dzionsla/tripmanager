package pl.edu.agh.mwo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TripManagerTest {

	TripManager tripManager;
	Trip trip;
	Photo photo;
	
	@Before
	public void setUp() {
		tripManager = new TripManager();
		photo = new Photo("zdjecie");
		trip = new Trip("nazwa", "opis");
	}
	
	@Test
	public void testAdd() throws TripAlreadyExistsException {
		assertEquals(0, tripManager.getTrips().size());
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
	}

	@Test(expected = TripAlreadyExistsException.class)
	public void testAddTripTwice() throws TripAlreadyExistsException {
		assertEquals(0, tripManager.getTrips().size());
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
	}

	@Test
	public void testRemoveTrip() throws Exception {
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		tripManager.remove(trip.getName());
		assertEquals(0, tripManager.getTrips().size());
	}
	
	@Test
	public void testSearchByName() throws Exception {
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		assertEquals(true, tripManager.search("nazwa"));
	}
	
	@Test
	public void testSearchByDescription() throws Exception {
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		assertEquals(true, tripManager.search("opis"));
	}
	
	@Test
	public void testSearchNull() throws Exception {
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		assertEquals(true, tripManager.search(null));
	}
	
	@Test
	public void testSearchByPhotoComment() throws Exception {
		trip.addPhoto(photo);
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		assertEquals(true, tripManager.search("zdjecie"));
	}
}
