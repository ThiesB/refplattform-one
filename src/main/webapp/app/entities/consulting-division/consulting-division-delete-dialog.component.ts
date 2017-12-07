import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ConsultingDivision } from './consulting-division.model';
import { ConsultingDivisionPopupService } from './consulting-division-popup.service';
import { ConsultingDivisionService } from './consulting-division.service';

@Component({
    selector: 'jhi-consulting-division-delete-dialog',
    templateUrl: './consulting-division-delete-dialog.component.html'
})
export class ConsultingDivisionDeleteDialogComponent {

    consultingDivision: ConsultingDivision;

    constructor(
        private consultingDivisionService: ConsultingDivisionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.consultingDivisionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'consultingDivisionListModification',
                content: 'Deleted an consultingDivision'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-consulting-division-delete-popup',
    template: ''
})
export class ConsultingDivisionDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private consultingDivisionPopupService: ConsultingDivisionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.consultingDivisionPopupService
                .open(ConsultingDivisionDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
