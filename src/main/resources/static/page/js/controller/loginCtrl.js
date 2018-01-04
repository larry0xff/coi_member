memberApp.controller('loginCtrl', ['$scope', '$rootScope', function($scope, $rootScope){
    $scope.login = function(){
        alert();
        $('#loginModal').modal('close');
    }
}]);