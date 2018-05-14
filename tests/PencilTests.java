import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PencilTests {
    private Pencil standardPencil;
    private Pencil durablePencil;
    private Paper paper;

    @Before
    public void initialize() {
        standardPencil = new Pencil(15, 10, 10);
        durablePencil = new Pencil(2000, 2000, 2000);
        paper = new Paper("This is some initial text.");
    }

    @Test
    public void givenAPaperWithInitialTextWriteAdditionalTextOnThePaper() {
        durablePencil.Write(paper, "  Here is some followup text.");
        assertEquals("This is some initial text.  Here is some followup text.", paper.getText());
    }

    @Test
    public void createAPencilWithADurabilityAndRetrieveThatDurability(){
        assertEquals(15, standardPencil.getPointDurability());
    }

    @Test
    public void writeALowerCaseLetterAndCheckThatDurabilityHasBeenLoweredByOne(){
        standardPencil.Write(paper, "a");
        assertEquals(14, standardPencil.getPointDurability());
    }

    @Test
    public void writeAnUpperCaseLetterAndCheckThatDurabilityHasBeenLoweredByTwo(){
        standardPencil.Write(paper, "A");
        assertEquals(13, standardPencil.getPointDurability());
    }

    @Test
    public void writeANewLineCharacterAndCheckThatDurabilityIsUnchanged(){
        standardPencil.Write(paper, System.getProperty("line.separator"));
        assertEquals(15, standardPencil.getPointDurability());
    }

    @Test
    public void writeSomeTextAndVerifyThatOnceDurabilityIsZeroNewCharactersAreNotWritten(){
        standardPencil.Write(paper, "  Here is some followup text");
        assertEquals(paper.getText(), "This is some initial text.  Here is some foll         ");
    }

    @Test
    public void writeSomeTextSharpenThePencilVerifyThatInitialPointDurabilityIsRestored(){
        standardPencil.Write(paper, "  Here is some followup text");
        standardPencil.Sharpen();
        assertEquals(15, standardPencil.getPointDurability());
    }

    @Test
    public void sharpenPencilAndLowerLengthByOne(){
        standardPencil.Sharpen();
        assertEquals(9, standardPencil.getLength());
    }

    @Test
    public void sharpenPencilMultipleTimesWhenLengthIsZeroDurabilityIsNoLongerRestored(){
        for( int i = 0; i < 10; i ++ )
            standardPencil.Sharpen();

        standardPencil.Write(paper," Here is some followup text");
        standardPencil.Sharpen();
        assertEquals( -8, standardPencil.getPointDurability());
    }

    @Test
    public void eraseAStringFromThePaper(){
        standardPencil.Erase(paper,"initial");
        assertEquals("This is some         text.", paper.getText());
    }

    @Test
    public void givenAPaperWithTheSameWordMultipleTimesEraseTheLatestOccurrenceEachTime(){
        durablePencil.Write(paper, " And this is some followup this follows this.");
        durablePencil.Erase(paper, "this");
        assertEquals("This is some initial text. And this is some followup this follows     .", paper.getText());
        durablePencil.Erase(paper, "this");
        assertEquals("This is some initial text. And this is some followup      follows     .", paper.getText());
    }

    @Test
    public void givenAPencilWithEraserDurabilityOnlyEraseCharactersUpToDurabilityLimit(){
        Pencil lameEraserPencil = new Pencil(200, 20, 3);
        lameEraserPencil.Write(paper, " And this is some followup this follows this.");
        lameEraserPencil.Erase(paper, "this");
        assertEquals("This is some initial text. And this is some followup this follows t   .", paper.getText());
    }

    @Test
    public void givenAPencilWithEraserDurabilityGreaterThanASingleWordCheckThatEraserDurabilityGetsUpdatedForEachCharacterErased(){
        Pencil lameEraserPencil = new Pencil(200, 20, 7);
        lameEraserPencil.Write(paper, " And this is some followup this follows this.");
        lameEraserPencil.Erase(paper, "this");
        lameEraserPencil.Erase(paper, "this");
        assertEquals("This is some initial text. And this is some followup t    follows     .", paper.getText());
    }

    @Test
    public void givenAPaperEraseTextAndAllowAnEditToWriteTextOfSameSizeInTheWhitespace(){
        durablePencil.Write(paper, " And this is some followup      follows     .");
        durablePencil.Edit(paper, "blah", 53);
        assertEquals("This is some initial text. And this is some followup blah follows     .", paper.getText());
    }

    @Test
    public void givenAPaperEraseTextAndAllowAnEditToWriteTextOfGreaterSizeInTheWhitespace(){
        durablePencil.Write(paper, " And this is some followup      follows     .");
        durablePencil.Edit(paper, "incredible", 53);
        assertEquals("This is some initial text. And this is some followup incre@@@l@ws     .", paper.getText());
    }

    @Test
    public void givenAPaperEraseTextAndEditAWordOffTheEndOfTheText(){
        durablePencil.Write(paper, " And this is some followup this follows     .");
        durablePencil.Edit(paper, "incredible", 66);
        assertEquals("This is some initial text. And this is some followup this follows incr@dible", paper.getText());
    }
}