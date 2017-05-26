app.factory('Contacts', function($http) {
  var URL = 'http://localhost:8080/contactApplication/contacts';

  function getContacts() {
    return $http.get(URL).then(function(response) {
      return response.data;
    });
  }
  
   function getContactByEmail(email) {
    return $http.get(URL + '/' + email).then(
      function(resp) {
        return resp.data;
      }
    );
  }
  
  
   function addContact(contact) {
    return $http.post(URL,contact).then(
      function(resp) {
        return resp.data;
      }
    );
  }
  
  function deleteContactByEmail(email) {
    return $http.delete(URL + '/' + email).then(
      function(resp) {
        return resp.data;
      }
    );
  }
   function updateContact(contact) {
      return $http.put(URL + '/' + contact.emailId, contact).then(
        function(resp) {
          return resp.data;
      }
    );
   }
   
  return {
    getContacts: getContacts,
    getContactByEmail : getContactByEmail,
    addContact: addContact,
    deleteContactByEmail: deleteContactByEmail,
    updateContact : updateContact
  };
});
