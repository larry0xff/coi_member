memberApp.controller('homeCtrl', ['$scope', '$rootScope', '$http', function($scope, $rootScope, $http){
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
    function getCommentList(){
        $http.get($rootScope.contextPath + '/comment/list?pageSize=15&page=1&isSelf=N').then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                alert(data.msg);
            }else{
                $scope.commentList = data.data;
            }
        });
    }
    //初始化数据
    (function (){
        getpagedata();
        getCommentList();
    })();
}]);