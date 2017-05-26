app.controller('CreateContactCtrl', function($scope, $window, $routeParams, Contacts) {
 
  function init() {
    $scope.email = $routeParams.email;
    if ($scope.email) {
      Contacts.getContactByEmail($scope.email).then(
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
