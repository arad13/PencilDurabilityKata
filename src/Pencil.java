class Pencil {
    private int pointDurability;
    private final int initialPointDurability;
    private int length;
    private int eraserDurability;

    Pencil(int newPencilPointDurability, int newPencilLength, int newPencilEraserDurability){
        initialPointDurability = pointDurability = newPencilPointDurability;
        length = newPencilLength;
        eraserDurability = newPencilEraserDurability;
    }

    void Write(Paper paper, String textToWrite){
        StringBuilder currentPaperText = new StringBuilder(paper.getText());

        for( char c : textToWrite.toCharArray()){
            if( pointDurability > 0 )
                currentPaperText.append(c);
            else
                currentPaperText.append(" ");

            updatePointDurability(c);
        }

        paper.setText(currentPaperText.toString());
    }

    int getPointDurability(){
        return pointDurability;
    }

    int getLength(){
        return length;
    }

    void Sharpen(){
        if( length > 0 ) {
            pointDurability = initialPointDurability;
            length--;
        }
    }

    void Erase(Paper paper, String textToErase) {
        StringBuilder paperText = new StringBuilder(paper.getText());

        int lastIndex = paperText.lastIndexOf(textToErase);
        int numberOfCharactersThatCannotBeErased = 0;
        int lengthOfCharactersToBeErased = textToErase.length();

        if( lastIndex < 0 )
            return;

        if( eraserDurability < textToErase.length()){
            numberOfCharactersThatCannotBeErased = textToErase.length() - eraserDurability;
            lengthOfCharactersToBeErased = eraserDurability;
        }
        paperText.replace(lastIndex + numberOfCharactersThatCannotBeErased, lastIndex + numberOfCharactersThatCannotBeErased + lengthOfCharactersToBeErased, createSpaceString(lengthOfCharactersToBeErased));

        eraserDurability -= lengthOfCharactersToBeErased;

        paper.setText(paperText.toString());
    }

    void Edit(Paper paper, String textToEditIn, int indexToBeginEditingInText){
        StringBuilder paperText = new StringBuilder(paper.getText());

        for( int i = 0; i < textToEditIn.length(); i++ ){
            char characterToInsert = textToEditIn.charAt(i);

            updatePointDurability(characterToInsert);

            if( indexToBeginEditingInText + i >= paperText.length() )
                paperText.append(characterToInsert);
            else
                paperText.replace(indexToBeginEditingInText+i, indexToBeginEditingInText+i+1, tryAndReplaceCharacter(paperText.charAt(indexToBeginEditingInText+i), characterToInsert));
        }

        paper.setText(paperText.toString());
    }

    private void updatePointDurability(char characterToWrite) {
        if( Character.isLowerCase(characterToWrite))
            pointDurability --;
        else if( Character.isUpperCase(characterToWrite))
            pointDurability -= 2;
    }

    private String createSpaceString(int length){
        StringBuilder strReturn = new StringBuilder();
        for(int i = 0; i < length; i++ ){
            strReturn.append(" ");
        }
        return strReturn.toString();
    }

    private String tryAndReplaceCharacter(char existingCharToReplace, char newCharBeingInserted){
        if( existingCharToReplace != ' ' && newCharBeingInserted != ' ' && newCharBeingInserted != existingCharToReplace )
            return "@";
        else if( existingCharToReplace == ' ' )
            return Character.toString(newCharBeingInserted);
        else
            return Character.toString(existingCharToReplace);
    }
}
