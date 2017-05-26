app.controller('ContactsCtrl', function($scope, $window,Contacts) {
  function init() {
    
    loadContacts();
  }
  
  function loadContacts() {
    Contacts.getContacts().then(
      function(contacts) {
        $scope.contacts = contacts;
      }
    );
  }
  
  $scope.remove=function (email, index) {
    Contacts.deleteContactByEmail(email).then(
      function(contact) {
        $scope.contact = contact;
        $scope.contacts.splice(index, 1);
      }
    );
  }
 
  $scope.Edit=function(email){
    $window.location.href = "#/AddContact/" + email;
 };
  
  init();
});

