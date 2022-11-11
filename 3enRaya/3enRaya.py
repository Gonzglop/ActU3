import random

victoria = False
sign = False
contX = 1
contO = 0
tablero = {1: 1, 2: 2, 3: 3,
           4: 4, 5: 'X', 6: 6,
           7: 7, 8: 8, 9: 9}


def DisplayBoard(tablero):
    # La función acepta un parámetro el cual contiene el estado actual del tablero
    # y lo muestra en la consola.
    print(
        '+-------+-------+-------+\n' +
        '|       |       |       |\n' +
        '|  ', tablero[1], '  |  ', tablero[2], '  |  ', tablero[3], '  |\n' +
        '|       |       |       |\n' +
        '+-------+-------+-------+\n' +
        '|       |       |       |\n' +
        '|  ', tablero[4], '  |  ', tablero[5], '  |  ', tablero[6], '  |\n' +
        '|       |       |       |\n' +
        '+-------+-------+-------+\n' +
        '|       |       |       |\n' +
        '|  ', tablero[7], '  |  ', tablero[8], '  |  ', tablero[9], '  |\n' +
        '|       |       |       |\n' +
        '+-------+-------+-------+\n')


def EnterMove(tablero):
    # La función acepta el estado actual del tablero y pregunta al usuario acerca de su movimiento,
    # verifica la entrada y actualiza el tablero acorde a la decisión del usuario.
    bol = False
    global sign
    global contO

    while not bol:
        try:
            posicion = int(input("Ingresa tu movimiento:"))
            if posicion in tablero.keys() and tablero[posicion] != 'X' and tablero[posicion] != 'O':
                tablero[posicion] = 'O'
                DisplayBoard(tablero)

                contO =+ 1
                if contO >= 3:
                    # metodoVictoria
                    sign=VictoryFor(tablero)
                    if contO > 4 and not sign:
                        print("Habéis quedado en tablas.")
                        sign = True

                bol = True
            else:
                print('la posición no es válida')
        except ValueError:
            print('Número no válido.')


def DrawMove(tablero):
    # La función dibuja el movimiento de la máquina y actualiza el tablero.
    global sign
    global contX
    print('Turno de la máquina.')
    numAleatorio = 5
    while tablero[numAleatorio] == 'X' or tablero[numAleatorio] == 'O':
        numAleatorio = random.randint(1, 9)
    tablero[numAleatorio] = 'X'

    DisplayBoard(tablero)

    contX = contX + 1
    if contX >= 3:
        # metodoVictoria
        VictoryFor(tablero)
        if contX > 4 and not sign:
            print("Habéis quedado en tablas.")
            sign = True


def VictoryFor(tablero):
    global sign

    x = tablero[1]
    y = tablero[2]
    z = tablero[3]
    if x == y and y == z:
        print('El ganador es ', x)
        sign = True

    x = tablero[4]
    y = tablero[5]
    z = tablero[6]
    if x == y and y == z:
        print('El ganador es ', x)
        sign = True

    x = tablero[7]
    y = tablero[8]
    z = tablero[9]
    if x == y and y == z:
        print('El ganador es ', x)
        sign = True

    x = tablero[1]
    y = tablero[4]
    z = tablero[7]
    if x == y and y == z:
        print('El ganador es ', x)
        sign = True

    x = tablero[2]
    y = tablero[5]
    z = tablero[8]
    if x == y and y == z:
        print('El ganador es ', x)
        sign = True

    x = tablero[3]
    y = tablero[6]
    z = tablero[9]
    if x == y and y == z:
        print('El ganador es ', x)
        sign = True

    x = tablero[1]
    y = tablero[5]
    z = tablero[9]
    if x == y and y == z:
        print('El ganador es ', x)
        sign = True

    x = tablero[3]
    y = tablero[5]
    z = tablero[7]
    if x == y and y == z:
        print('El ganador es ', x)
        sign = True



# La función analiza el estatus del tablero para verificar si
# el jugador que utiliza las 'O's o las 'X's ha ganado el juego.


print('Empieza la máquina.')

DisplayBoard(tablero)
while not sign:
    EnterMove(tablero)

    DrawMove(tablero)
