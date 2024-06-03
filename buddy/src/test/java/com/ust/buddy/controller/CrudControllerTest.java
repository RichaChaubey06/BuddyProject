
package com.ust.buddy.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ust.buddy.entity.Associate;
import com.ust.buddy.entity.Buddy;
import com.ust.buddy.repository.AssociateRepository;
import com.ust.buddy.repository.BuddyRepository;
import com.ust.buddy.service.AssociateService;
import com.ust.buddy.service.BuddyService;

import javassist.NotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
public class CrudControllerTest {

	@InjectMocks
	CrudController controller;

	@Mock
	AssociateService associateService;

	@Mock
	BuddyService buddyService;

	@Mock
	AssociateRepository associateRepository;

	@Mock
	BuddyRepository buddyRepository;

	private Buddy testBuddy;
	
//	private static final Buddy expectedBuddy = null;

	@Test
	public void testSaveAssociate() {

		Associate associate = new Associate();
		associate.setAssociate_id("255389");
		associate.setBuddy(null);
		associate.setEmail_id("richa@ust.com");
		associate.setEnd_date(new Date());
		associate.setFirst_name("ritika");
		associate.setLast_name("mishra");
		associate.setPhoneno(905056040);
		associate.setStatus("Active");

		String result = controller.saveAssociate(associate);
		verify(associateService, times(1)).saveAssociate(associate);
		assertEquals("success", result);

	}

	/*
	 * @Test public void testFindAssociateById() {
	 * 
	 * Associate expected = new Associate(); Associate
	 * associate=mock(Associate.class); String associate_id = "1245";
	 * when(associate.getAssociate_id()).thenReturn(associate_id);
	 * when(associate.getFirst_name()).thenReturn("richa");
	 * when(associate.getPrev_account_name()).thenReturn("walmart");
	 * when(associateService.findAssociatesById(associate_id)).thenReturn(associate)
	 * ; Associate actual = controller.findAssociatesById(associate_id);
	 * verify(associateService, times(1)).findAssociatesById(associate_id);
	 * assertEquals(expected, actual);
	 * 
	 * //
	 * when(associateRepository.findByAssociateId(associate_id)).thenReturn(Optional
	 * .of(expected)); // Associate actual =
	 * associateService.findAssociatesById(associate_id); // assertEquals(expected,
	 * actual); }
	 */

	@Test
	public void testFindAssociateById_AssociateNotFound() {
		String associate_id = "13456";
		when(associateService.findAssociatesById(associate_id)).thenReturn(null);
		Associate result = controller.findAssociatesById(associate_id);
		verify(associateService, times(1)).findAssociatesById(associate_id);
		assertEquals(null, result);
	}

