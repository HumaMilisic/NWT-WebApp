DMApp.controller('homeController', [
    '$scope',
    'auth',
    '$translate',
    'randomElem',
    '$http',
    'SpringDataRestAdapter',
    'loader',
    function($scope,auth,$translate,randomElem,$http,SpringDataRestAdapter,loader) {
        $scope.main = {};
        $scope.name = "naziv!!!";
        $scope.logovan = null;
        $scope.docs = [];
        $scope.docsR = randomElem.nizDocSortiranPoAzuriran(10);

        $scope.docsB = [];
        loader.startSpin();
        var getDocPromise = $http.get('/api/dokument?size=10')
            .success(function(data,status,x,y,z){
                var a = 0;
            })
            .error(function(x,y,z,k){
                $scope.docs = $scope.docsR;
                loader.stopSpin();
            });

        var obrada = SpringDataRestAdapter.process(getDocPromise,'_allLinks')
            .then(function(data,x,y,z,k){
                $scope.docs = data._embeddedItems;
                loader.stopSpin();
            });

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