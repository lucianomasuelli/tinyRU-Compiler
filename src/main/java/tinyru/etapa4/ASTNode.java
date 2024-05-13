package tinyru.etapa4;

public class ASTNode {
    private String type;

    public ASTNode(String type) {
        this.type = type;
    }

    public void print() {
        System.out.println(type);
    }
}
