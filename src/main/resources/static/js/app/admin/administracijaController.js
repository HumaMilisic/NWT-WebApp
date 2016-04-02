DMApp.controller('administracijaController', ['$scope', function($scope) {
    $scope.main = {};
    $scope.name = "administracija";
    var self = this;
    $scope.data = [{name: "Moroni", age: 50} /*,*/];
    $scope.tableParams = new NgTableParams({}, { dataset: data});
}]);