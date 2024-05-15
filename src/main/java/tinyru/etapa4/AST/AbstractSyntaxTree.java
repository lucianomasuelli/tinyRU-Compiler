package tinyru.etapa4.AST;

public class AbstractSyntaxTree {
    private ProgramNode root;

    public AbstractSyntaxTree(ProgramNode root) {
        this.root = root;
    }

    public ProgramNode getRoot() {
        return root;
    }

    public void setRoot(ProgramNode root) {
        this.root = root;
    }

}
