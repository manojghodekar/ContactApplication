app.controller('ContactCtrl', function($scope, $routeParams, Contacts) {
  function init() {
    
    loadContacts();
  }
  
  function loadContacts() {
   var id=$routeParams.id;
    Contacts.getContact(id).then(
      function(contact) {
        $scope.contact = contact;
      }
    );
  }
  
  init();
});
