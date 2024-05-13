package tinyru.etapa4;

import java.util.LinkedList;

public class AST {
    private LinkedList<ASTNode> root;

    public AST() {
        root = new LinkedList<>();
    }

    public LinkedList<ASTNode> getRoot() {
        return root;
    }
    public void addSentence(ASTNode sentence) {
        root.add(sentence);
    }


}
