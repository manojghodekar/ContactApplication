<!doctype html>
<html ng-app>
	<head>
		<title>Spring MVC + AngularJS Demo</title>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
    	<script>
    	function mainController($scope, $http) {
    	    $http.get('http://localhost:8080/contactApplication/contact/Contact1'+contact.emailId).
    	        success(function(data) {
    	            $scope.contact = data;
    	        });
    	}
    	</script>
	</head>
	<body>

	<div ng-controller="getAllContact">
	<h2>Angularjs Spring MVC sample application!!</h2>
       <button ng-click="getContact(email)">Get User Details</button>
    	<h2>Contact Information</h2>

		<p>First Name		: {{contact.firstName}}</p>

		<p>Last Name	    : {{contact.lastName}}</p>

		<p>Email Id  		: {{contact.emailId}}</p>
		
		<p>InstituteName	: {{contact.instituteName}}</p>
		
		<p>Country       	: {{contact.country}}</p>
		
		<p>State      		: {{contact.state}}</p>
		
		<P>status           : {{contact.status}}></P>
	</div>

</body>

</html>