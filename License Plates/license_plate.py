import string,sys

#Define the list of digits and alphabet letters.
digits=[i for i in string.digits]
letters=[i for i in string.uppercase]

def license(n):
	pattern,coverage=calculate_pattern(n)
	#Print the output 
	print 'Population:{0:30d}'.format(n)
	print 'Pattern:',
	length=len(pattern)*8+len(pattern)-1
	if length<31:
		print ' '*(31-length),
	for i in pattern:
		if i[0]==1:print '1 letter',
		elif i[1]==1: print '1 number',
	print
	print 'Total Plates:{0:28d}'.format(coverage)
	print 'Excess Plates:{0:27d}'.format(coverage-n)

def calculate_pattern(n):
	#Recursive function to calculate which pattern to use.
	if n<=10: return [(0,1)],10
	if n<=26: return [(1,0)],26
	digit_choice,digit_coverage=calculate_pattern(n/10)
	letter_choice,letter_coverage=calculate_pattern(n/26)
	if digit_coverage*10<=letter_coverage*26:
		digit_choice.insert(0,(0,1))
		return digit_choice,digit_coverage*10
	else:   
		letter_choice.insert(0,(1,0))
		return letter_choice,letter_coverage*26

if len(sys.argv)>1:
	license(int(sys.argv[1]))
else: license(25)
