package tinyru.etapa3;

import java.util.Hashtable;
import tinyru.etapa3.VarInput;


public class StructInput {
    Hashtable<String, VarInput> attributeTable = new Hashtable<>();
    Hashtable<String, ConstInput> constantTable = new Hashtable<>();
    Hashtable<String, MethodInput> methodTable = new Hashtable<>();
}
