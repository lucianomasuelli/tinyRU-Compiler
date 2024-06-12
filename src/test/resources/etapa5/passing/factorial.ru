struct Factorial {
    Int resultado;
}

impl Factorial {
    .(){ }
    fn calcular_factorial(Int n) -> Int {
        resultado = 1;
        while (n > 1) {
            resultado = resultado * n;
            (--n);
        }
        ret resultado;
    }
}

start {
    Factorial fact;
    Int num;
    fact = new Factorial();
    num = 5;
    (IO.out_int(fact.calcular_factorial(num)));
}