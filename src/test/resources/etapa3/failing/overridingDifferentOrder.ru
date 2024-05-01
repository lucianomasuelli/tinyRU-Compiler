struct A {
    Int a;
}

impl A {
    .() {}
    fn method_1 (Int a, Str b) -> void {
    }
}

struct B:A {}

impl B {
    .() {}
    fn method_1 (Str b, Int a) -> void {}

}

start {}