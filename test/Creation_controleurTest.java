/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controleurs.Creation_controleur;
import javax.swing.JTextField;
import javax.swing.tree.DefaultTreeCellEditor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author clement
 */
public class Creation_controleurTest {
    
    public Creation_controleurTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testVerifierPrix() {
         controleurs.Creation_controleur ctrl=new Creation_controleur();
         JTextField txt=new JTextField("1.3");
         assertTrue(ctrl.verifierPrix(txt));
         txt.setText("-1");
         assertFalse(ctrl.verifierPrix(txt));
     }
}
