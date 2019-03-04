import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestExplosivesJUnit4 {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e;

    public static void main(String args[]) {
    	String testClass = "TestExplosivesJUnit4";
     	org.junit.runner.JUnitCore.main(testClass);
     }


    private void handleJMLAssertionError(JmlAssertionError e) {
    	if (e.getClass().equals(JmlAssertionError.PreconditionEntry.class)) {
    	    System.out.println("\n INCONCLUSIVE "+(new Exception().getStackTrace()[1].getMethodName())+ "\n\t "+ e.getMessage());
            nb_inconclusive++;}
    else{
	    // test failure	
	    nb_fail++;
	    fail("\n\t" + e.getMessage());	
		}  
    }
	
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nb_inconclusive = 0;
		nb_fail = 0;
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	     System.out.println("\n inconclusive tests: "+nb_inconclusive+" -- failures : "+nb_fail );
	}
	
	/*@Test
	// Test déjà fourni par le sujet
	public void  test_P7() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_Nitro","Prod_Glycerine");
			e.add_incomp("Prod_Dyna","Prod_Mite");
			e.add_assign("Bat_1","Prod_Dyna");
			e.add_assign("Bat_1","Prod_Nitro");
			e.add_assign("Bat_2","Prod_Mite");
			e.add_assign("Bat_1","Prod_Glycerine");
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}*/

	@Test
	public void test_P1() {
		try{
			e=new Explosives();
			int i = 0;
			while (i < 26) { // >25
				e.add_incomp("Prod_"+i,"Prod_100");
				i++;
			}			
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}
	}

	@Test
	public void test_P2() {
		try{
			e=new Explosives();
			int i = 0;
			while (i < 31) { // >30
				e.add_assign("Bat_1","Prod_1");
				i++;
			}			
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}
	}

	@Test 
	public void test_P3() {
		try{
			e=new Explosives();
			//e.add_incomp("SomethingWrong","Prod_X");	
			e.add_incomp("Prod_X","SomethingWrong");
			//e.add_incomp("SomethingWrong","SomethingElse");		
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}
	}

	@Test
	public void test_P4() {
		try{
			e=new Explosives();	
			//e.add_assign("Bat_X","SomethingWrong");
			//e.add_assign("SomethingWrong","Prod_X");
			e.add_assign("Prod_X","Bat_Y");	
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}
	}

	@Test
	public void test_P5() {
		try{
			e=new Explosives();	
			e.add_incomp("Prod_X","Prod_X");	
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}
	}

	/*@Test
	public void test_P7() {
		try{
			e=new Explosives();	
			e.add_incomp("Prod_Dyna","Prod_Mite");
			e.add_assign("Bat_1","Prod_Dyna");
			e.add_assign("Bat_1","Prod_Mite");	
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}
	}*/


}
