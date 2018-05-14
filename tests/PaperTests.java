import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PaperTests {

    @Test
    public void givenAnInitialTextForPaperGetTheTextBackAsOutput() {
        Paper paper = new Paper("This is an initial paper string.");
        assertEquals("This is an initial paper string.", paper.getText());
    }

    @Test
    public void givenAnInitialTextForPaperSetTheTextToSomethingDifferent(){
        Paper paper = new Paper("This is an initial paper string.");
        paper.setText("This is a modified paper string.");
        assertEquals("This is a modified paper string.", paper.getText());
    }
}