/? Acceso a variables encadenadas

struct B {
    Int b;

}

impl B {
    .() {
    	b = 10;
    }
}

start {
    B b;
    b = new B();
    (IO.out_int(b.b));

}