/**
 * Created by monta on 5/7/2016.
 */
DMApp.controller('administracijaDokumentaController', [
    '$scope',
    '$controller',
    '$http',
    '$location',
    '$mdDialog',
    function($scope, $controller,$http,$location,$mdDialog) {
        $scope.main = {};
        $scope.name = "naziv!!!dokumenti";
        $scope.childEntity = 'dokument';
        $controller('administracijaController', { $scope: $scope});

        $scope.newDialogChild = function(event){
            $mdDialog.show({
                templateUrl: 'js/app/parts/noviDokument.html',
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

        $scope.childDelete = function(objekat,oznaka){
            var urlPretraga = '/api/'+$scope.entity+'/search/findByOznaka?oznaka='+oznaka;
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

        $scope.editModalTemplateUrlChild = 'js/app/parts/editDokument.html';

        $controller('administracijaController', { $scope: $scope});
    }
]);
