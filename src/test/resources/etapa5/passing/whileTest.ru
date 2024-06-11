struct Fibonacci {
    Int suma;
    Int i,j;
}
impl Fibonacci {
    fn sucesion_fib(Int n)-> Int{
        i=0; j=0; suma=0;
        while (i<= n){
            i = i + 1;
        }
        ret i;
    }
    .(){
        i=0; /? inicializo i
        j=0; /? inicializo j
        suma=0; /? inicializo suma
    }
}

start{
    Fibonacci fib;
    Int n;
    fib = new Fibonacci();
    n = fib.sucesion_fib(5);
    /? (IO.out_int(fib.sucesion_fib(n)));
}