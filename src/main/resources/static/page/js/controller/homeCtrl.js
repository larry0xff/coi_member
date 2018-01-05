memberApp.controller('homeCtrl', ['$scope', '$rootScope', '$http', function($scope, $rootScope, $http){
    //初始化数据
    (function (){
        $http.get($rootScope.contextPath + '/pagedata/home').then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                alert(data.msg);
            }else{
                $scope.pagedata = data.data;
            }
        });
    })();
}]);