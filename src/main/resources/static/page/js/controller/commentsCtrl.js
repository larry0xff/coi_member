memberApp.controller('commentsCtrl', ['$scope', '$rootScope', '$http',
    function($scope, $rootScope, $http){
        //评论列表
        function getCommentList(num){
            $scope.page += num;
            $http.get($rootScope.contextPath + '/comment/list?pageSize=10&page=' + $scope.page + '&isSelf=N').then(function(result){
                var data = result.data;
                if(data.returnCode != 200){
                    alert(data.msg);
                }else{
                    if(data.data.length <= 0){
                        $scope.page -= num;
                        return;
                    }
                    if($scope.commentList){
                        $scope.commentList = $scope.commentList.concat(data.data);
                    }else{
                        $scope.commentList = data.data;
                    }
                }
            });
        }
        $scope.checkSwitch = function () {
            $http.get($rootScope.contextPath + "/switch/check?name=COMMENT_MODULE").then(function (result) {
                var data = result.data;
                if(data.returnCode != 200) {
                    Materialize.toast(data.msg, 2000);
                } else {
                    $scope.sw =  data.data;
                }
            });
        };
        (function(){
            $scope.page = 1;
            getCommentList(0);
            $scope.checkSwitch();
            $(window).scroll(function() {
                if($(window).scrollTop() + $(window).height() == $(document).height()) {
                    getCommentList(1);
                }
            });
        })();
        //点击回复
        $scope.toReply = function(replyName, replyId){
            if ($scope.sw.status == 1) {
                alert($scope.sw.message);
                return;
            }
            var $w = $(window);
            $w.smoothScroll({position: 0});
            $scope.newComment = '@' + replyName + ' ';
            $scope.replyId = replyId;
            $('#commentField').focus();
        };
        //取消回复
        $scope.cancelReply = function(){
            delete $scope.replyId;
            delete $scope.newComment;
        };
        //回复、发布评论
        $scope.addComment = function(){
            if ($scope.sw.status == 1) {
                alert($scope.sw.message);
                return;
            }
            var param = {
                replyId: $scope.replyId,
                content: $scope.newComment
            };
            $http.post($rootScope.contextPath + '/comment/save', param).then(function(result){
                var data = result.data;
                if(data.returnCode != 200){
                    alert(data.msg);
                }else{
                    delete $scope.newComment;
                    delete $scope.commentList;
                    $scope.page = 1;
                    getCommentList(0);
                }
            });
        };
        $scope.agree = function(c, flag){
            if ($scope.sw.status == 1) {
                alert($scope.sw.message);
                return;
            }
            $http.get($rootScope.contextPath + '/comment/agree?igCommentId=' + c.igCommentId + '&flag=' + flag).then(function(result){
                var data = result.data;
                if(data.returnCode != 200){
                    console.log(data.msg);
                }else{
                    if(flag == 'Y'){
                        c.agree += 1;
                        c.agreedisabled = true;
                    }else{
                        c.against += 1;
                        c.againstdisabled = true;
                    }
                }
            });
        };
    }]);