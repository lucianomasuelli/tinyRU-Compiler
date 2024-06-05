package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.TypeAssignError;
import tinyru.etapa4.Exceptions.TypesMismatchError;
import tinyru.etapa5.CodeGenerator;

import java.util.Objects;

public class AsigNode extends SentenciaNode {
    AccesoVarNode variable;
    ExpresionNode expr;

    public AsigNode(AccesoVarNode variable, ExpresionNode expr){
        this.variable = variable;
        this.expr = expr;
    }

    public void print() {
        System.out.print("Asignacion: ");
        variable.print();
        System.out.print(" = ");
        expr.print();
        System.out.println(";");
    }

    public String check(SymbolTable st) {
        String varType = variable.check(null, st);
        String expType = expr.check(null, st);
        //Chequea si varType es un tipo primitivo
        if(varType.equals("Int") || varType.equals("Char") || varType.equals("Bool") || varType.equals("Str")){
            if(expType.equals("Nil")){
                throw new TypesMismatchError(varType, expType, variable.getToken().getLine(), variable.getToken().getColumn());
            }
        }
        if(!varType.equals(expType)){
            //Chequea si expType hereda de varType
            if(!st.isSubType(expType, varType)){// Si no hereda
                if(!expType.equals("Nil") && !expType.equals("void")) {
                    throw new TypeAssignError(varType, expType, variable.getToken().getLine(), variable.getToken().getColumn());
                }
            }
        }
        return "Asignación";
    }

    @Override
    public String jsonify(){
        String json = "{\n";
        json += "\"AsigNode\": {\n";
        json += "\"variable\": " + variable.jsonify() + ",\n";
        json += "\"expr\": " + expr.jsonify() + "\n";
        json += "}\n";
        json += "}\n";
        return json;
    }

    @Override
    public void generateCode(CodeGenerator cg) {
        //TODO
//        int offset = variable.generateCode(cg);
//        expr.generateCode(cg);
//        cg.storeValueToStack("t0", offset);
//        cg.deallocateStackSpace();
    }
}
