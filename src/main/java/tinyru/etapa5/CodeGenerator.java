package tinyru.etapa5;

import tinyru.etapa3.StructInput;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.AST.AbstractSyntaxTree;
import tinyru.etapa4.AST.BloqueNode;

import java.util.HashSet;
import java.util.Set;

public class CodeGenerator {
    private StringBuilder dataSection;
    private StringBuilder textSection;
    private Set<String> dataLabels;
    private Integer stackOffset;

    public CodeGenerator() {
        this.dataSection = new StringBuilder();
        this.textSection = new StringBuilder();
        this.dataLabels = new HashSet<>();
        this.stackOffset = 0;

        // Inicia las secciones
        this.dataSection.append(".data\n");
        this.textSection.append(".text\n.globl main\nmain:\n");
    }

    public StringBuilder getTextSection() {
        return textSection;
    }


    public void generateCode() {
//        for (BloqueNode node : ast.getRoot()) {
//            for(SentenciaNode sentencia : node.getSentencias()){
//                if(sentencia instanceof SentSimpleNode sentSimple){
//                    if(sentSimple.getExpresion() instanceof LlamadaMetodoEstaticoNode llamadaMetodoEstatico){
//                        if(llamadaMetodoEstatico.getEncadenado() == null){
//                            List<ExpresionNode> args = llamadaMetodoEstatico.getMetodo().getArgActuales();
//                            if(args.getFirst() instanceof LiteralNode literalNode) {
//                                String data = literalNode.getLiteral();
//                                addData("msg", data);
//                                generatePrintInstruction("msg");
//                            }
//                        }
//                    }
//                }
//            }
//        }
        generateExitInstruction();
    }

    public Integer getStackOffset() {
        return stackOffset;
    }


    public void addData(String label, String data) {
        if (!dataLabels.contains(label)) {
            dataSection.append(label).append(": .asciiz \"").append(data).append("\"\n");
            dataLabels.add(label);
        }
    }

    public void addVariable(String label, int initialValue) {
        if (!dataLabels.contains(label)) {
            dataSection.append(label).append(": .word ").append(initialValue).append("\n");
            dataLabels.add(label);
        }
    }

    public void generatePrintInstruction(String label) {
        textSection.append("la $a0, ").append(label).append("\n"); // Cargar la dirección del mensaje en $a0
        textSection.append("li $v0, 4\n"); // Código de servicio para imprimir cadena
        textSection.append("syscall\n"); // Llamar al sistema para imprimir
    }

    public void generateExitInstruction() {
        textSection.append("li $v0, 10\n"); // Código de servicio para salir del programa
        textSection.append("syscall\n"); // Llamar al sistema para salir
    }

    public String getCode() {
        return dataSection.toString() + textSection.toString();
    }

    public void genAsigCode(String varCode, String exprCode){
        textSection.append(varCode);
        textSection.append(exprCode);
    }

    public int allocateStackSpace() {
        textSection.append("addi $sp, $sp, -4\n"); // Decrementar el puntero de pila
        textSection.append("sw $ra, ").append(stackOffset).append("($sp)\n");  // Guardar la dirección de retorno en el offset correcto
        int currentOffset = stackOffset;
        stackOffset += 4;
        return currentOffset;
    }

    public void deallocateStackSpace() {
        stackOffset -= 4;
        textSection.append("lw $ra, ").append(stackOffset).append("($sp)\n"); // Cargar la dirección de retorno desde el offset correcto
        textSection.append("addi $sp, $sp, 4\n"); // Incrementar el puntero de pila
    }

    public void loadImmediateValueToRegister(String register, String label) {
        textSection.append("li ").append(register).append(", ").append(label).append("\n");
    }

    public void storeValueToStack(String register, int offset) {
        textSection.append("sw ").append(register).append(", ").append(offset).append("($sp)\n");
    }

    public void addMethodLabels(SymbolTable st) {
        for (String struct : st.getStructTable().keySet()) {
            dataSection.append(struct).append("_vt").append(":\n");
            StructInput s = st.getStructTable().get(struct);
            for (String method : s.getMethodTable().keySet()) {
                dataSection.append(".word ").append(struct).append("_").append(method).append("\n");
                textSection.append(struct).append("_").append(method).append(":\n");
            }
            textSection.append(struct).append("_").append("constructor").append(":\n");
        }
        textSection.append("start_start:\n");
    }

    public void generateMethodDef(String struct, String method) {
        textSection.append(struct).append("_").append(method).append(":\n");
        textSection.append("move $fp, $sp\n");
        textSection.append("sw $ra, 0($sp)\n");
        textSection.append("addi $sp, $sp, -4\n");
    }

}
