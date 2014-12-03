import models.Meta;
import org.junit.*;

/**
 * Created by orion on 29/11/14.
 */
public class ClasseMetaTest {
    @Test
    public void testaConstrutor(){
        try {
            Meta meta = new Meta("", 1, 4);
            Assert.fail();
        } catch (Exception e){
        }

        try{
            Meta meta = new Meta("Fazer o lab", 0, 4);
            Assert.fail();
        } catch (Exception e){
        }

        try{
            Meta meta = new Meta("Fazer o lab", 7, 4);
            Assert.fail();
        } catch (Exception e){
        }

        try{
            Meta meta = new Meta("Fazer o lab", 1, -1);
            Assert.fail();
        } catch (Exception e){
        }

        try{
            Meta meta = new Meta("Fazer o lab", 1, 6);
            Assert.fail();
        } catch (Exception e){
        }
    }

    @Test
    public void testaGetsESets(){

        try{
            Meta meta = new Meta("Fazer o lab!", 1, 5);
            Assert.assertEquals(meta.getDescricao(), "Fazer o lab!");
            Assert.assertTrue(meta.getPrioridade() == 5);
            Assert.assertTrue(meta.getSemana() == 1);
            Assert.assertFalse(meta.isAlcancada());

            meta.setAlcancada(true);

            Assert.assertTrue(meta.isAlcancada());
        } catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void testaCompareTo() throws Exception {
        Meta meta1 = new Meta("Fazer o lab!", 1, 5);
        Meta meta2 = new Meta("Aprender o play", 2, 4);
        Meta meta3 = new Meta("Meta3", 1, 4);
        Meta meta4 = new Meta("Meta4", 1, 4);

        Assert.assertTrue(meta1.compareTo(meta2) < 0);
        Assert.assertTrue(meta1.compareTo(meta3) < 0);
        Assert.assertTrue(meta2.compareTo(meta3) > 0);
        Assert.assertTrue(meta3.compareTo(meta4) < 0);
    }
}
