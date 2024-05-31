package tinyru.etapa5;
import tinyru.etapa4.AST.AbstractSyntaxTree;
import tinyru.etapa4.AST.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CodeGenerator {
    private StringBuilder dataSection;
    private StringBuilder textSection;
    private Set<String> dataLabels;
    private AbstractSyntaxTree ast;

    public CodeGenerator(AbstractSyntaxTree ast) {
        this.dataSection = new StringBuilder();
        this.textSection = new StringBuilder();
        this.dataLabels = new HashSet<>();
        this.ast = ast;

        // Inicia las secciones
        this.dataSection.append(".data\n");
        this.textSection.append(".text\n.globl main\nmain:\n");
    }


    public void generateCode() {
        for (BloqueNode node : ast.getRoot()) {
            for(SentenciaNode sentencia : node.getSentencias()){
                if(sentencia instanceof SentSimpleNode sentSimple){
                    if(sentSimple.getExpresion() instanceof LlamadaMetodoEstaticoNode llamadaMetodoEstatico){
                        if(llamadaMetodoEstatico.getEncadenado() == null){
                            List<ExpresionNode> args = llamadaMetodoEstatico.getMetodo().getArgActuales();
                            if(args.getFirst() instanceof LiteralNode literalNode) {
                                String data = literalNode.getLiteral();
                                addData("msg", data);
                                generatePrintInstruction("msg");
                            }
                        }
                    }
                }
            }
        }
        generateExitInstruction();
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


}