package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa5.CodeGenerator;

public class AccVarSimpleNode extends AccesoVarNode {

    public AccVarSimpleNode(Token token, String struct){
        super(token, struct);
    }

    public AccVarSimpleNode(Token token) {
        super(token);
    }

    @Override
    public String jsonify(){
        String json = "{\n";
        json += "\"AccVarSimpleNode\": {\n";
        json += "\"id\": " + getToken().getLexeme() + "\n";
        json += "}\n";
        json += "}\n";
        return json;
    }

    @Override
    public int generateCode(CodeGenerator cg) {
        return cg.allocateStackSpace();
    }
}
