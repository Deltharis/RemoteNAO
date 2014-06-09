import urllib2
import json
from naoqi import ALProxy, ALModule, ALBroker
from speechReco import WordlistSpeechReco
from speechReco import MockWordlistReco
import unicodedata

url = 'http://localhost:8080/NaoServer/webapi/get/getList'
actionUrl = 'http://localhost:8080/NaoServer/webapi/get/getAction/'
ip = "127.0.0.1"
port = 9559
tts = ALProxy("ALTextToSpeech", ip, port)
motion = ALProxy("ALMotion", ip, port)
posture = ALProxy("ALRobotPosture", ip, port)

tts.say("Hello, world! Trying to obtain list now")
response = urllib2.urlopen(url).read()
data  = json.loads(response)
list = data['list']
print list

tts.say("List received! It has " + str(len(list)) + " words!")	

def work():
	global list
	global tts
	print list
	tts.say("To list actions say list")
	tts.say("To reload action list say reload")
	tts.say("To do action say choose")
	tts.say("To exit say exit")
	reco.set_word_list(["list","choose", "reload","exit"])
	text = reco.recognize()
	while text == None:
		tts.say("Could you repeat?")
		text = reco.recognize()
	if text == "list":
		tts.say("Listing actions available.")
		words_in_list = ', '.join(list)
		words_in_list = words_in_list.encode('ascii','ignore')
		print words_in_list
		tts.say(words_in_list)
		return True
	elif text == "reload":
		tts.say("Reload in progress")
		response = urllib2.urlopen(url).read()
		data  = json.loads(response)
		list = data['list']
		tts.say("List received! It has " + str(len(list)) + " words!")
		return True
	elif text == "choose":
		doAction()
		return True
	elif text == "exit":
		tts.say("bye")
		return False
	else:
		tts.say("weird stuff")
		return True

def doAction():
	global list
	global tts
	global reco
	reco.set_word_list(list)
	tts.say("Say desired action")
	text = reco.recognize()
	while text == None:
		tts.say("Could you repeat?")
		text = reco.recognize()
	tts.say("You said " + text)
	netParseAction(text)
	tts.say("Action finished")
	
def netParseAction(text):
	print "text"
	print text
	action = urllib2.urlopen(actionUrl + text).read()
	print "action"
	print action
	data = json.loads(action)
	print "data"
	print data
	parseAction(data)

def parseAction(data):
	if data['kod'] == 'complex':
		print "complex debug"
		for l in data['actionChain']:
			print 'Here2 debug!'
			print l
			parseAction(l)
	else:
		print 'proper action debug'
		print "Server partial action: " + data['kod']
		if data['kod'] == 'angle':
			handleAngle(data)
		elif data['kod'] == 'posture':
			handlePosture(data)
		elif data['kod'] == 'say':
			handleSay(data)

			
def handleAngle(data):
	print data
	names  = ["HeadYaw", "HeadPitch"]
	angles = [0., 0.]
	times  = [1.0, 1.0]
	isAbsolute = True
	motion.angleInterpolation(names, angles, times, isAbsolute)
	print data['names']
	names = data['names']
	angles = data['angles']
	times = data['times']
	print names
	names2 = []
	for name in names:
		names2.append(unicodedata.normalize('NFKD', name).encode('ascii','ignore'))
	print names2
	print 'Here!'
	motion.setStiffnesses("Body",1.0)
	motion.angleInterpolation(names2, angles, times, True);
	motion.setStiffnesses("Body",0.0)
	
def handlePosture(data):
	print data
	name = data['name']
	name = unicodedata.normalize('NFKD', name).encode('ascii','ignore')
	speed = data['speed']
	result = posture.goToPosture(name, speed)
	if result:
		print 'yay'
	else:
		tty.say("Sorry, can't do that Dave")
		
def handleSay(data):
	print data
	text = data['text']
	text = unicodedata.normalize('NFKD', text).encode('ascii','ignore')
	tts.say(text)
	

broker = ALBroker("myBroker",
   		"0.0.0.0",   # listen to anyone
   		0,           # find a free port and use it
   		ip,         # parent broker IP
   		port)       # parent broker port


# reco = WordlistSpeechReco(broker)

reco = MockWordlistReco()

while work():
	pass
	
	


#action = urllib2.urlopen(actionUrl + "do").read()
#data = json.loads(action)
#print data
#print data
#if data != "":
	#parseAction(data)


