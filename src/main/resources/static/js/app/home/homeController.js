DMApp.controller('homeController', [
    '$scope',
    'auth',
    '$translate',
    'randomElem',
    '$http',
    'SpringDataRestAdapter',
    'loader',
    //'ResourceNew',
    function($scope,auth,$translate,randomElem,$http,SpringDataRestAdapter,loader) {
        $scope.main = {};
        $scope.main.time = new Date();
        $scope.name = "naziv!!!";
        $scope.logovan = null;
        $scope.docs = [];
        $scope.docsR = randomElem.nizDocSortiranKreiran(50);

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

                $scope.docs = $scope.docsR;

                var dateDanas = function(date){
                    var datum = date['azuriran'];
                    datum.setDate(datum.getDate()+1);
                    var sad = new Date();
                    sad.setDate(sad.getDate());
                    var rezultat =  datum-sad;
                    return rezultat>0;
                }

                var dateOstali = function(date){
                    var datum = date['azuriran'];
                    datum.setDate(datum.getDate()+1);
                    var sad = new Date();
                    sad.setDate(sad.getDate());
                    var rezultat =  datum-sad;
                    return rezultat<0;
                }

                $scope.docFilterDanas = $scope.docs.filter(dateDanas);
                $scope.docFilterOstali = $scope.docs.filter(dateOstali);

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