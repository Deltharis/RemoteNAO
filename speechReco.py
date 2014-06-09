from naoqi import ALProxy, ALModule
import sys
import time
import threading

class MockWordlistReco(object):
	"""Mock word recognition module reading words from standard input."""
	def __init__(self):
		self.word_list = []

	def set_word_list(self, word_list):
		self.word_list = word_list

	def recognize(self):
		word = sys.stdin.readline().strip()
		# print word
		if word in self.word_list:
			# print word
			return word
		else:
			return None

class WordlistSpeechReco(ALModule):
	"""Word recognition module working with word lists."""
	NAME = "Reco"

	def __init__(self, broker, treshold = 0.3):

		ALModule.__init__(self, WordlistSpeechReco.NAME)

		self.broker = broker
		self.treshold = treshold
		self.word_list = []
		self.asr = ALProxy("ALSpeechRecognition")
		self.asr.setLanguage("English")
		self.recognized_word = None
		self.event = threading.Event()
		self.event.clear()

		global memory
		if self.broker != None:
			memory = ALProxy("ALMemory")

	def set_word_list(self, word_list):
		"""Set word list used for recognition."""
		self.word_list = word_list

	def recognize(self):
		"""Return the recognized word."""
		if self.word_list == []:
			self.recognized_word = None
			return
		else:
			self.asr.setWordListAsVocabulary(self.word_list)
			print "subscribing", WordlistSpeechReco.NAME, self.name
			memory.subscribeToEvent("WordRecognized",
            	WordlistSpeechReco.NAME, 
            	"onWordRecognized")
			print "waiting"
			self.event.wait()
			self.event.clear()
			return self.recognized_word

	def onWordRecognized(self, event, value, identifier):
		"""Callback invoked when word is recognized by robots speech recognition.
			Sets the recognized word and the flag in order to resume the execution thread."""
		memory.unsubscribeToEvent("WordRecognized", 
			WordlistSpeechReco.NAME)
		print value
		self.recognized_word = self.__chooseRecognizedWord(value)
		print self.recognized_word
		self.event.set()


	def __chooseRecognizedWord(self, value):
		"""Choose the recognized word based on the value returned from speech recognition.
			Value is a list, where items 2 * i are words and 2 * i + 1 probabilities.
			Value is sorEnted in a descending order by probability. """
		
		# Uncomment this for treshold testing
		# if float(value[1]) < self.tresh:
		# 	return None
		return value[0]