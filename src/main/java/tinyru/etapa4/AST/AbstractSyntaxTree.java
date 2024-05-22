package tinyru.etapa4.AST;

import tinyru.etapa3.Exceptions.SemanticError;
import tinyru.etapa3.SymbolTable;

import java.util.List;

public class AbstractSyntaxTree{
    private List<BloqueNode> children;


    public AbstractSyntaxTree(List<BloqueNode> children) {
        this.children = children;
    }

    public List<BloqueNode> getRoot() {
        return children;
    }

    public void setRoot(List<BloqueNode> children) {
        this.children = children;
    }

    public void print() {
        if(children != null) {
            for (BloqueNode node : children) {
                node.print();
            }
        }
    }

    public BloqueNode getMethod(String struct, String name) {
        for (BloqueNode node : children) {
            if (node.getMethodName().equals(name) && node.getStructName().equals(struct)){
                return node;
            }
        }
        return null;
    }

    public String toJSON(){
        String json = "";
        return json;
    }

    public void check(SymbolTable st) {
        try {
            if(children != null){
                for (BloqueNode node : children) {
                    node.check(st);
                }
            }
        } catch (SemanticError e) {
            System.out.println(e.getMessage());
        }
    }

}
