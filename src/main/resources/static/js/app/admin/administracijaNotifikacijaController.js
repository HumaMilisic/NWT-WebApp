DMApp.controller('administracijaNotifikacijaController', [
    '$scope',
    '$controller',
    function($scope, $controller) {
        $scope.main = {};
        $scope.name = "naziv!!!";
        $scope.childEntity = 'notifikacija';
        $controller('administracijaController', { $scope: $scope});
    }
]);
