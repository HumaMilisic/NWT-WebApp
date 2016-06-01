'use strict';
//DMApp.controller('uploadtestController', function($scope, $http) {
DMApp.controller('uploadtestController', ['$rootScope', '$scope', '$http', '$location', '$window', 'fileUpload', function($rootScope, $scope, $http, $location, $window, fileUpload) {
    $scope.file = null;
    $scope.dokumenti = [];
    //$scope.testUpload = function() {
    //    fileUpload.uploadFileToUrl($scope.file, '/api/upload'); //, $scope.dokument);
    //};

    $http.get("/document").then(function(response) {
        $scope.dokumenti = response.data.docs;
    });

    $scope.newVersion = function() {
        fileUpload.uploadFileToUrl($scope.file, '/document'); //, $scope.dokument);
        $scope.dokumenti.push($scope.file.name);
    };

    $scope.download = function (id) {
        $window.open('/document/' + id, '_blank');
    };

    $scope.delete = function (fileName) {
        $http.delete("/document/" + fileName).then(function(response) {
                for(var i = 0; i < $scope.dokumenti.length; i++)
                    if($scope.dokumenti[i] == fileName) $scope.dokumenti.splice(i);
                alert(response.data.status);
            },
            function(response) {
                alert(response.data.status);
            });
    };

    $scope.sign = function (fileName) {
        $http.post("/sign/" + fileName).then(function(response) {
                alert("sucess!")
            },
            function(response) {
                //alert("fail!")
            });
    };
}]);