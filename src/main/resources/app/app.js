/**
 * Created by Owner on 18.11.2015.
 */

'use strict';

var DMApp = angular.module('DMApp', [
    'ngRoute'
]);

//DMApp.config(['$routeProvider', function($routeProvider) {
//    $routeProvider
//        //.when("/login", {
//        //    controller: "loginController",
//        //    templateUrl: "View/login/login.html"
//        //})
//        .when("/home",{
//            controller: "homeController",
//            templateUrl:"../templates/home.html"
//        })
//        .when("/welcome", {
//            controller: "welcomeController",
//            templateUrl: "View/welcome/welcome.html"
//        })
//        .when("/changePassword", {
//            controller: "changePasswordController",
//            templateUrl: "View/changePassword/changePassword.html"
//        })
//        .when("/administracija/akcije", {
//            controller: "akcijaController",
//            templateUrl: "View/administracija/akcija/akcija.html"
//        })
//        .when("/administracija/dogadjaji", {
//            controller: "dogadjajController",
//            templateUrl: "View/administracija/akcija/dogadjaj.html"
//        })
//        .when("/administracija/notifikacije", {
//            controller: "notifikacijaController",
//            templateUrl: "View/administracija/akcija/notifikacija.html"
//        })
//        .when("/administracija/uloge", {
//            controller: "ulogaController",
//            templateUrl: "View/administracija/akcija/uloga.html"
//        })
//        .when("/administracija/slike", {
//            controller: "slikaController",
//            templateUrl: "View/administracija/akcija/slika.html"
//        })
//        .when("/administracija/vrsteDokumenata", {
//            controller: "vrstaDokumentaController",
//            templateUrl: "View/administracija/akcija/vrstaDokumenta.html"
//        })
//        .when("/administracija/relacijeDokumenti", {
//            controller: "relacijaDokumentController",
//            templateUrl: "View/administracija/akcija/relacijaDokument.html"
//        })
//        .when("/administracija/dokumenti", {
//            controller: "dokumentController",
//            templateUrl: "View/administracija/akcija/dokument.html"
//        })
//        .when("/administracija/relacijeKorisnici", {
//            controller: "relacijaKorisnikController",
//            templateUrl: "View/administracija/akcija/relacijaKorisnik.html"
//        })
//        .when("/administracija/statusi", {
//            controller: "statusController",
//            templateUrl: "View/administracija/akcija/status.html"
//        })
//        .when("/administracija/korisnici", {
//            controller: "korisnikController",
//            templateUrl: "View/administracija/korisnik/korisnik.html"
//        })
//        .when("/administracija/komentari", {
//            controller: "komentarController",
//            templateUrl: "View/administracija/akcija/komentar.html"
//        })
//        // .when("administracija/statusi", {
//        //     controller: "statusController",
//        //     templateUrl: "View/administracija/akcija/status.html"
//        // })
//        .when("/documents", {
//            controller: "documentsController",
//            templateUrl: "View/documents/documents.html"
//        })
//        .when("/digitalsignature", {
//            controller: "digitalsignatureController",
//            templateUrl: "View/digitalsignature/digitalsignature.html"
//        })
//        .when("/ocr", {
//            controller: "ocrController",
//            templateUrl: "View/ocr/ocr.html"
//        })
//        //.otherwise({redirectTo: '/login'});
//        .otherwise({redirectTo: "/"});
//}]);

DMApp.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

DMApp.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
            .success(function(data, fata, kata){
                debugger;
                ////callback.data;
                ////var blob = new Blob([data], {type: "application/pdf"});
                //var chars = atob(data); //
                var chars = data;
                var bytes = new Array(chars.length);
                for (var i = 0; i < chars.length; i++) bytes[i]=chars.charCodeAt(i);
                var blob = new Blob([new Uint8Array(bytes)]);
                //return blob;
                //var blob = new Blob([data], {type: "application/pdf", endings: "native"}); // ili "transparent"
                saveAs(blob, "Document.pdf");
            })
            .error(function(){
                debugger;
            });
    }
}]);

DMApp.controller('appController', ['$scope', function($scope) {
    $scope.main = {};
}]);