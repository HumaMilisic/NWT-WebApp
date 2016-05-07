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

        $controller('administracijaController', { $scope: $scope});
    }
]);
