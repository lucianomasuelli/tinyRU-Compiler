struct A {
    Int a, b;
}

impl A {
    .(Int a, Int b) {}
    fn method_1 () -> void {}
    st fn method_2(Int wala, Str jeje) -> Int {
        ret 1;
    }
}

struct B:A {
    Int c,d;
}

struct C:B {
    Str e;
}

start {}