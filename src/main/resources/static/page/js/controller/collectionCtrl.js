memberApp.controller('collectionCtrl', ['$scope', '$rootScope', '$routeParams','$http','$cookieStore', function($scope, $rootScope, $routeParams,$http, $cookieStore){
    function getItem(){
        $http.get($rootScope.contextPath + '/advice/collection/get?igAdviceCollectionId=' + $routeParams.id).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                console.log(data.msg);
            }else{
                $scope.collectionItem = data.data;
            }
        });
    }
    function getAdvice(){
        $http.get($rootScope.contextPath + '/advice/getMemberAdviceById?igAdviceCollectionId=' + $routeParams.id).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                console.log(data.msg);
            }else{
                $scope.myAdvice = data.data.content;
            }
        });
    }
    $scope.addAdvice = function(){
        $http.post($rootScope.contextPath + '/advice/save?igAdviceCollectionId=' + $routeParams.id + "&content=" + $scope.content).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                console.log(data.msg);
            }else{
                getAdvice();
            }
        });
    };
    (function(){
        getItem();
        getAdvice();
        console.log($cookieStore.get('memberId'),$cookieStore.get('realname'));
        $scope.content = "";
    })()
}]);