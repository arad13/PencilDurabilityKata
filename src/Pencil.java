class Pencil {
    private int durability;
    private final int initialDurability;

    Pencil(int newPencilDurability){
        initialDurability = durability = newPencilDurability;
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

    void Sharpen(){
        durability = initialDurability;
    }
}
