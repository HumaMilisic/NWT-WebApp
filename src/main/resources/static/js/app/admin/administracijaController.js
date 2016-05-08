DMApp.controller('administracijaController', [
    '$scope',
    //"NgTableParams",
    'Resource',
    '$http',
    'loader',
    '$location',
    'auth',
    '$q',
    'SpringDataRestAdapter',
    'Item',
    '$resource',
    '$mdEditDialog',
    '$mdToast',
    '$mdDialog',
    function($scope/*,NgTableParams*/,Resource,$http,loader,$location,auth,$q,SpringDataRestAdapter,Item,$resource,$mdEditDialog,$mdToast,$mdDialog) {
        //auth.check();
        $scope.main = {};
        $scope.name = "administracija";

        $scope.entity = null;

        if($scope.childEntity){
            $scope.entity = $scope.childEntity;
        }

        $scope.selected = [];
        $scope.limitOptions = [5, 10, 15];

        $scope.options = {
            rowSelection: true,
            multiSelect: false,
            autoSelect: false,
            decapitate: false,
            largeEditDialog: true,
            boundaryLinks: true,
            limitSelect: true,
            pageSelect: true
        };

        $scope.query = {
            order: 'name',
            limit: 5,
            page: 1
        };

        $scope.desserts = {
            "count": 9,
            "data": [
                {
                    "name": "Frozen yogurt",
                    "type": "Ice cream",
                    "calories": { "value": 159.0 },
                    "fat": { "value": 6.0 },
                    "carbs": { "value": 24.0 },
                    "protein": { "value": 4.0 },
                    "sodium": { "value": 87.0 },
                    "calcium": { "value": 14.0 },
                    "iron": { "value": 1.0 }
                }, {
                    "name": "Ice cream sandwich",
                    "type": "Ice cream",
                    "calories": { "value": 237.0 },
                    "fat": { "value": 9.0 },
                    "carbs": { "value": 37.0 },
                    "protein": { "value": 4.3 },
                    "sodium": { "value": 129.0 },
                    "calcium": { "value": 8.0 },
                    "iron": { "value": 1.0 }
                }, {
                    "name": "Eclair",
                    "type": "Pastry",
                    "calories": { "value":  262.0 },
                    "fat": { "value": 16.0 },
                    "carbs": { "value": 24.0 },
                    "protein": { "value":  6.0 },
                    "sodium": { "value": 337.0 },
                    "calcium": { "value":  6.0 },
                    "iron": { "value": 7.0 }
                }, {
                    "name": "Cupcake",
                    "type": "Pastry",
                    "calories": { "value":  305.0 },
                    "fat": { "value": 3.7 },
                    "carbs": { "value": 67.0 },
                    "protein": { "value": 4.3 },
                    "sodium": { "value": 413.0 },
                    "calcium": { "value": 3.0 },
                    "iron": { "value": 8.0 }
                }, {
                    "name": "Jelly bean",
                    "type": "Candy",
                    "calories": { "value":  375.0 },
                    "fat": { "value": 0.0 },
                    "carbs": { "value": 94.0 },
                    "protein": { "value": 0.0 },
                    "sodium": { "value": 50.0 },
                    "calcium": { "value": 0.0 },
                    "iron": { "value": 0.0 }
                }, {
                    "name": "Lollipop",
                    "type": "Candy",
                    "calories": { "value": 392.0 },
                    "fat": { "value": 0.2 },
                    "carbs": { "value": 98.0 },
                    "protein": { "value": 0.0 },
                    "sodium": { "value": 38.0 },
                    "calcium": { "value": 0.0 },
                    "iron": { "value": 2.0 }
                }, {
                    "name": "Honeycomb",
                    "type": "Other",
                    "calories": { "value": 408.0 },
                    "fat": { "value": 3.2 },
                    "carbs": { "value": 87.0 },
                    "protein": { "value": 6.5 },
                    "sodium": { "value": 562.0 },
                    "calcium": { "value": 0.0 },
                    "iron": { "value": 45.0 }
                }, {
                    "name": "Donut",
                    "type": "Pastry",
                    "calories": { "value": 452.0 },
                    "fat": { "value": 25.0 },
                    "carbs": { "value": 51.0 },
                    "protein": { "value": 4.9 },
                    "sodium": { "value": 326.0 },
                    "calcium": { "value": 2.0 },
                    "iron": { "value": 22.0 }
                }, {
                    "name": "KitKat",
                    "type": "Candy",
                    "calories": { "value": 518.0 },
                    "fat": { "value": 26.0 },
                    "carbs": { "value": 65.0 },
                    "protein": { "value": 7.0 },
                    "sodium": { "value": 54.0 },
                    "calcium": { "value": 12.0 },
                    "iron": { "value": 6.0 }
                }
            ]
        };

        $scope.editComment = function (event, dessert) {
            event.stopPropagation(); // in case autoselect is enabled

            var editDialog = {
                modelValue: dessert.comment,
                placeholder: 'Add a comment',
                save: function (input) {
                    if(input.$modelValue === 'Donald Trump') {
                        input.$invalid = true;
                        return $q.reject();
                    }
                    if(input.$modelValue === 'Bernie Sanders') {
                        return dessert.comment = 'FEEL THE BERN!'
                    }
                    dessert.comment = input.$modelValue;
                },
                targetEvent: event,
                title: 'Add a comment',
                validators: {
                    'md-maxlength': 30
                }
            };

            var promise;

            if($scope.options.largeEditDialog) {
                promise = $mdEditDialog.large(editDialog);
            } else {
                promise = $mdEditDialog.small(editDialog);
            }

            promise.then(function (ctrl) {
                var input = ctrl.getInput();

                input.$viewChangeListeners.push(function () {
                    input.$setValidity('test', input.$modelValue !== 'test');
                });
            });
        };

        $scope.toggleLimitOptions = function () {
            $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
        };

        $scope.getTypes = function () {
            return ['Candy', 'Ice cream', 'Other', 'Pastry'];
        };

        $scope.loadStuff = function () {
            $scope.getPage($scope.query.page-1,$scope.query.limit,$scope.entity,$scope.query,$scope.promise);
        };

        $scope.logItem = function (item) {
            //console.log(item.username, 'was selected');
            var i = $scope.selected;
            $scope.mainSelectedItem = item;
            if($scope.getAkcijeIKorisnikeUloge){
                $scope.getAkcijeIKorisnikeUloge(item);
            }
        };

        $scope.logOrder = function (order) {
            console.log('order: ', order);
        };

        $scope.logPagination = function (page, limit) {
            $scope.getPage(page-1,limit,$scope.entity,$scope.query,$scope.promise);
            console.log('page: ', page);
            console.log('limit: ', limit);
        };

        //if($scope.logPaginationChild){
        //    $scope.logPagination = $scope.dummy();
        //        //$scope.logPaginationChild;
        //}


        $scope.getPage = function(page,pageSize,entity,query,promise,linkovi){
            var url = '/api/'+entity+'?page='+page+'&size='+pageSize;
            var httpGetPromise = $http.get(url)
                .success(function(x,y,z){
                    var a = 0;
                })
                .error(function(x,y,z){
                    var a =0;
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
            });
            promise = httpGetPromise.$promise;
        };

        if($scope.loadStuffUloga){
            $scope.loadStuff = $scope.loadStuffUloga;
        }
        $scope.loadStuff();




        $scope.newDialog = function(event){
            alert('napraviti specificno');
            //$mdDialog.show({
            //    //controller: novaUlogaCtrl,
            //    templateUrl: 'views/parts/novaUloga.html',
            //    targetEvent: event
            //}).then(function(answer){
            //        //$scope.toastMsg(answer);
            //    if(answer!=null){
            //        answer.deleted = "0";
            //        var url = '/api/'+$scope.entity;
            //        $http({
            //            method:'POST',
            //            data:answer,
            //            url:url
            //        }).success(function(x,y,z){
            //            $scope.toastMsg('dodano');
            //            $scope.loadStuff();
            //        }).error(function(x,y,z){
            //            $scope.toastMsg('problem');
            //        })
            //    }
            //},
            //    function(){
            //        $scope.toastMsg('cancel');
            //    })
        };

        if($scope.newDialogChild){
            $scope.newDialog = $scope.newDialogChild;
        }

        $scope.delete = function(){
            var selected = $scope.selected;
            angular.forEach(selected,function(item){
                var a = 0;
                var self = item._links.self.href;
                var naziv = item.naziv;
                $http.delete(self)
                    .success(function(x,z,y){
                        var a = 0;
                        $scope.toastMsg(naziv+" je obrisan");
                        $scope.loadStuff();
                    })
                    .error(function(x,z,y){
                        var a = 0;
                        $scope.toastMsg("greska");
                    })
            });
            $scope.selected = [];
        };

        $scope.deleteStatusa = function(){
            var selected = $scope.selected;
            angular.forEach(selected,function(item){
                var a = 0;
                var self = item._links.self.href;
                var nazivba = item.nazivba;
                $http.delete(self)
                    .success(function(x,z,y){
                        var a = 0;
                        $scope.toastMsg(nazivba+" je obrisan");
                        $scope.loadStuff();
                    })
                    .error(function(x,z,y){
                        var a = 0;
                        $scope.toastMsg("greska");
                    })
            });
            $scope.selected = [];
        };

        $scope.deleteNotifikacije = function(){
            var selected = $scope.selected;
            angular.forEach(selected,function(item){
                var a = 0;
                var self = item._links.self.href;
                //var naziv = item.naziv;
                $http.delete(self)
                    .success(function(x,z,y){
                        var a = 0;
                        $scope.toastMsg("Notifikacija je obrisana");
                        $scope.loadStuff();
                    })
                    .error(function(x,z,y){
                        var a = 0;
                        $scope.toastMsg("greska");
                    })
            });
            $scope.selected = [];
        };

        $scope.deleteVrsteDokumenta = function(){
            var selected = $scope.selected;
            angular.forEach(selected,function(item){
                var a = 0;
                var self = item._links.self.href;
                var nazivba = item.nazivba;
                $http.delete(self)
                    .success(function(x,z,y){
                        var a = 0;
                        $scope.toastMsg(nazivba+" je obrisan");
                        $scope.loadStuff();
                    })
                    .error(function(x,z,y){
                        var a = 0;
                        $scope.toastMsg("greska");
                    })
            });
            $scope.selected = [];
        };

        $scope.deleteDogadjaj = function(){
            var selected = $scope.selected;
            angular.forEach(selected,function(item){
                var a = 0;
                var self = item._links.self.href;
                var naziv = item.naziv;
                $http.delete(self)
                    .success(function(x,z,y){
                        var a = 0;
                        $scope.toastMsg(naziv+" je obrisan");
                        $scope.loadStuff();
                    })
                    .error(function(x,z,y){
                        var a = 0;
                        $scope.toastMsg("greska");
                    })
            });
            $scope.selected = [];
        };

        $scope.toastMsg = function(text) {
            var pinTo = "bottom right";
            $mdToast.show(
                $mdToast.simple()
                    .textContent(text)
                    .position(pinTo )
                    .hideDelay(3000)
            );
        };

        //$scope.paginatorCallback = function(page, pageSize){
        //    //alert('page: '+page+', pageSize: '+pageSize);
        //    var offset = (page-1) * pageSize;
        //    page = page -1;
        //    return $http({
        //        method: 'GET',
        //        url: '/api/'+$scope.entity+'?page='+page+'&size='+pageSize,
        //    }).then(function(result){
        //        if(result.status == 200){
        //            $scope.results = result.data._embedded[$scope.entity]
        //            var rez = {
        //                results: result.data._embedded[$scope.entity],
        //                totalResultCount: result.data.page.totalElements
        //            }
        //            return rez;
        //        }
        //
        //    });
        //}
        //
        //$scope.deleteRowCallback = function(rows){
        //    alert('del');
        //    //$http({
        //    //
        //    //}).then({});
        //    //$mdToast.show(
        //    //    $mdToast.simple()
        //    //        .content('Deleted row id(s): '+rows)
        //    //        .hideDelay(3000)
        //    //);
        //};

        $scope.dummy = function(){
            alert('nije napravljeno');
        };
        //loader.startSpin();
       //
       // $scope.maxSize = 5;
       // $scope.bigTotalItems = 175;
       // $scope.bigCurrentPage = 1;
       //
       // $scope.nutritionList = [
       //     {
       //         id: 601,
       //         name: 'Frozen joghurt',
       //         calories: 159,
       //         fat: 6.0,
       //         carbs: 24,
       //         protein: 4.0,
       //         sodium: 87,
       //         calcium: '14%',
       //         iron: '1%'
       //     },
       //     {
       //         id: 602,
       //         name: 'Ice cream sandwitch',
       //         calories: 237,
       //         fat: 9.0,
       //         carbs: 37,
       //         protein: 4.3,
       //         sodium: 129,
       //         calcium: '84%',
       //         iron: '1%'
       //     },
       //     {
       //         id: 603,
       //         name: 'Eclair',
       //         calories: 262,
       //         fat: 16.0,
       //         carbs: 24,
       //         protein: 6.0,
       //         sodium: 337,
       //         calcium: '6%',
       //         iron: '7%'
       //     },
       //     {
       //         id: 604,
       //         name: 'Cupkake',
       //         calories: 305,
       //         fat: 3.7,
       //         carbs: 67,
       //         protein: 4.3,
       //         sodium: 413,
       //         calcium: '3%',
       //         iron: '8%'
       //     },
       //     {
       //         id: 605,
       //         name: 'Gingerbread',
       //         calories: 356,
       //         fat: 16.0,
       //         carbs: 49,
       //         protein: 2.9,
       //         sodium: 327,
       //         calcium: '7%',
       //         iron: '16%'
       //     },
       //     {
       //         id: 606,
       //         name: 'Jelly bean',
       //         calories: 375,
       //         fat: 0.0,
       //         carbs: 94,
       //         protein: 0.0,
       //         sodium: 50,
       //         calcium: '0%',
       //         iron: '0%'
       //     },
       //     {
       //         id: 607,
       //         name: 'Lollipop',
       //         calories: 392,
       //         fat: 0.2,
       //         carbs: 98,
       //         protein: 0,
       //         sodium: 38,
       //         calcium: '0%',
       //         iron: '2%'
       //     },
       //     {
       //         id: 608,
       //         name: 'Honeycomb',
       //         calories: 408,
       //         fat: 3.2,
       //         carbs: 87,
       //         protein: 6.5,
       //         sodium: 562,
       //         calcium: '0%',
       //         iron: '45%'
       //     },
       //     {
       //         id: 609,
       //         name: 'Donut',
       //         calories: 452,
       //         fat: 25.0,
       //         carbs: 51,
       //         protein: 4.9,
       //         sodium: 326,
       //         calcium: '2%',
       //         iron: '22%'
       //     },
       //     {
       //         id: 610,
       //         name: 'KitKat',
       //         calories: 518,
       //         fat: 26.0,
       //         carbs: 65,
       //         protein: 7,
       //         sodium: 54,
       //         calcium: '12%',
       //         iron: '6%'
       //     }
       // ];
       //
       // $scope.checkModel = {
       //     left: false,
       //     middle: true,
       //     right: false
       // };
       //
       // $scope.displayed = [];
       // $scope.page ={
       //     size:5,
       //     totalElements:1
       // };
       //
       // $scope.nutritionList = [
       //     {
       //         id: 601,
       //         name: 'Frozen joghurt',
       //         calories: 159,
       //         fat: 6.0,
       //         carbs: 24,
       //         protein: 4.0,
       //         sodium: 87,
       //         calcium: '14%',
       //         iron: '1%'
       //     },
       //     {
       //         id: 602,
       //         name: 'Ice cream sandwitch',
       //         calories: 237,
       //         fat: 9.0,
       //         carbs: 37,
       //         protein: 4.3,
       //         sodium: 129,
       //         calcium: '84%',
       //         iron: '1%'
       //     },
       //     {
       //         id: 603,
       //         name: 'Eclair',
       //         calories: 262,
       //         fat: 16.0,
       //         carbs: 24,
       //         protein: 6.0,
       //         sodium: 337,
       //         calcium: '6%',
       //         iron: '7%'
       //     },
       //     {
       //         id: 604,
       //         name: 'Cupkake',
       //         calories: 305,
       //         fat: 3.7,
       //         carbs: 67,
       //         protein: 4.3,
       //         sodium: 413,
       //         calcium: '3%',
       //         iron: '8%'
       //     },
       //     {
       //         id: 605,
       //         name: 'Gingerbread',
       //         calories: 356,
       //         fat: 16.0,
       //         carbs: 49,
       //         protein: 2.9,
       //         sodium: 327,
       //         calcium: '7%',
       //         iron: '16%'
       //     },
       //     {
       //         id: 606,
       //         name: 'Jelly bean',
       //         calories: 375,
       //         fat: 0.0,
       //         carbs: 94,
       //         protein: 0.0,
       //         sodium: 50,
       //         calcium: '0%',
       //         iron: '0%'
       //     },
       //     {
       //         id: 607,
       //         name: 'Lollipop',
       //         calories: 392,
       //         fat: 0.2,
       //         carbs: 98,
       //         protein: 0,
       //         sodium: 38,
       //         calcium: '0%',
       //         iron: '2%'
       //     },
       //     {
       //         id: 608,
       //         name: 'Honeycomb',
       //         calories: 408,
       //         fat: 3.2,
       //         carbs: 87,
       //         protein: 6.5,
       //         sodium: 562,
       //         calcium: '0%',
       //         iron: '45%'
       //     },
       //     {
       //         id: 609,
       //         name: 'Donut',
       //         calories: 452,
       //         fat: 25.0,
       //         carbs: 51,
       //         protein: 4.9,
       //         sodium: 326,
       //         calcium: '2%',
       //         iron: '22%'
       //     },
       //     {
       //         id: 610,
       //         name: 'KitKat',
       //         calories: 518,
       //         fat: 26.0,
       //         carbs: 65,
       //         protein: 7,
       //         sodium: 54,
       //         calcium: '12%',
       //         iron: '6%'
       //     }
       // ];
       //

       //
       // $scope.sveUloge = {};
       // $scope.sveAkcije = {};
       //
       // $scope.popuni = function(){
       //     var httpPromiseUloge = $http.get('/api/uloga')
       //         .success(function(x,y,z){
       //             var a = 0;
       //         })
       //         .error(function(x,y,z){
       //             var a = 0;
       //         });
       //     var httpPromiseAkcije = $http.get('/api/akcija')
       //         .success(function(x,y,z){
       //             var a = 0;
       //         })
       //         .error(function(x,y,z){
       //             var a = 0;
       //         });
       //     SpringDataRestAdapter.process(httpPromiseAkcije,'_allLinks').then(function(data){
       //         $scope.sveAkcije = data._embeddedItems;
       //     });
       //     SpringDataRestAdapter.process(httpPromiseUloge,'_allLinks').then(function(data){
       //         $scope.sveUloge = data._embeddedItems;
       //     });
       // };
       // //$scope.popuni();
       //
       // $scope.dajStranicu = function(){
       //     //loader.startSpin();
       //     //auth.check();
       //
       //     var httpPromise = $http.get('/api/'+$scope.entity)
       //         .success(function(x,y,z){
       //             var a = 0;
       //         })
       //         .error(function(x,y,z){
       //             var a = 0;
       //         });
       //
       //     SpringDataRestAdapter.process(httpPromise,'_allLinks').then(function(x,y,z,k){
       //         var resourceObject = {
       //             "name": "self",
       //             "parameters": {
       //                 "size": 20,
       //                 "sort": "asc"
       //             }
       //         };
       //
       //         //$scope.ppl = x._resources(resourceObject, paramDefaults, actions, options);
       //         $scope.page = x.page;
       //         $scope.links = x._links;
       //         $scope.displayed = x._embeddedItems;
       //     });
       //
            $scope.displaySet = function(objekat){
                var rez = "";
                angular.forEach(objekat._embeddedItems,function(item){
                    if(item['naziv']){
                        rez+='['+item['naziv']+'] ';
                    }else if(item['username']){
                        rez+='['+item['username']+'] ';
                    }
                });
                return rez;
            };
       //     //Resource.getPageBroj($scope.bigCurrentPage,$scope.page.size,$scope.entity).then(function(result){
       //     //    loader.stopSpin();
       //     //    if(result.status == 200){
       //     //        $scope.displayed = result.data;
       //     //        $scope.page = result.page;
       //     //        $scope.links = result.links;
       //     //    }else {
       //     //        //alert(result.status);
       //     //    }
       //     //})
       // };
       //
       // $scope.dajStranicu();
       //
       // $scope.resursTest = $resource("/api/korisnik");
       // $scope.dodajUloga = function(){
       //     $scope.ppl = $scope.resursTest.get(null,function(){
       //         var a = 8;
       //     });
       //     var httpPromise = $http.get('/api/'+$scope.entity)
       //         .success(function(x,y,z){
       //             var a = 0;
       //         })
       //         .error(function(x,y,z){
       //             var a = 0;
       //         });
       //
       //     SpringDataRestAdapter.process(httpPromise,'_allLinks').then(function(x,y,z,k){
       //
       //         var item = x._resources();
       //         var a = 0;
       //         $scope.items = x._embeddedItems;
       //         //$scope.items[5]._resources()
       //         angular.forEach($scope.items,function(a){
       //             console.log(a.naziv);
       //             var t = a._resources()
       //         })
       //     });
       //     $http({
       //         method:'DELETE',
       //         url:'/api/uloga/2',
       //         //headers: {'Content-Type': 'application/x-www-form-urlencoded'},
       //     }).success(function(x,y,z,r,k){
       //         $scope.dajStranicu();
       //     });
       //
       //     //
       //     //Item.query(function(response){
       //     //    $scope.items = response ? response :[];
       //     //},'uloga').then(function(data){
       //     //
       //     //})
       //     //
       //     //$scope.ppl = $scope.resursTest.query(null,function(x,y,z,k){
       //     //    var fist = ppl[0];
       //     //
       //     //})
       //     //
       //     //$scope.newItem = "";
       // };
       //
       //
       //
       // $scope.dodaj = function(){
       //     //new Item({
       //     //    naziv:'test'
       //     //},'uloga').save(function(item){
       //     //    //$scope.items.push(item);
       //     //    var a = 0;
       //     //});
       //     $http({
       //         method:'POST',
       //         url:'/api/uloga',
       //         data:{'naziv':'testunos','deleted':'0'}
       //         //headers: {'Content-Type': 'application/x-www-form-urlencoded'},
       //     }).success(function(x,y,z,r,k){
       //         $scope.dajStranicu();
       //     }).error(function(x,y,zr,k){
       //         var a =0 ;
       //     });
       // };
       //
       // $scope.dodaj();
       // $scope.dodaj();
       // $scope.dodaj();
       // $scope.dodaj();
       //
       // $scope.dodaj2 = function(){
       //     var korisnik = "http://localhost:8181/api/korisnik/5 \n" +
       //         "http://localhost:8181/api/korisnik/2";
       ////     ];
       //     $http({
       //         method:'POST',
       //         url:'/api/uloga/1/korisnikSet',
       //         data:korisnik,
       //         headers: {'Content-Type': 'text/uri-list'},
       //     }).success(function(x,y,z,r,k){
       //         $scope.dajStranicu();
       //     }).error(function(x,y,zr,k){
       //         var a =0 ;
       //     });
       // };
       // $scope.dodaj3 = function(){
       //     var korisnik = "http://localhost:8181/api/korisnik/2";//},
       //     //            {"uri":"http://localhost:8181/api/korisnik/2"}
       //     //     ];
       //     $http({
       //         method:'POST',
       //         url:'/api/uloga/2/korisnikSet',
       //         data:korisnik,
       //         headers: {'Content-Type': 'text/uri-list'},
       //     }).success(function(x,y,z,r,k){
       //         $scope.dajStranicu();
       //     }).error(function(x,y,zr,k){
       //         var a =0 ;
       //     });
       // };
       //
       //
       //
       // //function getPageBroj(brojStranice,brojNaStranici,tabelaa){
       // //    var deferred = $q.defer();
       // //    auth.check().then(function(rez){
       // //        if(rez.status==200){
       // //            //http://localhost:8181/api/korisnik?page=1&size=5
       // //            var url = '/api/'+tabelaa+'?page='+(brojStranice-1)+'&size='+brojNaStranici;
       // //
       // //            $http({
       // //                method: 'GET',
       // //                url: url,
       // //                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
       // //            })
       // //                .success(function (data,status,x,y,z){
       // //                    if(data.page!=null && data.page!=undefined){
       // //                        //result = data;
       // //                        //links = result._links;
       // //                        result = data;
       // //                        links = result._links;
       // //                        page = result.page;
       // //                        deferred.resolve({
       // //                            data: result._embedded[tabelaa],
       // //                            page: result.page,
       // //                            links: result._links,
       // //                            status: 200
       // //                        });
       // //                    }else {
       // //                        deferred.resolve({
       // //                            status: 404
       // //                        });
       // //                    }
       // //
       // //                })
       // //                .error(function(response,status,nesto,request){
       // //                    var a = 0;
       // //                    deferred.resolve({
       // //                        status:status
       // //                    });
       // //                })
       // //        }else{
       // //            deferred.resolve({
       // //                status:403
       // //            });
       // //        }
       // //    });
       // //    return deferred.promise;
       // //}
       //
       //
       //
       // $scope.delete = function(objekat,naziv){
       //     var urlPretraga = '/api/'+$scope.entity+'/search/findByNaziv?naziv='+naziv;
       //     $http({
       //         method: 'GET',
       //         url: urlPretraga
       //     })
       //         .success(function(data,status,x,y,z){
       //
       //             var obj = data._embedded[$scope.entity];
       //             var link = obj[0]._links.self.href;
       //             var a = 0;
       //
       //             $http({
       //                 method: 'DELETE',
       //                 url: link
       //             })
       //                 .success(function(data,status){
       //                     $scope.dajStranicu();
       //                     //status 204 uspjesno i no content, nema dodatnog sadrzaja
       //                 })
       //                 .error(function(x,y,z){
       //                     var a = 0;
       //                 });
       //
       //             //$scope.dajStranicu();
       //         })
       //         .error(function(data,status,x,y,z){
       //             var a = 0;
       //         })
       // };
       //
       // if($scope.childDelete){
       //     $scope.delete = $scope.childDelete;
       // }

        $scope.editModalTemplateUrl = '';

        if($scope.editModalTemplateUrlChild){
            $scope.editModalTemplateUrl = $scope.editModalTemplateUrlChild;
        }

        $scope.editDialog = function(objekat){
            //var link = $scope.selected[0]._links.self.href;
            //var a = 0;
            //$http({
            //    method: 'PATCH',
            //    url: link,
            //    data: {
            //        "nazivba": "baa",
            //        "naziven": "enen",
            //        "deleted": "0"
            //    }
            //})
            //    .success(function(data,status){
            //        $scope.dajStranicu();
            //        //status 204 uspjesno i no content, nema dodatnog sadrzaja
            //    })
            //    .error(function(x,y,z){
            //        var a = 0;
            //        //409 constraint, povezan korisnik
            //    })

            // bilo za status
            // var naziv_BA = $scope.selected[0].nazivba;
            // var naziv_EN = $scope.selected[0].naziven;

            $mdDialog.show({
                scope: $scope,
                controller: "editItemModalCtrl",
                templateUrl: $scope.editModalTemplateUrl,
                targetEvent: event
            }).then(function(answer){
                    if(answer!=null){
                        answer.deleted = "0";
                        var link = $scope.selected[0]._links.self.href;
                        $http({
                            method:'PATCH',
                            url: link,
                            data: answer
                        }).success(function(x,y,z){
                            $scope.toastMsg('izmijenjeno');
                            $scope.loadStuff();
                        }).error(function(x,y,z){
                            $scope.toastMsg('problem');
                        })
                    }
                },
                function(){
                    $scope.toastMsg('cancel');
                })
        };

}]);