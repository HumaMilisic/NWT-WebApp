DMApp.controller('administracijaController', [
    '$scope',
    //"NgTableParams",
    'Resource',
    '$http',
    'loader',
    '$location',
    'auth',
    function($scope/*,NgTableParams*/,Resource,$http,loader,$location,auth) {
        //auth.check();
        $scope.main = {};
        $scope.name = "administracija";


        $scope.dummy = function(){
            alert('nije napravljeno');
        }
        //loader.startSpin();

        $scope.maxSize = 5;
        $scope.bigTotalItems = 175;
        $scope.bigCurrentPage = 1;

        $scope.checkModel = {
            left: false,
            middle: true,
            right: false
        };

        $scope.displayed = [];
        $scope.page ={
            size:5,
            totalElements:1
        };

        $scope.entity = null;

        if($scope.childEntity){
            $scope.entity = $scope.childEntity;
        }

        $scope.dajStranicu = function(){
            loader.startSpin();
            //auth.check();
            Resource.getPageBroj($scope.bigCurrentPage,$scope.page.size,$scope.entity).then(function(result){
                loader.stopSpin();
                if(result.status == 200){
                    $scope.displayed = result.data;
                    $scope.page = result.page;
                    $scope.links = result.links;
                }else {
                    //alert(result.status);
                }
            })
        }

        $scope.dajStranicu();

        $scope.detalji = function(username){
            $location.path('/admin/korisnik/'+username);
        }
}]);