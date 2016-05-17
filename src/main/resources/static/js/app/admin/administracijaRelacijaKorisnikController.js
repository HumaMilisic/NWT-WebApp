DMApp.controller('administracijaRelacijaKorisnikController', [
    '$scope',
    '$controller',
    '$http',
    '$location',
    '$mdDialog',
    '$filter',
    function($scope, $controller, $http, $location, $mdDialog, $filter) {
        $scope.main = {};
        $scope.name = "naziv!!!relacijaKorisnik";
        $scope.childEntity = 'relacijaKorisnik';
        //$controller('administracijaController', { $scope: $scope});

        $scope.newDialogChild = function(event){
            $mdDialog.show({
                templateUrl: 'js/app/parts/novaRelacijaKorisnik.html',
                targetEvent: event
            }).then(function(answer){
                    if(answer!=null){
                        answer.deleted = "0";
                        var url = '/api/'+$scope.entity;
                        $http({
                            method:'POST',
                            data:answer,
                            url:url
                        }).success(function(x,y,z){
                            $scope.toastMsg($filter('translate')('ADDED'));
                            $scope.loadStuff();
                        }).error(function(x,y,z){
                            $scope.toastMsg('Problem');
                        })
                    }
                },
                function(){
                    $scope.toastMsg($filter('translate')('CANCEL'));
                })
        };

        $scope.childDelete = function(objekat,nazivba){
            var urlPretraga = '/api/'+$scope.entity+'/search/findByNazivba?nazivba='+nazivba;
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

        $scope.editModalTemplateUrlChild = 'js/app/parts/editRelacijaKorisnik.html';

        $controller('administracijaController', { $scope: $scope});
    }
]);

