DMApp.controller('administracijaDashboardController', [
    '$scope',
    '$controller',
    '$http',
    '$timeout',
    '$interval',
    //'$tim'
    //'chart.js',
    function($scope, $controller,$http,$timeout,$interval) {
        $scope.main = {};
        $scope.name = "administracijaDashboardController";

        $scope.labelsLine = ["January", "February", "March", "April", "May", "June", "July"];
        $scope.seriesLine = ['Series A', 'Series B'];
        $scope.dataLine = [
            [65, 59, 80, 81, 56, 55, 40],
            [28, 48, 40, 19, 86, 27, 90]
        ];

        $scope.labelsRadar =["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"];

        $scope.dataRadar = [
            [65, 59, 90, 81, 56, 55, 40],
            [28, 48, 40, 19, 96, 27, 100]
        ];

        $scope.labelsPie = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
        $scope.dataPie = [300, 500, 100];

        $scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
        $scope.data = [300, 500, 100];

        $scope.onClick = function (points, evt) {
            console.log(points, evt);
        };

        $scope.labelsMem = ["Free","Used"];

        $scope.defaultMetrics = [];
        $scope.customMetrics = {status:[],auth:[]}

        $scope.obrada = function(sub,niz){
            var mailIndex = [];
            var rez = null;
            for(var i =1;i<niz[0].length;i++){
                if(niz[0][i].indexOf(sub)!=-1){
                    mailIndex.push(i);
                }
            }
            if(mailIndex.length>0){
                rez = [];
                var validniSize = mailIndex.length;
                for(var i=0;i<validniSize;i++){
                    rez.push([]);
                }
                for(var i = 1;i<niz.length;i++){
                    var temp = niz[i];
                    for(var j=0;j<validniSize;j++){
                        rez[j].push(temp[mailIndex[j]]);
                    }
                }
                var temp = {};
                for(var i=0;i<rez.length;i++){
                    temp[niz[0][mailIndex[i]]]=rez[i];
                }
                rez = temp;
            }
            return rez;
        }

        $scope.vrijeme = function(niz){
            var timeNiz=null;
            if(niz.length>0){
                if(niz[0].length>0){
                    timeNiz = [];
                    for(var i=1;i<niz.length;i++){
                        timeNiz.push(niz[i][0]);
                    }
                }
            }
            return timeNiz;
        }

        $scope.srediPodatke = function(){
            //memorija
            var mem = $scope.defaultMetrics["mem"];
            var free = $scope.defaultMetrics["mem.free"];
            $scope.dataMem = [free,mem-free];

            //mail info iz custom mail_send_attempt, mail_send_success, mail_send_error
            var niz = $scope.customMetrics.auth;
            var timeNiz = null;
            $scope.mailVrijeme = $scope.vrijeme(niz);

            var mailData = $scope.obrada("mail",niz);
            var mailSeries = [];
            for(var k in mailData){
                if(mailData.hasOwnProperty(k)){
                    mailSeries.push(k);
                }
            }

            if(mailSeries){
                $scope.mailSeries = mailSeries;
                $scope.dataMail = [];
                for(var i=0;i<mailSeries.length;i++){
                    $scope.dataMail.push(mailData[mailSeries[i]]);
                }
            }




        };
        $scope.dataGet = function(){
            var t = 0;
            $http.get('/metrics')
                .success(function(data,status,y){
                    $scope.defaultMetrics = data;
                    $scope.srediPodatke();
                    var a = 0;
                })
                .error(function(x,y,z){
                    var a = 0;
                })
            $http.get('metric-graph-data/status')
                .success(function(data,status,y){
                    $scope.customMetrics.status = data;
                    $scope.srediPodatke();
                    var a = 0;
                })
                .error(function(x,y,z){
                    var a = 0;
                })
            $http.get('metric-graph-data/auth')
                .success(function(data,status,y){
                    $scope.customMetrics.auth = data;
                    $scope.srediPodatke();
                    var a = 0;
                })
                .error(function(x,y,z){
                    var a = 0;
                })
        }


        $scope.start = function(){
            $scope.dataGet();
            //return $interval(function(){
            //    $scope.dataGet()
            //},95000);
            //setInterval($scope.dataGet(),50);
            //return $timeout(function(){
            //    $scope.dataGet();
            //},150);
        }

        $scope.start();
        //$scope.childEntity = 'korisnik';
        //$controller('administracijaController', { $scope: $scope});
    }
]);