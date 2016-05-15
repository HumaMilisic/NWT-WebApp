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
                for(var i =0;i<$scope.docs.length;i++){
                    $scope.docs[i].azuriran = new Date($scope.docs[i].azuriran);
                    $scope.docs[i].kreiran = new Date($scope.docs[i].kreiran);
                    $scope.docs[i].istice = new Date($scope.docs[i].istice);
                    $scope.docs[i].potpisan = new Date($scope.docs[i].potpisan);
                }

                //$scope.docs = $scope.docsR;

                var dateDanas = function(date){
                    var datum = new Date(JSON.parse(JSON.stringify(date['azuriran'])));
                    datum.setDate(datum.getDate()+1);
                    var sad = new Date();
                    sad.setDate(sad.getDate());
                    var rezultat =  datum-sad;
                    datum.setDate(datum.getDate()-1);
                    return rezultat>0;
                }

                var dateOstali = function(date){
                    var datum = new Date(JSON.parse(JSON.stringify(date['azuriran'])));
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