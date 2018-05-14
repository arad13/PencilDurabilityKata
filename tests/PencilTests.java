import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class PencilTests {

    @Test
    public void givenAPaperWithInitialTextWriteAdditionalTextOnThePaper() {
        Paper paper = new Paper("This is some initial text.");
        Pencil pencil = new Pencil();
        pencil.Write(paper, "  Here is some followup text.");
        assertEquals("This is some initial text.  Here is some followup text.", paper.getText());
    }
}