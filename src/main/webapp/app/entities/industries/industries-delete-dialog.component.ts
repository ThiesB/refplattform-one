import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Industries } from './industries.model';
import { IndustriesPopupService } from './industries-popup.service';
import { IndustriesService } from './industries.service';

@Component({
    selector: 'jhi-industries-delete-dialog',
    templateUrl: './industries-delete-dialog.component.html'
})
export class IndustriesDeleteDialogComponent {

    industries: Industries;

    constructor(
        private industriesService: IndustriesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.industriesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'industriesListModification',
                content: 'Deleted an industries'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-industries-delete-popup',
    template: ''
})
export class IndustriesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private industriesPopupService: IndustriesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.industriesPopupService
                .open(IndustriesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
