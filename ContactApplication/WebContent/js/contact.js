app.factory('Contacts', function($http) {
  var URL = 'http://localhost:8080/contactApplication/contacts';

  function getContacts() {
    return $http.get(URL).then(function(response) {
      return response.data;
    });
  }
  
   function getContact(id) {
    return $http.get(URL + '/' + id).then(
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
  
  function deleteContact(id) {
    return $http.delete(URL + '/' + id).then(
      function(resp) {
        return resp.data;
      }
    );
  }
  
  function updateContact(contact) {
      return $http.put(URL + '/' + contact.id, contact).then(
        function(resp) {
          return resp.data;
      }
    );
   }
   
  return {
    getContacts: getContacts,
    getContact : getContact,
    addContact: addContact,
    deleteContact: deleteContact,
    updateContact : updateContact
  };
});
