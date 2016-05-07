/**
 * Created by monta on 5/7/2016.
 */
DMApp.controller('administracijaRelacijaDokumentController', [
    '$scope',
    '$controller',
    '$http',
    '$location',
    '$mdDialog',
    function($scope, $controller,$http,$location,$mdDialog) {
        $scope.main = {};
        $scope.name = "naziv!!!relacija dokumenti";
        $scope.childEntity = 'relacijaDokument';
        $controller('administracijaController', { $scope: $scope});

        $scope.newDialogChild = function(event){
            $mdDialog.show({
                templateUrl: 'js/app/parts/novaRelacijaDokument.html',
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
