memberApp.controller('notifyCtrl', ['$scope','$rootScope','$http','$interval','$routeParams','$cookieStore', function($scope, $rootScope, $http, $interval, $routeParams,$cookieStore){
    $scope.listComment = function(){
        $http.get($rootScope.contextPath + '/comment/notifyList').then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                alert(data.msg);
            }else{
                $scope.comments = data.data;
            }
        });
    };
    $scope.listMail = function(){
        $http.get($rootScope.contextPath + '/mail/notifyList').then(function(result){
            var data = result.data;
            if(data.returnCode != 200){
                alert(data.msg);
            }else{
                $scope.mails = data.data;
            }
        });
    };

    $scope.openModal = function (index) {
        $scope.temp = $scope.mails[index];
        var $modal = $('#mailModal');
        var options = {
            height: 400,
        };
        $modal.modal(options);
        $modal.modal('open');
    };
    $scope.closeModal = function(){
        $('#mailModal').modal('close');
    };
    (function() {
        $scope.listMail();
        $scope.listComment();
    })();
}]);