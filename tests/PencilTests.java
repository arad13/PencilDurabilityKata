import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class PencilTests {
    private Pencil pencil = new Pencil(15);
    private Paper paper = new Paper("This is some initial text.");

    @Test
    public void givenAPaperWithInitialTextWriteAdditionalTextOnThePaper() {
        pencil = new Pencil(200);
        pencil.Write(paper, "  Here is some followup text.");
        assertEquals("This is some initial text.  Here is some followup text.", paper.getText());
    }

    @Test
    public void createAPencilWithADurabilityAndRetrieveThatDurability(){
        assertEquals(15, pencil.getDurability());
    }

    @Test
    public void writeALowerCaseLetterAndCheckThatDurabilityHasBeenLoweredByOne(){
        pencil.Write(paper, "a");
        assertEquals(14, pencil.getDurability());
    }

    @Test
    public void writeAnUpperCaseLetterAndCheckThatDurabilityHasBeenLoweredByTwo(){
        pencil.Write(paper, "A");
        assertEquals(13, pencil.getDurability());
    }

    @Test
    public void writeANewLineCharacterAndCheckThatDurabilityIsUnchanged(){
        pencil.Write(paper, System.getProperty("line.separator"));
        assertEquals(15, pencil.getDurability());
    }

    @Test
    public void writeAWordAndVerifyThatOnceDurabilityIsZeroNewCharactersAreNotWritten(){
        pencil.Write(paper, "  Here is some followup text");
        assertEquals(paper.getText(), "This is some initial text.  Here is some foll         ");
    }
}