	/*
	 * @Test public void testFindAssociateById_Exception() { String associate_id=
	 * "13456";
	 * when(associateService.findAssociatesById(associate_id)).thenThrow(new
	 * NotFoundException("Associate not found")); Associate result =
	 * controller.findAssociatesById(associate_id);
	 * verify(associateService,times(1)).findAssociatesById(associate_id);
	 * assertEquals(null, result);
	 * 
	 * 
	 * }
	 */
	@Test
	public void testUpdateAssociate_success() {
		Associate updatedAssociate = new Associate();
		String associate_id = "123585";
		Associate existingAssociate = new Associate();
		when(associateService.findAssociatesById(associate_id)).thenReturn(existingAssociate);
		ResponseEntity<String> result = controller.updateAssociate(associate_id, updatedAssociate);
		verify(associateService, times(1)).findAssociatesById(associate_id);
		verify(associateService, times(1)).updateAssociate(existingAssociate);
		assertEquals("Associate updated successfully", result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	/*
	 * @Test public void testUpdateAssociate_Exception() { String associate_id =
	 * "1234585";
	 * when(associateService.findAssociatesById(associate_id)).thenThrow(Exception.
	 * class); ResponseEntity<String> result =
	 * controller.updateAssociate(associate_id, new Associate());
	 * verify(associateService, times(1)).findAssociatesById(associate_id);
	 * assertEquals("failure", result.getBody());
	 * assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode()); }
	 */

	@Test
	public void testDeleteAssociateById_success() {
		String associate_id = "123585";
		when(associateService.deleteAssociateById(associate_id)).thenReturn(true);
		ResponseEntity<String> result = controller.deleteAssociateById(associate_id);
		verify(associateService, times(1)).deleteAssociateById(associate_id);
		assertEquals("Associate deleted", result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());

	}

	@Test
	public void testDeleteAssociateById_NotFound() {
		String associate_id = "12345";
		when(associateService.deleteAssociateById(associate_id)).thenReturn(false);
		ResponseEntity<String> result = controller.deleteAssociateById(associate_id);
		verify(associateService, times(1)).deleteAssociateById(associate_id);
		assertEquals("Associate not found", result.getBody());
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

	}

	@Test
	public void testCreateBuddy_Success() {

		Buddy buddy = new Buddy();
		buddy.setAccount_name(null);
		buddy.setAssociate(null);
		buddy.setBuddy_id("123456");
		buddy.setEmail(null);
		buddy.setNo_associates_assigned(0);

		String result = controller.createBuddy(buddy);
		verify(buddyService, times(1)).createBuddy(buddy);
		assertEquals("success", result);

	}

	/*
	 * @Test public void testFindAssociatesById() { // Arrange String associate_id =
	 * "12345"; Associate expected = new Associate();
	 * when(associateRepository.findByAssociateId(associate_id)).thenReturn(Optional
	 * .of(expected)); // Act Associate actual =
	 * associateService.findAssociatesById(associate_id); // Assert //
	 * assertNotNull(actual); assertEquals(expected, actual); }
	 */
	/*
	 * @Test public void testFindByBuddyId_Success() { Buddy buddy = new Buddy();
	 * String buddy_id="123456";
	 * when(buddyService.findBuddyById(buddy_id)).thenReturn(buddy); Buddy result =
	 * controller.findBuddyById(buddy_id);
	 * verify(buddyService,times(1)).findBuddyById(buddy_id); assertEquals(buddy,
	 * result); }
	 */

	@Test
	public void testFindBuddyById_Success() {
	    // Arrange
	    String buddyId = "123456";
	    Buddy expectedBuddy = new Buddy();
	    expectedBuddy.setBuddy_id(buddyId);
	    when(buddyService.findBuddyById(buddyId)).thenReturn(expectedBuddy);
 
	    // Act
	    Buddy result = controller.findBuddyById(buddyId);
 
	    // Assert
	    assertNotNull("Result should not be null", result);
	    assertEquals("Returned buddy should match expected", expectedBuddy, result);
	}
}


/*
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
 
import com.ust.buddy.entity.Buddy;
import com.ust.buddy.repository.BuddyRepository;
import com.ust.buddy.service.BuddyService;
 
@RunWith(MockitoJUnitRunner.class)
public class CrudControllerTest {
 
    @Mock
    private BuddyRepository buddyRepository;
 
    @InjectMocks
    private BuddyService buddyService;
 
    private Buddy testBuddy;
 
    private static final String BUDDY_ID = "123456";
    private static final String TEST_NAME = "Test Buddy";
 
    @Before
    public void setUp() {
        testBuddy = new Buddy();
        testBuddy.setId("1");
        testBuddy.setBuddy_id(BUDDY_ID);
        testBuddy.setBuddy_name(TEST_NAME);
    }
 
    @Test
    public void testCreateBuddy() {
        when(buddyRepository.save(any(Buddy.class))).thenReturn(testBuddy);
 
        Buddy createdBuddy = buddyService.createBuddy(testBuddy);
 
        assertEquals(testBuddy, createdBuddy);
        verify(buddyRepository, times(1)).save(any(Buddy.class));
    }
 
    @Test
    public void findBuddyById_Success() {
        // Arrange
        when(buddyRepository.findByBuddy_id(BUDDY_ID)).thenReturn(Optional.of(testBuddy));
 
        // Act
        Buddy foundBuddy = buddyService.findBuddyById(BUDDY_ID);
 
        // Assert
        assertEquals(testBuddy, foundBuddy);
        verify(buddyRepository, times(1)).findByBuddy_id(BUDDY_ID);
    }
 
 
    @Test
    public void testGetAllBuddy() {
        List<Buddy> buddyList = new ArrayList<>();
        buddyList.add(testBuddy);
        when(buddyRepository.findAll()).thenReturn(buddyList);
 
        List<Buddy> retrievedBuddyList = buddyService.getAllBuddy();
 
        assertEquals(buddyList, retrievedBuddyList);
        verify(buddyRepository, times(1)).findAll();
    }
 
    @Test
    public void testUpdateBuddy() {
        when(buddyRepository.save(any(Buddy.class))).thenReturn(testBuddy);
 
        Buddy updatedBuddy = buddyService.updateBuddy(testBuddy);
 
        assertEquals(testBuddy, updatedBuddy);
        verify(buddyRepository, times(1)).save(any(Buddy.class));
    }
 
    @Test
    public void testDeleteBuddyById() {
        when(buddyRepository.findByBuddy_id(BUDDY_ID)).thenReturn(Optional.of(testBuddy));
 
        boolean deleted = buddyService.deleteBuddyById(BUDDY_ID);
 
        assertEquals(true, deleted);
        verify(buddyRepository, times(1)).deleteById(testBuddy.getId());
    }
}

*/





