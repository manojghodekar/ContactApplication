<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html data-ng-app>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
<script>
var app = angular.module("ContactManagement", []);
app.controller("ContactManagementController", function($scope, $http){
	 $http({
         method : 'GET',
         url : 'http://localhost:8080/contactApplication/contact/contactlist/',
         {params:{"param1":institueName, "param2": country , "param3": state , "param4": status}})
         
     }).then(function successCallback(response) {
         $scope.employees = response.data.employees;
     }, function errorCallback(response) {
         console.log(response.statusText);
     });
}
  
}
/script>
</head>
<body>
	<div data-ng-controller="ContactManagementController">
		<h2>Contact Infromation</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>Email Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>InstituteName</th>
				<th>Country</th>
				<th>State</th>
				<th>status</th>

			</tr>

			<tr data-ng-repeat="Contact in contactlist">

				<td>{{ Contact.firstName}}</td>
				<td>{{ Contact.lastName }}</td>
				<td>{{ Contact.emailId }}</td>
				<td>{{ Contact.instituteName}}</td>
				<td>{{ Contact.country }}</td>
				<td>{{ Contact.state }}</td>
				<td>{{ Contact.status }}</td>
			</tr>

		</table>
	</div>
</body>
</html>