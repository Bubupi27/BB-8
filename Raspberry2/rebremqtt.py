import paho.mqtt.client as mqtt
client=mqtt.Client()
missatge=""
missatge2=""


def on_connect(client, userdata, flags, rc):
	print("Conneted with result code {0}".format(str(rc)))
	client.subscribe("moviment")


def on_message(client, userdata, msg):
	global missatge2
	missatge=str(msg.payload)
	separat=missatge.split("//")
	#print("força1: "+separat[0])
	#print("angle1: "+separat[1])
	#print("força2: "+separat[2])
	#print("angle2: "+separat[3])
	print("message -> "+msg.topic+" "+str(msg.payload))
	missatge2=missatge

client.on_connect=on_connect
client.on_message=on_message
client.connect("192.168.1.58")
def rebredades():
	client.loop_start()
	global missatge2
	return missatge2
client.loop_stop()
