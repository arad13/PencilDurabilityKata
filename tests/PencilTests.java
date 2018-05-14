import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class PencilTests {
    private Pencil pencil = new Pencil(15);
    private Paper paper = new Paper("This is some initial text.");

    @Test
    public void givenAPaperWithInitialTextWriteAdditionalTextOnThePaper() {
        pencil.Write(paper, "  Here is some followup text.");
        assertEquals("This is some initial text.  Here is some followup text.", paper.getText());
    }

    @Test
    public void createAPencilWithADurabilityAndRetrieveThatDurability(){
        assertEquals(15, pencil.getDurability());
    }
}