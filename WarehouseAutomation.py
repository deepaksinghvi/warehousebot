import RPi.GPIO as IO
import time
import SimpleMFRC522
import requests
from requests.auth import HTTPDigestAuth
import json

IO.setwarnings(False)
IO.setmode(IO.BCM)
IO.setup(16,IO.IN) #PIN 36 -> Left IR out
IO.setup(20,IO.IN) #PIN 38 -> Right IR out
IO.setup(21,IO.IN) #PIN 40 -> Centre IR out
LIR=16
RIR=20
CIR=21
MRT1=6
MRT2=13
MLT1=19
MLT2=26
IO.setup(MRT1,IO.OUT) #GPIO 6 - 31  -> Motor 1 terminal A
IO.setup(MRT2,IO.OUT) #GPIO 13 - 33 -> Motor 1 terminal B
IO.setup(MLT1,IO.OUT) #GPIO 19 - 35 -> Motor 2 Left terminal A
IO.setup(MLT2,IO.OUT) #GPIO 26 - 37 -> Motor 2 Left terminal B
IO.setup(24,IO.IN,pull_up_down=IO.PUD_UP)
currLoc=""
intermediateLoc = ""
endLoc=""
nextLoc = intermediateLoc
itemPicked=False
locationNumber=""
previousNumber=""
locNumber=""
def manualDelay():
    time.sleep(0.01)
    stayStill()
    time.sleep(0.01)

def moveStraight():
    IO.output(MRT1,True) #1A+ 31
    IO.output(MRT2,False) #1B- 33
    IO.output(MLT1,True) #2A+ 35
    IO.output(MLT2,False) #2B- 37
    manualDelay()

def turnLeft():
    IO.output(MRT1,True) #1A+
    IO.output(MRT2,False) #1B-
    IO.output(MLT1,True) #2A+
    IO.output(MLT2,True) #2B-
    manualDelay()

def turnRight():
    IO.output(MRT1,True) #1A+
    IO.output(MRT2,True) #1B-
    IO.output(MLT1,True) #2A+
    IO.output(MLT2,False) #2B-
    manualDelay()
    
def turnOneEightDegree():
    IO.output(MRT1,False) #1A+
    IO.output(MRT2,True) #1B-
    IO.output(MLT1,True) #2A+
    IO.output(MLT2,False) #2B-
    manualDelay()
    
def stayStill():
    IO.output(MRT1,True) #1A+
    IO.output(MRT2,True) #1B-
    IO.output(MLT1,True) #2A+
    IO.output(MLT2,True) #2B-import RPi.GPIO as IO    

def readBotInstruction():
    global currLoc
    global intermediateLoc
    global endLoc
    global nextLoc
    url = "http://warehousebot.us-east-2.elasticbeanstalk.com/warehousebot/bot/bot1"
    myResponse = requests.get(url)
    if(myResponse.ok):
        jData = json.loads(myResponse.content)
        j = json.loads(myResponse.content)
        currLoc = j['currLoc']
        intermediateLoc = j['intermediateLoc']
        endLoc = j['endLoc']
        print("$$$$$ Start $$$$$$$$$$$$$$ :"+currLoc)
        print("$$$$$ PickUpLocation $$$$$ :"+intermediateLoc)
        print("$$$$$ End $$$$$$$$$$$$$$$$ :"+endLoc)
        nextLoc = str(intermediateLoc)
    else:
        myResponse.raise_for_status()
        
def moveBotBasedOnLocation(location):
    if(location=="L1" or location =="L3" or location =="L5"):
        turnLeft()
    else:
        turnRight()

def isBotInstructionRequired():
    global currLoc
    global intermediateLoc
    global endLoc
    if currLoc and currLoc.strip():
        if intermediateLoc and intermediateLoc.strip():
            if endLoc and endLoc.strip():
                return True
            return False
        return False
    return False
