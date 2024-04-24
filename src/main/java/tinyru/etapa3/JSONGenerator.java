package tinyru.etapa3;

import tinyru.etapa3.SymbolTable;

public class JSONGenerator {
    private String filename;

    public JSONGenerator(String filename){
        this.filename = filename;
    }

    //Crea el json
    public String jasonify(SymbolTable st){
        String json;
        json = "{\n";
        json += "\"nombre\": \"" + this.filename + "\",\n";
        json += "\"structs\": [\n";

        for (String key : st.getStructTable().keySet()) {
            StructInput struct = st.getStructTable().get(key);
            json += "{\n";
            json += "\"nombre\": \"" + key + "\",\n";
            if (struct.getInheritanceName() != null) {
                json += "\"herencia\": \"" + struct.getInheritanceName() + "\",\n";
            } else {
                json += "\"herencia\": Object,\n";
            }
            json += "\"atributos\": [\n";
            for (String key2 : struct.getAttributeTable().keySet()) {
                VarInput attribute = struct.getAttributeTable().get(key2);
                json += "{\n";
                json += "\"nombre\": \"" + key2 + "\",\n";
                json += "\"tipo\": \"" + attribute.getType() + "\",\n";
                json += "\"visibilidad\": \"" + attribute.getVisibility() + "\"\n";
                json += "\"posicion\": " + attribute.getPosition() + "\n";
                json += "},\n";
            }
            json += "],\n";

            json += "\"metodos\": [\n";
            for (String key2 : struct.getMethodTable().keySet()) {
                MethodInput method = struct.getMethodTable().get(key2);
                json += "{\n";
                json += "\"nombre\": \"" + key2 + "\",\n";
                json += "\"retorno\": \"" + method.getReturnType() + "\",\n";
                json += "\"posicion\": " + method.getPosition() + "\n";
                json += "\"parametros\": [\n";
                for (String key3 : method.getParameterTable().keySet()) {
                    ParamInput parameter = method.getParameterTable().get(key3);
                    json += "{\n";
                    json += "\"nombre\": \"" + key3 + "\",\n";
                    json += "\"tipo\": \"" + parameter.getType() + "\"\n";
                    json += "},\n";
                }
                json += "}\n";
            }
            json += "}\n";
        }
        json += "]\n";
        json += "}\n";
        //añade el start
        return json;
    }

    //añade clases predefinidas
}
