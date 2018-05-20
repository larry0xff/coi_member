memberApp.controller('collectionsCtrl', ['$routeParams', '$rootScope', '$scope', '$http', function($routeParams, $rootScope, $scope, $http){
    $scope.getCollections = function(){
        $http.get($rootScope.contextPath + '/advice/collection/list?orgName=' + $scope.selectOrg).then(function(result){
           var data = result.data;
           if(data.returnCode != 200){
               console.log(data.msg);
           }else{
               $scope.collections = data.data;
           }
        });
    };
    function getpagedata(){
        $http.get($rootScope.contextPath + '/pagedata/home').then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                alert(data.msg);
            }else{
                $scope.pagedata = data.data;
            }
        });
    }
    (function(){
        $scope.selectOrg = "";
        $scope.getCollections();
        getpagedata();
    })();
}]);