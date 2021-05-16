from motors import *
from rebremqtt import *
import threading


def principal():
    while 1:
        dades = rebredades()
        #  print("dades --> ", dades)
        separat = dades.split("/")
        print("separat --> ", separat)
        try:
            #  print("força1 --> ", separat[1])
            #  print("angle1 --> ", separat[2])
            #  print("força2 --> ", separat[3])
            #  print("angle2 --> ", separat[4])
            separat2 = [int(separat[1].split("g")[1]), int(separat[2]), int(separat[3]), int(separat[4].split("'")[0])]
            print(separat2)
            accionssimple(separat2[0], separat2[1], separat2[2], separat2[3])
        except IndexError:
            pass


def keyboardInput():
    while True:
        action = str(input())
        if action == "endavant" or action == "f":
            print("endavant")
            accionssimple(100, 90, 0, 0)
        elif action == "stop" or action == "s":
            print("stop")
            accionssimple(0, 0, 0, 0)
        elif action == "endarrere" or action == "b":
            print("endarrere")
            accionssimple(100, 280, 0, 0)


if __name__ == "__main__":
    t1 = threading.Thread(target=principal, args=[])
    t2 = threading.Thread(target=keyboardInput, args=[])
    t1.start()
    t2.start()


def abans():
    while 1:
        dades = rebredades()
        print("dades --> ", dades)
        separat = dades.split("/")
        print("separat --> ", separat)
        try:
            print("força1 --> ", separat[1])
            print("angle1 --> ", separat[2])
            print("força2 --> ", separat[3])
            print("angle2 --> ", separat[4])
            sepatat2 = [int(separat)]
            accionssimple(int(separat[1]), int(separat[2]), int(separat[3]), int(separat[4]))
        except IndexError:
            pass

#  accions(int(searat[0]), int(separat[1]), int(separat[2]), int(separat[3]))
#  accionssimple()
