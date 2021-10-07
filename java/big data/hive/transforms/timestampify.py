#!/usr/bin/python

import sys
from datetime import datetime
import re

# Sample input header and row:
# Priority,Call_Type,Jurisdiction,Dispatch_Area,Received_Date,Received_Time,Dispatch_Time,Arrival_Time,Cleared_Time,Disposition
# 3\tSUSPV\tRP\tRS\t 03/21/2013\t173011\t182946\t182946\t183107\tOK
# https://www.programiz.com/python-programming/online-compiler/

try:
	# Iterate through every line passed in to stdin
	for line in sys.stdin.readlines():
		# Split incoming pieces on tab character
		pieces = line.strip().split("\t")

		# If the line has 10 column values, then proceed. Otherwise, skip it.
		if (len(pieces) == 10):
			# Put each piece into its own variable. Make each variable a string, and remove leading and
			# trailing whitespace to deal with bad data.
			priority = str(pieces[0]).strip()
			callType = str(pieces[1]).strip()
			jurisdiction = str(pieces[2]).strip()
			dispatchArea = str(pieces[3]).strip()
			receivedDate = str(pieces[4]).strip()
			receivedTime = str(pieces[5]).strip()
			dispatchTime = str(pieces[6]).strip()
			arrivalTime = str(pieces[7]).strip()
			clearedTime = str(pieces[8]).strip()
			disposition = str(pieces[9]).strip()

			# If the receivedDate has bad data, then skip this line of data.
			try:
				convertedReceivedDate = str(datetime.strptime(receivedDate, '%m/%d/%Y').date())
			except:
				continue

			# If the times are good data, then merge them with the date.
			pattern = '[0-9]{6}'

			if re.match(pattern, receivedTime):
				convertedReceivedTime = datetime.strptime(convertedReceivedDate + ' ' + receivedTime, '%Y-%m-%d %H%M%S')
			else:
				receivedTime = '000000'
				convertedReceivedTime = datetime.strptime(convertedReceivedDate + ' ' + receivedTime, '%Y-%m-%d %H%M%S')

			if re.match(pattern, dispatchTime):
				convertedDispatchTime = datetime.strptime(convertedReceivedDate + ' ' + dispatchTime, '%Y-%m-%d %H%M%S')
			else:
				dispatchTime = '000000'
				convertedDispatchTime = datetime.strptime(convertedReceivedDate + ' ' + dispatchTime, '%Y-%m-%d %H%M%S')

			if re.match(pattern, arrivalTime):
				convertedArrivalTime = datetime.strptime(convertedReceivedDate + ' ' + arrivalTime, '%Y-%m-%d %H%M%S')
			else:
				arrivalTime = '000000'
				convertedArrivalTime = datetime.strptime(convertedReceivedDate + ' ' + arrivalTime, '%Y-%m-%d %H%M%S')

			if re.match(pattern, clearedTime):
				convertedClearedTime = datetime.strptime(convertedReceivedDate + ' ' + clearedTime, '%Y-%m-%d %H%M%S')
			else:
				clearedTime = '000000'
				convertedClearedTime = datetime.strptime(convertedReceivedDate + ' ' + clearedTime, '%Y-%m-%d %H%M%S')

			# Output the entire timestampified row with tab separation
			print(priority + '\t' + callType + '\t' + jurisdiction + '\t' + dispatchArea + '\t' + receivedDate + '\t' + str(convertedReceivedTime) + '\t' + str(convertedDispatchTime) + '\t' + str(convertedArrivalTime) + '\t' + str(convertedClearedTime) + '\t' + disposition)	
		else:
			continue
			
except:
	# In the case of an exception, write the stack trace to stdout so we can see it in Hive, in the results of the UDF call.
	print(sys.exc_info())
