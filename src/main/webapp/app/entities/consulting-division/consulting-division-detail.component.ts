import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { ConsultingDivision } from './consulting-division.model';
import { ConsultingDivisionService } from './consulting-division.service';

@Component({
    selector: 'jhi-consulting-division-detail',
    templateUrl: './consulting-division-detail.component.html'
})
export class ConsultingDivisionDetailComponent implements OnInit, OnDestroy {

    consultingDivision: ConsultingDivision;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private consultingDivisionService: ConsultingDivisionService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConsultingDivisions();
    }

    load(id) {
        this.consultingDivisionService.find(id).subscribe((consultingDivision) => {
            this.consultingDivision = consultingDivision;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConsultingDivisions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'consultingDivisionListModification',
            (response) => this.load(this.consultingDivision.id)
        );
    }
}
