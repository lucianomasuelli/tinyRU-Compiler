/? Acceso a variables encadenadas

struct A {
    Int a;
}

impl A {
    .() { a = 10;}
}

struct B {
    A a;
}

impl B {
    .() { a = new A();}
}

start {
    B b;
    b = new B();
    (IO.out_int(b.a.a));
    b.a.a = 9;
    (IO.out_int(b.a.a));
}