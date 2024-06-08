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
        // Genera el código para la expresión del lado derecho de la asignación
        cg.getTextSection().append("# Asignación\n");
        cg.getTextSection().append("# Expresión derecha\n");
        expr.generateCode(cg);
        cg.getTextSection().append("sw $a0, 0($sp)\n");  // Almacena el valor de la expresión en el tope del stack
        cg.getTextSection().append("addiu $sp, $sp, -4\n");  // Mueve el stack pointer

        // Genera el código para almacenar el valor del registro en la ubicación de memoria de la variable
        cg.getTextSection().append("# Acceso variable\n");
        variable.generateCode(cg);
        cg.getTextSection().append("# Asigna valor a variable\n");
        cg.getTextSection().append("lw $t0, 4($sp)\n");  // Carga el valor de la expresión en $t0
        cg.getTextSection().append("sw $t0, 0($a0)\n");  // Almacena el valor de la expresión en la ubicación de memoria de la variable
        cg.getTextSection().append("addiu $sp, $sp, 4\n");  // Mueve el stack pointer
    }
}
