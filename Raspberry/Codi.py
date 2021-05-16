#!/usr/bin/env python
# -*- coding: utf-8 -*-
llista_general = [" ", "!", '"', "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", "<", ">", "_",
                  "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", ">", "?", "@", "A", "B", "C",
                  "D", "E", "F", "G",
                  "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "[",
                  "]", "^", "_", "`", "à", "á", "è", "é", "ì", "í", "ò", "ó", "ù", "ú", "À", "Á", "È", "É", "Ì", "Í",
                  "Ò", "Ó", "Ù", "Ú", "ï", "ë", "ä", "ü", "ö", "Ä", "Ë", "Ï", "Ö", "Ü", "â", "ê", "î", "ô", "û", "Â",
                  "Ê", "Î", "Ô", "Û", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                  "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"]


def invert_dic(dic, llista):
    inv_dic = {}
    i = 0
    while i < len(llista):
        pos1 = dic[llista[i]]
        inv_dic[pos1] = llista[i]
        i = i + 1
    return inv_dic


def generate_dic(groud, llista):
    dic = {}
    l_original = llista
    llista_interna = []

    for i in llista:  # s'ha de fer aixo i no "l = llista" perque sino
        llista_interna.append(i)  # "l" s'assigna en el mateix espai que "llista"
    longitud_inicial_l = len(llista_interna)

    clau = groud

    aleatoritzador = groud

    i = 0

    while i != longitud_inicial_l:
        rand = ((((clau % 1000000000000) + aleatoritzador % 1000000000) + 96739) % len(llista_interna))
        clau = ((clau % 1000000000003) + (aleatoritzador % 1000000000) + 104729)
        dic[l_original[i]] = llista_interna[rand]

        del llista_interna[rand]
        aleatoritzador += 3
        i = i + 1

    return dic


def encoder(dic, text):
    text = str(text)
    xifrat = ""
    for i in text:
        xifrat = xifrat + str(dic[i])

    return xifrat


def text_to_clue(text):
    text = str(text)
    llista_numeros = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
    numbers = ""
    charstonum = ""
    for i in text:
        quant_numbers = 0
        for i1 in llista_numeros:
            if i == i1:
                quant_numbers += 1
                numbers += i
        if quant_numbers == 0:
            charstonum += str(ord(i))

    groud = numbers + charstonum
    final_groud = overflow_case(groud)
    return int(final_groud)


def overflow_case(text):
    groud = text
    reformed_groud = ""
    x1 = 0
    clau = ""

    if len(groud) > 18:
        x2 = 0

        while x1 < 4:
            clau += groud[x1]
            x1 += 1
        clau_original = int(clau)
        clau = int(clau)

        while x2 < 18:
            reformed_groud += groud[((clau + clau_original + x2) % len(groud))]
            clau = (clau + clau_original + x2) % len(groud)
            x2 += 1

    if reformed_groud == "":
        return groud
    else:
        return reformed_groud


def xifrar(text, clau):
    clau = text_to_clue(clau)
    dic = generate_dic(clau, llista_general)
    return encoder(dic, text)


def desxifrar(text, clau):
    clau = text_to_clue(clau)
    dic = generate_dic(clau, llista_general)
    inv_dic = invert_dic(dic, llista_general)
    return encoder(inv_dic, text)


def xifrar_mk2(text, clau):  # encoder modificat
    clau = text_to_clue(clau)
    xifrat = ""
    for i in text:
        dic = generate_dic(clau, llista_general)  # modificacio
        xifrat = xifrat + str(dic[i])
        clau = clau + 1  # modificacio
    return xifrat


def desxifrar_mk2(text, clau):
    clau = text_to_clue(clau)
    xifrat = ""
    for i in text:
        dic = generate_dic(clau, llista_general)
        inv_dic = invert_dic(dic, llista_general)
        xifrat = xifrat + str(inv_dic[i])
        clau = clau + 1
    return xifrat


def xifrat_complex(text, clau_primaria, clau_secundaria, rexifrar):
    clau_primaria = text_to_clue(clau_primaria)
    clau_secundaria = text_to_clue(clau_secundaria)
    aleatoritzador = clau_primaria
    i = 0
    while i < rexifrar:
        dic = generate_dic(clau_primaria, llista_general)
        clau_primaria = encoder(dic, clau_secundaria)
        clau_primaria = text_to_clue(clau_primaria)
        clau_secundaria = clau_primaria + aleatoritzador
        aleatoritzador += 3

        i += 1
    dic = generate_dic(clau_secundaria, llista_general)
    return encoder(dic, text)


def desxifrat_complex(text, clau_primaria, clau_secundaria, rexifrar):
    clau_primaria = text_to_clue(clau_primaria)
    clau_secundaria = text_to_clue(clau_secundaria)
    aleatoritzador = clau_primaria
    i = 0
    while i < rexifrar:
        dic = generate_dic(clau_primaria, llista_general)
        clau_primaria = encoder(dic, clau_secundaria)
        clau_primaria = text_to_clue(clau_primaria)
        clau_secundaria = clau_primaria + aleatoritzador
        aleatoritzador += 3

        i += 1
    dic = generate_dic(clau_secundaria, llista_general)
    inv_dic = invert_dic(dic, llista_general)
    return encoder(inv_dic, text)
