var paresUtilizados = ArrayList<Pair<Int, Int>>() //variable para incluir todos los pares (i, j) que ya están rellenos. Con ella evitamos "rellenar" dos veces la misma posición.

fun printArray(v:Array<Array<Int>>){
    for (i in v.indices) { //Recorremos las filas
        for (k in v[0].indices)  //Recorremos las columnas
            print("${v[i][k]}  ")
        print("\n")
    }
}

fun anyadirparEnArrayList(v: Array<Array<Int>>, par:Pair<Int, Int>){
    paresUtilizados.add(par)
    v[par.first][par.second] = 1
}

fun rellenoAleatorio(v:Array<Array<Int>>, cantidad:Int){
    do{
        val par = Pair((v.indices).random(), (v[0].indices).random())
        //Sólo si el par (i,j) generado no se ha rellenado anteriormente, se rellena con el par anterior
        if (par !in paresUtilizados)
            anyadirparEnArrayList(v, par)
    }while(paresUtilizados.size < cantidad)
}

fun solicitarPosicionPorTeclado(): Pair<Int, Int>{
    print("Introduzca la posición en formato 'i,j':")
    val posiciones = (readLine() ?: "0,0").split(",")  // Leemos por pantalla y dividimos la línea introducida en dos por la coma.
    val posicion1: Int = try {
        posiciones[0].trim().toInt()  //Este será el primer elemento
    } catch (e: Exception) {0}
    val posicion2: Int = try {
        posiciones[1].trim().toInt()  //Este será el segundo elemento
    } catch (e: Exception) {0}

    return Pair(posicion1, posicion2)
}

fun comprobacionPorTeclado(v:Array<Array<Int>>, cantidad:Int){
    var contador = 0
    do{
        print("[${contador+1}]")
        val par = solicitarPosicionPorTeclado()
        if(par.first !in (v.indices) || par.second !in (v[0].indices))
            mostrarParNoIncluido(par)  //Mostramos que esa posición es errónea y no pertenece a la matriz
        else {
            if (par in paresUtilizados)
                mostrarParTieneValorUno(v, par, true)   // Mostramos que (i,j) = 1
            else
                mostrarParTieneValorUno(v, par, false)  // Mostramos que (i,j) != 1
        }
    }while (++contador < cantidad)
}

fun mostrarParNoIncluido(par: Pair<Int, Int>) = print("El par introducido (${par.first}, ${par.second}) no pertenece a la matriz.\n")

fun mostrarParTieneValorUno(v:Array<Array<Int>>, par: Pair<Int, Int>, esUno:Boolean){
    print("El par introducido (${par.first}, ${par.second}) tiene valor ")
    if(esUno) println("1.")
    else println("distinto de 1.")
}


fun main() {
    val v = Array(8) {Array(8){0} }

    rellenoAleatorio(v, 10)
    printArray(v)

    comprobacionPorTeclado(v, 5)
}