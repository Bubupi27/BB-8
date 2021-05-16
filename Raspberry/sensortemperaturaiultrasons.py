import time

import Adafruit_DHT
import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BCM)
pinecho = 23
pintigger = 24
GPIO.setup(pintigger, GPIO.OUT)
GPIO.setup(pinecho, GPIO.IN)


def ultrasonsitemperatura():
    distancia = round(calculardistancia(), 2)
    humitat, temperatura = Adafruit_DHT.read(11, 3)
    # print("La temperatura és de: ",temperatura,"ºC")
    # print("L'humitat és de: ",humitat,"%")
    # print("La distancia és de: ",distancia, "cm")
    return [temperatura, humitat, distancia]


def calculardistancia():
    GPIO.output(pintigger, True)
    time.sleep(0.00001)
    GPIO.output(pintigger, False)
    tempsinici = time.time()
    tempsfinal = time.time()
    while GPIO.input(pinecho) == 0:
        tempsinici = time.time()
    while GPIO.input(pinecho) == 1:
        tempsfinal = time.time()
    tempsquehatardat = tempsfinal - tempsinici
    distancia = (tempsquehatardat * 34300) / 2
    return distancia


ultrasonsitemperatura()
