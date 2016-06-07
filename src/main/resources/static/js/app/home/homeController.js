DMApp.controller('homeController', [
    '$scope',
    'auth',
    '$translate',
    'randomElem',
    '$http',
    'SpringDataRestAdapter',
    'loader',
    '$mdDialog',
    '$filter',
    '$rootScope',
    '$mdSidenav',
    '$location',
    'fileUpload',
    //'ResourceNew',
    function($scope,auth,$translate,randomElem,$http,SpringDataRestAdapter,loader,$mdDialog,$filter,$rootScope,$mdSidenav,$location, fileUpload) {
        $scope.trenutniKorisnik = null;
        $scope.trenutniKorisnik = $rootScope.korisnik;

        $scope.selectedStatus = '';

        $rootScope.$on('korisnik',function(event){
            $scope.trenutniKorisnik = $rootScope.korisnik;
        })

        $scope.main = {};
        $scope.main.time = new Date();
        $scope.name = "naziv!!!";
        $scope.logovan = null;
        $scope.docs = [];
        $scope.docsR = randomElem.nizDocSortiranKreiran(15);

        $scope.docsB = [];
        //loader.startSpin();
        //var getDocPromise = $http.get('/api/dokument?size=10')
        //    .success(function(data,status,x,y,z){
        //        var a = 0;
        //    })
        //    .error(function(x,y,z,k){
        //        $scope.docs = $scope.docsR;
        //        loader.stopSpin();
        //    });
        //
        //var obrada = SpringDataRestAdapter.process(getDocPromise,'_allLinks')
        //    .then(function(data,x,y,z,k){
        //        $scope.docs = data._embeddedItems;
        //        for(var i =0;i<$scope.docs.length;i++){
        //            $scope.docs[i].azuriran = new Date($scope.docs[i].azuriran);
        //            $scope.docs[i].kreiran = new Date($scope.docs[i].kreiran);
        //            $scope.docs[i].istice = new Date($scope.docs[i].istice);
        //            $scope.docs[i].potpisan = new Date($scope.docs[i].potpisan);
        //        }
        //
        //        //$scope.docs = $scope.docsR;
        //
        //        var dateDanas = function(date){
        //            var datum = new Date(JSON.parse(JSON.stringify(date['azuriran'])));
        //            datum.setDate(datum.getDate()+1);
        //            var sad = new Date();
        //            sad.setDate(sad.getDate());
        //            var rezultat =  datum-sad;
        //            datum.setDate(datum.getDate()-1);
        //            return rezultat>0;
        //        }
        //
        //        var dateOstali = function(date){
        //            var datum = new Date(JSON.parse(JSON.stringify(date['azuriran'])));
        //            datum.setDate(datum.getDate()+1);
        //            var sad = new Date();
        //            sad.setDate(sad.getDate());
        //            var rezultat =  datum-sad;
        //            return rezultat<0;
        //        }
        //
        //        $scope.docFilterDanas = $scope.docs.filter(dateDanas);
        //        $scope.docFilterOstali = $scope.docs.filter(dateOstali);
        //
        //        loader.stopSpin();
        //    });

        $scope.dummyDoc = function(doc,option){
            alert(doc.tekst+" Opcija: "+option);
        };


        $scope.limitOptions = [5, 10, 15];

        $scope.options = {
            rowSelection: true,
            multiSelect: false,
            autoSelect: false,
            decapitate: false,
            largeEditDialog: true,
            boundaryLinks: true,
            limitSelect: false,
            pageSelect: false
        };

        $scope.query = {
            order: 'name',
            limit: 10,
            page: 1
        };

        $scope.getPage = function(page,pageSize,entity,query,promise,linkovi){
            $scope.flagLoader=true;
            $scope.docSvi = null;
           var url = '/api/'+entity+'?page='+page+'&size='+pageSize+'&sort=azuriran,desc';
            var httpGetPromise = $http.get(url)
                .success(function(x,y,z){
                    var a = 0;
                })
                .error(function(x,y,z){
                    var a =0;
                    $scope.flagLoader=false;
                });
            $scope.promise = httpGetPromise;
            promise = httpGetPromise;
            $scope.selected = [];
            if(linkovi==null|| typeof(linkovi)=='undefined'){
                linkovi = '_allLinks';
            }

            var obrada = SpringDataRestAdapter.process(httpGetPromise,linkovi).then(function(data,x,y,z,k){
                query.limit = data.page.size;
                query.page = data.page.number+1;
                query.totalElements = data.page.totalElements;
                query.data = data._embeddedItems;
                $scope.docs = data._embeddedItems;
                //$scope.$apply();
                //
                ////$scope.docs = $scope.docsR;
                //for(var i =0;i<$scope.docs.length;i++){
                //    //if($scope.docs[i].azuriran)
                //        $scope.docs[i].azuriran = new Date($scope.docs[i].azuriran);
                //
                //    //if($scope.docs[i].kreiran)
                //        $scope.docs[i].kreiran = new Date($scope.docs[i].kreiran);
                //
                //    //if($scope.docs[i].istice)
                //        $scope.docs[i].istice = new Date($scope.docs[i].istice);
                //
                //    //if($scope.docs[i].potpisan)
                //        $scope.docs[i].potpisan = new Date($scope.docs[i].potpisan);
                //}
                //
                ////$scope.docs = $scope.docsR;
                ////
                ////var dateDanas = function(date){
                ////    var datum = new Date(JSON.parse(JSON.stringify(date['azuriran'])));
                ////    datum.setDate(datum.getDate()+1);
                ////    var sad = new Date();
                ////    sad.setDate(sad.getDate());
                ////    var rezultat =  datum-sad;
                ////    datum.setDate(datum.getDate()-1);
                ////    return rezultat>0;
                ////};
                ////
                ////var dateOstali = function(date){
                ////    var datum = new Date(JSON.parse(JSON.stringify(date['azuriran'])));
                ////    datum.setDate(datum.getDate()+1);
                ////    var sad = new Date();
                ////    sad.setDate(sad.getDate());
                ////    var rezultat =  datum-sad;
                ////    return rezultat<0;
                ////};
                //
                ////$scope.docFilterDanas = $scope.docs.filter(dateDanas);
                ////$scope.docFilterOstali = $scope.docs.filter(dateOstali);
                //$scope.docSvi = null;//
                //$scope.docSvi = $scope.docs;
                //$scope.flagLoader = false;
                //$scope.$apply();
                $scope.flagLoader=false;
            });
            //promise = httpGetPromise.$promise;
        };

        $scope.loadStuff = function () {
            $scope.getPage($scope.query.page-1,$scope.query.limit,'dokument',$scope.query,$scope.promise);
        };

        $scope.logPagination = function (page, limit) {
            $scope.getPage(page-1,limit,'dokument',$scope.query,$scope.promise);
            console.log('page: ', page);
            console.log('limit: ', limit);
        };

        $scope.loadStuff();

        $scope.ocr = function(doc){
            //za newDoc i meni
            //alert('ocr');
        }
        $scope.potpis = function(doc){
            //za newDoc i meni
            //alert('potpis');
        }
        $scope.upload = function(doc){
            //za newDoc i meni
            alert('upload');
        }



        $scope.newDialog = function(event){
            var dialogeScope = $scope.$new(true);
            dialogeScope.upload = $scope.upload;
            dialogeScope.ocr = $scope.ocr;
            dialogeScope.potpis = $scope.potpis;

            $mdDialog.show({
                templateUrl: 'js/app/parts/noviDokumentHome.html',
                scope: dialogeScope,
                controller: 'noviDocHomeModalCtrl',
                targetEvent: event
            }).then(function(answer){
                    if(answer!=null){
                        if(answer.file) {
                            answer.oznaka = answer.file.name;
                            //alert(answer.file.name);
                            fileUpload.uploadFileToUrl(answer.file, '/document');
                        }
                        //answer.file = null;

                        var url = '/api/dokument';
                        $http({
                            method:'POST',
                            data:answer,
                            url:url
                        }).success(function(data,y,z){
                            //$location.url('/home');
                            //$scope.loadStuff();
                            //$scope.toastMsg($filter('translate')('ADDED'));
                            var docLink = data._links.korisnikSet.href;
                            var userLink = $scope.trenutniKorisnik._links.self.href;
                            var data1 = {
                                _links:{
                                    korisnikSet:[{'href':userLink}]
                                }};
                            $http({
                                method:'PUT',
                                data:data1,
                                url:docLink
                            }).success(function(x,y,z){
                                var a = 0;
                                //$scope.loadStuff()
                                //$scope.loadStuffUloga();
                            }).error(function(x,y,z){
                                var a =0;
                                //$scope.loadStuff()
                            }).finally(function(){
                                $scope.loadStuff();
                            })


                            var docLink2 = data._links.statusSet.href;
                            var statusLink = answer.selectedStatus;

                            if(statusLink){
                                var data2 = {
                                    _links:{
                                        statusSet:[{'href':statusLink}]
                                    }};
                                $http({
                                    method:'PUT',
                                    data:data2,
                                    url:docLink2
                                }).success(function(x,y,z){
                                    var a = 0;
                                    //$scope.loadStuff()
                                    //$scope.loadStuffUloga();
                                }).error(function(x,y,z){
                                    var a =0;
                                    //$scope.loadStuff()
                                }).finally(function(){
                                    $scope.loadStuff();
                                })
                            }
                            //$scope.loadStuff();
                        }).error(function(x,y,z){
                            var a = 0;
                            //$scope.toastMsg('Problem');
                        })
                    }
                },
                function(){
                    $scope.toastMsg($filter('translate')('CANCEL'));
                })
        };


        //auth.jeLogovan().then(function(rez){
        //    $scope.logovan = rez.logovan;
        //});
        //
        //$scope.changeLanguage = function (langKey) {
        //    $translate.use(langKey);
        //};

        var dohvatiKorisnike = function(doc){

            var komentari = doc.komentarSet._embeddedItems;
            var link = doc._links.komentarSet.href;

            var httpGetPromise = $http.get(link)
                .error(function(){
                    $scope.flagLoaderKomentari = false;
                });

            var obrada = SpringDataRestAdapter.process(httpGetPromise,'_allLinks').then(function(data,x,y,z,k) {
                var a = data;

                data._embeddedItems.sort(function(a,b){
                    a = new Date(a.kreiran);
                    b = new Date(b.kreiran);
                    return a>b?-1:a<b?1:0;
                })

                //for(var i=0;i<data._embeddedItems;i++){
                //    data._embeddedItem[i].kreiran = new Date(data._embeddedItem[i].kreiran);
                //}

                doc['komentarSet'] = data._embeddedItems;
                $scope.flagLoaderKomentari = false;
                //query.limit = data.page.size;
                //query.page = data.page.number + 1;
                //query.totalElements = data.page.totalElements;
                //query.data = data._embeddedItems;
                //$scope.docs = data._embeddedItems;
            });
        }

        $scope.otvoriDetalje = function(doc){
            //alert('eh sidebar nesto nesto sa komentarima, ',doc);
            $scope.toggleNavBar('docDetalji');
            $scope.odabraniDoc = doc;
            $scope.flagLoaderKomentari = true;
            dohvatiKorisnike(doc);
        }

        $scope.otvoriDelete = function(doc){
            alert('delete: ',doc);
        }

        $scope.otvoriEdit = function(doc){
            alert('edit: ',doc);
        }

        $scope.toggleNavBar = function(id){
            //$scope.isOpenRight = function(){
            //    return $mdSidenav('right').isOpen();
            //};
            $mdSidenav(id).open();
            $scope.navBarVisibleFlag = !$scope.navBarVisibleFlag;
            //.toggle()
            //.then(function () {
            //    $log.debug("toggle " +  " is done");
            //});
        };

        $scope.newKomentar = function(event,doc){
            //alert('novi komentar');
            $mdDialog.show({
                templateUrl: 'js/app/parts/noviKomentarHome.html',
                targetEvent: event
            }).then(function(answer){
                    if(answer!=null){
                        answer.deleted = "0";
                        var url = '/api/komentar';
                        $http({
                            method:'POST',
                            data:answer,
                            url:url
                        }).success(function(x,y,z){
                            var a =0;
                            var urlDocSet = doc._links.komentarSet.href;
                            var urlKomentar = x._links.self.href;
                            var urlKomentarUser = x._links.korisnik.href;
                            var userLink = $scope.trenutniKorisnik._links.self.href;
                            $http({
                                method:"POST",
                                url:urlDocSet,
                                headers:{
                                    'Content-Type':"text/uri-list"
                                },
                                data:urlKomentar
                            })
                                .success(function(x,y,z,k,r){
                                    var a = 0;
                                })
                                .error(function(x,y,z,k){
                                    var a = 0;
                                })

                            $http({
                                method:"PUT",
                                url:urlKomentarUser,
                                headers:{
                                    'Content-Type':"text/uri-list"
                                },
                                data:userLink
                            })
                                .success(function(data,status,x,z){
                                    var a =0;
                                })
                                .error(function(x,z,y){
                                    var a =0;
                                })
                                .finally(function(){
                                    dohvatiKorisnike(doc);
                                })

                        }).error(function(x,y,z){
                            $scope.toastMsg('Problem');
                        }).finally(function(){
                            $scope.loadStuff();
                        })
                    }
                },
                function(){
                    $scope.toastMsg($filter('translate')('CANCEL'));
                })
        }


    }]);