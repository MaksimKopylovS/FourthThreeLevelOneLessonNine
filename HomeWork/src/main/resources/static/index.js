
angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params:{
                page: pageIndex
            }
        }).then(function (response){
            $scope.ProductsPage = response.data;
            console.log(response)
            $scope.PaginationArray = $scope.generatePageIndex(1, $scope.ProductsPage.totalPages);
        });
    };
    $scope.fillTable();


    $scope.generatePageIndex = function(startPage, endPage){
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++){
            arr.push(i);
        };
        return arr
    };

    $scope.deleteProductById = function(productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                console.log(response)
                $scope.fillTable();
            });
    };



    $scope.productFindById = function(){
        console.log(document.getElementById('elem1').value)
        var id = document.getElementById('elem1').value

        $http.get(contextPath + '/products/' + id)
            .then(function (response){
               console.log(response)
                $scope.productId = response.data;
                console.log(response.data)
            });
    };

    $scope.editProduct = function (){
        $http.put(contextPath + '/products', $scope.product)
            .then(function (response){
                console.log($scope.product);
                console.log(response.data);
                $scope.product = null;
                $scope.fillTable();
            });
    };




    $scope.createNewProduct = function(){
        $http.post(contextPath +'/products', $scope.product)
            .then(function (response){
                console.log($scope.product);
                console.log(response.data);
                $scope.product = null;
                $scope.fillTable();
        });
    };

});