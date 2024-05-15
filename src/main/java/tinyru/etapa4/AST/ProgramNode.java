package tinyru.etapa4.AST;

import java.util.List;

public class ProgramNode {
    private List<SentenciaNode> children;

    public ProgramNode(List<SentenciaNode> children) {
        this.children = children;
    }

    public List<SentenciaNode> getChildren() {
        return children;
    }

    public void setChildren(List<SentenciaNode> children) {
        this.children = children;
    }
}
