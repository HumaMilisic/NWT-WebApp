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
        $scope.labelsStatusi = ["200", "401", "404"];
        $scope.labelsKonekcije = [""];
        $scope.labelsClasses = ["Used", "Max", "Unused"];
        $scope.labelsHeap = ["Comm.", "Init", "Used", "Heap"];
        //$scope.dataThreads = [threads, total, peek, daemon];
        $scope.labelsThreads = ["threads", "total", "peek", "daemon"];
        //"gc.ps_scavenge.count":57,"gc.ps_scavenge.time":8189,"gc.ps_marksweep.count":3,"gc.ps_marksweep.time":3636
        //scavenge_count, gc_marksweep_count
        $scope.labelsCounts = ["Scavange count", "Marksweep count"];
        $scope.labelsTimes = ["Scavange time", "Marksweep time"];

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
        };

        $scope.srediPodatke = function(){
            //memorija
            var mem = $scope.defaultMetrics["mem"];
            var free = $scope.defaultMetrics["mem.free"];
            $scope.dataMem = [free,mem-free];

            //classes
            var classes = $scope.defaultMetrics["classes"];
            var classesLoaded = $scope.defaultMetrics["classes.loaded"];
            var classesUnloaded = $scope.defaultMetrics["classes.unloaded"];
            $scope.dataClasses = [classes,classesLoaded,classesUnloaded];

            //heap
            var heapCommitted = $scope.defaultMetrics["heap.committed"];
            var heapInit = $scope.defaultMetrics["heap.init"];
            var heapUsed = $scope.defaultMetrics["heap.used"];
            var heap = $scope.defaultMetrics["heap"];
            $scope.dataHeap = [heapCommitted,heapInit,heapUsed,heap];

            //threads
            //threads.peak":21,"threads.daemon":19,"threads.totalStarted":27,"threads":21,
            var peek = $scope.defaultMetrics["threads.peak"];
            var daemon = $scope.defaultMetrics["threads.daemon"];
            var total = $scope.defaultMetrics["threads.totalStarted"];
            var threads = $scope.defaultMetrics["threads"];
            $scope.dataThreads = [threads, total, peek, daemon];

            //"gc.ps_scavenge.count":57,"gc.ps_scavenge.time":8189,"gc.ps_marksweep.count":3,"gc.ps_marksweep.time":3636
            //gc counts
            var gc_scavenge_count = $scope.defaultMetrics["gc.ps_scavenge.count"];
            var gc_marksweep_count = $scope.defaultMetrics["gc.ps_marksweep.count"];
            $scope.dataCounts = [gc_scavenge_count, gc_marksweep_count];
            //gc times
            var gc_scavenge_time = $scope.defaultMetrics["gc.ps_scavenge.time"];
            var gc_marksweep_time = $scope.defaultMetrics["gc.ps_marksweep.time"];
            $scope.dataTimes = [gc_scavenge_time, gc_marksweep_time];

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

            //statusi 200, 401, 404
            var s1 = $scope.defaultMetrics["counter.status.200.api.profile.repository"];
            var s2 = $scope.defaultMetrics["counter.status.200.api.repository.id.property"];
            var s3 = $scope.defaultMetrics["counter.status.200.api.repository.id"];
            var s4 = $scope.defaultMetrics["counter.status.200.metric-graph-data.auth"];
            var s5 = $scope.defaultMetrics["counter.status.200.metrics"];
            var s6 = $scope.defaultMetrics["counter.status.200.api.repository.search.search"];
            var s7 = $scope.defaultMetrics["counter.status.200.metric-graph-data.status"];
            var s8 = $scope.defaultMetrics["counter.status.200.star-star"];
            var s9 = $scope.defaultMetrics["counter.status.200.user"];
            var s10 = $scope.defaultMetrics["counter.status.200.api.repository.search"];
            var s11 = $scope.defaultMetrics["counter.status.200.login"];
            var s12 = $scope.defaultMetrics["counter.status.200.api.repository"];
            var status200 = s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11+s12;
            var status401 = $scope.defaultMetrics["counter.status.401.unmapped"];
            var status404 = $scope.defaultMetrics["counter.status.404.star-star"];
            $scope.dataStatusi = [status200, status401, status404];

            //konekcije
            var konekcije = $scope.defaultMetrics["datasource.primary.active"];
            var iskoristenost = $scope.defaultMetrics["datasource.primary.usage"];
            //var konekcije = 5;
            //var iskoristenost = 0.25;
            $scope.seriesKonekcije = ["Broj konekcija", "Iskorištenost"];
            $scope.dataKonekcije = [[konekcije],[iskoristenost]];


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

        $scope.clearCustomMetrics = function(){
            $http.get("/metric-graph-data/reset")
                .success(function(){
                    $scope.dataGet();
                });
        }

        $scope.start = function(){
            $scope.dataGet();
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