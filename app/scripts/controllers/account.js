'use strict';

coincollectorApp.controller('AccountCtrl', function($scope,$routeParams,$http) {
  $scope.email = $routeParams.email;
  
$http({method: 'GET', url: '/services/users/praemien'}).
  success(function(data, status, headers, config) {
    console.log(data);
    $scope.praemien = data;
  }).
  error(function(data, status, headers, config) {
  });

  $http({method: 'GET', url: '/services/users/'+$routeParams.email}).
  success(function(data, status, headers, config) {
    $scope.coins = data.coins;
  }).
  error(function(data, status, headers, config) {
    $scope.coins = 123;
  });

});