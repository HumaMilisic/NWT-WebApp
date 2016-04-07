DMApp.controller('administracijaKorisnikaController', [
    '$scope',
    '$controller',
    function($scope, $controller) {
        $scope.main = {};
        $scope.name = "naziv!!!";
        $scope.childEntity = 'korisnik';
        $controller('administracijaController', { $scope: $scope});
    }
]);