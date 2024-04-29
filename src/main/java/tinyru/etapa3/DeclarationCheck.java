package tinyru.etapa3;

import tinyru.etapa3.Exceptions.*;

public class DeclarationCheck {
    private SymbolTable symbolTable;

    public DeclarationCheck(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void declarationCheck() {
        for (String key : symbolTable.getStructTable().keySet()) {
            StructInput struct = symbolTable.getStruct(key);
            inheritanceCheck(struct);
            circularInheritanceCheck(struct);
        }
    }

    // Check if the inheritance is valid
    public void inheritanceCheck(StructInput struct) {
        if (struct.getInheritanceName() != null) {
            if (!symbolTable.fetchStruct(struct.getInheritanceName())) {
                throw new InheritanceError(struct.getName(), struct.getInheritanceName(),  struct.getLine(), struct.getColumn());
            }
        }
    }


    // Check if the struct is already declared
    public void structCheck(StructInput struct) {
        if (symbolTable.fetchStruct(struct.getName())) {
            throw new StructAlreadyDeclaredError(struct.getName(), struct.getLine(), struct.getColumn());
        }
    }

    // Check circular inheritance
    public void circularInheritanceCheck(StructInput struct) {
        StructInput actualStruct = struct;
        while (actualStruct.getInheritanceName() != null) {
            if (actualStruct.getInheritanceName().equals(struct.getName())) {
                throw new CircularInheritanceError(struct.getName(), struct.getInheritanceName(), struct.getLine(), struct.getColumn());
            }
            actualStruct = symbolTable.getStruct(actualStruct.getInheritanceName());
        }
    }

    // Check if the method is already declared
    public void methodCheck(MethodInput method) {
        if (symbolTable.actualStruct.fetchMethod(method.getName())) {
            throw new MethodAlreadyDeclaredError(method.getName(), method.getLine(), method.getColumn());
        }
    }

    //Check if the variable is already declared
    public void varCheck(VarInput var) {
        if (symbolTable.actualStruct.fetchAttribute(var.getName())) {
            throw new VarAlreadyDeclaredError(var.getName(), var.getLine(), var.getColumn());
        }
    }
}
