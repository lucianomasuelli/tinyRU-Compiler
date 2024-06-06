package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;
import tinyru.etapa5.CodeGenerator;

import java.util.List;

public abstract class BloqueNode{
    protected String structName;
    protected String methodName;
    protected List<SentenciaNode> sentencias;

    public BloqueNode(List<SentenciaNode> sentencias, String structName,String methodName) {
        this.sentencias = sentencias;
        this.structName = structName;
        this.methodName = methodName;
    }

    public void print() {
        for (SentenciaNode sentencia : sentencias) {
            sentencia.print();
        }
    }

    public List<SentenciaNode> getSentencias() {
        return sentencias;
    }

    public String getMethodName() {
        return methodName;
    }
    public String getStructName(){
        return structName;
    }

    public abstract String jsonify();

    public abstract void check(SymbolTable st);

    public void generateCode(CodeGenerator cg) {
        if(structName.equals("start")) {
            cg.getTextSection().append("main:\n");
            cg.getTextSection().append("move $fp, $sp\n");  // Guarda el frame pointer
            cg.getTextSection().append("sw $ra, 0($sp)\n");  // Guarda el return address
            cg.getTextSection().append("addiu $sp, $sp, -4\n");  // Mueve el stack pointer
            // Genera el código de las sentencias
            for (SentenciaNode sentencia : sentencias) {
                sentencia.generateCode(cg);
            }
        }
        else{ // Genera el código del bloque de un método
            cg.getTextSection().append(structName).append("_").append(methodName).append(":\n");
            cg.getTextSection().append("# Prologo\n");
            cg.getTextSection().append("move $fp, $sp\n");  // Guarda el frame pointer
            cg.getTextSection().append("sw $ra, 0($sp)\n");  // Guarda el return address
            cg.getTextSection().append("addiu $sp, $sp, -4\n");  // Mueve el stack pointer

            // Genera el código de las sentencias
            cg.getTextSection().append("# Cuerpo del método\n");
            for (SentenciaNode sentencia : sentencias) {
                sentencia.generateCode(cg);
            }

            int numArgs = cg.getSt().getStructTable().get(structName).getMethod(methodName).getParameterTable().size();
            cg.getTextSection().append("# Epilogo\n");
            cg.getTextSection().append("_end_").append(structName).append("_").append(methodName).append(":\n");
            cg.getTextSection().append("lw $ra, 4($sp)\n");  // Recupera el return address
            cg.getTextSection().append("addiu $sp, $sp, ").append(4 * (numArgs + 1)).append("\n");  // Restaura el stack pointer
            cg.getTextSection().append("lw $fp, 0($sp)\n");  // Recupera el frame pointer
            cg.getTextSection().append("jr $ra\n");  // Salta a la dirección de retorno
        }
    }
}
