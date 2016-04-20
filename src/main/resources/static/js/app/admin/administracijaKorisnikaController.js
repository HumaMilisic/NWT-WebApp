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

        $scope.ulogeFlag = false;
        $scope.uloge = function(osoba){
            $scope.ulogeFlag = true;
            $scope.trenutni = osoba;
        };

        $scope.dodajKorisnikaUUlogu = function(uloga,osoba){
            var korisnik = osoba._links.self.href;
            var url = uloga._links.self.href+'/korisnikSet';
            $http({
                method:'POST',
                url:url,
                data:korisnik,
                headers: {'Content-Type': 'text/uri-list'},
            }).success(function(x,y,z,r,k){
                $scope.dajStranicu();
            }).error(function(x,y,zr,k){
                var a =0 ;
            });
        };

        $scope.izbaciKorisnikaIzUloge = function(uloga,osoba){
            var korisnik = osoba._links.self.href;
            var url = uloga._links.self.href+'/korisnikSet';
            $http({
                method:'PUT',
                url:url,
                //data:korisnik,
                headers: {'Content-Type': 'text/uri-list'},
            }).success(function(x,y,z,r,k){
                $scope.dajStranicu();
            }).error(function(x,y,z,r,k){
                var a =0 ;
            });
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