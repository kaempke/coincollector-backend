'use strict';

coincollectorApp.controller('FormCtrl', function($scope,$http,$location) {
  $scope.submit = function() {
    $http.post('/services/users', $scope.form).success(function(data, status, headers, config) {
      $location.path('/account/' + $scope.form.werber);
    }).error(function(data, status, headers, config) {
    });
  };
});