(function() {
    'use strict';

    angular
        .module('jhipsterApp')
        .controller('QualificationDeleteController',QualificationDeleteController);

    QualificationDeleteController.$inject = ['$uibModalInstance', 'entity', 'Qualification'];

    function QualificationDeleteController($uibModalInstance, entity, Qualification) {
        var vm = this;

        vm.qualification = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Qualification.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
