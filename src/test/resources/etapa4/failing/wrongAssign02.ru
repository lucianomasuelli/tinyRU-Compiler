/? chequea que el tipo de una asignación es incorrecto, donde la variable está definida dentro de un método.

struct A{
}

impl A {
    .(){}
    fn met1(Int a) -> Int {
        Str b;
        b = "hola";
        a = b;
    }
}


start {}