DMApp.controller('homeController', [
    '$scope',
    'auth',
    '$translate',
    function($scope,auth,$translate) {
        $scope.main = {};
        $scope.name = "naziv!!!";
        $scope.logovan = null;
        auth.jeLogovan().then(function(rez){
            $scope.logovan = rez.logovan;
        });

        $scope.changeLanguage = function (langKey) {
            $translate.use(langKey);
        };
}]);