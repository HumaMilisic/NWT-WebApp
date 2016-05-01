DMApp.controller('administracijaVrstaDokumentaController', [
    '$scope',
    '$controller',
    function($scope, $controller) {
        $scope.main = {};
        $scope.name = "naziv!!!";
        $scope.childEntity = 'vrstaDokumenta';
        $controller('administracijaController', { $scope: $scope});
    }
]);