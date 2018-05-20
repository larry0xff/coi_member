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
    $scope.checkSwitch = function () {
        $http.get($rootScope.contextPath + "/switch/check?name=COLLECTION_MODULE").then(function (result) {
            var data = result.data;
            if(data.returnCode != 200) {
                Materialize.toast(data.msg, 2000);
            } else {
                $scope.sw =  data.data;
            }
        });
    };
    $scope.addAdvice = function(){
        if ($scope.sw.status == 1) {
            alert($scope.sw.message);
            return;
        }
        if (!$cookieStore.get("realname")) {
            alert("请先登录！");
            return;
        }
        if (!/\S/.test($scope.content)) {
            $scope.errorMsg = "你的意见内容不能为空哦";
            return;
        }
        $http.post($rootScope.contextPath + '/advice/save?igAdviceCollectionId=' + $routeParams.id + "&content=" + $scope.content).then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                console.log(data.msg);
                $scope.errorMsg = data.msg;
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
        $scope.checkSwitch();
    })();
}]);