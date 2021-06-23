/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainLobby;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 79302
 */

public class MainForm_v2Test {
    
    public MainForm_v2Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetResultFromDispline() {
    }

    @Test
    public void testGetResultFromLecturer() {
    }

    @Test
    public void testGetResultFromGroups() {
    }

    @Test
    public void testGetResultFromNag() {
    }

    @Test
    public void testGetResultFromKabinet() {
    }

    @Test
    public void testGetResultFromSchedule() {
    }

    @Test
    public void testGetResultFromScheduleChangeTrue() {
    }

    @Test
    public void testCheckString() {
    String fullname="Здравствуйте";
    String shortname="Привет";
    boolean expResult = true;
    boolean result= MainForm_v2.CheckString(fullname, shortname);
        assertEquals(expResult, result);
    }

    @Test
    public void testSubstringCount() {
    }

    @Test
    public void testPracticCheck() {
    }

    @Test
    public void testStartScheduleChange() {
    }

    @Test
    public void testNextScheduleChange() throws Exception {
    }

    @Test
    public void testMain() {
    }
    
}
