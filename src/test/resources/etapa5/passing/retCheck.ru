/? Chequeo de operación de retorno

struct A {
}

impl A {
    .() {}
    fn met() -> Int {
    	Int a;
    	a = 5;
    	ret a;
    }
}

start {
	A a;
    Int b;
    b = a.met();
    (IO.out_int(b));
}