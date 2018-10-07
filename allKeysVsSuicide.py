import pandas 
from pytrends.request import TrendReq
pytrend = TrendReq()
timeF = ['all','2007-01-01 2017-10-10','2012-01-01 2017-10-01','2015-10-01 2017-10-01','2016-10-01 2017-10-01','2015-01-01 2015-02-01','2015-02-01 2015-03-01',
	'2015-03-01 2015-04-01','2015-04-01 2015-05-01','2015-05-01 2015-06-01','2015-06-01 2015-07-01','2015-07-01 2015-08-01'#7 to 8 missing
	,'2015-08-01 2015-09-01','2015-09-01 2015-10-01','2015-10-01 2015-11-01'
	,'2015-11-01 2015-12-01','2015-12-01 2016-01-01','2016-01-01 2016-02-01','2016-02-01 2016-03-01',#repetition
	'2016-03-01 2016-04-01','2016-04-01 2016-05-01'
	,'2016-05-01 2016-06-01','2016-06-01 2016-07-01','2016-07-01 2016-08-01','2016-08-01 2016-09-01','2016-09-01 2016-10-01','2016-10-01 2016-11-01','2016-11-01 2016-12-01'
	,'2016-12-01 2017-01-01','2017-01-01 2017-02-01','2017-02-01 2017-03-01','2017-03-01 2017-04-01',#3 to 4 was missing
	'2017-04-01 2017-05-01','2017-05-01 2017-06-01','2017-06-01 2017-07-01','2017-07-01 2017-08-01'
	,'2017-08-01 2017-09-01','2017-09-01 2017-10-01']
# timeF = ['all','2007-01-01 2017-10-10','2012-01-01 2017-10-01','2015-10-01 2017-10-01','2016-10-01 2017-10-01','2015-01-01 2015-02-01','2015-02-01 2015-03-01',
	# '2015-03-01 2015-04-01','2015-04-01 2015-05-01','2015-05-01 2015-06-01','2015-06-01 2015-07-01','2015-08-01 2015-09-01','2015-09-01 2015-10-01','2015-10-01 2015-11-01'
	# ,'2015-11-01 2015-12-01','2015-12-01 2016-01-01','2016-01-01 2016-02-01','2016-02-01 2016-03-01','2016-02-01 2016-03-01','2016-03-01 2016-04-01','2016-04-01 2016-05-01'
	# ,'2016-05-01 2016-06-01','2016-06-01 2016-07-01','2016-07-01 2016-08-01','2016-08-01 2016-09-01','2016-09-01 2016-10-01','2016-10-01 2016-11-01','2016-11-01 2016-12-01'
	# ,'2016-12-01 2017-01-01','2017-01-01 2017-02-01','2017-02-01 2017-03-01','2017-04-01 2017-05-01','2017-05-01 2017-06-01','2017-06-01 2017-07-01','2017-07-01 2017-08-01'
	# ,'2017-08-01 2017-09-01','2017-09-01 2017-10-01']
# myList = [" Self Harm ","loss of a loved one ","mental illness ","grief " #old keywords
			# ,"troubled teens ","substance abuse ","trauma","family conflict"
			# ," rape ","abuse ","guilt ","heartache ","broken heart ","addiction relapse "
			# ,"bipolar disorder ","personality disorder ","answer","Bible ","broken ","confusion "
			# ,"fail ","failing ","failure ","faith","fear ","feeling ","forgive ","freedom"," God"
			# ,"healing ","hope","hunger ","hurt","hurting ","invisible ","incomplete ","Jesus","memory "
			# ,"missing ","mistakes ","pain","runaway ","seeking ","shattered ","dreams","weak","wounded "
			# ,"weary","hopelessness ","lost, forgotten ","ashamed","Abuse","addiction ","adult suicide "
			# ,"alcohol","alcoholic parents ","alcoholism","alimony","anger","anger issues ","anxiety attacks "
			# ,"anxiety disorder ","attempted suicide ","breakup advice","bullying","bullies ","child abuse "
			# ,"child neglect ","committing suicide ","counselor","cutting","community suicide ","dependence"
			# ,"depression","despair","divorce ","divorce advice","domestic abuse","domestic violence "
			# ,"donate","drug abuse ","drug addictions ","drugs","emotional abuse ","emotional imbalance "
			# ,"emotional problems ","emptiness","family problems ","family violence ","gender questions "
			# ,"gender confusion ","help for suicidal thoughts ","help me hopelessness"," hotline","isolation "
			# ,"loneliness ","health issues"," live chat","obsession ","obesity","peer pressure ","physical abuse "
			# ,"pornography addiction  ","intervention","post traumatic stress ","postpartum depression ","prayer team"
			# ,"prevent suicide ","prevention","rage","relationship issues ","relationship problem advice ","sadness","seclusion "
			# ,"self injury ","self mutilation ","self-esteem","separation anxiety ","sexual abuse ","sexual assault ","sibling rivalry "
			# ,"social anxiety disorder ","solitariness","solitude","spiritual help ","spirituality","stop suicide ","suffering suicidal thoughts "
			# ,"suicide, suicide help","suicide hotline","suicide hotlines ","suicide prevention ","suicide support "
			# ,"support groups ","support networks ","support systems, ","teen dating violence ","teenage suicide"
			# ,"teenage suicides ","unhappiness","verbal abuse ","victim assistance ","violence prevention ","youth suicide"] 
myList = ["teenage suicide","adult suicide ","bullies ","relationship issues ","relationship problem advice ","suicide prevention ","suicide support "
			,"violence prevention "," live chat"," rape ","addiction ","addiction relapse ","alcoholic parents ","alcoholism","alimony","anger"
			,"anger issues ","anxiety attacks ","anxiety disorder ","ashamed","attempted suicide ","bipolar disorder "
			,"breakup advice","broken heart ","child abuse ","child neglect ","committing suicide ","community suicide "
			,"counselor","cutting","dependence","depression","despair","divorce ","domestic abuse","domestic violence "
			,"drug abuse ","drug addictions ","emotional abuse ","emotional imbalance ","emotional problems ","emptiness","fail "
			,"family conflict","family problems ","family violence ","fear ","forgive ","grief ","guilt ","healing ","help for suicidal thoughts "
			,"help me hopelessness","hopelessness ","hunger ","hurt","hurting ","intervention","isolation ","loneliness ","loss of a loved one "
			,"lost, forgotten ","mental illness ","missing ","obesity","obsession ","peer pressure ","personality disorder ","physical abuse "
			,"pornography addiction  ","post traumatic stress ","rage","runaway ","sadness","seclusion ","seeking ","Self Harm ","self injury "
			,"self mutilation ","self-esteem","separation anxiety ","sexual abuse ","sexual assault ","shattered ","sibling rivalry "
			,"social anxiety disorder ","solitariness","solitude","spiritual help ","substance abuse ","suffering suicidal thoughts "
			,"teen dating violence ","trauma","troubled teens ","unhappiness","verbal abuse ","victim assistance ","weak","weary","wounded"]
for j in range(99,len(myList)):
	print 'Processing the KeyWord ',j
	pytrend.build_payload(kw_list=[myList[j],'suicide'],timeframe=timeF[0])
	result = pytrend.interest_over_time()
	result = result.assign(timeF=0)
	for i in range(1,len(timeF)):
		print '   Processing timeFrame ',i
		pytrend.build_payload(kw_list=[myList[j],'suicide'],timeframe=timeF[i])
		temp = pytrend.interest_over_time()
		temp = temp.assign(timeF=i)
		result = result.append(temp)
	result.to_excel('suicideVS'+myList[j]+'.xlsx')	