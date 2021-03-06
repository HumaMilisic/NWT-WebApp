DMApp.factory('navigacijaDozvoljena',function(){
    var sve = function(){
        var niz = [
            {label:"home",url:"/home"},
            {label:"admin",url:"/admin"},
            {label:"/admin/korisnik",url:"/admin/korisnik"},
            {label:"/admin/uloga",url:"/admin/uloga"},
            {label:"/admin/status",url:"/admin/status"},
            {label:"/admin/vrstaDokumenta",url:"/admin/vrstaDokumenta"},
            {label:"/admin/notifikacija",url:"/admin/notifikacija"},
            {label:"/admin/komentar",url:"/admin/komentar"},
            {label:"/admin/dokument",url:"/admin/dokument"},
            {label:"/admin/relacijaDokument",url:"/admin/relacijaDokument"},
            {label:"/admin/dogadjaj",url:"/admin/dogadjaj"},
            {label:"/admin/relacijaKorisnik",url:"/admin/relacijaKorisnik"}
        ];
        return niz;
    };
    return{
        sve:sve
    }
});


DMApp.factory('randomElem',function(){

    var status = function(){
        return{
            nazivba:faker.lorem.word(),
            naziven:faker.lorem.word()
        }
    }

    var doc = function(){
        if(Math.random()>0.5)
            var azuriran=faker.date.recent();
        else
            var azuriran=faker.date.past();

        var istice=faker.date.future();
        var kreiran=faker.date.past();
        var oznaka=faker.lorem.text();
        var potpis=faker.lorem.text();
        var potpisan=kreiran;
        var tekst=faker.lorem.text();
        return{
            azuriran:azuriran,
            istice:istice,
            kreiran:kreiran,
            oznaka:oznaka,
            potpis:potpis,
            potpisan:potpisan,
            tekst:tekst
        }
    };

    var niz = function(n,callback){
        var niz = [];
        for(var i =0;i<n;i++){
            var temp = callback && callback();
            niz.push(temp);
        }
        return niz;
    };

    var nizDoc = function(n){
        return niz(n,doc);
    };

    var nizStatus = function(n){
        return niz(n,status);
    }

    var nizDocSortiranAzuriran = function(n){
        var niz = nizDoc(n);
        niz.sort(function(a,b){
            return new Date(b.azuriran)- new Date(a.azuriran);
        });
        return niz;
    };

    var nizDocSortiranKreiran = function(n){
        var niz = nizDoc(n);
        niz.sort(function(a,b){
            return new Date(b.kreiran)- new Date(a.kreiran);
        });
        return niz;
    };

    return {
        doc:doc,
        nizDoc:nizDoc,
        nizDocSortiranPoAzuriran:nizDocSortiranAzuriran,
        nizDocSortiranKreiran:nizDocSortiranKreiran,
        nizStatus:nizStatus
    }
});

