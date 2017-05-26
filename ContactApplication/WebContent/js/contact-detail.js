app.controller('ContactCtrl', function($scope, $routeParams, Contacts) {
  function init() {
    
    loadContacts();
  }
  
  function loadContacts() {
   var email=$routeParams.email;
    Contacts.getContactByEmail(email).then(
      function(contact) {
        $scope.contact = contact;
      }
    );
  }
  
  init();
});
