package contracts;

org.springframework.cloud.contract.spec.Contract.make {
  request {
    method ('GET')
		url $(consumer(~/\/visa\/name\?firstname=[a-zA-Z]+&lastname=[a-zA-Z]+&birthdate=(19|20)\d\d-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])(.*)/),
		producer('/visa/name?firstname=Tim&lastname=Tracer&birthdate=1985-01-01'))
  }
	
response {
  status 200
	body(
		content: [
			"id":$(regex('[0-9]+')),
			// created date will need to be modified once the model is defined and added to this project as it refers to the 
			// created Date of the record. Not the "instant" the record was created in the database. This change will
			// be made when the model is updated.
			//"createdDate": $(regex('\d{4}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[1-2]\d|3[0-1])T(?:[0-1]\d|2[0-3]):.*:.*Z')),
			// See comment about created date.
			//"modifiedDate": $(regex('\d{4}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[1-2]\d|3[0-1])T(?:[0-1]\d|2[0-3]):.*:.*Z')),
			"firstname": "Tim",
			"middlename": "T",
			"lastname": "Tracer",
			// while this is not the correct format, we are using this atm until the model is defined and added to this project
			// "birthdate": [1985,01,01],
			"nationality": "ANDO",
			"birthplace": "ANDO",
			"passportNumber": "passport2",
			"visaControlNumber": "FRN Number",
			"alienNumber": "A123456789",
			"fingerprintId": "finger2",
			"siteCode": "FRN",
			"caseId": "case2",
			"applicantId": "applicant2",
			"crossReferenceId": "crossreferenceid1",
			"followToJoin": true,
			"totalApplicants": 0,
			"issued": 0,
			"refused": 0,
			"traveling": 0,
			"willJoinApplicants": 0 ],
		"pageable" : "INSTANCE",
		"totalPages" : 1,
		"last" : true,
		"totalElements" : 1,
		"size" : 0,
		"number" : 0,
		//"sort" : "unsorted":true,"sorted":false,"empty":true, 
		"numberOfElements":1,
		"first":true,
		"empty":false
  )
	headers {
    header('Content-Type': 'application/json;charset=UTF-8')
  }
 }
}