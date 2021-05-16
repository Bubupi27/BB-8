#!/usr/bin/env python
import RPi.GPIO as GPIO

GPIO.setwarnings(False)

motoraf = 2
motorab = 3
motorac = 18

motorbf = 17
motorbb = 4
motorbc = 12

motorcf = 21
motorcb = 20
motorcc = 13

motordf = 16
motordb = 26
motordc = 19


ences = GPIO.LOW
apagat = GPIO.HIGH


GPIO.setmode(GPIO.BCM)
GPIO.setup(motoraf, GPIO.OUT)
GPIO.setup(motorab, GPIO.OUT)
GPIO.setup(motorac, GPIO.OUT)
GPIO.setup(motorbf, GPIO.OUT)
GPIO.setup(motorbb, GPIO.OUT)
GPIO.setup(motorbc, GPIO.OUT)
GPIO.setup(motorcf, GPIO.OUT)
GPIO.setup(motorcb, GPIO.OUT)
GPIO.setup(motorcc, GPIO.OUT)
GPIO.setup(motordf, GPIO.OUT)
GPIO.setup(motordb, GPIO.OUT)
GPIO.setup(motordc, GPIO.OUT)
controla = GPIO.PWM(motorac, 1000)
controlb = GPIO.PWM(motorbc, 1000)
controlc = GPIO.PWM(motorcc, 1000)
controld = GPIO.PWM(motordc, 1000)

GPIO.output(motoraf, apagat)
GPIO.output(motorab, apagat)
GPIO.output(motorbf, apagat)
GPIO.output(motorbb, apagat)
GPIO.output(motorcf, apagat)
GPIO.output(motorcb, apagat)
GPIO.output(motordf, apagat)
GPIO.output(motordb, apagat)
controla.start(0)
controlb.start(0)
controlc.start(0)
controld.start(0)

f1abans = 0
f2abans = 0
a1abans = 0
a2abans = 0


