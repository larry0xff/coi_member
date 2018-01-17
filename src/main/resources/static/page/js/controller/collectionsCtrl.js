memberApp.controller('collectionsCtrl', ['$routeParams', '$rootScope', '$scope', '$http', function($routeParams, $rootScope, $scope, $http){
    function getCollections(){
        $http.get($rootScope.contextPath + '/advice/collection/list').then(function(result){
           var data = result.data;
           if(data.returnCode != 200){
               console.log(data.msg);
           }else{
               $scope.collections = data.data;
           }
        });
    }
    (function(){
        getCollections();
    })();
}]);