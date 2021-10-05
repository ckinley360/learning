#!/usr/bin/python

import sys
from datetime import datetime
import re

# Sample input header and row:
# Priority,Call_Type,Jurisdiction,Dispatch_Area,Received_Date,Received_Time,Dispatch_Time,Arrival_Time,Cleared_Time,Disposition
# 3,SUSPV,RP,RS, 03/21/2013,173011,182946,182946,183107,OK
# https://www.programiz.com/python-programming/online-compiler/

# Iterate through every line passed in to stdin
for line in sys.stdin.readlines():
	# Split incoming pieces on commas
	pieces = line.strip().split(",")

	# Put each piece into its own variable. Make each variable a string, and remove leading and
	# trailing whitespace to deal with bad data.
	priority = str(pieces[0]).strip(),
	callType = str(pieces[1]).strip(),
	jurisdiction = str(pieces[2]).strip(),
	dispatchArea = str(pieces[3]).strip(),
	receivedDate = str(pieces[4]).strip(),
	receivedTime = str(pieces[5]).strip(),
	dispatchTime = str(pieces[6]).strip(),
	arrivalTime = str(pieces[7]).strip(),
	clearedTime = str(pieces[8]).strip(),
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
	print(priority + '\t' + callType + '\t' + jurisdiction + '\t' + dispatchArea + '\t' + receivedDate + '\t' + convertedReceivedTime + '\t' + convertedDispatchTime + '\t' + convertedArrivalTime + '\t' + convertedClearedTime)
