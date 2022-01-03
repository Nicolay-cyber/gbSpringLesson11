angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    // delete $localStorage.springWebUser;


    // $localStorage.carts = new Map;
    // delete $localStorage.carts;

    /*    if (!$localStorage.carts) {
            $localStorage.carts = new Map();
        }
        if (!$scope.cart) {
            $scope.cart = [];
        }*/

    let currentPageIndex = 1;

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
        $localStorage.roles = (JSON.parse(window.atob($localStorage.springWebUser.token.split('.')[1]))).roles;
    }

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};
                    $localStorage.roles = (JSON.parse(window.atob($localStorage.springWebUser.token.split('.')[1]))).roles;
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.loadCart();
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        if ($localStorage.carts && $scope.cart) {
            $localStorage.carts.set($localStorage.springWebUser.username, $scope.cart);
        }
        $scope.cart = null;

        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isRole = function (role) {
        if ($localStorage.springWebUser) {
            return ($localStorage.roles.indexOf(role) > -1);
        }
    }


    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                p: pageIndex,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null
            }
        }).then(function (response) {
            $scope.productList = response.data;
            $scope.pageArray = $scope.generatePageArray(1, $scope.productList.totalPages);
        });
    };

    $scope.loadCart = function () {
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.Cart = response.data;
            });
    }


    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/cart/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.clearCart = function () {
        $http.get(contextPath + '/cart/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.removeFromCart = function (productId) {
        $http.delete(contextPath + '/cart/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.createOrder = function (){
        if($localStorage.springWebUser.username){
            $http.get(contextPath + '/order/' + $localStorage.springWebUser.username)
            .then(function (response) {
                $scope.loadCart();
            });
        } else {
            alert("Пожалуйста, войдите в систему")
        }

    }

    $scope.generatePageArray = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }
    $scope.changeCount = function (delta, id) {
        $http({
            url: contextPath + '/cart/change_count',
            method: 'POST',
            params: {
                id: id,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts(currentPageIndex);
            });
    }

    $scope.createProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function successCallBack(response) {
                $scope.newProduct = null;
                $scope.loadProducts(currentPageIndex);
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    }

    $scope.loadProducts(currentPageIndex);
    $scope.loadCart();
});