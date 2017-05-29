var app=angular.module("contact", ["ngRoute"]);
app.config(function($routeProvider) {
  $routeProvider.when("/contactlist", {
    templateUrl : "html/contact-list.html",
    controller :'ContactsCtrl'
  }).when("/contact-details/:id", {
    templateUrl : "html/contact-details.html",
    controller :'ContactCtrl'
  }).when("/AddContact/:id?", {
    templateUrl : "html/create-contact.html",
    controller :'CreateContactCtrl'
  }).when("/404",{
    templateUrl : "html/error.html",
    controller :''
   })
});
