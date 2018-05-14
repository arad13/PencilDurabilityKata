class Pencil {
    private int durability;
    private final int initialDurability;
    private int length;
    private int eraserDurability;
    private int lastErasedTextIndex;

    Pencil(int newPencilDurability, int newPencilLength, int newPencilEraserDurability){
        initialDurability = durability = newPencilDurability;
        length = newPencilLength;
        eraserDurability = newPencilEraserDurability;
    }

    void Write(Paper paper, String textToWrite){
        StringBuilder currentPaperText = new StringBuilder(paper.getText());

        for( char c : textToWrite.toCharArray()){
            if( durability > 0 )
                currentPaperText.append(c);
            else
                currentPaperText.append(" ");

            if( Character.isLowerCase(c))
                durability --;
            else if( Character.isUpperCase(c))
                durability -= 2;
        }

        paper.setText(currentPaperText.toString());
    }

    int getDurability(){
        return durability;
    }

    int getLength(){
        return length;
    }

    void Sharpen(){
        if( length > 0 ) {
            durability = initialDurability;
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

        lastErasedTextIndex = lastIndex + numberOfCharactersThatCannotBeErased;

        eraserDurability -= lengthOfCharactersToBeErased;

        paper.setText(paperText.toString());
    }

    void Edit(Paper paper, String textToEditIn){
        StringBuilder paperText = new StringBuilder(paper.getText());

        for( int i = 0; i < textToEditIn.length(); i++ ){
            char characterToInsert = textToEditIn.charAt(i);

            if( Character.isLowerCase(characterToInsert) )
                durability --;
            else if( Character.isUpperCase(characterToInsert))
                durability -= 2;

            if( lastErasedTextIndex + i >= paperText.length() )
                paperText.append(characterToInsert);
            else
                paperText.replace(lastErasedTextIndex+i, lastErasedTextIndex+i+1, tryAndReplaceCharacter(paperText.charAt(lastErasedTextIndex+i), characterToInsert));
        }

        paper.setText(paperText.toString());
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
