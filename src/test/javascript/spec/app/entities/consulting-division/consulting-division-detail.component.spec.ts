/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { RefplattformOneTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ConsultingDivisionDetailComponent } from '../../../../../../main/webapp/app/entities/consulting-division/consulting-division-detail.component';
import { ConsultingDivisionService } from '../../../../../../main/webapp/app/entities/consulting-division/consulting-division.service';
import { ConsultingDivision } from '../../../../../../main/webapp/app/entities/consulting-division/consulting-division.model';

describe('Component Tests', () => {

    describe('ConsultingDivision Management Detail Component', () => {
        let comp: ConsultingDivisionDetailComponent;
        let fixture: ComponentFixture<ConsultingDivisionDetailComponent>;
        let service: ConsultingDivisionService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [RefplattformOneTestModule],
                declarations: [ConsultingDivisionDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ConsultingDivisionService,
                    JhiEventManager
                ]
            }).overrideTemplate(ConsultingDivisionDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConsultingDivisionDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsultingDivisionService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ConsultingDivision(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.consultingDivision).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
