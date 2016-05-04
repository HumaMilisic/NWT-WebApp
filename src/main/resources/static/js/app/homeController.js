DMApp.controller('homeController', [
    '$scope',
    'auth',
    '$translate',
    'randomElem',
    function($scope,auth,$translate,randomElem) {
        $scope.main = {};
        $scope.name = "naziv!!!";
        $scope.logovan = null;
        $scope.docs = [];
        $scope.docs = randomElem.nizDocSortiranPoAzuriran(10);
        $scope.dummyDoc = function(doc,option){
            alert(doc.tekst+" Opcija: "+option);
        };
        //auth.jeLogovan().then(function(rez){
        //    $scope.logovan = rez.logovan;
        //});
        //
        //$scope.changeLanguage = function (langKey) {
        //    $translate.use(langKey);
        //};
}]);