DMApp.factory('auth',function($http,$rootScope,$location,SpringDataRestAdapter,redirekt){
    var user = null;
    var korisnik = null;
    var role = [];
    var jeAdmin = false;

    var imaRola = function(rola,niz){
        var rez = false;
        for(var i =0;i<niz.length;i++){
            if(niz[i].authority == rola){
                rez = true;
                break;
            }
        }
        return rez;
    }

    var checkAuth = function(user,callback){
        $http.get("/user")
            .success(function(response,status,x,y,z){
                if(response.name){
                    $rootScope.authenticated = true;
                    $rootScope.$broadcast('authenticated');
                    user = response;
                    $rootScope.user = user;
                    $rootScope.$broadcast('user');
                    if(korisnik==null){
                        getKorisnik(user.name);
                    }
                    $rootScope.jeAdmin = imaRola("ROLE_ADMIN",response.authorities);
                    $rootScope.$broadcast('jeAdmin');
                }else{
                    $rootScope.jeAdmin = false;
                    //$rootScope.jeAdmin = imaRola("ROLE_ADMIN",response.authorities);
                    $rootScope.$broadcast('jeAdmin');
                    $rootScope.authenticated = false;
                    $rootScope.$broadcast('authenticated');
                    $rootScope.user = null;
                    $rootScope.$broadcast('user');
                    $rootScope.korisnik = null;
                    $rootScope.$broadcast('korisnik');
                    korisnik = null;
                }
                callback && callback();
            })
            .error(function(x,y,z,k){
                var a = 0;
                $rootScope.jeAdmin = false;
                //$rootScope.jeAdmin = imaRola("ROLE_ADMIN",response.authorities);
                $rootScope.$broadcast('jeAdmin');
                $rootScope.authenticated = false;
                $rootScope.$broadcast('authenticated');
                $rootScope.user = null;
                $rootScope.$broadcast('user');
                $rootScope.korisnik = null;
                $rootScope.$broadcast('korisnik');
                korisnik = null;
                callback && callback();
            })

    };

    var getKorisnik = function(username){
        var url = '/api/korisnik/search/findByUsername?username='+username;
        var promise = $http.get(url)
            .success(function(x,y,z){
                var a = 0;
            })
            .error(function(x,y,z){
                var a = 0;
            });
        SpringDataRestAdapter.process(promise,'_allLinks').then(function(resurs,y,z,k){
            var a = 0;
            korisnik = resurs;
            $rootScope.korisnik = korisnik;
            $rootScope.user.name = korisnik.username;
            $rootScope.$broadcast('korisnik');
            $rootScope.$broadcast('user');
        })
    };

    var checkUser = function(user,callback){
        var headers = {};

        //if(user!=null && typeof (user)!='undefined'){
        //    if(user.username){
        //        if(user.password){
        //            headers = {authorization:"Basic " + btoa(user.username+":"+user.password)};
        //        }
        //    }
        //}

        //$http({
        //    method: 'POST',
        //    url:'/register',
        //    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        //    transformRequest: function(obj) {
        //        var str = [];
        //        for(var p in obj)
        //            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        //        return str.join("&");
        //    },
        //    data: $scope.user
        //})

        $http({
            method: 'POST',
            url: '/login',
            headers:{'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
            data:user
        })
        //.get('user',{headers:headers})
            .success(function(response,status,z,k){
                if(status==200){
                    $rootScope.authenticated = true;
                    $rootScope.$broadcast('authenticated');
                    //user = null;
                    $rootScope.user = user;
                    $rootScope.$broadcast('user');
                    if(korisnik==null)
                        getKorisnik(user.username);
                }else{
                    $rootScope.jeAdmin = false;
                    //$rootScope.jeAdmin = imaRola("ROLE_ADMIN",response.authorities);
                    $rootScope.$broadcast('jeAdmin');
                    $rootScope.authenticated = false;
                    $rootScope.$broadcast('authenticated');
                    $rootScope.user = null;
                    $rootScope.$broadcast('user');
                    $rootScope.korisnik = null;
                    $rootScope.$broadcast('korisnik');
                }
                callback && callback();
            })
            .error(function(x,y,z,k){
                $rootScope.jeAdmin = false;
                //$rootScope.jeAdmin = imaRola("ROLE_ADMIN",response.authorities);
                $rootScope.$broadcast('jeAdmin');
                $rootScope.authenticated = false;
                $rootScope.$broadcast('authenticated');
                $rootScope.user = null;
                $rootScope.$broadcast('user');
                $rootScope.korisnik = null;
                $rootScope.$broadcast('korisnik');
                callback && callback();
            })
    };

    var login = function(user,callback,fail){
        checkUser(user,function(){
            if($rootScope.authenticated){
                checkAuth(user,function(){
                    redirekt.goToStaro();
                });

                //checkUser(user,function(){
                //    if($rootScope.authenticated){
                //        //$location.path("/");
                //        redirekt.goToHome();
                //    }else{
                //        //$location.path("login");
                //        redirekt.goToLogin();
                //    }
                //})
            }else{
                //$location.path("login");
                $rootScope.jeAdmin = false;
                //$rootScope.jeAdmin = imaRola("ROLE_ADMIN",response.authorities);
                $rootScope.$broadcast('jeAdmin');
                redirekt.goToLogin();
                fail && fail();
            }
            callback&&callback();
        })
    };

    var logout = function(){
        $http.post('logout',{})
            .success(function(x,y,z){
                var a = 0;
                $rootScope.korisnik = null;
                $rootScope.user = null;
                $rootScope.$broadcast('korisnik');
                $rootScope.$broadcast('user');
            })
            .error(function(x,y,z){
                var a  = 0;
            })
            .finally(function(){
                $rootScope.authenticated = false;
                $rootScope.$broadcast('authenticated');
                //$location.path("/login");
                redirekt.goToLogin(true);
            })
    };
    return{
        check: checkAuth,
        login: login,
        logout: logout,
        user: user,
        korisnik: korisnik
    }
});

DMApp.factory('Resource',
    //['$q','$filter','$timeout','tabela'],

    function($q,$filter,$timeout,$http,auth){
        //http://localhost:8181/api/korisnik?page=1&size=5
        var links = null;
        var tabela = "";
        var result = [];
        var page = [];
        var result = null;
        function goTo(glagol,link){
            var deferred = $q.defer();
            $http({
                method: glagol,
                url: link
            })
                .success(function(data,status,x){
                    if(data.page!=null && data.page!=undefined){
                        result = data;
                        links = result._links;
                        page = result.page;
                        deferred.resolve({
                            data: result._embedded[tabela],
                            page: result.page,
                            links: result._links,
                            status: 200
                        });
                    }else {
                        deferred.resolve({
                            status: 404
                        });
                    }
                })
                .error(function(response,status,nesto,request){
                    deferred.resolve({
                        status:status
                    });
                });

            return deferred.promise;
        }
        function getPage(page, size, params,tabelaa,link) {
            tabela = tabelaa;
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: '/api/'+tabela+'?page='+page+'$size='+size,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            })
                .success(function (data,status){
                    if(data.page!=null && data.page!=undefined){
                        //result = data;
                        //links = result._links;
                        result = data;
                        links = result._links;
                        page = result.page;
                        deferred.resolve({
                            data: result._embedded[tabelaa],
                            page: result.page,
                            links: result._links,
                            status: 200
                        });
                    }else {
                        deferred.resolve({
                            status: 404
                        });
                    }

                })
                .error(function(response,status,nesto,request){
                    var a = 0;
                    deferred.resolve({
                        status:status
                    });
                });

            return deferred.promise;
        }
        function nextPage(){
            if(links!=null){
                if(links.next!=undefined){
                    return goTo('GET',links.next.href)
                }
            }
        }
        function getPageBroj(brojStranice,brojNaStranici,tabelaa){
            var deferred = $q.defer();
            auth.check().then(function(rez){
                if(rez.status==200){
                    //http://localhost:8181/api/korisnik?page=1&size=5
                    var url = '/api/'+tabelaa+'?page='+(brojStranice-1)+'&size='+brojNaStranici;

                    $http({
                        method: 'GET',
                        url: url,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    })
                        .success(function (data,status,x,y,z){
                            if(data.page!=null && data.page!=undefined){
                                //result = data;
                                //links = result._links;
                                result = data;
                                links = result._links;
                                page = result.page;
                                deferred.resolve({
                                    data: result._embedded[tabelaa],
                                    page: result.page,
                                    links: result._links,
                                    status: 200
                                });
                            }else {
                                deferred.resolve({
                                    status: 404
                                });
                            }

                        })
                        .error(function(response,status,nesto,request){
                            var a = 0;
                            deferred.resolve({
                                status:status
                            });
                        })
                }else{
                    deferred.resolve({
                        status:403
                    });
                }
            });
            return deferred.promise;
        }
        function prevPage() {
        }
        function firstPage() {
        }
        function lastPage() {
        }
        return {
            //getPage: getPage,
            //nextPage: nextPage,
            //prevPage: prevPage,
            //firstPage: firstPage,
            //lastPage: lastPage,
            getPageBroj: getPageBroj
        };
    }
);



