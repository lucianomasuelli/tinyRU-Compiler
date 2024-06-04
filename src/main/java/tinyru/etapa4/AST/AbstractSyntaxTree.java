package tinyru.etapa4.AST;

import tinyru.etapa3.Exceptions.SemanticError;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.MissingReturnError;
import tinyru.etapa5.CodeGenerator;

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
                    if(!node.getSentencias().isEmpty()){
                        node.check(st);
                    }
                    else {
                        //si no es el constructor
                        if(!node.getMethodName().equals("constructor")){
                            if(!node.getStructName().equals("start")){
                                if(!(st.getStruct(node.getStructName()).getMethod(node.getMethodName()).getReturnType().equals("void"))){
                                    // Si el metodo no tiene sentencias y no es de tipo void, entonces no tiene return
                                    throw new MissingReturnError(node.getMethodName(), st.getStruct(node.getStructName()).getMethod(node.getMethodName()).getLine(),
                                            st.getStruct(node.getStructName()).getMethod(node.getMethodName()).getColumn());
                                }
                            }
                        }
                    }
                }
            }
        } catch (SemanticError e) {
            System.out.println(e.getMessage());
        }
    }

    public String generateCode(CodeGenerator cg){
        String code = "";
        for (BloqueNode node : children) {
            code = node.generateCode(cg);
        }
        return code;
    }

}
