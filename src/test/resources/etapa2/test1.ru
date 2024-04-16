struct Fibonacci {
    Int suma;
    Int i,j;
}

start{
    Fibonacci fib;
    Int n;
    fib = new Fibonacci();
    n = IO.in_int();
    (IO.out_int(fib.sucesion_fib(n)));
}