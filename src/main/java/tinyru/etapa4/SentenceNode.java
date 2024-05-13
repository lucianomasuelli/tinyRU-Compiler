package tinyru.etapa4;

public class SentenceNode extends ASTNode{

    private int position;
    private ASTNode child;

    public SentenceNode(int position) {
        super("Sentence");
        this.position = position;
    }

    public void setChild(ASTNode child) {
        this.child = child;
    }

    public ASTNode getChild() {
        return child;
    }

    public void print() {
        System.out.println("SentenceNode");
        this.child.print();
    }

}
