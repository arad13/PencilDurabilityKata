import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PaperTests {
    private Paper paper = new Paper("This is an initial paper string.");

    @Test
    public void givenAnInitialTextForPaperGetTheTextBackAsOutput() {
        assertEquals("This is an initial paper string.", paper.getText());
    }

    @Test
    public void givenAnInitialTextForPaperSetTheTextToSomethingDifferent(){
        paper.setText("This is a modified paper string.");
        assertEquals("This is a modified paper string.", paper.getText());
    }
}