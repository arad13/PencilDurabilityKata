class Pencil {
    private int durability;

    Pencil(int newPencilDurability){
        durability = newPencilDurability;
    }

    void Write(Paper paper, String textToWrite){
        StringBuilder currentPaperText = new StringBuilder(paper.getText());

        for( char c : textToWrite.toCharArray()){
            if( Character.isLowerCase(c))
                durability --;
            else if( Character.isUpperCase(c))
                durability -= 2;

            currentPaperText.append(c);
        }

        paper.setText(currentPaperText.toString());
    }

    int getDurability(){
        return durability;
    }
}
