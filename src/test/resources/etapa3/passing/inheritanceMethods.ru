struct A:B {

}

impl A {
    .(Int a, Int b) {}
    fn method_2 () -> void {}

}

struct B {
}

impl B {
    .(Int c, Str d) {}
    fn method_1 () -> void {}
}


start {}