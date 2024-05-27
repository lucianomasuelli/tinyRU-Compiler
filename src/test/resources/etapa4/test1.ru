/? Asignación de tipo incorrecto accediendo a un método de una clase desde un objeto.

struct A{
}
impl A{
    .(){}
    fn metodo1 (Str f) -> Str {
        ret f;
    }

    fn m2 () -> void {
    }
}

struct B:A {
    Int j;
}

impl B{
    .(){}
    fn m1 (Str f, Int j) -> Int { ret 1;}
}

start {}