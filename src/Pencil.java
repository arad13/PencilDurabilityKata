class Pencil {
    private int durability;

    Pencil(int newPencilDurability){
        durability = newPencilDurability;
    }

    void Write(Paper paper, String textToWrite){
        paper.setText(paper.getText() + textToWrite);
    }

    int getDurability(){
        return durability;
    }
}
