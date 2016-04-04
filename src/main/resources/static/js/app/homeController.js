DMApp.controller('homeController', [
    '$scope',
    'auth',
    function($scope,auth) {
        $scope.main = {};
        $scope.name = "naziv!!!";
        $scope.logovan = null;
        auth.jeLogovan().then(function(rez){
            $scope.logovan = rez.logovan;
        });
}]);