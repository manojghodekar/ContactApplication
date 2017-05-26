var app=angular.module("contact", ["ngRoute"]);
app.config(function($routeProvider) {
  $routeProvider.when("/contactlist", {
    templateUrl : "html/contact-list.html",
    controller :'ContactsCtrl'
  }).when("/contact-details/:email", {
    templateUrl : "html/contact-details.html",
    controller :'ContactCtrl'
  }).when("/AddContact/:email?", {
    templateUrl : "html/create-contact.html",
    controller :'CreateContactCtrl'
  });
});
