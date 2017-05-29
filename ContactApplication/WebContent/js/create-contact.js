app.controller('CreateContactCtrl', function($scope, $window, $routeParams, Contacts) {
 
  function init() {
    $scope.id = $routeParams.id;
    if ($scope.id) {
      Contacts.getContact($scope.id).then(
        function(contact) {
          $scope.contact = contact;
        }
      );
    }
  }

  $scope.addContact = function() {
    Contacts.addContact($scope.contact).then(
      function(contact) {
        $scope.contact = contact;
        $window.location.href = "#/contactlist";
      },function(error){
        if(error.status== '404'){
          $window.alert("Contact with given Email Id Alreadt  exist");
          $scope.errorMsg = "Sorry ,Contact with given Email Id Alreadt  exist;";
          $window.location.href = "#/AddContact";
        }
      }
   );
 }
  
  $scope.updateContact=function() {
    Contacts.updateContact($scope.contact).then(
      function(contact) {
        $scope.contact = contact;
        $window.location.href = "#/contactlist";
      }
    );
  }
  
  init();
});
