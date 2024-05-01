struct A:B {
    Int a, b;
}

impl A {
    .(Int a, Int b) {}
}

struct B {
    Int c;
    Str d;
}

impl B {
    .(Int c, Str d) {}
}


start {}