/? Creación de un objeto

struct A {
    Int a;
    Bool b;
}

impl A {
    .(Int c, Bool d) {
        a = c;
        b = d;
    }
}

start {
    A a;
    a = new A(1,true);
}