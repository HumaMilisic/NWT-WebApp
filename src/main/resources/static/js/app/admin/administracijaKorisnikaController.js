DMApp.controller('administracijaKorisnikaController', [
    '$scope',
    '$controller',
    '$http',
    function($scope, $controller,$http) {
        $scope.main = {};
        $scope.name = "naziv!!!";
        $scope.childEntity = 'korisnik';
        $scope.detalji = function(username){
            $location.path('/admin/korisnik/'+username);
        };



        $scope.childDelete = function(objekat,username){
            var urlPretraga = '/api/'+$scope.entity+'/search/findByUsername?username='+username;
            $http({
                method: 'GET',
                url: urlPretraga
            })
                .success(function(data,status,x,y,z){

                    var obj = data;
                    var link = data._links.self.href;
                    var a = 0;

                    $http({
                        method: 'DELETE',
                        url: link
                    })
                        .success(function(data,status){
                            $scope.dajStranicu();
                            //status 204 uspjesno i no content, nema dodatnog sadrzaja
                        })
                        .error(function(x,y,z){
                            var a = 0;
                            //409 constraint, povezan korisnik
                        });

                    //$scope.dajStranicu();
                })
                .error(function(data,status,x,y,z){
                    var a = 0;
                })
        };


        $controller('administracijaController', { $scope: $scope});
    }
]);

DMApp.controller('administracijaAkcijaController', [
    '$scope',
    '$controller',
    function($scope, $controller) {
        $scope.main = {};
        $scope.name = "naziv!!!akcije";
        $scope.childEntity = 'akcija';
        $controller('administracijaController', { $scope: $scope});
    }
]);

DMApp.controller('administracijaUlogaController', [
    '$scope',
    '$controller',
    '$http',
    function($scope, $controller,$http) {
        $scope.main = {};
        $scope.name = "naziv!!!uloge";
        $scope.childEntity = 'uloga';

        $controller('administracijaController', { $scope: $scope});
    }
]);