stayStill()
reader = SimpleMFRC522.SimpleMFRC522() 
while 1:
    time.sleep(0.05)
    if(currLoc==""):
        print("READING.... bot instruction")
        readBotInstruction()
        time.sleep(1)
    print("nextLoc " + nextLoc) 
    #print("locNumber " + locNumber)
    #if(locNumber !=""):
    #    locationNumber = locNumber
     #   print("locationNumber " + locationNumber)
    #locNumber = "abc"

    #input_state=IO.input(18)
    #if(input_sate == False):
    #   nextLoc = endLoc
    #rotate 180 degree and start moving
    #   turnOneEightDegree()
      
     # button press event and set nextLoc=endLoc
    if(locationNumber == nextLoc): # if reached to destination stand still
        previousNumber = locationNumber
        print("starting.... 1")
        #stayStill()
        if(nextLoc=="L1" or nextLoc =="L3" or nextLoc =="L5"):
            turnLeft()
        else:
            turnRight()
        nextLoc = endLoc    
        manualDelay()
        
    print("starting.... 2")
    if(IO.input(LIR)==False and IO.input(RIR)==False and IO.input(CIR)==False): #both while move forward
        print("starting.... 3")
        if(previousNumber!=""):
            moveBotBasedOnLocation(previousNumber)
            previousNumber=""
        # move left or right
        if(nextLoc=="End"):
             # move right
            print("turning right.... 4")
            turnRight()           
        if(nextLoc=="Start"):
             # move left
            print("turning left.... 5")
            turnLeft()
    elif(IO.input(LIR)==False and IO.input(CIR)==False and IO.input(RIR)==True): #turn left
        print("turning left.... 6")

        if(locNumber==""):
            stayStill()
            id, locNumber = reader.readOnce()
        if(locNumber==None):
            locNumber=""
        if(locNumber.startswith(nextLoc)):
            nextLoc = str(endLoc)
            print("turning right.... 6.1")
            t = 30
            for i in range(t):
                turnLeft()
                turnLeft()
                turnLeft()
                moveStraight()
                locNumber=""
        else:
            print("location does not match... move straight 6.2")
            moveStraight()
            locNumber=""
    elif(IO.input(LIR)==False and IO.input(CIR)==True and IO.input(RIR)==True): #turn left
        print("turning left.... 7")
        turnLeft()
    elif(IO.input(LIR)==True and IO.input(CIR)==False and IO.input(RIR)==False): #turn right
        print("locNumber 00: " + locNumber)
        moveStraight()
        if(locNumber==""):    
            print("reading....locNumber : " + locNumber + "...") 
            time.sleep(2)
            print("waking up from 2 secs   11.0: ")
            id,locNumber = reader.readOnce()
            if(locNumber==None):
                locNumber=""
        print("locNumb vcer 11: " )
        print(locNumber)
        print("locNumb type")
        print(type(locNumber))

        print("locNumb vcer 11: " + locNumber)
        print("nextLoc   11: " + nextLoc)
        print(locNumber.startswith("L1"))
        print(len(nextLoc))
        testloc = str(locNumber)
        print(len(testloc))
        print(len("L1"))
        if(locNumber.startswith(nextLoc)):
            nextLoc = str(endLoc)
            print("turning right.... 8")
            t = 30
            for i in range(t):
                turnRight()
                turnRight()
                turnRight()
                moveStraight()
                locNumber=""
        else:
            print("location does not match... move straight 8.1")
            moveStraight()
            locNumber=""
    elif(IO.input(LIR)==True and IO.input(CIR)==False and IO.input(RIR)==True): #move straight
        print("moving straight.... 9")
        moveStraight()
    elif(IO.input(LIR)==True and IO.input(CIR)==True and IO.input(RIR)==False): #turn right
        print("turning right.... 10")
        turnRight()
    elif(IO.input(LIR)==True and IO.input(CIR)==True and IO.input(RIR)==True): #turn 180 degree  
        print("turning 180 degree.... 11")
        t = 90
        for i in range(t):
            turnOneEightDegree()
        t = 10
        for i in range(t):    
            stayStill()
            time.sleep(i);
    else:  #stay still
        print("starting.... 12")
        stayStill()

              
