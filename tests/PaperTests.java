import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PaperTests {

    @Test
    public void givenAnInitialTextForPaperGetTheTextBackAsOutput() {
        Paper paper = new Paper("This is an initial paper string.");
        assertEquals("This is an initial paper string.", paper.getText());
    }
}