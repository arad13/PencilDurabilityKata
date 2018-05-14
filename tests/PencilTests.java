import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class PencilTests {
    private Pencil pencil = new Pencil(15, 10, 10);
    private Paper paper = new Paper("This is some initial text.");

    @Test
    public void givenAPaperWithInitialTextWriteAdditionalTextOnThePaper() {
        pencil = new Pencil(200, 20, 10);
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
    public void writeSomeTextAndVerifyThatOnceDurabilityIsZeroNewCharactersAreNotWritten(){
        pencil.Write(paper, "  Here is some followup text");
        assertEquals(paper.getText(), "This is some initial text.  Here is some foll         ");
    }

    @Test
    public void writeSomeTextSharpenThePencilVerifyThatInitialPointDurabilityIsRestored(){
        pencil.Write(paper, "  Here is some followup text");
        pencil.Sharpen();
        assertEquals(15, pencil.getDurability());
    }

    @Test
    public void sharpenPencilAndLowerLengthByOne(){
        pencil.Sharpen();
        assertEquals(9, pencil.getLength());
    }

    @Test
    public void sharpenPencilMultipleTimesWhenLengthIsZeroDurabilityIsNoLongerRestored(){
        for( int i = 0; i < 10; i ++ )
            pencil.Sharpen();

        pencil.Write(paper," Here is some followup text");
        pencil.Sharpen();
        assertEquals( -8, pencil.getDurability());
    }

    @Test
    public void eraseAStringFromThePaper(){
        pencil.Erase(paper,"initial");
        assertEquals("This is some         text.", paper.getText());
    }

    @Test
    public void givenAPaperWithTheSameWordMultipleTimesEraseTheLatestOccurrenceEachTime(){
        pencil = new Pencil( 200, 20, 3000);
        pencil.Write(paper, " And this is some followup this follows this.");
        pencil.Erase(paper, "this");
        assertEquals("This is some initial text. And this is some followup this follows     .", paper.getText());
        pencil.Erase(paper, "this");
        assertEquals("This is some initial text. And this is some followup      follows     .", paper.getText());
    }

    @Test
    public void givenAPencilWithEraserDurabilityOnlyEraseCharactersUpToDurabilityLimit(){
        pencil = new Pencil(200, 20, 3);
        pencil.Write(paper, " And this is some followup this follows this.");
        pencil.Erase(paper, "this");
        assertEquals("This is some initial text. And this is some followup this follows t   .", paper.getText());
    }
}