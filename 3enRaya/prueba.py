# coding=utf-8
from random import randrange

import range as range

cont = 1
tablero = [[1, 2, 3], [4, 'X', 6], [7, 8, 9]]
diccionario = {1:tablero[0][0], 2:tablero[0][1], 3:tablero[0][2],
               4:tablero[1][0], 5:tablero[1][1], 6:tablero[1][2],
               7:tablero[2][0], 8:tablero[2][1], 9:tablero[2][2]}

def DisplayBoard(tablero):
    # La función acepta un parámetro el cual contiene el estado actual del tablero
    # y lo muestra en la consola.
    print(
        '+-------+-------+-------+\n'+
        '|       |       |       |\n'+
        '|  ',tablero[0][0],'  |  ',tablero[0][1],'  |  ',tablero[0][2],'  |\n'+
        '|       |       |       |\n'+
        '+-------+-------+-------+\n'+
        '|       |       |       |\n'+
        '|  ',tablero[1][0],'  |  ',tablero[1][1],'  |  ',tablero[1][2],'  |\n'+
        '|       |       |       |\n'+
        '+-------+-------+-------+\n'+
        '|       |       |       |\n'+
        '|  ',tablero[2][0],'  |  ',tablero[2][1],'  |  ',tablero[2][2],'  |\n'+
        '|       |       |       |\n'+
        '+-------+-------+-------+\n')


def EnterMove(tablero):

    # La función acepta el estado actual del tablero y pregunta al usuario acerca de su movimiento,
    # verifica la entrada y actualiza el tablero acorde a la decisión del usuario.
    try:
        posicion = int(input("Ingresa tu movimiento:"))
    except ValueError:
        print('Número no válido.')
    if posicion in diccionario and diccionario[posicion]!='X'or'O':
        diccionario[posicion]='O'

    else:
        print('la posición no es válida')




def MakeListOfFreeFields(tablero):
    # La función examina el tablero y construye una lista de todos los cuadros vacíos.
    # La lista esta compuesta por tuplas, cada tupla es un par de números que indican la fila y columna.
    camposLibres = ((True, True, True), (True, True, True), (True, True, True))

def VictoryFor(tablero, sign):

    print()
    # La función analiza el estatus del tablero para verificar si
    # el jugador que utiliza las 'O's o las 'X's ha ganado el juego.
def DrawMove(tablero):
    # La función dibuja el movimiento de la máquina y actualiza el tablero.
    for i in range(10):
        print(randrange(8))

DisplayBoard(tablero)
EnterMove(tablero)
diccionario[2] = 'O'
print(diccionario[2])
DisplayBoard(tablero)