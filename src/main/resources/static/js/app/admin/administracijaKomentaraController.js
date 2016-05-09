/**
 * Created by monta on 5/7/2016.
 */
DMApp.controller('administracijaKomentaraController', [
    '$scope',
    '$controller',
    '$http',
    '$location',
    '$mdDialog',
    function($scope, $controller,$http,$location,$mdDialog) {
        $scope.main = {};
        $scope.name = "naziv!!!komentari";
        $scope.childEntity = 'komentar';
        //$controller('administracijaController', { $scope: $scope});

        $scope.newDialogChild = function(event){
            $mdDialog.show({
                templateUrl: 'js/app/parts/noviKomentar.html',
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
                            $scope.toastMsg('dodano');
                            $scope.loadStuff();
                        }).error(function(x,y,z){
                            $scope.toastMsg('problem');
                        })
                    }
                },
                function(){
                    $scope.toastMsg('cancel');
                })
        };

        $scope.childDelete = function(objekat,id){
            var urlPretraga = '/api/'+$scope.entity+'/search/findById?id='+id;
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

        $scope.editModalTemplateUrlChild = 'js/app/parts/editKomentar.html';

        $controller('administracijaController', { $scope: $scope});
    }
]);
