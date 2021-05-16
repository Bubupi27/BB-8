import paho.mqtt.client as mqtt

from sensortemperaturaiultrasons import *

client = mqtt.Client()
client.connect("localhost")

temperaturadabans = ""
humitatdabans = ""
distanciadabans = ""


def enviartemperaturaiultrasons():
    global temperaturadabans
    global humitatdabans
    global distanciadabans
    totjunt = ultrasonsitemperatura()
    print(totjunt)
    temperatura = totjunt[0]
    humitat = totjunt[1]
    distancia = totjunt[2]
    if temperatura is None:
        temperatura = temperaturadabans
    else:
        temepraturadabans = temperatura
    if humitat is None:
        humitat = humitatdabans
    else:
        humitatdabans = humitat
    if distancia is None:
        distancia = distanciadabans
    else:
        distanciadabans = distancia

    textjunt = str(temperatura) + "//" + str(humitat) + "//" + str(distancia)
    client.publish("sensors", textjunt)
