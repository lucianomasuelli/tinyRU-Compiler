package tinyru.etapa3;

public class ParamInput {

        private String name;
        private String type;
        private Integer position; //TODO agregar que calcule la posición relativa

        public ParamInput(String name, String type) {
            this.name = name;
            this.type = type;
            this.position = 0;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public Integer getPosition() {
            return position;
        }
}