def accions(f1: int, a1: int, f2: int, a2: int) -> None:
	global f1abans, f2abans, a1abans, a2abans
	if f1 != f1abans:
		pass
	if f2 != f2abans:
		pass
	if f1 == 0:
		controla.ChangeDutyCycle(0)
		controlb.ChangeDutyCycle(0)
		controlc.ChangeDutyCycle(0)
		controld.ChangeDutyCycle(0)
		GPIO.output(motoraf, apagat)
		GPIO.output(motorab, apagat)
		GPIO.output(motorbf, apagat)
		GPIO.output(motorbb, apagat)
		GPIO.output(motorcf, apagat)
		GPIO.output(motorcb, apagat)
		GPIO.output(motordf, apagat)
		GPIO.output(motordb, apagat)
	elif a1 != a1abans and f1 > 0:
		if a1 < 90:
			x = (a1 * 100) / 90
			GPIO.output(motordb, apagat)
			GPIO.output(motordf, ences)
			controld.ChangeDutyCycle(100)
			GPIO.output(motorab, apagat)
			GPIO.output(motoraf, ences)
			controla.ChangeDutyCycle(100)

			if x < 0:
				GPIO.output(motorcf, apagat)
				GPIO.output(motorcb, ences)
				controlc.ChangeDutyCycle(100-abs(x))

				GPIO.output(motorbf, apagat)
				GPIO.output(motorbb, ences)
				controlb.ChangeDutyCycle(100-abs(x))
			else:
				GPIO.output(motorcb, apagat)
				GPIO.output(motorcf, ences)
				controlc.ChangeDutyCycle(100 - abs(x))

				GPIO.output(motorbb, apagat)
				GPIO.output(motorbf, ences)
				controlb.ChangeDutyCycle(100 - abs(x))
		elif 90 <= a1 < 180:
			x = ((a1-90) * 100) / -90
			GPIO.output(motordb, apagat)
			GPIO.output(motordf, ences)
			controld.ChangeDutyCycle(100)
			GPIO.output(motorab, apagat)
			GPIO.output(motoraf, ences)
			controla.ChangeDutyCycle(100)
			if x < 0:
				GPIO.output(motorcf, apagat)
				GPIO.output(motorcb, ences)
				controlc.ChangeDutyCycle(abs(x))

				GPIO.output(motorbf, apagat)
				GPIO.output(motorbb, ences)
				controlb.ChangeDutyCycle(abs(x))
			else:
				GPIO.output(motorcb, apagat)
				GPIO.output(motorcf, ences)
				controlc.ChangeDutyCycle(abs(x))

				GPIO.output(motorbb, apagat)
				GPIO.output(motorbf, ences)
				controlb.ChangeDutyCycle(x)
		elif 180 <= a1 < 270:
			x = ((a1-180) * 100) / -90
			GPIO.output(motorbb, apagat)
			GPIO.output(motorbf, ences)
			controlb.ChangeDutyCycle(100)
			GPIO.output(motorcb, apagat)
			GPIO.output(motorcf, ences)
			controlc.ChangeDutyCycle(100)

			if x < 0:
				GPIO.output(motordf, apagat)
				GPIO.output(motordb, ences)
				controld.ChangeDutyCycle(abs(x))

				GPIO.output(motoraf, apagat)
				GPIO.output(motorab, ences)
				controla.ChangeDutyCycle(abs(x))
			else:
				GPIO.output(motordb, apagat)
				GPIO.output(motordf, ences)
				controld.ChangeDutyCycle(abs(x))

				GPIO.output(motorab, apagat)
				GPIO.output(motoraf, ences)
				controla.ChangeDutyCycle(abs(x))

		elif 270 <= a1 < 360:
			x = ((a1-270) * 100) / 90
			GPIO.output(motorbb, apagat)
			GPIO.output(motorbf, ences)
			controlb.ChangeDutyCycle(100)
			GPIO.output(motorcb, apagat)
			GPIO.output(motorcf, ences)
			controlc.ChangeDutyCycle(100)

			if x < 0:
				GPIO.output(motoraf, apagat)
				GPIO.output(motorab, ences)
				controld.ChangeDutyCycle(100-abs(x))

				GPIO.output(motoraf, apagat)
				GPIO.output(motorab, ences)
				controla.ChangeDutyCycle(100-abs(x))
			else:
				GPIO.output(motorab, apagat)
				GPIO.output(motoraf, ences)
				controld.ChangeDutyCycle(100-abs(x))

				GPIO.output(motorab, apagat)
				GPIO.output(motoraf, ences)
				controla.ChangeDutyCycle(100-abs(x))

		else:
			print("Algo has fet malament XD")
	if a2 != a2abans:
		pass


def accionssimple(s1: int, a1: int, s2: int, a2: int) -> None:
	global motoraf, motorab, motorbf, motorbb, motorcf, motorcb, motordf, motordb
	if s1 == 0:
		GPIO.output(motoraf, apagat)
		GPIO.output(motorbf, apagat)
		GPIO.output(motorcf, apagat)
		GPIO.output(motordf, apagat)
		GPIO.output(motorab, apagat)
		GPIO.output(motorbb, apagat)
		GPIO.output(motorcb, apagat)
		GPIO.output(motordb, apagat)
	if a1 == 90 and s1 > 0:
		GPIO.output(motorab, apagat)
		GPIO.output(motorbb, apagat)
		GPIO.output(motorcb, apagat)
		GPIO.output(motordb, apagat)
		GPIO.output(motoraf, ences)
		GPIO.output(motorbf, ences)
		GPIO.output(motorcf, ences)
		GPIO.output(motordf, ences)

	elif a1 == 280 and s1 > 0:
		GPIO.output(motoraf, apagat)
		GPIO.output(motorbf, apagat)
		GPIO.output(motorcf, apagat)
		GPIO.output(motordf, apagat)
		GPIO.output(motorab, ences)
		GPIO.output(motorbb, ences)
		GPIO.output(motorcb, ences)
		GPIO.output(motordb, ences)
