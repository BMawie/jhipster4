(function() {
    'use strict';
    angular
        .module('jhipsterApp')
        .factory('Qualification', Qualification);

    Qualification.$inject = ['$resource'];

    function Qualification ($resource) {
        var resourceUrl =  'api/qualifications/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
