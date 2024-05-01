struct A {}
impl A {
	fn method_a(Int a, Int b) -> Int {
		ret a;
	}
	.(){}
}

struct B {}

impl B {
    fn method_a(Int pepe, Int pedro) -> Int {
        pepe = pedro + 1;
        ret pepe;
    }
    .(){}
}

start {
}