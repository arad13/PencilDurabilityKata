class Pencil {
    private int durability;
    private final int initialDurability;
    private int length;

    Pencil(int newPencilDurability, int newPencilLength){
        initialDurability = durability = newPencilDurability;
        length = newPencilLength;
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
        String paperText = paper.getText();
        paperText = paperText.replace(textToErase, createSpaceString(textToErase.length()));
        paper.setText(paperText);
    }

    private String createSpaceString(int length){
        StringBuilder strReturn = new StringBuilder();
        for(int i = 0; i < length; i++ ){
            strReturn.append(" ");
        }
        return strReturn.toString();
    }
}
