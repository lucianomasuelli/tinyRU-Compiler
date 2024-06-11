package tinyru.etapa5;

import tinyru.etapa3.StructInput;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.AST.AbstractSyntaxTree;
import tinyru.etapa4.AST.BloqueNode;
import tinyru.etapa4.AST.SentenciaNode;

import java.util.HashSet;
import java.util.Set;

public class CodeGenerator {
    private StringBuilder dataSection;
    private StringBuilder textSection;
    private Set<String> dataLabels;
    private Integer stackOffset;
    private SymbolTable st;
    private Integer ifCounter = 0;
    private Integer whileCounter = 0;

    public CodeGenerator(SymbolTable st) {
        this.dataSection = new StringBuilder();
        this.textSection = new StringBuilder();
        this.dataLabels = new HashSet<>();
        this.stackOffset = 0;
        this.st = st;

        // Inicia las secciones
        this.dataSection.append(".data\n");
        addVirtualMethodLabels(st);
        this.textSection.append(".text\n.globl main\n.globl _start\n");
        this.textSection.append("_start:\njal main\n");
    }

    public StringBuilder getTextSection() {
        return textSection;
    }

    public StringBuilder getDataSection() {
        return dataSection;
    }

    public SymbolTable getSt() {
        return st;
    }

    public void increaseIfCounter() {
        ifCounter++;
    }

    public Integer getIfCounter() {
        return ifCounter;
    }

    public void increaseWhileCounter() {
        whileCounter++;
    }

    public Integer getWhileCounter() {
        return whileCounter;
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

    public void divByZero() {
        //genera el label divByZero
        textSection.append("divByZero:\n");
        //Cargo el mensaje de error
        dataSection.append("divError: .asciiz \"Error: Division by zero\\n\"\n");
        //Imprime el mensaje de error
        textSection.append("li $v0, 4\n");  // Código de servicio para imprimir cadena
        textSection.append("la $a0, divError\n");  // Cargar la dirección del mensaje en $a0
        textSection.append("syscall\n");
        //Sale del programa
        textSection.append("li $v0, 10\n"); // Código de servicio para salir del programa
        textSection.append("syscall\n"); // Llamar al sistema para salir
    }

    public String getCode() {
        generateExitInstruction();

        generateOutInt();
        divByZero();
        return dataSection.toString() + textSection.toString() ;
    }

    public void genAsigCode(String varCode, String exprCode){
        textSection.append(varCode);
        textSection.append(exprCode);
    }

//    public int allocateStackSpace() {
//        textSection.append("addi $sp, $sp, -4\n"); // Decrementar el puntero de pila
//        textSection.append("sw $ra, ").append(stackOffset).append("($sp)\n");  // Guardar la dirección de retorno en el offset correcto
//        int currentOffset = stackOffset;
//        stackOffset += 4;
//        return currentOffset;
//    }

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

    public void addVirtualMethodLabels(SymbolTable st) {
        for (String struct : st.getStructTable().keySet()) {
            dataSection.append(struct).append("_vt").append(":\n");
            StructInput s = st.getStructTable().get(struct);
            for (String method : s.getMethodTable().keySet()) {
                dataSection.append(".word ").append(struct).append("_").append(method).append("\n");
                //textSection.append(struct).append("_").append(method).append(":\n");
            }
            //textSection.append(struct).append("_").append("constructor").append(":\n");
        }
        textSection.append("start_start:\n");
    }

    public void generateMethodDef(String struct, String method) {
        textSection.append(struct).append("_").append(method).append(":\n");
        textSection.append("move $fp, $sp\n");
        textSection.append("sw $ra, 0($sp)\n");
        textSection.append("addi $sp, $sp, -4\n");
    }

    public void generateOutInt() {
        textSection.append("IO_out_int:\n");
        textSection.append("# Prologo\n");
        textSection.append("move $fp, $sp\n");  // Guarda el frame pointer
        textSection.append("sw $ra, 0($sp)\n");  // Guarda el return address
        textSection.append("addiu $sp, $sp, -4\n");  // Mueve el stack pointer

        // Genera el código para imprimir el entero que se encuentra en los argumentos (arriba del $fp y de self)
        textSection.append("# Cuerpo del método\n");
        textSection.append("lw $a0, 8($fp)\n");  // Carga el entero a imprimir
        textSection.append("li $v0, 1\n");  // Código de servicio para imprimir entero
        textSection.append("syscall\n");  // Llama al sistema para imprimir

        textSection.append("# Epilogo\n");
        textSection.append("_end_IO_out_int:\n");
        textSection.append("lw $ra, 4($sp)\n");  // Recupera el return address
        textSection.append("addiu $sp, $sp, ").append(4 * (1 + 3)).append("\n");  // Restaura el stack pointer (z = 4*n + 8)
        textSection.append("lw $fp, 0($sp)\n");  // Recupera el frame pointer
        textSection.append("jr $ra\n");  // Salta a la dirección de retorno
    }

}