DMApp.factory("Access",function($q,auth,$rootScope){
    "use strict";

    var Access = {
        OK: 200,
        UNAUTHORISED: 401,
        FORBIDDEN: 403,

        hasRole: function(role){
            var deferred = $q.defer();
            deferred.reject(Access.UNAUTHORISED);
            return deferred.promise;
        },

        isAnon: function(){
            var deferred = $q.defer();
            //deferred.reject(Access.FORBIDDEN);
            deferred.resolve(Access.OK);
            return deferred.promise;
        }
    };

    return Access;
});


DMApp.factory('Item',function(SpringDataRestAdapter,$http){
    var entity = "";
    var baseUrl = "/api/";
    function Item(item,tabela){
        if(tabela){
            entity = tabela;
        }

        if(item._resources){
            item.resources = item._resources("self",{},{
                update:{
                    method: 'PUT'
                }
            });

            item.save = function(callback){
                item.resources.update(item,function(){
                    callback && callback(item);
                })
            };

            item.remove = function(callback){
                item.resources.remove(function(){
                    callback && callback(item);
                })
            }
        }else {
            item.save = function (callback) {
                Item.resources.save(item, function (item, headers) {
                    var deferred = $http.get(headers().location);
                    return SpringDataRestAdapter.processWithPromise(deferred).then(function (newItem) {
                        callback && callback(new Item(newItem));
                    });
                });
            };
        }
        return item;

    }

    Item.query = function(callback,tabela){
        if(tabela){
            entity = tabela;
        }
        var deffered = $http.get(baseUrl+entity);
        return SpringDataRestAdapter.process(deffered,'_allLinks').then(function(data){
            Item.resources = data._resources("self");
            callback && callback(_.map(data._embeddedItems,function(item){
                return new Item(item);
            }))
        })
    };

    Item.resources = null;

    return Item;
});

DMApp.factory('razmjena',function(){
    var objekat = null;
    return{
        setObjekat: function(obj){
            objekat=obj;
        },
        getObjekat:function(){
            return objekat;
        }
    }
})

DMApp.factory('ResourceNew',function(SpringDataRestAdapter,$http){
    //var Item = {
    //    entity:null,
    //    page:null,
    //    size:null,
    //    sort:null,
    //}
    //
    //var nextPage = function(item){
    //    if(item==null || typeof (item)=="undefined"){
    //        item = Item;
    //    }
    //}
    //
    //
    //return {
    //    nextPage:nextPage
    //}
})